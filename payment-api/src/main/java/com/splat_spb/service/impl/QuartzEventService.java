package com.splat_spb.service.impl;

import com.spb_splat.dto.WebHookEventDto;
import com.splat_spb.job.NotificationJob;
import com.splat_spb.service.EventService;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Default comment.
 **/
@Service
public class QuartzEventService implements EventService {

    private static final String EVENT_PARAM = "event";
    private static final String URL_PARAM = "url";
    private static final String GROUP = "webHook";
    private final Scheduler scheduler;
    @Value("${testpay.job.retryCount}")
    private int retryCount;
    @Value("${testpay.job.intervalInSeconds}")
    private int intervalInSeconds;
    private final RestTemplate restTemplate;

    public QuartzEventService(Scheduler scheduler, RestTemplate restTemplate) {
        this.scheduler = scheduler;
        this.restTemplate = restTemplate;
    }

    @Override
    public void publishEvent(String url, WebHookEventDto event) throws SchedulerException {
        try {
            restTemplate.postForEntity(url, event, String.class);
        } catch (Exception e) {
            scheduleJob(url, event);
        }
    }

    private void scheduleJob(String url, WebHookEventDto event) throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(EVENT_PARAM, event);
        jobDataMap.put(URL_PARAM, url);

        JobDetail jobDetail = newJob(NotificationJob.class)
                .withIdentity(UUID.randomUUID().toString(), GROUP)
                .usingJobData(jobDataMap)
                .build();

        Trigger trigger = newTrigger()
                .withIdentity(UUID.randomUUID().toString())
                .withSchedule(simpleSchedule().withIntervalInSeconds(intervalInSeconds)
                                              .withRepeatCount(retryCount))
                .forJob(jobDetail)
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

}
