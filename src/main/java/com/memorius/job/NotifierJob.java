package com.memorius.job;

import com.memorius.model.Goal;
import com.memorius.service.EmailService;
import com.memorius.service.GoalService;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * Created by dpivovar on 15.06.2016.
 */
public class NotifierJob extends QuartzJobBean {
    private static final Logger LOG = Logger.getLogger(NotifierJob.class);

    private String frequency;

    private GoalService goalService;

    private EmailService emailService;

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setGoalService(GoalService goalService) {
        this.goalService = goalService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("Notifier job : frequency=" + frequency + " fired!");

        List<Goal> goalsForNotifications = goalService.findGoalsByStatusAndNotificationFrequencyAllIgnoresCase("open", frequency);

        if (goalsForNotifications != null) {
            LOG.info("Number of selected rows: " + goalsForNotifications.size());
        } else {
            LOG.info("goalsForNotifications = null ");
        }

        for (Goal goal: goalsForNotifications) {
            LOG.info("send email");
        }
    }
}
