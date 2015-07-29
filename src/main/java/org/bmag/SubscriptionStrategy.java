package org.bmag;

public interface SubscriptionStrategy {

    public boolean apply(PLEventEnum eventCode);

}
