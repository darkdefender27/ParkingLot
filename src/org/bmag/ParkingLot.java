package org.bmag;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private PLOwner plOwner;
    private final int lotSize;
    private int tokenizer;
    private Map<Integer, Car> lotMap;

    public ParkingLot(PLOwner plOwner, int lotSize, int tokenizer) {
        this.plOwner = plOwner;
        this.lotSize=lotSize;
        this.tokenizer=tokenizer;
        this.lotMap = new HashMap<>();
    }

    public Map<Integer, Car> getLotMap() {
        return lotMap;
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
                if(lotMap.size()==lotSize) {
                    plOwner.onFull();
                }
                return tokenizer;
            }

        }


    }

    public Car unPark(int tokenId) {

        if(!this.lotMap.containsKey(tokenId))
            throw new CarNotFoundException("No such CAR exists!");

        Car c = this.lotMap.remove(tokenId);


        //CHECK DOCS FOR THIS.. CONCURRENCY!
       /* Car c1 = this.lotMap.remove(tokenId);

        if(c1==null)
            throw new CarNotFoundException("No CAR exists");*/

        return c;
    }

}
