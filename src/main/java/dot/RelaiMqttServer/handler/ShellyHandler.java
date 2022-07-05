package dot.RelaiMqttServer.handler;

import dot.RelaiMqttServer.entitys.BrokerMsgEnity;
import dot.RelaiMqttServer.entitys.Shelly4ProEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShellyHandler {

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());
   private static HashMap<String, Shelly4ProEntity> conectetDevices = new HashMap<String,Shelly4ProEntity>();

    public ShellyHandler() {

    }

    public Shelly4ProEntity getDevice(String device){
        return this.conectetDevices.get(device);
    }

    public void newDevice(String device){
        this.conectetDevices.put(device, new Shelly4ProEntity(device));
    }

    public void deleteDevice(String device){
        this.conectetDevices.remove(device);
    }

    public void checkIfConectClient (BrokerMsgEnity brokerMsgEnity){
        this.conectetDevices.putIfAbsent(brokerMsgEnity.getClientID(), new Shelly4ProEntity(brokerMsgEnity.getClientID()));
        log.info("CLIENT: " + brokerMsgEnity.getClientID() );
        //TOdo Abfrage von announce mittels Mqtt
    }

    public void sortMsgToShelly(BrokerMsgEnity brokerMsgEnity) {
        String[] sArray = brokerMsgEnity.getTopic().split("/");
        log.info("TOPIC AS ARRAY\n" + sArray);
        if (sArray.length == 3){
            //ToDo auswerten von .getMsg() als Json , werte in jeweiliges ShellyObj schreiben
        }

        else if (sArray.length == 4) {
            switch (sArray[2]) {
                case "input": {
                    this.conectetDevices.get(brokerMsgEnity.getClientID()).getChanels().set(Integer.parseInt(sArray[3]), sArray[2], brokerMsgEnity.getMsg());
                    break;
                }

                case "relay": {
                    this.conectetDevices.get(brokerMsgEnity.getClientID()).getChanels().set(Integer.parseInt(sArray[3]), "status", brokerMsgEnity.getMsg());
                    break;
                }
            }
        } else if (sArray.length == 5) {
            switch (sArray[5]) {
                case "power": {
                    this.conectetDevices.get(brokerMsgEnity.getClientID()).getChanels().set(Integer.parseInt(sArray[3]), "power", brokerMsgEnity.getMsg());
                    break;
                }

                case "energy" : {
                    this.conectetDevices.get(brokerMsgEnity.getClientID()).getChanels().set(Integer.parseInt(sArray[3]),"energy",brokerMsgEnity.getMsg());
                }
            }

        }
    }



    public void handelShelly(BrokerMsgEnity brokerMsgEnity){
        checkIfConectClient(brokerMsgEnity);
        sortMsgToShelly(brokerMsgEnity);
    }

    public Map<String, Shelly4ProEntity> getAllDevices(){
       return this.conectetDevices;
    }

    public JSONArray sendShellys(){
       String[] a = getAllDevices().keySet().toArray(new String[0]);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i <a.length; i++) {
            jsonArray.put(new JSONObject(getAllDevices().get(a[i])));
        }
        log.info("SHELLY JSON\n" + jsonArray);
        return jsonArray;
    }


}
