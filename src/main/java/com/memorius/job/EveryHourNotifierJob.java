package com.memorius.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by dpivovar on 14.06.2016.
 */
public class EveryHourNotifierJob extends QuartzJobBean {

    private static Logger LOG = Logger.getLogger(EveryHourNotifierJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("EveryHourNotifierJob fires!");
    }
}
