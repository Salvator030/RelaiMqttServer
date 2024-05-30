package dot.RelaiMqttServer.controller;

import dot.RelaiMqttServer.handler.ShellyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/shellys")
public class ShellyController {


    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    @Autowired
    private ShellyHandler shellyHandler;

    @GetMapping("/shellys")
    public String getShellys(){
  //   log.info("api/shellys" + shellyHandler.sendShellys().toString());
    return shellyHandler.sendShellys().toString();
    }





}
