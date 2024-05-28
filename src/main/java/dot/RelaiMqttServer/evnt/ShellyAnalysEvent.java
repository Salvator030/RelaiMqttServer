package dot.RelaiMqttServer.evnt;

import org.springframework.context.ApplicationEvent;

public class ShellyAnalysEvent extends ApplicationEvent {

    private EAnalys analys;

    public ShellyAnalysEvent(Object source, EAnalys analys) {
        super(source);
        this.analys = analys;
    }
    
    public EAnalys getAnalys() {
        return analys;
    }

}
