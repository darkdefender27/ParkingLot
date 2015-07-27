package org.bmag;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private final int lotSize;
    private int tokenizer;
    private Map<Car, Integer> lotMap;

    public ParkingLot(int lotSize, int tokenizer) {
        this.lotSize=lotSize;
        this.tokenizer=tokenizer;
        this.lotMap = new HashMap<>();
    }

    public int park(Car c) {
        this.tokenizer++;
        if(this.tokenizer>lotSize) {
            throw new LotFullException("Go Away! Parking is FULL!");
        }
        else {
            lotMap.put(c, tokenizer);
            return tokenizer;
        }
    }
}
