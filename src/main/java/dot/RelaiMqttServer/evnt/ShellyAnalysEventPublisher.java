package dot.RelaiMqttServer.evnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ShellyAnalysEventPublisher {
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final EAnalys analys) {
        ShellyAnalysEvent shellyAnalysEvent = new ShellyAnalysEvent(this, analys);
        applicationEventPublisher.publishEvent(shellyAnalysEvent);
    }
}