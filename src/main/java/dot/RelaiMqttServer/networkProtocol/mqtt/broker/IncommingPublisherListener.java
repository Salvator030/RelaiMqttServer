package dot.RelaiMqttServer.networkProtocol.mqtt.broker;

import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import dot.RelaiMqttServer.handler.MqttBrokerIncommingMsgHandler;
import dot.RelaiMqttServer.handler.ShellyHandler;
import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncommingPublisherListener extends AbstractInterceptHandler {

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());


    @Autowired
   private MqttBrokerIncommingMsgHandler mqttBrokerIncommingMsgHandler;

    @Autowired
    private ShellyHandler shellyHandler;

    public IncommingPublisherListener(MqttBrokerIncommingMsgHandler mqttBrokerIncommingMsgHandler, ShellyHandler shellyHandler) {
        this.mqttBrokerIncommingMsgHandler = mqttBrokerIncommingMsgHandler;
        this.shellyHandler = shellyHandler;
    }

    @Override
    public String getID() {
        return "PublishListener";
    }

    @Override
    public void onPublish(InterceptPublishMessage msg) {

    System.out.println("getPayload() " + msg.getPayload());
        mqttBrokerIncommingMsgHandler.handelMsg(new BrokerMsgEnity(msg.getTopicName(), msg.getClientID(), msg.getPayload(), msg.getUsername()));
        /*
        
        System.out.println("decodedPayload " + decodedPayload);
        try {
            log.info("Received on topic: " + msg.getTopicName() + " content: " + decodedPayload);
        }
        catch (Exception e){
            log.error(e.toString());
        }
         */
    }
}
