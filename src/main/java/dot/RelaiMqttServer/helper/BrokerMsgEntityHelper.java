package dot.RelaiMqttServer.helper;

import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrokerMsgEntityHelper {

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());
    public static JSONObject getJson (BrokerMsgEnity msg){

        JSONObject json = new JSONObject();
        json.put("topicName",msg.getTopic());
        json.put("clientID",msg.getClientID());
        json.put("username",msg.getUsername());
        json.put("msg",msg.getMsg());
        json.put("date",msg.dateTooString());
    //    log.info("JSON\n" + json);
        return json;
    };

    public static String getShellyModel(String clientId){
        return clientId.split("-")[0];
    }
}
