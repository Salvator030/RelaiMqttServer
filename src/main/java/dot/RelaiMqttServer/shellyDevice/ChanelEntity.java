package dot.RelaiMqttServer.shellyDevice;


import javax.persistence.*;
import java.io.*;

import java.time.LocalDateTime;


@Entity
@Table(name="channels")
public class ChanelEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String chanelID;
    @Column
    private int relayIndex;
    @Column
    private String source;
    @Column

    private boolean output;
    @Column
    private double power;
    @Column
    private double voltage;
    @Column
    private double current;
    @Column
    private double pf;

    @Column
    private float energy_total;


    @OrderColumn(name = "energy_byMinute")
    private byte[] energy_byMinute;


    @Column
    private float energy_minuteTs;

    @Column
    private float temp_c;

    @Column
    private float temp_f;

    @Column
    private String shelly4ProClientId;
    @Column
    private LocalDateTime dateOfChange;

    public ChanelEntity(){}
/*
   public ChanelEntity(Shelly4ProEntity shelly4Pro, int i) {
       this.shelly4ProClientId = shelly4Pro.getClientID();
       this.chanelID = shelly4Pro.getClientID()+i;

       this.source = "";
       this.output = false;
       this.power = 0.0f;
       this.voltage = 0.0f;
       this.current = 0.0f;
       this.pf = 0.00f;
       this.energy_total = 0.0f;
/*
       float[] array = {0.0f,0.0f,0.0f};
       this.energy_byMinute = convetFloatArrayToByteArray(array);


       this.energy_minuteTs = 0.0f;
       this.temp_c = 0.0f;
       this.temp_f = 0.0f;
       this.index = i;
       this.dateOfChange = null;
   }
    */

    public String getChanelID() {
        return chanelID;
    }

    public void setChanelID(String chanelID) {
        this.chanelID = chanelID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isOutput() {
        return output;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

    public float getEnergy_total() {
        return energy_total;
    }

    public void setEnergy_total(float energy_total) {
        this.energy_total = energy_total;
    }

    
    public float[] getEnergy_byMinute()   {
        return convertByteArrayToFloatArray(energy_byMinute);
    }

    public void setEnergy_byMinute(float[] energy_byMinute)  {
        this.energy_byMinute = convetFloatArrayToByteArray(energy_byMinute);
    }


    
    public float getEnergy_minuteTs() {
        return energy_minuteTs;
    }

    public void setEnergy_minuteTs(float energy_minuteTs) {
        this.energy_minuteTs = energy_minuteTs;
    }

    public float getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(float temp_c) {
        this.temp_c = temp_c;
    }

    public float getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(float temp_f) {
        this.temp_f = temp_f;
    }

    public String getShelly4Pro() {
        return shelly4ProClientId;
    }

    public int getRelayIndex() {return relayIndex;}

    public void setRelayIndex(int relayIndex) {
        this.relayIndex = relayIndex;
    }

    public void setShelly4ProClientId(String shelly4ProClientId) {
        this.shelly4ProClientId = shelly4ProClientId;
    }

    public LocalDateTime getDateOfChange() {
        return dateOfChange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateOfChange(LocalDateTime dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    private byte[] convetFloatArrayToByteArray(float[] floatA)  {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(bas);
       try {
           for (float f : floatA) {
               ds.writeFloat(f);
           }
       }
       catch (Exception e){
           System.out.println(e);
       }
       return bas.toByteArray();
    }

    private float[] convertByteArrayToFloatArray(byte[] byteA) {
        ByteArrayInputStream bas = new ByteArrayInputStream(byteA);
        DataInputStream ds = new DataInputStream(bas);
        float[] fArr = new float[byteA.length / 4];  // 4 bytes per float
        try {

            for (int i = 0; i < fArr.length; i++) {
                fArr[i] = ds.readFloat();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return fArr;

    }
}
