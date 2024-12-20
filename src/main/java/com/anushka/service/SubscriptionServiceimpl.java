package com.anushka.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anushka.model.PlanType;
import com.anushka.model.Subscription;
import com.anushka.model.User;
import com.anushka.repository.SubscriptionRepository;

@Service
public class SubscriptionServiceimpl implements SubscriptionService{

    
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription = new Subscription();

        subscription.setUser(user);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setGetSubcriptionEndDate(LocalDate.now().plusMonths( 12));
        subscription.setValid(true);
        subscription.setPlanType (PlanType.FREE);
        
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUsersSubscription(Long userId) throws Exception {
        Subscription subscription=subscriptionRepository.findByUserId(userId);
         
        if(!isValid(subscription)){
            subscription.setPlanType (PlanType.FREE);
            subscription.setGetSubcriptionEndDate(LocalDate.now().plusMonths (12));
            subscription.setSubscriptionStartDate(LocalDate.now());
        
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgradeSubscription(Long userId, PlanType planType) {
        Subscription subscription=subscriptionRepository.findByUserId(userId);
        
        subscription.setPlanType (planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        
        if(planType.equals(PlanType.ANNUALLY)){
            subscription.setGetSubcriptionEndDate(LocalDate.now().plusMonths(12));
        }else{
            subscription.setGetSubcriptionEndDate(LocalDate.now().plusMonths ( 1));
        }
        
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if(subscription.getPlanType().equals(PlanType.FREE)){

            return true;
            
            }
            
            LocalDate endDate=subscription.getGetSubcriptionEndDate();
            
            LocalDate currentDate=LocalDate.now();
            
            return endDate.isAfter (currentDate) || endDate.isEqual(currentDate);
    }

}
