package org.test;

import org.bmag.PLEventEnum;
import org.bmag.PLObserver;

public interface TestPLObserver extends PLObserver{

    public PLEventEnum checkStatus();

}
