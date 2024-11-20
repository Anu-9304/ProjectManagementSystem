package com.anushka.repository;

//import java.util.concurrent.Flow.Subscription;
import com.anushka.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long>{

    Subscription findByUserId(Long userId);

}
