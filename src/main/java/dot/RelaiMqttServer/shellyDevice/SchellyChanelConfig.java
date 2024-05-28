package dot.RelaiMqttServer.shellyDevice;

import org.json.JSONObject;

public class SchellyChanelConfig {

    private static float maxPower = 0.0f;

    public static float getMaxPower() {
        return maxPower;
    }

    public static void setMaxPower(float maxPower) {
        SchellyChanelConfig.maxPower = maxPower;
    }

    public static JSONObject shellyChannelConfigJSON(){
        JSONObject jo = new JSONObject().put("maxPower", SchellyChanelConfig.getMaxPower());
        return  jo;
    }
}
