package dot.RelaiMqttServer.shellyDevice;

import dot.RelaiMqttServer.evnt.ShellyCommandEventPublisher;
import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShellyAndChanelCommander {

    @Autowired
    private ShellyCommandEventPublisher shellyCommandEventPublisher;
    @Autowired
    ShellysAndChanels shellysAndChanels;

    public void commandGetAnnounce(String client) {
        shellyCommandEventPublisher.publishCustomEvent(0, client);
    }

    public void commandSetAllChannelsWithOutOutputOff() {
        for (Map.Entry<String, ChanelEntity> entry : shellysAndChanels.getOutputOnChanels().entrySet()) {
            if (entry.getValue().getPower() == 0 /* && entry.getValue().getPower() == 0 */) {
                shellyCommandEventPublisher.publishCustomEvent(1,
                        entry.getValue().getShelly4Pro(),
                        entry.getValue().getRelayIndex());
            }
            ;
        }
    }

    public void commandSetAllChannelOn() {
        for (Map.Entry<String, ChanelEntity> entry : shellysAndChanels.getOutputOffchanels().entrySet()) {
            shellyCommandEventPublisher.publishCustomEvent(2,
                    entry.getValue().getShelly4Pro(),
                    entry.getValue().getRelayIndex());
        }
    }
}
