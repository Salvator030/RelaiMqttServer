package dot.RelaiMqttServer.shellyDevice;

import dot.RelaiMqttServer.networkProtocol.mqtt.ShellysAndChanels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Analyser {

    private static Logger log = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    @Autowired
    ShellysAndChanels shellysAndChanels;

    private Boolean channelsWiehtOutOutputOff = false;

/*
    private void changeOutputMap(){
        //ToDO kosten intensiev
        HashMap<String, Shelly4ProEntity> conectet = shellysAndChanels.getConectetDevices();

        for ( Map.Entry<String, Shelly4ProEntity> entry :conectet.entrySet() ){
           List<ChanelEntity> chanels =  entry.getValue().getChanels();
           for(int i = 0; i<4; i++){
               if(chanels.get(i).isOutput()){
                   shellysAndChanels.getOutputOnChanels().putIfAbsent(entry.getKey()+i,chanels.get(i));
                   shellysAndChanels.getOutputOffchanels().remove(chanels.get(i));
               }
               else {
                   shellysAndChanels.getOutputOnChanels().remove(chanels.get(i));
                   shellysAndChanels.getOutputOffchanels().putIfAbsent(entry.getKey()+i,chanels.get(i));
               }
           }
        }
    }

 */

    public  int analysPower(){
    //    log.info("SchellyChanelConfig.getMaxPower() " + SchellyChanelConfig.getMaxPower());
        if (SchellyChanelConfig.getMaxPower() != 0.0f ){
    //        log.info("shellysAndChanels.sumOnChanelsPower() " + shellysAndChanels.sumOnChanelsPower());

            if ( shellysAndChanels.sumOnChanelsPower() >= SchellyChanelConfig.getMaxPower() && !channelsWiehtOutOutputOff ){
                channelsWiehtOutOutputOff = true;
               return  -1;
           }
            else if (shellysAndChanels.sumOnChanelsPower() < SchellyChanelConfig.getMaxPower() && channelsWiehtOutOutputOff){
                return 1;
            }
       }
        return 0;
    }



    private float sumEnergy(){
        HashMap<String, ChanelEntity> chanels = shellysAndChanels.getOutputOnChanels();
        float count = 0;
        for (Map.Entry<String, ChanelEntity> e : chanels.entrySet()){
            count += (Float) e.getValue().getEnergy_total();
        }
        return count;
    }


}
