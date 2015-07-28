package org.test;

import org.bmag.PLEventEnum;
import org.bmag.PLObserver;

public class TestFBIAgent implements TestPLObserver {

    private PLEventEnum status = PLEventEnum.VACANT;

    @Override
    public PLEventEnum checkStatus() {
        return status;
    }

    @Override
    public void notificationHandler(PLEventEnum eventCode) {
        switch(eventCode) {

            case FULL:
                this.status = PLEventEnum.FULL;
                break;

            case VACANT:
                this.status = PLEventEnum.VACANT;
                break;
        }
    }
}
