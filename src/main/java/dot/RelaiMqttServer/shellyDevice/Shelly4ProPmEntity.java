package dot.RelaiMqttServer.shellyDevice;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;


public class Shelly4ProPmEntity extends ShellyEntity{

  @OneToMany
  private WifiEntity wifi_sta;
  private CloudEntity cloud;
   private  ArrayList<ChanelEntity> channels;
   private float total_power;


    public Shelly4ProPmEntity(String clientID) {
        setModel(clientID);
        initChannels(clientID);
        wifi_sta = new WifiEntity();
        cloud = new CloudEntity();
   }
    
    public WifiEntity getWifi_sta() {
        return wifi_sta;
    }

     public CloudEntity getCloud() {
        return cloud;
    }

    public float getTotal_power() {
        return total_power;
    }

    public void setTotal_power(float total_power) {
        this.total_power = total_power;
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
