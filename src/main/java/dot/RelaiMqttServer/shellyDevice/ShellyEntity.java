package dot.RelaiMqttServer.shellyDevice;


import javax.persistence.*;


public abstract class ShellyEntity {


    @Column
    private String model;
    @Column
    private String clientID;

    @Column
    private String ip ;
    @Column
    private String mac;
    @Column
    private boolean new_fw;
    @Column
    private String fw_ver;

    public ShellyEntity() {


    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public boolean isNew_fw() {
        return new_fw;
    }

    public void setNew_fw(boolean new_fw) {
        this.new_fw = new_fw;
    }

    public String getFw_ver() {
        return fw_ver;
    }

    public void setFw_ver(String fw_ver) {
        this.fw_ver = fw_ver;
    }
}
