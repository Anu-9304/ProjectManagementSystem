package com.anushka.service;

import com.anushka.model.PlanType;
import com.anushka.model.Subscription;
import com.anushka.model.User;

public interface SubscriptionService {
    Subscription createSubscription (User user);
    Subscription getUsersSubscription (Long userId) throws Exception;
    Subscription upgradeSubscription (Long userId, PlanType planType);
    boolean isValid(Subscription subscription);

}
