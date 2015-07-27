package org.bmag;

public class TestPLOwner extends PLOwner{
    private boolean status=false;

    public boolean isStatus() {
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
