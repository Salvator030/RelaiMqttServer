package dot.RelaiMqttServer.entitys;


import io.moquette.interception.messages.InterceptPublishMessage;
import io.netty.buffer.ByteBuf;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.netty.util.CharsetUtil.UTF_8;

public class BrokerMsgEnity {

    private String topicName;
    private String clientID;
    private String msg;
    private String username;

    private LocalDateTime date;

    public BrokerMsgEnity(String topic, String clientID, ByteBuf msg, String username) {
        this.topicName = topic;
        this.clientID = clientID;
        this.msg = parseMsgPayLoad( msg);
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
