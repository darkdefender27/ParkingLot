package org.test;

import org.bmag.PLObserver;
import org.bmag.PLOwner;

public class TestPLOwner implements PLObserver {
    private boolean status=false;

    public boolean isStatus() {
        return status;
    }

    @Override
    public boolean checkStatus() {
        return status;
    }

    @Override
    public void onFull() {
        this.status = true;
    }

    @Override
    public void onSpaceAvailable() {
        this.status = false;
    }
}
