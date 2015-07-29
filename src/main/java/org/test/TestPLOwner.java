package org.test;

import org.bmag.PLEventEnum;
import org.bmag.PLObserver;


public class TestPLOwner implements TestPLObserver {
    private PLEventEnum status = PLEventEnum.DEFAULT;

    public PLEventEnum checkStatus() {
        return status;
    }

    public void notificationHandler(PLEventEnum eventCode) {
        switch(eventCode) {

            case FULL:
                this.status = PLEventEnum.FULL;
                break;

            case VACANT:
                this.status = PLEventEnum.VACANT;
                break;

            case EIGHTYCENT:
                this.status = PLEventEnum.EIGHTYCENT;
                break;
        }
    }
}
