package dot.RelaiMqttServer.networkProtocol.mqtt.broker;

import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import org.json.JSONObject;

public class BrokerMsgPublishConfig {
    private static MqttMessageType messageType;
    private static boolean isDup = false;
    private static MqttQoS qos = MqttQoS.EXACTLY_ONCE;
    private static String clientId = "Broker";

   /*
   When set, the Retain flag indicates that the broker holds the message, and sends it as an initial message to new subscribers to this topic.
    This means that a new client connecting to the broker can quickly establish the current number of topics. This is useful where publishers send messages on a "report by exception" basis,
    and it might be some time before a new subscriber receives data on a particular topic. The data has a value of retained or Last Known Good (LKG).
    http://stanford-clark.com/MQTT/
    */
   private static boolean isRetain = false;
    private static int remainingLength;



    public static void setMessageType(MqttMessageType messageType) {
        BrokerMsgPublishConfig.messageType = messageType;
    }

    public static void setIsDup(boolean isDup) {
        BrokerMsgPublishConfig.isDup = isDup;
    }

    public static void setQos(int qos) {
        BrokerMsgPublishConfig.qos = MqttQoS.valueOf(qos);
    }

    public static void setIsRetain(boolean isRetain) {
        BrokerMsgPublishConfig.isRetain = isRetain;
    }

    public static void setRemainingLength(int remainingLength) {
        BrokerMsgPublishConfig.remainingLength = remainingLength;
    }

    public static MqttMessageType getMessageType() {
        return messageType;
    }

    public static boolean isDup() {
        return isDup;
    }

    public static MqttQoS getQos() {
        return qos;
    }

    public static boolean isIsRetain() {
        return isRetain;
    }

    public static int getRemainingLength() {
        return remainingLength;
    }

    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String clientId) {
        BrokerMsgPublishConfig.clientId = clientId;
    }

    public static JSONObject brokerMsgPublishConfigJSON(){
        JSONObject jo = new JSONObject()
                .put("messageType",messageType)
                .put("isDup", isDup);
                if (qos == MqttQoS.AT_MOST_ONCE){
                    jo.put("qos", 0);
                }
                else if (qos == MqttQoS.AT_LEAST_ONCE){
                    jo.put("qos",1);
                }
                else if (qos == MqttQoS.EXACTLY_ONCE){
                    jo.put("qos",2);
                }

               jo.put("isRetain", isRetain)
                .put("remainingLength", remainingLength);
        return  jo;

    }
}
