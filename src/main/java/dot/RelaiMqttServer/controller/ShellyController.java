package dot.RelaiMqttServer.controller;

import dot.RelaiMqttServer.handler.ShellyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/api/shellys")
public class ShellyController {

    @Autowired
    private ShellyHandler shellyHandler;

    @GetMapping("/shellys")
    public String getShellys(){

        return shellyHandler.sendShellys().toString();
    }

}
