package com.splat_spb.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Default comment.
 **/
@Component
public class NotificationJobListener implements JobListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationJobListener.class);

    @Override
    public String getName() {
        return "notificationJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        Scheduler scheduler = context.getScheduler();
        if (jobException == null) {
            try {
                scheduler.deleteJob(context.getJobDetail().getKey());
            } catch (SchedulerException e) {
                LOGGER.warn("Failed to remove job", e);
            }
        }
    }
}
