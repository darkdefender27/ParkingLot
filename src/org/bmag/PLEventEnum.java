package org.bmag;

public enum PLEventEnum {
    FULL(1), VACANT(2), EIGHTYCENT(3);

    private int eventCode;

    private PLEventEnum(int val) {
        this.eventCode = val;
    }
}
