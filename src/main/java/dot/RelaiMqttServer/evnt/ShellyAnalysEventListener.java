package dot.RelaiMqttServer.evnt;

import dot.RelaiMqttServer.shellyDevice.Analyser;
import dot.RelaiMqttServer.shellyDevice.ShellyAndChanelCommander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class ShellyAnalysEventListener implements ApplicationListener<ShellyAnalysEvent> {

    private  Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    @Autowired
    private Analyser analyser;
    @Autowired
    ShellyAndChanelCommander shellyAndChanelCommander;


    @Autowired
    private ShellyCommandEventPublisher shellyCommandEventPublisher;
    @Override
    public void onApplicationEvent(ShellyAnalysEvent event) {
        switch (event.getAnalys()){
            case power: {
                int anaylyse = analyser.analysPower();
                System.out.println("analyser.analysPower() " +anaylyse);
               if (anaylyse == -1){
                   shellyAndChanelCommander.commandSetAllChannelsWithOutOutputOff();
               }
               else if (anaylyse == 1){
                   shellyAndChanelCommander.commandSetAllChannelOn();
               }
               break;
            }
        }

    }
}
