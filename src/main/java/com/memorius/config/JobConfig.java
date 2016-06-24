package com.memorius.config;

import com.memorius.job.NotifierJob;
import com.memorius.service.EmailService;
import com.memorius.service.GoalService;
import com.memorius.service.UserService;
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
    @Autowired
    public JobDetailFactoryBean everydayNotifier(GoalService goalService, EmailService emailService, UserService userService) {
        JobDetailFactoryBean everydayNotifierJob = new JobDetailFactoryBean();
        everydayNotifierJob.setJobClass(NotifierJob.class);
        everydayNotifierJob.setDurability(true);
        Map<String, Object> jobData = new HashMap<String, Object>();
        jobData.put("frequency", "Everyday");
        jobData.put("goalService", goalService);
        jobData.put("emailService", emailService);
        jobData.put("userService", userService);
        everydayNotifierJob.setJobDataAsMap(jobData);

        return everydayNotifierJob;
    }

    @Bean
    @Autowired
    public JobDetailFactoryBean oneInTwoDaysNotifier(GoalService goalService, EmailService emailService, UserService userService) {
        JobDetailFactoryBean oneInTwoDaysNotifier = new JobDetailFactoryBean();
        oneInTwoDaysNotifier.setJobClass(NotifierJob.class);
        oneInTwoDaysNotifier.setDurability(true);
        Map<String, Object> jobData = new HashMap<String, Object>();
        jobData.put("frequency", "One in two days");
        jobData.put("goalService", goalService);
        jobData.put("emailService", emailService);
        jobData.put("userService", userService);
        oneInTwoDaysNotifier.setJobDataAsMap(jobData);

        return oneInTwoDaysNotifier;
    }

    @Bean
    @Autowired
    public JobDetailFactoryBean oneInAWeekNotifier(GoalService goalService, EmailService emailService, UserService userService) {
        JobDetailFactoryBean oneInAWeekNotifier = new JobDetailFactoryBean();
        oneInAWeekNotifier.setJobClass(NotifierJob.class);
        oneInAWeekNotifier.setDurability(true);
        Map<String, Object> jobData = new HashMap<String, Object>();
        jobData.put("frequency", "One in a week");
        jobData.put("goalService", goalService);
        jobData.put("emailService", emailService);
        jobData.put("userService", userService);
        oneInAWeekNotifier.setJobDataAsMap(jobData);

        return oneInAWeekNotifier;
    }


    @Bean
    @Autowired
    public CronTriggerFactoryBean everydayNotifierTrigger(JobDetail everydayNotifier) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(everydayNotifier);
        trigger.setStartDelay(5000);
        trigger.setCronExpression("0 0 12 * * ?");

        return trigger;
    }

    @Bean
    @Autowired
    public CronTriggerFactoryBean oneInTwoDaysNotifierTrigger(JobDetail oneInTwoDaysNotifier) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(oneInTwoDaysNotifier);
        trigger.setStartDelay(5000);
        trigger.setCronExpression("0 5 12 */2 * ?");

        return trigger;
    }

    @Bean
    @Autowired
    public CronTriggerFactoryBean oneInAWeekNotifierTrigger(JobDetail oneInAWeekNotifier) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(oneInAWeekNotifier);
        trigger.setStartDelay(5000);
        trigger.setCronExpression("0 10 12 */7 * ?");

        return trigger;
    }

    /*@Bean
    @Autowired
    public SimpleTriggerFactoryBean everydaySimpleNotifierTrigger(JobDetail everydayNotifier) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(everydayNotifier);
        trigger.setStartDelay(5000);
        trigger.setRepeatInterval(86400000);

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
