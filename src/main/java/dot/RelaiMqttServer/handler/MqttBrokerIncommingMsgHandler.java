package dot.RelaiMqttServer.handler;

import dot.RelaiMqttServer.evnt.ShellyAnalysEventPublisher;
import dot.RelaiMqttServer.handler.deviceHandler.MsgHandler;
import dot.RelaiMqttServer.handler.deviceHandler.Shelly4ProMsgHandler;
import dot.RelaiMqttServer.handler.deviceHandler.ShellyEM3MsgHandler;
import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import dot.RelaiMqttServer.helper.BrokerMsgEntityHelper;
import dot.RelaiMqttServer.shellyDevice.ShellyAndChanelCommander;

import org.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MqttBrokerIncommingMsgHandler implements MsgHandler{

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());
    private static List<BrokerMsgEnity> msgList = new ArrayList<>(100);
    
    private ShellysAndChanels shellysAndChanels = new ShellysAndChanels();

    @Autowired
    ShellyHandler shellyHandler;

    @Autowired
     ShellyAnalysEventPublisher shellyAnalysEventPublisher;
    
    @Autowired
     ShellyAndChanelCommander shellyAndChanelCommander;

    private long startTime = System.currentTimeMillis();
    private boolean not60SecAfterSart = true;


   private BrokerMsgEnity restorLast100msg(BrokerMsgEnity brokerMsgEnity){
    //   log.info("MSG\n" + brokerMsgEnity.getMsg());
       if( msgList.size() == 100){
           msgList.remove(0);
       }
       msgList.add(brokerMsgEnity);
        return brokerMsgEnity;
   }



   public JSONArray sendMsg(){
       JSONArray jsonArray = new JSONArray();
       int count = this.msgList.size();
       for ( int i = 0; i < count; i++){
           jsonArray.put(BrokerMsgEntityHelper.getJson(this.msgList.get(i)));
       }
   //    log.info ("JSONArray\n" + jsonArray);
       return jsonArray;
   }

    private void identifyClient(BrokerMsgEnity brokerMsgEnity){
        // ToDo !unschön!
        if(not60SecAfterSart){
            //bis 60 sec nach start des Servers wird für jede eingehende Massage, ein Shelly-Dewise instanziiert wenn es noch nicht vorhanden ist
            shellyHandler.handelNewShelly(brokerMsgEnity);

            if ( System.currentTimeMillis() >= startTime + 100000000){
                not60SecAfterSart = false;
            }
        }
    }



    public void sortMsgToShelly(BrokerMsgEnity brokerMsgEnity) {
        MsgHandler msgHandler = null;
    //    log.info("id: " + brokerMsgEnity.getClientID().split("-")[0]);
       switch (BrokerMsgEntityHelper.getShellyModel(brokerMsgEnity.getClientID().split("-")[0])) {
           case "shellypro4pm" : {
                msgHandler = new Shelly4ProMsgHandler();
                break;
           }
           case "shellyem3" : {
                msgHandler = new ShellyEM3MsgHandler();
                break;
           }
       }
       msgHandler.handelMsg(brokerMsgEnity);
   }





    public void handelMsg(BrokerMsgEnity brokerMsgEnity){
       restorLast100msg(brokerMsgEnity);
        identifyClient(brokerMsgEnity);
        sortMsgToShelly(brokerMsgEnity);
    }
}
