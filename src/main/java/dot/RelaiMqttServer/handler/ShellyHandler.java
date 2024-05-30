package dot.RelaiMqttServer.handler;

import dot.RelaiMqttServer.evnt.ShellyCommandEventPublisher;
import dot.RelaiMqttServer.helper.BrokerMsgEntityHelper;
import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import dot.RelaiMqttServer.shellyDevice.*;
import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShellyHandler {

    private static Logger log = LoggerFactory
            .getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    @Autowired
    ShellysAndChanels shellysAndChanels;

    @Autowired
    ShellyAndChanelCommander shellyAndChanelCommander;

    @Autowired
    ShellyCommandEventPublisher commandEventPublisher;
    public ShellyEntity initShelly(BrokerMsgEnity brokerMsgEnity) {

        String model = BrokerMsgEntityHelper.getShellyModel(brokerMsgEnity.getClientID().split("-")[0]);
        switch (model) {
            case "shellypro4pm": {
                return new Shelly4ProPmEntity(brokerMsgEnity.getClientID());
             
            }
            case "shellyem3": {
                return new ShellyEM3Entity(brokerMsgEnity.getClientID());
            }
        }
        // shellyAndChanelCommander.commandGetAnnounce(model,
        // brokerMsgEnity.getClientID());
        return null;
    }

    private void checkIfKnownShelly(BrokerMsgEnity brokerMsgEnity) {
     //   log.info("device key,value: " + shellysAndChanels.getConectetDevices().entrySet());
        ShellyEntity shelly = shellysAndChanels.getConectetDevices().putIfAbsent(brokerMsgEnity.getClientID(), initShelly(brokerMsgEnity));
        if (shelly == null) {
         // anpassen an unterschitlichen fwV
            commandEventPublisher.publishCustomEvent(0,  brokerMsgEnity.getClientID());
        }
    }

    public void handelNewShelly(BrokerMsgEnity brokerMsgEnity) {
        checkIfKnownShelly(brokerMsgEnity);
    }

    public JSONObject sendShellys() {
        JSONObject jsonObject = new JSONObject();
        for (String string : shellysAndChanels.getConectetDevices().keySet()) {
            ShellyEntity device = shellysAndChanels.getDevice(string);
        //    log.info("device " + device.toString());
            jsonObject.put(string,new JSONObject(device));
        }
        // for (int i = 0; i < shellysAndChanels.getConectetDevices().keySet().size(); i++) {
        //     ShellyEntity device = shellysAndChanels.getDevice(a[i]);
        //     log.info("device " + device);
        //     jsonObject.put(device.getClientID(),new JSONObject(device.getClientID()));
        // }
        return jsonObject;

        /*
         * JSONArray deviceJsonArray = new JSONArray();
         * 
         * for (String shelly : this.shellysAndChanels.getConectetDevices().keySet()){
         * try {
         * JSONObject jsonObject = new JSONObject(shellysAndChanels.getDevice(shelly));
         * JSONArray jsonArray = new
         * JSONArray(shellysAndChanels.getDevice(shelly).ge());
         * jsonObject.put("chanels", jsonArray);
         * deviceJsonArray.put(jsonObject);
         * log.info("sendShellys()\n" + shellysAndChanels.getDevice(shelly).toString());
         * }catch (Exception e ){
         * log.error(e.getLocalizedMessage());
         * }
         * }
         * 
         * return deviceJsonArray;
         * 
         */

    }

}
