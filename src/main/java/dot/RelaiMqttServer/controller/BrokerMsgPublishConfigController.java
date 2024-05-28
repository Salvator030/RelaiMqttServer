package dot.RelaiMqttServer.controller;

import dot.RelaiMqttServer.networkProtocol.mqtt.broker.BrokerMsgPublishConfig;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/broker/msgPublish/setting")
public class BrokerMsgPublishConfigController {

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());


    @PostMapping("/setConnfig")
    public String setConnfig(@RequestBody String req){
        BrokerMsgPublishConfig.setQos(new JSONObject(req).getInt("qos"));
        BrokerMsgPublishConfig.setIsRetain( new JSONObject(req).getBoolean("retain"));
        return "0";
    }

    @GetMapping("/getConfig")
    public String getConfig(){
        return BrokerMsgPublishConfig.brokerMsgPublishConfigJSON().toString();
    }
}
