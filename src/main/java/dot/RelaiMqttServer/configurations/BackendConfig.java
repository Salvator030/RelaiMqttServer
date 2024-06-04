package dot.RelaiMqttServer.configurations;

import dot.RelaiMqttServer.evnt.ShellyAnalysEventListener;
import dot.RelaiMqttServer.evnt.ShellyAnalysEventPublisher;
import dot.RelaiMqttServer.evnt.ShellyCommandEventPublisher;
import dot.RelaiMqttServer.evnt.ShellyCommandEventListener;
import dot.RelaiMqttServer.handler.MqttBrokerIncommingMsgHandler;
import dot.RelaiMqttServer.handler.ShellyHandler;
import dot.RelaiMqttServer.handler.deviceHandler.Shelly4ProMsgHandler;
import dot.RelaiMqttServer.handler.deviceHandler.ShellyEM3MsgHandler;
import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import dot.RelaiMqttServer.networkProtocol.mqtt.broker.IncommingPublisherListener;
import dot.RelaiMqttServer.shellyDevice.Analyser;
import dot.RelaiMqttServer.shellyDevice.ShellyChanelCommandMsg;
import dot.RelaiMqttServer.shellyDevice.ShellyAndChanelCommander;
import io.moquette.broker.Server;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import dot.RelaiMqttServer.networkProtocol.mqtt.broker.Broker;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"dot.RelaiMqttServer.mqtt.broker", "dot.RelaiMqttServer.evnt", "dot.RelaiMqttServer.handler"})

public class BackendConfig {


    //MQTT
    @Bean
    public Server server() { return  new Server();}

    @Bean
    public Broker broker(){return new Broker();}

    @Bean
    public MqttBrokerIncommingMsgHandler mqttBrokerIncommingMsgHandler(){return new MqttBrokerIncommingMsgHandler();}

    @Bean
    public ShellyHandler shellyHandler(){ return new ShellyHandler(); }

    @Bean
    public JSONObject jsonObject(){ return new JSONObject();}

    @Bean
    public ShellysAndChanels shellysAndChanels() {return new ShellysAndChanels();}

    @Bean
    public Analyser analyser() {return new Analyser();}

    @Bean
    public ShellyChanelCommandMsg shellyChanelCommandMsg() {return new ShellyChanelCommandMsg();}

    @Bean
    public ShellyCommandEventPublisher shellyCommandEventPublisher() { return  new ShellyCommandEventPublisher();}

    @Bean
    public ShellyCommandEventListener shellyCommandEventListener() { return new ShellyCommandEventListener();}

    @Bean
    public ShellyAnalysEventPublisher shellyAnalysEventPublisher(){return  new ShellyAnalysEventPublisher();}

    @Bean
    public ShellyAnalysEventListener shellyAnalysEventListener() {return new ShellyAnalysEventListener();}

    @Bean
    public IncommingPublisherListener incommingPublisherListener() {return new IncommingPublisherListener(mqttBrokerIncommingMsgHandler(), shellyHandler() );}

    @Bean
    public ShellyAndChanelCommander shellyChanelCommander(){return new ShellyAndChanelCommander();}

    @Bean
    public ShellyEM3MsgHandler shellyEM3MsgHandler(){return new ShellyEM3MsgHandler();}

    @Bean
    public Shelly4ProMsgHandler shelly4ProMsgHandler(){return new Shelly4ProMsgHandler();}

}
