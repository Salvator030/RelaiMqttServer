package dot.RelaiMqttServer.entitys;

public class ShellyAnnounceEntity {

    private String model;
    private String id ;
    private String mac;
    private boolean new_fw;
    private String fw_ver;

    public ShellyAnnounceEntity(String model) {
        this.model = model;
        this.id = null;
        this.mac = null;
        this.new_fw = false;
        this.fw_ver = null;
    }

    public ShellyAnnounceEntity(String model, String id, String mac, boolean new_fw, String fw_ver) {
        this.model = model;
        this.id = id;
        this.mac = mac;
        this.new_fw = new_fw;
        this.fw_ver = fw_ver;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
