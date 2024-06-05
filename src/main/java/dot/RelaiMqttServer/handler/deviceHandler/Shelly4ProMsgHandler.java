package dot.RelaiMqttServer.handler.deviceHandler;

import dot.RelaiMqttServer.evnt.EAnalys;
import dot.RelaiMqttServer.evnt.ShellyAnalysEventPublisher;
import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import dot.RelaiMqttServer.shellyDevice.ChanelEntity;
import dot.RelaiMqttServer.shellyDevice.Shelly4ProPmEntity;
import dot.RelaiMqttServer.shellyDevice.ShellyEM3Entity;
import dot.RelaiMqttServer.shellyDevice.ShellyEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Shelly4ProMsgHandler extends DeviceBasicMsgHandler {

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
            setAnnaunce(brokerMsgEnity);
        }

        else if (brokerMsgEnity.getTopic().contains("events")) {
            setEvent(brokerMsgEnity, msg);
        }
        else if (brokerMsgEnity.getTopic().contains("info")) {
            setInfo(brokerMsgEnity);
        }
        else { log.error("handelMsg(): uknown topic: " + brokerMsgEnity.getTopic() + "\n" + brokerMsgEnity.getMsg());}

    }

    

    private void setShellyStatusValues(BrokerMsgEnity brokerMsgEnity, JSONObject jObject) {
     try{
        JSONObject msg = new JSONObject(brokerMsgEnity.getMsg());
        int index = msg.getInt("id");
        Shelly4ProPmEntity shelly = (Shelly4ProPmEntity) this.getSHELLYS_AND_CHANELS()
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
        getSHELLYS_AND_CHANELS().setChanelOutputMap(msg.getBoolean("output"), chanel);
    }catch(Exception exception){
        log.error("setShellyStatusValues: \n"+ exception + "\n" + exception.getStackTrace()+ "\n" + brokerMsgEnity.getMsg());
    }
    }

    @Override
        protected void setInfo(BrokerMsgEnity brokerMsgEnity) {
        try {
            JSONObject msg = new JSONObject(brokerMsgEnity.getMsg());
            Shelly4ProPmEntity shelly = (Shelly4ProPmEntity) this.getSHELLYS_AND_CHANELS().getDevice(brokerMsgEnity.getClientID());
            JSONObject wifi = msg.getJSONObject("wifi_sta");
            log.info(wifi.toString());
            shelly.getWifi_sta().setConnected(wifi.getBoolean("connected"));
            shelly.getWifi_sta().setSsid(wifi.getString("ssid"));
            shelly.getWifi_sta().setIp(wifi.getString("ip"));
            shelly.getWifi_sta().setRssi(wifi.getInt("rssi"));

            JSONObject cloud = msg.getJSONObject("cloud");
            shelly.getCloud().setConnected(cloud.getBoolean("connected"));
            shelly.getCloud().setEnabled(cloud.getBoolean("enabled"));
            

            JSONArray chanels = msg.getJSONArray("emeters");
            int count = chanels.length();
            for (int i = 0; i < count; i++) {
                ChanelEntity chanel = ((Shelly4ProPmEntity) this.getSHELLYS_AND_CHANELS()
                        .getDevice(brokerMsgEnity.getClientID()))
                        .getChannels().get(i);

                setChanel(chanel, chanels.getJSONObject(i));
            }

            shelly.setMac(msg.getString("mac"));
            shelly.setTotal_power(msg.getFloat("total_power"));

        } catch (Exception exception) {
            log.error("setInfo(): \n" + exception + "\n" + brokerMsgEnity.getMsg());
        }
    }

    private void setChanel(ChanelEntity chanel, JSONObject chanelJsonObject) {

        JSONObject enegry = chanelJsonObject.getJSONObject("aenergy");
        chanel.setEnergy_total(enegry.getFloat("total"));

        JSONArray jA = enegry.getJSONArray("by_minute");
        float[] array = new float[3];
        for (int i = 0; i < 3; i++) {
            array[i] = jA.optFloat(i);
        }
        chanel.setEnergy_total(chanelJsonObject.getFloat("energy_total"));
        chanel.setEnergy_byMinute(array);
        chanel.setPower(chanelJsonObject.getFloat("power"));
        chanel.setCurrent(chanelJsonObject.getFloat("current"));
        chanel.setPf(chanelJsonObject.getFloat("pf"));
        chanel.setVoltage(chanelJsonObject.getFloat("voltage"));

    }
    private void setEvent(BrokerMsgEnity brokerMsgEnity, JSONObject msg) {
        log.info("setEventMSG " + msg.toString());
        //get params from msg
        JSONObject params = msg.getJSONObject("params");
        log.info("params " + params.toString());
        String[] paramKeys = params.keySet().toArray(new String[0]);
        JSONObject switchI = params.getJSONObject(paramKeys[0]);
        log.info("switchI: " + switchI.getInt("id"));
        int index = switchI.getInt("id");
        Shelly4ProPmEntity shelly = (Shelly4ProPmEntity) getSHELLYS_AND_CHANELS()
                .getDevice(brokerMsgEnity.getClientID());
        ChanelEntity chanel = shelly.getChannels().get(index);
        String searchValue = switchI.keySet().toArray(new String[0])[0];

        try{
            switch (searchValue) {

            case "source": {
                log.info("source " );
                chanel.setSource(switchI.getString("source"));
                break;
            }
            case "output": {
                chanel.setOutput(switchI.getBoolean("output"));
                log.info("source " );
                getSHELLYS_AND_CHANELS().setChanelOutputMap(msg.getBoolean("output"), chanel);
                break;
            }
            case "apower": {
                chanel.setPower(switchI.getDouble("apower"));
                log.info("source " );
                shellyAnalysEventPublisher.publishCustomEvent(EAnalys.power);
                break;
            }
            case "voltage": {
                log.info("voltage" );
                chanel.setVoltage(switchI.getDouble("voltage"));
                break;
            }
            case "current": {
                log.info("current " );
                chanel.setCurrent(switchI.getDouble("current"));
                break;
            }
            case "pf": {
                log.info("pf " );
                chanel.setPf(switchI.getDouble("pf"));
                break;
            }
            case "aenergy": {
                log.info("aenergy " );
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
                log.info("setEvent(): Not Found << searchValue >>: "+ searchValue );
            }

        }
    }catch(Exception e){
        log.error(e.getMessage() +"\n"+ e.getStackTrace());
    }
        
        chanel.setDateOfChange((brokerMsgEnity.getDate()));

    }
}
