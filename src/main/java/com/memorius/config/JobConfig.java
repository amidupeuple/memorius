package com.memorius.config;

import com.memorius.job.EveryHourNotifierJob;
import com.memorius.job.NotifierJob;
import com.memorius.service.EmailService;
import com.memorius.service.GoalService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dpivovar on 14.06.2016.
 */
@Configuration
@ComponentScan("com.memorius")
public class JobConfig {
    private static final Logger LOG = Logger.getLogger(JobConfig.class);


    @Bean
    public JobDetailFactoryBean everyHourNotifier() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(EveryHourNotifierJob.class);
        factory.setDurability(true);

        return factory;
    }


    @Bean
    @Autowired
    public JobDetailFactoryBean everydayNotifier(GoalService goalService, EmailService emailService) {
        JobDetailFactoryBean everydayNotifierJob = new JobDetailFactoryBean();
        everydayNotifierJob.setJobClass(NotifierJob.class);
        everydayNotifierJob.setDurability(true);
        Map<String, Object> jobData = new HashMap<String, Object>();
        jobData.put("frequency", "Everyday");
        jobData.put("goalService", goalService);
        jobData.put("emailService", emailService);
        everydayNotifierJob.setJobDataAsMap(jobData);

        return everydayNotifierJob;
    }


    /*@Bean
    @Autowired*/
    public CronTriggerFactoryBean everydayNotifierTrigger(JobDetail everydayNotifier) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(everydayNotifier);
        trigger.setStartDelay(5000);
        trigger.setCronExpression("0 00 12 * * ?");
        LOG.info(new Date());

        return trigger;
    }

    @Bean
    @Autowired
    public SimpleTriggerFactoryBean everydaySimpleNotifierTrigger(JobDetail everydayNotifier) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(everydayNotifier);
        trigger.setStartDelay(5000);
        trigger.setRepeatInterval(60000);

        return trigger;
    }



    /*@Bean
    @Autowired
    public SimpleTriggerFactoryBean everyHourNotifierTrigger(JobDetail everyHourNotifier) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(everyHourNotifier);
        trigger.setStartDelay(5000);
        trigger.setRepeatInterval(60000);

        return trigger;
    }*/

    @Bean
    @Autowired
    public SchedulerFactoryBean schedulerFactoryBean(Trigger[] triggers) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(triggers);
        LOG.info("from schedulerFactoryBean()");

        return scheduler;
    }

}
