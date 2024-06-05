package dot.RelaiMqttServer.helper;

import java.util.Arrays;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;

import org.slf4j.LoggerFactory;

public final class MyLogger {
     private static Logger log = LoggerFactory
               .getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

     public void logException(String className , String name, Exception exception , BrokerMsgEnity brokerMsgEnity) {
          log.error( className + "\n" +name +":" +  "\n" + exception.getMessage() + "\n" + stackToSeperateLines(exception)  +"\n" + brokerMsgEnity.getMsg());
     }
     
     public void toppicError(String c, String name, BrokerMsgEnity brokerMsgEnity){
          log.error(name+": topic unkonw" + "\n" + brokerMsgEnity.getTopic() + "\n" + brokerMsgEnity.getMsg());
     }

     public void jsonKeyError(String name, String key){
          log.error(name+ ": Not Found Key: " + key );
     }

     private  String stackToSeperateLines(Exception exception){
                   
          return Arrays.toString(exception.getStackTrace()).replace(",",",\n") ;
     }
}
