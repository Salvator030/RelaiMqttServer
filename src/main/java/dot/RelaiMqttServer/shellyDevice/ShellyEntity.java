package dot.RelaiMqttServer.shellyDevice;


import javax.persistence.*;


public abstract class ShellyEntity {


    @Column
    protected String model;
    @Column
    protected String clientID;

    @Column
    protected String ip ;
    @Column
    protected String mac;
    @Column
    protected boolean new_fw;
    @Column
    protected String fw_ver;

    public ShellyEntity() {
        this.ip = "000.000.000.000";
        this.mac = "ABCDEFGH";
        this.new_fw = false;
        this.fw_ver = "0";
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
