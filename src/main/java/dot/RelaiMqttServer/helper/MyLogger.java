package dot.RelaiMqttServer.helper;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;

public final class MyLogger {
     private static Logger log = LoggerFactory
               .getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

     public void logException(String className , String functionName, Exception exception , BrokerMsgEnity brokerMsgEnity) {
          log.error( className + "\n" +functionName +":" +  "\n" + exception.getMessage() + "\n" + stackToSeperateLines(exception)  +"\n" + brokerMsgEnity.getMsg());
     }
     
     public void toppicError(String className, String functionname, BrokerMsgEnity brokerMsgEnity){
          log.error(className + "\n",functionname+": topic unkonw" + "\n" + brokerMsgEnity.getTopic() + "\n" + brokerMsgEnity.getMsg());
     }

     public void jsonKeyError(String className, String functionName, String key){
          log.error(className + "\n",functionName+ ": Not Found Key: " + key );
     }

     private  String stackToSeperateLines(Exception exception){
                   
          return Arrays.toString(exception.getStackTrace()).replace(",",",\n") ;
     }
}
