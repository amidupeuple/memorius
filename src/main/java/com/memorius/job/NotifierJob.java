package com.memorius.job;

import com.memorius.model.Goal;
import com.memorius.model.User;
import com.memorius.service.EmailService;
import com.memorius.service.GoalService;
import com.memorius.service.UserService;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by dpivovar on 15.06.2016.
 */
public class NotifierJob extends QuartzJobBean {
    private static final Logger LOG = Logger.getLogger(NotifierJob.class);

    private String frequency;
    private GoalService goalService;
    private EmailService emailService;
    private UserService userService;


    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setGoalService(GoalService goalService) {
        this.goalService = goalService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("Notifier job : frequency=" + frequency + " fired!");

        List<Goal> goalsForNotifications = goalService.findGoalsByStatusAndNotificationFrequencyAllIgnoresCase("Open", frequency);

        if (goalsForNotifications != null) {
            LOG.info("Number of selected rows: " + goalsForNotifications.size());
        } else {
            LOG.info("goalsForNotifications = null ");
        }

        for (Goal goal: goalsForNotifications) {
            LOG.info("send email to: " + goal.getParticipants());

            if (goal.getParticipants() == null) {
                LOG.info("participants = null");
            } else {
                String[] participantArr = goal.getParticipants().split(",");

                for (String p: participantArr) {
                    User u = userService.findUserByUserName(p);
                    /*LOG.info("email: " + u.getEmail());
                    emailService.sendEmail(u.getEmail(),
                            "memorius.notifier@gmail.com",
                            "Memorius. " + goal.getName(),
                            "Deadline for " + goal.getName() + " is " + new SimpleDateFormat("dd-MM-yyyy").format(goal.getDeadline()) + "\nPlease keep it in mind.\n\n\n \n" +
                                    "Sincerely yours,\nMemorius");*/
                }
            }
        }
    }
}
