package dot.RelaiMqttServer.shellyDevice;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;


public class Shelly4ProPmEntity extends ShellyEntity{

  @OneToMany
   private  ArrayList<ChanelEntity> channels;

    public Shelly4ProPmEntity(String clientID) {
        setModel(clientID);
        initChannels(clientID);
        

    }
    private void initChannels(String clientID){
        this.channels = new ArrayList<>(4);
        for ( int i = 0; i < 4; i++){
            ChanelEntity channel = new ChanelEntity();

            channel.setRelayIndex(i);
            channel.setChanelID(clientID+'r'+i);
            channels.add(i, channel);
        }
    };

    public ArrayList<ChanelEntity> getChannels() {
        return channels;
    }
}
