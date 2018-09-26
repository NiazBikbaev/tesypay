package com.splat_spb.job;

import com.spb_splat.dto.WebHookEventDto;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Default comment.
 **/
public class NotificationJob implements Job {

    private String url;
    private WebHookEventDto event;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            restTemplate.postForEntity(url, event, String.class);
        } catch (Exception e) {
            throw new JobExecutionException("Failed to to post data: " + url);
        }
    }

    /**
     * Setter for url.
     *
     * @param url value
     */
    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * Setter for event.
     *
     * @param event value
     */
    public void setEvent(WebHookEventDto event) {
        this.event = event;
    }

    /**
     * Setter for restTemplate.
     *
     * @param restTemplate value
     */
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
