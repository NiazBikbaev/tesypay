package com.splat_spb.service;

import com.spb_splat.dto.WebHookEventDto;
import org.quartz.SchedulerException;

/**
 * Default comment.
 **/
public interface EventService {
    void publishEvent(String url, WebHookEventDto event) throws SchedulerException;
}
