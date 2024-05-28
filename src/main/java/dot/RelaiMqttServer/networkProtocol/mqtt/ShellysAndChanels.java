package dot.RelaiMqttServer.networkProtocol.mqtt;

import dot.RelaiMqttServer.shellyDevice.ChanelEntity;
import dot.RelaiMqttServer.shellyDevice.ShellyEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class ShellysAndChanels {

    private static HashMap<String, ShellyEntity> shellys = new HashMap<String, ShellyEntity>();
    private static HashMap<String, ChanelEntity> outputOffchanels = new HashMap<String, ChanelEntity>();
    private static HashMap<String, ChanelEntity> outputOnChanels = new HashMap<String, ChanelEntity>();

    public HashMap<String, ShellyEntity> getConectetDevices() {
        return shellys;
    }

    public HashMap<String, ChanelEntity> getOutputOffchanels() {
        return outputOffchanels;
    }

    public HashMap<String, ChanelEntity> getOutputOnChanels() {
        return outputOnChanels;
    }

    public ShellyEntity getDevice(String device) {
        return shellys.get(device);
    }

    public void setChanelOutputMap(boolean output, ChanelEntity chanel) {
        if (output) {
            outputOnChanels.putIfAbsent(chanel.getChanelID(), chanel);
            outputOffchanels.remove(chanel.getChanelID());
        } else {
            outputOffchanels.putIfAbsent(chanel.getChanelID(), chanel);
        }
    }

    public float sumOnChanelsPower() {
        float count = 0;
        for (Map.Entry<String, ChanelEntity> e : outputOnChanels.entrySet()) {
            count += e.getValue().getPower();
        }
        return count;
    }

}
