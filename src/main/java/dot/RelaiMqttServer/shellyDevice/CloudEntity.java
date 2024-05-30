package dot.RelaiMqttServer.shellyDevice;

public class CloudEntity {
    private boolean enabled;
    private boolean connected;

    public CloudEntity(){
        enabled = false;
        connected = false;
    }

    public boolean getEnabled(){
        return enabled;
    }

    public void setEnabled(boolean e){
        enabled = e;
    }

    public boolean getConnected(){
        return connected;
    }

    public void setConnected(boolean c){
        connected = c;
    }
}




