package dot.RelaiMqttServer.configurations;

import dot.RelaiMqttServer.handler.MqttBrokerMsgHandler;
import dot.RelaiMqttServer.handler.ShellyHandler;
import io.moquette.broker.Server;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import dot.RelaiMqttServer.mqttBroke.Broker;

@Configuration

public class BackendConfig {

    @Bean
    public Broker broker(){
        return new Broker();
    }

    @Bean
    public MqttBrokerMsgHandler mqttBrokerMsgHandler(){
        return new MqttBrokerMsgHandler();
    }

    @Bean
    public ShellyHandler shellyHandler(){ return new ShellyHandler(); }

    @Bean
    public Server server() { return  new Server();};

    @Bean
    public JSONObject jsonObject(){ return new JSONObject();}

    @Bean
    public JSONArray jsonArray(){ return new JSONArray(); }


}
