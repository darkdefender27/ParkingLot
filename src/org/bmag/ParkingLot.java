package org.bmag;

import org.exceptions.CarNotFoundException;
import org.exceptions.LotFullException;
import org.exceptions.UniqueCarException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingLot {

    private PLObserver plOwner;

    private List<PLObserver> observerList;
    private final int lotSize;
    private int tokenizer;
    private Map<Integer, Car> lotMap;

    public ParkingLot(PLObserver plOwner, int lotSize, int tokenizer) {
        this.plOwner = plOwner;
        this.lotSize=lotSize;
        this.tokenizer=tokenizer;

        this.lotMap = new HashMap<Integer, Car>();
        this.observerList = new ArrayList<>();
    }

    /**
     * Register the new USERS to the LIST
     * @param obv
     */
    public void subscribe(PLObserver obv) {
        //this.observerList = Stream.concat(this.observerList.stream(), obvList.stream()).collect(Collectors.toList());
        this.observerList.add(obv);
    }

    /**
     * Unregister observers from the List
     * @param plObv
     */
    public void unRegister(PLObserver plObv) {
        this.observerList.remove(plObv);
    }

    public void notifyCall(List<PLObserver> obvList, PLEventEnum eventCode){

        switch(eventCode) {

            case FULL:
                for(PLObserver ob: obvList) {
                    ob.notificationHandler(PLEventEnum.FULL);
                }
                break;

            case VACANT:
                for(PLObserver ob: obvList) {
                    ob.notificationHandler(PLEventEnum.VACANT);
                }
                break;
        }
    }

    public int park(Car c) {
        this.tokenizer++;
        if(this.tokenizer>lotSize ) {
            throw new LotFullException("Go Away! Parking is FULL!");
        }
        else {
            if(lotMap.containsValue(c)) {
                throw new UniqueCarException("Same Car getting parked TWICE! :/");
            }
            else {
                lotMap.put(tokenizer, c);
                //LAST CAR TO ENTER
                if(lotMap.size()==lotSize) {
                    notifyCall(this.observerList, PLEventEnum.FULL);
                }
                return tokenizer;
            }

        }
    }

    public Car unPark(int tokenId) {

        if(!this.lotMap.containsKey(tokenId))
            throw new CarNotFoundException("No such CAR exists!");

        if(lotMap.size()==lotSize) {
            notifyCall(this.observerList, PLEventEnum.VACANT);
        }

        Car c = this.lotMap.remove(tokenId);


        //CHECK DOCS FOR THIS.. CONCURRENCY!
       /* Car c1 = this.lotMap.remove(tokenId);

        if(c1==null)
            throw new CarNotFoundException("No CAR exists");*/

        return c;
    }

}
