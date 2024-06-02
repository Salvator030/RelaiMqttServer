package dot.RelaiMqttServer.handler;

import dot.RelaiMqttServer.evnt.EAnalys;
import dot.RelaiMqttServer.evnt.ShellyAnalysEventPublisher;
import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import dot.RelaiMqttServer.shellyDevice.ChanelEntity;
import dot.RelaiMqttServer.shellyDevice.Shelly4ProPmEntity;
import dot.RelaiMqttServer.shellyDevice.ShellyEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Shelly4ProMsgHandler implements MsgHandler {

  @Autowired
  private  ShellyAnalysEventPublisher shellyAnalysEventPublisher;

    private static Logger log = LoggerFactory
            .getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    @Override
    public void handelMsg(BrokerMsgEnity brokerMsgEnity) {

        JSONObject msg = new JSONObject(brokerMsgEnity.getMsg());

        if (brokerMsgEnity.getTopic().contains("status")) {
            setShellyStatusValues(brokerMsgEnity, msg);

        } else if (brokerMsgEnity.getTopic().contains("announce")) {
            setShellyAnnounceValue(brokerMsgEnity, msg);
        }

        else if (brokerMsgEnity.getTopic().contains("events")) {
            setEvent(brokerMsgEnity, msg);
        }

    }

    

    private void setShellyStatusValues(BrokerMsgEnity brokerMsgEnity, JSONObject jObject) {
        // zuordnen der werte
        JSONObject msg = jObject.getJSONObject("msg");
        int index = msg.getInt("id");
        Shelly4ProPmEntity shelly = (Shelly4ProPmEntity) this.SHELLYS_AND_CHANELS
                .getDevice(brokerMsgEnity.getClientID());
        ChanelEntity chanel = shelly.getChannels().get(index);
        chanel.setSource(msg.getString("source"));
        chanel.setOutput(msg.getBoolean("output"));
        chanel.setPower(msg.getDouble("apower"));
        chanel.setVoltage(msg.getDouble("voltage"));
        chanel.setCurrent(msg.getDouble("current"));
        chanel.setPf(msg.getDouble("pf"));
        JSONObject enegry = msg.getJSONObject("aenergy");
        chanel.setEnergy_total(enegry.getFloat("total"));

        JSONArray jA = enegry.getJSONArray("by_minute");
        float[] array = new float[3];
        for (int i = 0; i < 3; i++) {
            array[i] = jA.optFloat(i);
        }
        chanel.setEnergy_byMinute(array);
        chanel.setEnergy_minuteTs(enegry.getFloat("get"));

        JSONObject temp = msg.getJSONObject("temperature");
        chanel.setTemp_c(temp.getFloat("tC"));
        chanel.setTemp_f(temp.getFloat("tF"));
        chanel.setDateOfChange((brokerMsgEnity.getDate()));
        // setzen des chanels in die jeweilige map
        log.info("isOutput? " + msg.getBoolean("output"));
        SHELLYS_AND_CHANELS.setChanelOutputMap(msg.getBoolean("output"), chanel);

    }

    private void setShellyAnnounceValue(BrokerMsgEnity brokerMsgEnity, JSONObject msg) {
        try{
        ShellyEntity shelly = SHELLYS_AND_CHANELS.getDevice(brokerMsgEnity.getClientID());
        shelly.setIp(msg.getString("ip"));
        shelly.setMac(msg.getString("mac"));
        shelly.setFw_ver(msg.getString("fw_ver"));
        shelly.setNew_fw(msg.getBoolean("new_fw"));
    }catch(Exception exception){
        log.error("setAnaounce(): " + exception + "\n" + brokerMsgEnity.getMsg());
    }
    }

    private void setEvent(BrokerMsgEnity brokerMsgEnity, JSONObject msg) {
        LOGGER.info("setEventMSG " + msg.toString());
        //get params from msg
        JSONObject params = msg.getJSONObject("params");
        LOGGER.info("params " + params.toString());
        String[] paramKeys = params.keySet().toArray(new String[0]);
        JSONObject switchI = params.getJSONObject(paramKeys[0]);
        LOGGER.info("switchI: " + switchI.getInt("id"));
        int index = switchI.getInt("id");
        Shelly4ProPmEntity shelly = (Shelly4ProPmEntity) this.SHELLYS_AND_CHANELS
                .getDevice(brokerMsgEnity.getClientID());
        ChanelEntity chanel = shelly.getChannels().get(index);
        String searchValue = switchI.keySet().toArray(new String[0])[0];

        try{
            switch (searchValue) {

            case "source": {
                LOGGER.info("source " );
                chanel.setSource(switchI.getString("source"));
                break;
            }
            case "output": {
                chanel.setOutput(switchI.getBoolean("output"));
                LOGGER.info("source " );
                SHELLYS_AND_CHANELS.setChanelOutputMap(msg.getBoolean("output"), chanel);
                break;
            }
            case "apower": {
                chanel.setPower(switchI.getDouble("apower"));
                LOGGER.info("source " );
                shellyAnalysEventPublisher.publishCustomEvent(EAnalys.power);
                break;
            }
            case "voltage": {
                LOGGER.info("voltage" );
                chanel.setVoltage(switchI.getDouble("voltage"));
                break;
            }
            case "current": {
                LOGGER.info("current " );
                chanel.setCurrent(switchI.getDouble("current"));
                break;
            }
            case "pf": {
                LOGGER.info("pf " );
                chanel.setPf(switchI.getDouble("pf"));
                break;
            }
            case "aenergy": {
                LOGGER.info("aenergy " );
                JSONObject enegry = switchI.getJSONObject("aenergy");
                chanel.setEnergy_total(enegry.getFloat("total"));

                JSONArray jA = enegry.getJSONArray("by_minute");
                float[] array = new float[3];
                for (int i = 0; i < 3; i++) {
                    array[i] = jA.optFloat(i);
                }
                chanel.setEnergy_byMinute(array);
                chanel.setEnergy_minuteTs(enegry.getFloat("minute_ts"));
                break;
            }
            case "temperature": {
                JSONObject temp = switchI.getJSONObject("temperature");
                chanel.setTemp_c(temp.getFloat("tC"));
                chanel.setTemp_f(temp.getFloat("tF"));
                break;
            }

            default : {
                LOGGER.info("setEvent(): Not Found << searchValue >>: "+ searchValue );
            }

        }
    }catch(Exception e){
        LOGGER.error(e.getMessage() +"\n"+ e.getStackTrace());
    }
        
        chanel.setDateOfChange((brokerMsgEnity.getDate()));

    }
}
