package dot.RelaiMqttServer.networkProtocol.mqtt.incommingMsg;


import io.moquette.interception.messages.InterceptPublishMessage;
import io.netty.buffer.ByteBuf;
import org.json.JSONArray;
import org.json.JSONObject;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.netty.util.CharsetUtil.UTF_8;
@Entity
@Table(name="BrokerMsg")
public class BrokerMsgEnity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="topic")
    private String topicName;

    @Column(name = "clientID")
    private String clientID;

    @Column(name = "msg")
    private String msg;

    @Column(name = "username")
    private String username;

    @Column(name = "date")
    private LocalDateTime date;

    public BrokerMsgEnity(String topic, String clientID, ByteBuf msg, String username) {
        this.topicName = topic;
        this.clientID = clientID;
        this.msg = parseMsgPayLoad( msg);
        this.username = username;
        this.date= LocalDateTime.now();
    }

    public BrokerMsgEnity(String topic, String clientID, String msg, String username) {
        this.topicName = topic;
        this.clientID = clientID;
        this.msg =  msg;
        this.username = username;
        this.date= LocalDateTime.now();
    }

    public BrokerMsgEnity(String topic, String clientID, ByteBuf msg) {
        this.topicName = topic;
        this.clientID = clientID;
        this.msg = parseMsgPayLoad( msg);
        this.username = "default";
        this.username = username;
        this.date= LocalDateTime.now();
    }

    public String getTopic() {
        return topicName;
    }

    public String getClientID() {
        return clientID;
    }

    public String getMsg() {
        return msg;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String toJSONString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clientID",this.clientID);
        jsonObject.put("username", this.username);
        jsonObject.put("topicName", this.topicName);
        jsonObject.put("msg",this.msg);
        jsonObject.put("date", dateTooString());
       return jsonObject.toString();
    }

    private String parseMsgPayLoad(ByteBuf msgPayload){
        // Create array for payload
        int readableBytes = msgPayload.readableBytes();
        byte[] payload = new byte[readableBytes];

        // Read bytes from payload
        for (int i = 0; i < readableBytes; i++) {
            payload[i] = msgPayload.readByte();
        }
        // Create string from payload
        String decodedPayload = new String(payload, UTF_8);
        return decodedPayload;
    }

    public void test(){
        JSONArray jsonArray =new JSONArray();
        String string = jsonArray.toString();
    }

    public String dateTooString() {

       DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss");

     return this.date.format(myFormatObj);
    }
}
