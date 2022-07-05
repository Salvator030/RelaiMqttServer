package dot.RelaiMqttServer.handler;

import dot.RelaiMqttServer.entitys.BrokerMsgEnity;
import dot.RelaiMqttServer.helper.BrokerMsgEntityHelper;
import io.moquette.interception.messages.InterceptPublishMessage;

import org.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MqttBrokerMsgHandler {



    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());
    private static List<BrokerMsgEnity> msgQueue = new ArrayList<>(100);


   public BrokerMsgEnity handelMsg(InterceptPublishMessage msg){
       BrokerMsgEnity brokerMsgEnity = new BrokerMsgEnity(msg.getTopicName(),msg.getClientID(),msg.getPayload(),msg.getUsername());
       if(msgQueue.size() == 100){
           msgQueue.remove(0);
       }
       msgQueue.add(brokerMsgEnity);
        return brokerMsgEnity;
   }

   public JSONArray sendMsg(){
       JSONArray jsonArray = new JSONArray();
       int count = this.msgQueue.size();
       for ( int i = 0; i < count; i++){
           jsonArray.put(BrokerMsgEntityHelper.getJson(this.msgQueue.get(i)));
       }
       log.info ("JSONArray\n" + jsonArray);
       return jsonArray;
   }



}
