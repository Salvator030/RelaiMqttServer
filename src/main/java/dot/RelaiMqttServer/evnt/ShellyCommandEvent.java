package dot.RelaiMqttServer.evnt;

import org.springframework.context.ApplicationEvent;

public class ShellyCommandEvent extends ApplicationEvent {

    // ToDo
    private int type;
    private String client;

    private int index;

    public ShellyCommandEvent(Object source, int type, String client) {
        super(source);
        this.type = type;

        this.client = client;
        this.index = -1;
    }

    public ShellyCommandEvent(Object source, int type, String client, int index) {
        super(source);
        this.type = type;

        this.client = client;
        this.index = index;
    }

    public int getType() {
        return type;
    }

    public String getClient() {
        return client;
    }

    public int getIndex() {
        return index;
    }
}
