package dot.RelaiMqttServer.entitys;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shelly4ProEntity {

    private String model;
    private String diviced;
    private ChanelEntity chanels;
    private ShellyAnnounceEntity announce;

    public Shelly4ProEntity(String diviced) {
        this.diviced = diviced;
        this.model = "shelly4pro";
        this.chanels = new ChanelEntity();
        this.announce = new ShellyAnnounceEntity(this.model);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDiviced() {
        return diviced;
    }

    public void setDiviced(String diviced) {
        this.diviced = diviced;
    }

    public ChanelEntity getChanels() {
        return chanels;
    }

    public void setChanels(ChanelEntity chanels) {
        this.chanels = chanels;
    }

    public ShellyAnnounceEntity getAnnounce() {
        return announce;
    }

    public void setAnnounce(ShellyAnnounceEntity announce) {
        this.announce = announce;
    }
}
