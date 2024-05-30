package dot.RelaiMqttServer.shellyDevice;

import java.util.ArrayList;


public class ShellyEM3Entity extends ShellyEntity{

    private ArrayList<EmeterEntity> emeterList;
    private ArrayList<EM3RelayEntity> relayList;
    private WifiEntity wifi_sta;
    private CloudEntity cloud;


    public ShellyEM3Entity(String clientID) {
        wifi_sta = new WifiEntity();
        cloud = new CloudEntity();
        relayList = new ArrayList<EM3RelayEntity>();
        initEmeter(clientID);
        initRelay();
        setModel("shellyem3");

    }

    private void initEmeter(String clientID){
        this.emeterList = new ArrayList<>(4);
        for ( int i = 0; i<4; i++){
            emeterList.add(new EmeterEntity(clientID, i));
        }
    }

    private void initRelay(){
        this.relayList.add(new EM3RelayEntity());
    }

    public WifiEntity getWifi_sta(){
        return wifi_sta;
    }

    public CloudEntity getCloud(){
        return cloud;
    }

    public ArrayList<EmeterEntity> getEmeterList() {
        return emeterList;
    }

    public ArrayList<EM3RelayEntity> getRelayList() {
        return relayList;
    }
}
