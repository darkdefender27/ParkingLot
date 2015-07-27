package org.bmag;

import org.org.exceptions.CarNotFoundException;
import org.org.exceptions.LotFullException;
import org.org.exceptions.UniqueCarException;

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

    public Map<Integer, Car> getLotMap() {
        return lotMap;
    }

    /**
     * Register the new USERS to the LIST
     * @param obvList
     */
    public void register(List<PLObserver> obvList) {
        this.observerList = Stream.concat(this.observerList.stream(), obvList.stream()).collect(Collectors.toList());
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
                    for(PLObserver ob: this.observerList) {
                        ob.onFull();
                    }
                    plOwner.onFull();
                }
                return tokenizer;
            }

        }
    }

    public Car unPark(int tokenId) {

        if(!this.lotMap.containsKey(tokenId))
            throw new CarNotFoundException("No such CAR exists!");

        if(lotMap.size()==lotSize) {
            for(PLObserver ob: this.observerList) {
                ob.onSpaceAvailable();
            }
            plOwner.onSpaceAvailable();
        }

        Car c = this.lotMap.remove(tokenId);


        //CHECK DOCS FOR THIS.. CONCURRENCY!
       /* Car c1 = this.lotMap.remove(tokenId);

        if(c1==null)
            throw new CarNotFoundException("No CAR exists");*/

        return c;
    }

}
