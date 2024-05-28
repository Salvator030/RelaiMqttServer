package dot.RelaiMqttServer.handler;

import dot.RelaiMqttServer.evnt.ShellyAnalysEventPublisher;
import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MsgHandler {
    

    ShellysAndChanels SHELLYS_AND_CHANELS = new ShellysAndChanels();
   
    Logger LOGGER = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    public void handelMsg(BrokerMsgEnity brokerMsgEnity);
}
