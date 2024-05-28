package dot.RelaiMqttServer.evnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
//ToDo

@Component
public class ShellyCommandEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent( int type, String client) {
        System.out.println("Publishing custom event. ");
        ShellyCommandEvent shellyCommandEvent = new ShellyCommandEvent(this, type,client);
        applicationEventPublisher.publishEvent(shellyCommandEvent);
    }

    public void publishCustomEvent( int type,  String client, int index) {
        System.out.println("Publishing custom event. ");
        ShellyCommandEvent shellyCommandEvent = new ShellyCommandEvent(this, type,client,index);
        applicationEventPublisher.publishEvent(shellyCommandEvent);
    }
}
