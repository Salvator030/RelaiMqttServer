package dot.RelaiMqttServer.controller;

import dot.RelaiMqttServer.handler.MqttBrokerMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dot.RelaiMqttServer.mqttBroke.Broker;

@Component
@RestController
@RequestMapping("/api/broker")
public class BrokerController {
    @Autowired
    private Broker broker ;

  @Autowired
    private MqttBrokerMsgHandler mqttBrokerMsgHandler;


    @GetMapping("/isRun")
    public String isRun(){
       if(broker.isRun()){
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
