package dot.RelaiMqttServer.shellyDevice;

import javax.persistence.Column;
import javax.persistence.Entity;


public class EmeterEntity {

    public EmeterEntity(String clientID, int i){
        this.emeterID = clientID + "E" + i;
        this.emeterIndex = i;
    }

    @Column
    private int emeterIndex;
    @Column
    private String emeterID;
    @Column
    private float energy;
    @Column
    private float returned_energy;
    @Column
    private float total;
    @Column
    private float total_returned;
    @Column
    private float power;
    @Column
    private float voltage;
    @Column
    private float current;
    @Column
    private float pf;

    @Column
    private float reactive_power;


    public int getEmeterIndex() {
        return emeterIndex;
    }

    public void setEmeterIndex(int emeterIndex) {
        this.emeterIndex = emeterIndex;
    }

    public String getEmeterID() {
        return emeterID;
    }

    public void setEmeterID(String emeterID) {
        this.emeterID = emeterID;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getReturned_energy() {
        return returned_energy;
    }

    public void setReturned_energy(float returned_energy) {
        this.returned_energy = returned_energy;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotal_returned() {
        return total_returned;
    }

    public void setTotal_returned(float total_returned) {
        this.total_returned = total_returned;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getPf() {
        return pf;
    }

    public void setPf(float pf) {
        this.pf = pf;
    }
}

