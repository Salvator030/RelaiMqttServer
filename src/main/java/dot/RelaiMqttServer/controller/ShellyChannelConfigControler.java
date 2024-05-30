package dot.RelaiMqttServer.controller;

import dot.RelaiMqttServer.shellyDevice.SchellyChanelConfig;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shellys/setting")
public class ShellyChannelConfigControler {

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    @PostMapping("/setMaxPower")
    public String setMaxPower(@RequestBody String req){
     //   log.info(("shelly/setting\n" + req));
        SchellyChanelConfig.setMaxPower(new JSONObject(req).getFloat("maxPower"));
        return "0";
    }

    @GetMapping("/getMaxPower")
    public String getMaxPower(){
        return SchellyChanelConfig.shellyChannelConfigJSON().toString();
    }
}
