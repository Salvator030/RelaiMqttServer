package dot.RelaiMqttServer.shellyDevice;

import dot.RelaiMqttServer.networkProtocol.mqtt.broker.BrokerMsgPublishConfig;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import org.springframework.stereotype.Component;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class ShellyChanelCommandMsg {

    public MqttPublishMessage shellyChanelIndexCommandOnOfTogel (String shelly, int chanel, ECommand eCommand){

        String topic = "shellies/" + shelly + "/" + chanel + "/command";
        String msg = eCommand.toString();

        MqttPublishMessage message = MqttMessageBuilders.publish()
                .topicName(topic)
                .retained(BrokerMsgPublishConfig.isIsRetain())
                //        qos(MqttQoS.AT_MOST_ONCE);
                //        qQos(MqttQoS.AT_LEAST_ONCE);
                .qos(BrokerMsgPublishConfig.getQos())
                .payload(Unpooled.copiedBuffer(msg.getBytes(UTF_8)))
                .build();

        return message;
    }

    //ToDo announce
    public MqttPublishMessage commandMsgAnnounce(String shelly){
        String topic = "shellies/" + shelly + "/announce";
        String msg = "{msg:announce}";

        MqttPublishMessage message = MqttMessageBuilders.publish()
                .topicName(topic)
                .retained(BrokerMsgPublishConfig.isIsRetain())
                //        qos(MqttQoS.AT_MOST_ONCE);
                //        qQos(MqttQoS.AT_LEAST_ONCE);
                .qos(BrokerMsgPublishConfig.getQos())
                .payload(Unpooled.copiedBuffer(msg.getBytes(UTF_8)))
                .build();

        return message;
    }

    public MqttPublishMessage commandMsgSetChanelOff(String shelly, int index){
        String topic = "shellies/"  + shelly + "/relay/" + index ;
        String msg = "{msg:off}";

        MqttPublishMessage message = MqttMessageBuilders.publish()
                .topicName(topic)
                .retained(BrokerMsgPublishConfig.isIsRetain())
                //        qos(MqttQoS.AT_MOST_ONCE);
                //        qQos(MqttQoS.AT_LEAST_ONCE);
                .qos(BrokerMsgPublishConfig.getQos())
                .payload(Unpooled.copiedBuffer(msg.getBytes(UTF_8)))
                .build();

        return message;
    }

public MqttPublishMessage commandMsgSetChanelOn( String shelly, int index){
    String topic = "shellies/" + shelly + "/relay/" + index ;
    String msg = "{msg:on}";

    MqttPublishMessage message = MqttMessageBuilders.publish()
            .topicName(topic)
            .retained(BrokerMsgPublishConfig.isIsRetain())
            //        qos(MqttQoS.AT_MOST_ONCE);
            //        qQos(MqttQoS.AT_LEAST_ONCE);
            .qos(BrokerMsgPublishConfig.getQos())
            .payload(Unpooled.copiedBuffer(msg.getBytes(UTF_8)))
            .build();

    return message;
}

}
