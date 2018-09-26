package cpm.splat_spb.rest;

import com.spb_splat.dto.WebHookEventDto;
import cpm.splat_spb.validator.ChecksumValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Default comment.
 **/
@RestController
public class CallbackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackController.class);

    private final ChecksumValidator checksumValidator;

    public CallbackController(ChecksumValidator checksumValidator) {
        this.checksumValidator = checksumValidator;
    }

    @PostMapping("/callback")
    public ResponseEntity callback(@RequestBody WebHookEventDto eventDto) {
        boolean valid = checksumValidator.isValid(eventDto);
        LOGGER.info("EVENT {}", eventDto);
        LOGGER.info("SIGNATURE is {}", valid ? "VALID" : "INVALID");
        return ResponseEntity.ok().build();
    }

}
