package dot.RelaiMqttServer.mqttBroke;

import dot.RelaiMqttServer.entitys.BrokerMsgEnity;
import dot.RelaiMqttServer.handler.MqttBrokerMsgHandler;
import dot.RelaiMqttServer.handler.ShellyHandler;
import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublisherListener extends AbstractInterceptHandler {

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    @Autowired
   private MqttBrokerMsgHandler mqttBrokerMsgHandler = new MqttBrokerMsgHandler();

    @Autowired
    private ShellyHandler shellyHandler = new ShellyHandler();

    @Override
    public String getID() {
        return "PublishListener";
    }

    @Override
    public void onPublish(InterceptPublishMessage msg) {
    BrokerMsgEnity brokerMsgEnity = mqttBrokerMsgHandler.handelMsg(msg);
    shellyHandler.handelShelly(brokerMsgEnity);

        /*
        System.out.println("getPayload() " + msg.getPayload());
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
