package cpm.splat_spb;

import com.spb_splat.checksum.BasicChecksumCalculator;
import com.spb_splat.checksum.ChecksumCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class WebhookApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebhookApplication.class, args);
    }

    @Bean
    public ChecksumCalculator checksumCalculator() throws NoSuchAlgorithmException {
        return new BasicChecksumCalculator("SHA-256");
    }
}
