package dot.RelaiMqttServer.handler;

import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import dot.RelaiMqttServer.shellyDevice.EmeterEntity;
import dot.RelaiMqttServer.shellyDevice.ShellyEM3Entity;
import dot.RelaiMqttServer.shellyDevice.ShellyEntity;
import dot.RelaiMqttServer.shellyDevice.WifiEntity;
import netscape.javascript.JSObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellyEM3MsgHandler implements MsgHandler {

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());
   
    @Override
    public void handelMsg(BrokerMsgEnity brokerMsgEnity) {

        if (brokerMsgEnity.getTopic().contains("emeter")) {
            setEmeterValue(brokerMsgEnity);

        } else if (brokerMsgEnity.getTopic().contains("relay")) {
            setRelayValue(brokerMsgEnity);
            ((ShellyEM3Entity) this.SHELLYS_AND_CHANELS.getDevice(brokerMsgEnity.getClientID())).getRelayList()
                    .get(Integer.parseInt(brokerMsgEnity.getTopic().split("/")[3])).setStatus(brokerMsgEnity.getMsg());

        } else if (brokerMsgEnity.getTopic().contains("announce")) {
            // setShellyAnnounceValue(brokerMsgEnity,brokerMsgEnity.getMsg());
        }
        else if (brokerMsgEnity.getTopic().contains("info")) {
            setInfo(brokerMsgEnity,brokerMsgEnity.getMsg());
        }


    }

    private void setEmeterValue(BrokerMsgEnity brokerMsgEnity) {
        int emeterIndex = Integer.parseInt(brokerMsgEnity.getTopic().split("/")[3]);
        EmeterEntity emeter = ((ShellyEM3Entity) this.SHELLYS_AND_CHANELS.getDevice(brokerMsgEnity.getClientID()))
                .getEmeterList().get(emeterIndex);

        if (brokerMsgEnity.getTopic().contains("total")) {
            JSONObject container = new JSONObject(brokerMsgEnity.getMsg());
            emeter.setTotal(Float.parseFloat(container.getString("msg")));
        } else if (brokerMsgEnity.getTopic().contains("total_returned")) {
            emeter.setTotal_returned(Float.parseFloat(brokerMsgEnity.getMsg()));
        } else if (brokerMsgEnity.getTopic().contains("power")) {
            emeter.setPower(Float.parseFloat(brokerMsgEnity.getMsg()));
        } else if (brokerMsgEnity.getTopic().contains("current")) {
            emeter.setCurrent(Float.parseFloat(brokerMsgEnity.getMsg()));
        } else if (brokerMsgEnity.getTopic().contains("pf")) {
            emeter.setPf(Float.parseFloat(brokerMsgEnity.getMsg()));
        } else if (brokerMsgEnity.getTopic().contains("voltage")) {
            emeter.setVoltage(Float.parseFloat(brokerMsgEnity.getMsg()));
        }
    }

    private void setEmeter(EmeterEntity emeter, JSONObject emeterJsonObject){
        emeter.setTotal(emeterJsonObject.getFloat("total"));
        emeter.setTotal_returned(emeterJsonObject.getFloat("total_returned"));
        emeter.setPower(emeterJsonObject.getFloat("power"));
        emeter.setCurrent(emeterJsonObject.getFloat("current"));
        emeter.setPf(emeterJsonObject.getFloat("pf"));
        emeter.setVoltage(emeterJsonObject.getFloat("voltage"));
      
    }

    private void setRelayValue(BrokerMsgEnity brokerMsgEnity) {
        ((ShellyEM3Entity) this.SHELLYS_AND_CHANELS.getDevice(brokerMsgEnity.getClientID())).getRelayList()
                .get(Integer.parseInt(brokerMsgEnity.getTopic().split("/")[3])).setStatus(brokerMsgEnity.getMsg());

    }

    private void setAnnaunce(BrokerMsgEnity brokerMsgEnity, String msgString) {
       
        JSONObject msg = new JSONObject(msgString);
        ShellyEM3Entity shelly = (ShellyEM3Entity) this.SHELLYS_AND_CHANELS.getDevice(brokerMsgEnity.getClientID());
        shelly.setIp(msg.getString("ip"));
        shelly.setMac(msg.getString("mac"));
        shelly.setFw_ver(msg.getString("fw_ver"));
        shelly.setNew_fw(msg.getBoolean("new_fw"));
    }

    private void setInfo(BrokerMsgEnity brokerMsgEnity, String msgString){
        try{
            JSONObject msg = new JSONObject(msgString);
            ShellyEM3Entity shelly = (ShellyEM3Entity) this.SHELLYS_AND_CHANELS.getDevice(brokerMsgEnity.getClientID());
            log.info(msg.toString());
            JSONObject wifi = msg.getJSONObject("wifi_sta");     
            log.info(wifi.toString());
            shelly.getWifi_sta().setConnected(wifi.getBoolean("connected"));
            shelly.getWifi_sta().setSsid(wifi.getString("ssid"));
            shelly.getWifi_sta().setIp(wifi.getString("ip"));
            shelly.getWifi_sta().setRssi(wifi.getInt("rssi"));

            JSONObject cloud = msg.getJSONObject("cloud");     
            shelly.getCloud().setConnected(cloud.getBoolean("connected"));
            shelly.getCloud().setEnabled(cloud.getBoolean("enabled"));

            JSONArray emeters = msg.getJSONArray("emeters");
            int count =emeters.length();
            for(int i=0;i<count;i++){
                EmeterEntity emeter = ((ShellyEM3Entity) this.SHELLYS_AND_CHANELS.getDevice(brokerMsgEnity.getClientID()))
                .getEmeterList().get(i);

                setEmeter(emeter, emeters.getJSONObject(i));
            }

            shelly.setMac(msg.getString("mac"));
            shelly.setTotal_power(msg.getFloat("total_power"));

        }
        catch(Exception exception){    
            log.error(msgString, exception);
        }
    }

    private void setShellyInfoValue(BrokerMsgEnity brokerMsgEnity, String msgString) {
        // {"msg":"{\"wifi_sta\":{\"connected\":true,\"ssid\":\"mischwerk.garden\",\"ip\":\"192.168.101.187\",\"rssi\":-53},\"cloud\":{\"enabled\":false,\"connected\":false},\"mqtt\":{\"connected\":true},\"time\":\"12:28\",\"unixtime\":1658831321,\"serial\":8,\"has_update\":false,\"mac\":\"3494546ED09F\",\"cfg_changed_cnt\":0,\"actions_stats\":{\"skipped\":0},\"relays\":[{\"ison\":false,\"has_timer\":false,\"timer_started\":0,\"timer_duration\":0,\"timer_remaining\":0,\"overpower\":false,\"is_valid\":true,\"source\":\"http\"}],\"emeters\":[{\"power\":119.22,\"pf\":0.74,\"current\":0.69,\"voltage\":233.58,\"is_valid\":true,\"total\":867.9,\"total_returned\":3.0},{\"power\":0.00,\"pf\":0.00,\"current\":0.01,\"voltage\":0.11,\"is_valid\":true,\"total\":0.0,\"total_returned\":0.0},{\"power\":0.00,\"pf\":0.00,\"current\":0.01,\"voltage\":0.09,\"is_valid\":true,\"total\":0.0,\"total_returned\":0.0}],\"total_power\":119.22,\"fs_mounted\":true,\"update\":{\"status\":\"idle\",\"has_update\":false,\"new_version\":\"20220324-123835/v1.11.8-3EM-fix-g0014dcb\",\"old_version\":\"20220324-123835/v1.11.8-3EM-fix-g0014dcb\"},\"ram_total\":49440,\"ram_free\":30132,\"fs_size\":233681,\"fs_free\":156875,\"uptime\":555}","date":"26-07-2022
        // -
        // 11:28:41","clientID":"shellyem3-3494546ED09F","topicName":"shellies/shellyem3-3494546ED09F/info"}
    }
}
