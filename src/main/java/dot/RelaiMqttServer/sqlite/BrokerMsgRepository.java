package dot.RelaiMqttServer.sqlite;

import dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg.BrokerMsgEnity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface  BrokerMsgRepository extends JpaRepository<BrokerMsgEnity, Long> {

    public List<BrokerMsgEnity> findByDate(LocalDateTime date);
}
