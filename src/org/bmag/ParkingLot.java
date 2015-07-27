package org.bmag;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private final int lotSize;
    private int tokenizer;
    private Map<Integer, Car> lotMap;

    public ParkingLot(int lotSize, int tokenizer) {
        this.lotSize=lotSize;
        this.tokenizer=tokenizer;
        this.lotMap = new HashMap<>();
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
                return tokenizer;
            }

        }
    }
}
