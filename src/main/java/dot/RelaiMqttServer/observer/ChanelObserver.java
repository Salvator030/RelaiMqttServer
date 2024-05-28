package dot.RelaiMqttServer.observer;

public interface ChanelObserver {

    public void updatePower(ChanelObserver observer,Float power);
    public void updateEnergy(ChanelObserver observer,Float energy);
}
