package dot.RelaiMqttServer.shellyDevice;

import java.util.ArrayList;

public class ShellyEM3Entity extends ShellyEntity{

    private ArrayList<EmeterEntity> emeterList;
    private ArrayList<EM3RelayEntity> relayList;


    public ShellyEM3Entity(String clientID) {
        relayList = new ArrayList<EM3RelayEntity>();

        initEmeter(clientID);
        initRelay();
        setModel("shellyem3");

    }

    private void initEmeter(String clientID){
        this.emeterList = new ArrayList<>(3);
        for ( int i = 0; i<4; i++){
            emeterList.add(new EmeterEntity(clientID, i));
        }
    }

    private void initRelay(){
        this.relayList.add(new EM3RelayEntity());
    }

    public ArrayList<EmeterEntity> getEmeterList() {
        return emeterList;
    }

    public ArrayList<EM3RelayEntity> getRelayList() {
        return relayList;
    }
}
