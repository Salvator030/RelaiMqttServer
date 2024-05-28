package dot.RelaiMqttServer.observer;

public interface ChanelSubject {

    public void registerShellyObserver(ChanelObserver observer);

    public void removeShellyObserver(ChanelObserver observer);

    public void notifyShellyObservers();

    public void setPower(ChanelObserver observer, Float power);

    public void setEnergy(ChanelObserver observer, Float energy);
}
