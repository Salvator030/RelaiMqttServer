package dot.RelaiMqttServer.controller;

import dot.RelaiMqttServer.handler.MqttBrokerIncommingMsgHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import dot.RelaiMqttServer.networkProtocol.mqtt.broker.Broker;

@Component
@RestController
@RequestMapping("/api/broker")
public class BrokerController {

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());
    @Autowired
    private Broker broker ;

  @Autowired
    private MqttBrokerIncommingMsgHandler mqttBrokerMsgHandler;


    @GetMapping("/isRun")
    public String isRun(){
       if( this.broker != null && this.broker.isRun()){
          return "0";
       }
       else{
         return "-1";
        }
    }


    @GetMapping("/start")
    public String start(){
        try{
            broker.startServer();
        }
        catch (Exception e){
            return e.toString();
        }
        return "0";
    }

    @GetMapping("/stop")
    public String stop(){
        broker.stopServer();
        return "0";
    }

    @GetMapping("/msgs")
    public String msgs(){
       return mqttBrokerMsgHandler.sendMsg().toString();
    }




}
