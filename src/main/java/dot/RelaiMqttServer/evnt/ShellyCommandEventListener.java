package dot.RelaiMqttServer.evnt;

import dot.RelaiMqttServer.networkProtocol.mqtt.broker.Broker;
import dot.RelaiMqttServer.shellyDevice.ShellyChanelCommandMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ShellyCommandEventListener implements ApplicationListener<ShellyCommandEvent> {

    private static Logger log = LoggerFactory
            .getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    @Autowired
    private Broker broker;
    @Autowired
    private ShellyChanelCommandMsg shellyChanelCommandMsg;

    @Override
    public void onApplicationEvent(ShellyCommandEvent event) {
        switch (event.getType()) {
            case 0: {
                broker.sendMsg(shellyChanelCommandMsg.commandMsgAnnounce(event.getClient()));
                break;
            }
            case 1: {
                broker.sendMsg((shellyChanelCommandMsg.commandMsgSetChanelOff(event.getClient(), event.getIndex())));
                break;
            }

            case 2: {
                broker.sendMsg((shellyChanelCommandMsg.commandMsgSetChanelOn(event.getClient(), event.getIndex())));
                break;
            }

        }
    }
}
