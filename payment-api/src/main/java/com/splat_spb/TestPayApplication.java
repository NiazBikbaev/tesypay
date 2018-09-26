package com.splat_spb;

import com.spb_splat.checksum.ChecksumCalculator;
import com.spb_splat.checksum.BasicChecksumCalculator;
import com.spb_splat.dto.WebHookEventDto;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;

@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
public class TestPayApplication {

    @Autowired
    private Collection<JobListener> jobListeners;

    public static void main(String[] args) {
        SpringApplication.run(TestPayApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder,
                                     @Value("${testpay.webHook.readTimeout}") int readTimeout,
                                     @Value("${testpay.webHook.connectionTimeout}") int connectionTimeout) {
        return restTemplateBuilder.setConnectTimeout(connectionTimeout)
                                  .setReadTimeout(readTimeout)
                                  .build();
    }

    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        return schedulerFactoryBean -> schedulerFactoryBean.setGlobalJobListeners(jobListeners.toArray(new JobListener[0]));
    }

    @Bean
    public ChecksumCalculator checksumCalculator() throws NoSuchAlgorithmException {
        return new BasicChecksumCalculator("SHA-256");
    }


}
