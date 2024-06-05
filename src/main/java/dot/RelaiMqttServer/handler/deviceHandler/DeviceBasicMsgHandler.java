package dot.RelaiMqttServer.handler.deviceHandler;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import dot.RelaiMqttServer.shellyDevice.ShellyEM3Entity;

public abstract class DeviceBasicMsgHandler {

        private Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());
        private static  ShellysAndChanels SHELLYS_AND_CHANELS = new ShellysAndChanels();
      
        public abstract void handelMsg(BrokerMsgEnity brokerMsgEnity);
        protected abstract void setInfo(BrokerMsgEnity brokerMsgEnity);
    

      
        protected ShellysAndChanels getSHELLYS_AND_CHANELS() {
            return SHELLYS_AND_CHANELS;
        }


        protected void setAnnaunce(BrokerMsgEnity brokerMsgEnity) {
        try{
        log.info("an: " + brokerMsgEnity.getMsg());
        JSONObject msg = new JSONObject(brokerMsgEnity.getMsg());
        ShellyEM3Entity shelly = (ShellyEM3Entity) this.SHELLYS_AND_CHANELS.getDevice(brokerMsgEnity.getClientID());
        shelly.setIp(msg.getString("ip"));
        shelly.setMac(msg.getString("mac"));
        shelly.setFw_ver(msg.getString("fw_ver"));
        shelly.setNew_fw(msg.getBoolean("new_fw"));
    }catch(Exception exception){
        log.error("setAnaounce(): " + exception + "\n" + brokerMsgEnity.getMsg());
    }}
    

}
