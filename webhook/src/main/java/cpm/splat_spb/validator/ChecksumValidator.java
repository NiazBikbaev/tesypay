package cpm.splat_spb.validator;

import com.spb_splat.dto.WebHookEventDto;

/**
 * Default comment.
 **/
public interface ChecksumValidator {
    boolean isValid(WebHookEventDto eventDto);
}
