package org.bmag;

public interface PLObserver {

    public boolean checkStatus();
    public void onFull();
    public void onSpaceAvailable();

}
