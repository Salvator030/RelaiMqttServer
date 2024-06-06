package dot.RelaiMqttServer.handler.deviceHandler;

import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import dot.RelaiMqttServer.shellyDevice.EmeterEntity;
import dot.RelaiMqttServer.shellyDevice.ShellyEM3Entity;
import dot.RelaiMqttServer.shellyDevice.ShellyEntity;
import dot.RelaiMqttServer.shellyDevice.WifiEntity;
import netscape.javascript.JSObject;
import dot.RelaiMqttServer.helper.MyLogger;
import org.json.JSONArray;
import org.json.JSONObject;


public class ShellyEM3MsgHandler extends DeviceBasicMsgHandler {

    private static MyLogger  log = new MyLogger();

    @Override
    public void handelMsg(BrokerMsgEnity brokerMsgEnity) {

        if (brokerMsgEnity.getTopic().contains("emeter")) {
            setEmeterValue(brokerMsgEnity);

        } else if (brokerMsgEnity.getTopic().contains("relay")) {
            setRelayValue(brokerMsgEnity);
            ((ShellyEM3Entity) getSHELLYS_AND_CHANELS().getDevice(brokerMsgEnity.getClientID())).getRelayList()
                    .get(Integer.parseInt(brokerMsgEnity.getTopic().split("/")[3])).setStatus(brokerMsgEnity.getMsg());

        } else if (brokerMsgEnity.getTopic().contains("announce")) {
            setAnnaunce(brokerMsgEnity);
        } else if (brokerMsgEnity.getTopic().contains("info")) {
            setInfo(brokerMsgEnity);
        }

    }

    private void setEmeterValue(BrokerMsgEnity brokerMsgEnity) {
        int emeterIndex = Integer.parseInt(brokerMsgEnity.getTopic().split("/")[3]);
        EmeterEntity emeter = ((ShellyEM3Entity) getSHELLYS_AND_CHANELS().getDevice(brokerMsgEnity.getClientID()))
                .getEmeterList().get(emeterIndex);
        JSONObject msg = new JSONObject(brokerMsgEnity.getMsg());
        if (brokerMsgEnity.getTopic().contains("total_returned")) {
            emeter.setTotal_returned(Float.parseFloat(msg.getString("msg")));
        } else if (brokerMsgEnity.getTopic().contains("total")) {
            emeter.setTotal(Float.parseFloat(msg.getString("msg")));
        } else if (brokerMsgEnity.getTopic().contains("power")) {
            emeter.setPower(Float.parseFloat(msg.getString("msg")));
        } else if (brokerMsgEnity.getTopic().contains("current")) {
            emeter.setCurrent(Float.parseFloat(msg.getString("msg")));
        } else if (brokerMsgEnity.getTopic().contains("pf")) {
            emeter.setPf(Float.parseFloat(msg.getString("msg")));
        } else if (brokerMsgEnity.getTopic().contains("voltage")) {
            emeter.setVoltage(Float.parseFloat(msg.getString("msg")));
        } else if (brokerMsgEnity.getTopic().contains("energy")) {
            emeter.setEnergy(Float.parseFloat(msg.getString("msg")));
        } else {
            log.toppicError(this.getClass().getName() ,"setEmeterValue", brokerMsgEnity);}
    }

    private void setEmeter(EmeterEntity emeter, JSONObject emeterJsonObject) {
        emeter.setTotal(emeterJsonObject.getFloat("total"));
        emeter.setTotal_returned(emeterJsonObject.getFloat("total_returned"));
        emeter.setPower(emeterJsonObject.getFloat("power"));
        emeter.setCurrent(emeterJsonObject.getFloat("current"));
        emeter.setPf(emeterJsonObject.getFloat("pf"));
        emeter.setVoltage(emeterJsonObject.getFloat("voltage"));

    }

    private void setRelayValue(BrokerMsgEnity brokerMsgEnity) {
        ((ShellyEM3Entity) getSHELLYS_AND_CHANELS().getDevice(brokerMsgEnity.getClientID())).getRelayList()
                .get(Integer.parseInt(brokerMsgEnity.getTopic().split("/")[3])).setStatus(brokerMsgEnity.getMsg());

    }


     
@Override
    protected void setInfo(BrokerMsgEnity brokerMsgEnity) {
        try {
            JSONObject msg = new JSONObject(brokerMsgEnity.getMsg());
            ShellyEM3Entity shelly = (ShellyEM3Entity) getSHELLYS_AND_CHANELS().getDevice(brokerMsgEnity.getClientID());
            JSONObject wifi = msg.getJSONObject("wifi_sta");
            shelly.getWifi_sta().setConnected(wifi.getBoolean("connected"));
            shelly.getWifi_sta().setSsid(wifi.getString("ssid"));
            shelly.getWifi_sta().setIp(wifi.getString("ip"));
            shelly.getWifi_sta().setRssi(wifi.getInt("rssi"));

            JSONObject cloud = msg.getJSONObject("cloud");
            shelly.getCloud().setConnected(cloud.getBoolean("connected"));
            shelly.getCloud().setEnabled(cloud.getBoolean("enabled"));

            JSONArray emeters = msg.getJSONArray("emeters");
            int count = emeters.length();
            for (int i = 0; i < count; i++) {
                EmeterEntity emeter = ((ShellyEM3Entity) getSHELLYS_AND_CHANELS()
                        .getDevice(brokerMsgEnity.getClientID()))
                        .getEmeterList().get(i);

                setEmeter(emeter, emeters.getJSONObject(i));
            }

            shelly.setMac(msg.getString("mac"));
            shelly.setTotal_power(msg.getFloat("total_power"));

        } catch (Exception exception) {
            log.logException(this.getClass().getName() ,"setInfo", exception, brokerMsgEnity);
        }
    }

    
}
