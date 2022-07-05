package dot.RelaiMqttServer.entitys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChanelEntity {

    private  ArrayList<HashMap<String,String>> chanels;

    public ChanelEntity(){
        this.chanels = new ArrayList<HashMap<String,String>>();
        initChanel();

    }


    private void initChanel(){

        for ( int i = 0; i < 4; i++ ){
           HashMap<String,String> m =  new HashMap<>();
           m.put("input","0");
           m.put("status","off");
           m.put("power","0");
           m.put("energy","0");
           this.chanels.add(i,m);
        }
    }

    public void set(int i, String k, String v){
        this.chanels.get(i).put(k,v);

    }

    public String get(int i, String k){
       return this.chanels.get(i).get(k);

    }
}

