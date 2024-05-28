package dot.RelaiMqttServer.sqlite;

import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class BrokerMsgService {

    @Autowired
    BrokerMsgRepository brokerMsgRepository;

    public void insert(BrokerMsgEnity brokerMsgEnity){
        brokerMsgRepository.save(brokerMsgEnity);
    }

    public void insert(List<BrokerMsgEnity> brokerMsgEnities){
        brokerMsgRepository.saveAll(brokerMsgEnities);
    }

    public List<BrokerMsgEnity> getAll(){
       return brokerMsgRepository.findAll();
    }

    public List<BrokerMsgEnity> getByDate(LocalDateTime date){
        return brokerMsgRepository.findByDate(date);
    }
}
