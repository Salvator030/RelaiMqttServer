package dot.RelaiMqttServer.shellyDevice;

public class WifiEntity {
    private boolean connected;
    private String ssid;
    private String ip;
    private int rssi;

    public WifiEntity(){
        this.connected = false;
        this.ssid ="";
        this.ip = "";
        this.rssi = 0;
    };

    public WifiEntity(boolean connected ,String ssid,String ip,int rssi){
        this.connected = connected;
        this.ssid =ssid;
        this.ip = ip;
        this.rssi = rssi;
    }

    public boolean isConectet(){
        return connected;
    }

    public void setConnected(boolean connected){
        this.connected = connected;
    }
 
    public String getSsid(){
        return this.ssid;
    }

    public void setSsid(String ssid){
        this.ssid = ssid;
    }

    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

    public int getRssi(){
        return this.rssi;
    }

    public void setRssi(int rssi){
        this.rssi =rssi;
    }
}
