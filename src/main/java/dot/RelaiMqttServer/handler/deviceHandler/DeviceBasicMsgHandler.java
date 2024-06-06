package dot.RelaiMqttServer.handler.deviceHandler;

import org.json.JSONObject;

import dot.RelaiMqttServer.helper.MyLogger;
import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import dot.RelaiMqttServer.shellyDevice.ShellyEM3Entity;

public abstract class DeviceBasicMsgHandler {

        private MyLogger log =  new MyLogger();
        private static  ShellysAndChanels SHELLYS_AND_CHANELS = new ShellysAndChanels();
      
        public abstract void handelMsg(BrokerMsgEnity brokerMsgEnity);
        protected abstract void setInfo(BrokerMsgEnity brokerMsgEnity);
    

      
        protected ShellysAndChanels getSHELLYS_AND_CHANELS() {
            return SHELLYS_AND_CHANELS;
        }


        protected void setAnnaunce(BrokerMsgEnity brokerMsgEnity) {
        try{
        JSONObject msg = new JSONObject(brokerMsgEnity.getMsg());
        ShellyEM3Entity shelly = (ShellyEM3Entity) this.SHELLYS_AND_CHANELS.getDevice(brokerMsgEnity.getClientID());
        shelly.setIp(msg.getString("ip"));
        shelly.setMac(msg.getString("mac"));
        shelly.setFw_ver(msg.getString("fw_ver"));
        shelly.setNew_fw(msg.getBoolean("new_fw"));
    }catch(Exception exception){
         log.logException(this.getClass().getName() ,"setAnaounce():" ,exception, brokerMsgEnity );
    }
    

}
}