package org.bmag;

public class FBIAgent implements PLObserver{

    @Override
    public boolean checkStatus() {
        return false;
    }

    @Override
    public void onFull() {

    }

    @Override
    public void onSpaceAvailable() {

    }
}
