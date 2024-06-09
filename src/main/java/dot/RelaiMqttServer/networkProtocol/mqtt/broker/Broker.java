package dot.RelaiMqttServer.networkProtocol.mqtt.broker;

import io.moquette.broker.Server;
import io.moquette.broker.config.ClasspathResourceLoader;
import io.moquette.broker.config.IConfig;
import io.moquette.broker.config.IResourceLoader;
import io.moquette.broker.config.ResourceLoaderConfig;
import io.netty.handler.codec.mqtt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class  Broker{

        private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());
        private final Server mqttBroker = new Server();
        private static boolean isRun = false;

        @Autowired
        IncommingPublisherListener incommingPublisherListener;  //veröfentlicht die Mqtt Msg.

        public void startServer() {
                // Load class path for configuration
                IResourceLoader classpathLoader = new ClasspathResourceLoader();
                final IConfig classPathConfig = new ResourceLoaderConfig(classpathLoader);

                log.info("START MQTT BROKER...");
                List userHandlers = Collections.singletonList(incommingPublisherListener);
                // get sure that the broker not run
                if ( !this.isRun )
                try{
                        mqttBroker.startServer(classPathConfig, userHandlers);
                        log.info("MQTT BROKER IS RUNNING");
                        this.isRun = true;
                } catch (Exception ex) {
                        log.error("MQTT broker start failed...");
                }
        }

        public void stopServer() {
                try {
                        mqttBroker.stopServer();
                        this.isRun = false;
                        log.info("BROKER IS STOPPED...");
                }
                catch (Exception e){
                        log.error(e.toString());
                }
        }

        public boolean isRun(){
                log.info("Request BROKER IS RUNNING");
                return this.isRun;
        }

        public void sendMsg(MqttPublishMessage message){
                mqttBroker.internalPublish(message, BrokerMsgPublishConfig.getClientId());
        }
}