package com.green.jobdone.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataCleanUpService {
    @PersistenceContext
    private EntityManager em;

    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void deleteData(){
        String sql = "DELETE FROM business_pic WHERE createdAt < CURRENT_TIMESTAMP - INTERVAL 1 DAY AND state = 1";
        em.createNativeQuery(sql).executeUpdate();
    }

}
