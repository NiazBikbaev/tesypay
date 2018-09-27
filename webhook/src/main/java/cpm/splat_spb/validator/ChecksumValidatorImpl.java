package cpm.splat_spb.validator;

import com.spb_splat.checksum.ChecksumCalculator;
import com.spb_splat.dto.WebHookEventDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * Default comment.
 **/
@Component
public class ChecksumValidatorImpl implements ChecksumValidator {

    private final ChecksumCalculator checksumCalculator;
    private final String secretWord;

    public ChecksumValidatorImpl(ChecksumCalculator checksumCalculator, @Value("${testpay.secretWord}") String secretWord) {
        this.checksumCalculator = checksumCalculator;
        this.secretWord = secretWord;
    }

    @Override
    public boolean isValid(WebHookEventDto eventDto) {
        String rawString = prepareForHashing(eventDto);
        String checksum = checksumCalculator.calculateChecksum(rawString.getBytes());
        return eventDto.getSignature().equals(checksum);
    }

    private String prepareForHashing(WebHookEventDto eventDto) {
        StringJoiner joiner = new StringJoiner("");
        joiner.add(eventDto.getCurrency());
        joiner.add(eventDto.getAmount());
        joiner.add(checksumCalculator.calculateChecksum(secretWord.getBytes(US_ASCII)).toUpperCase());
        joiner.add(eventDto.getId());
        joiner.add(Optional.ofNullable(eventDto.getExternalId()).orElse(""));
        joiner.add(eventDto.getStatus().toString().toLowerCase());
        return joiner.toString();
    }
}
