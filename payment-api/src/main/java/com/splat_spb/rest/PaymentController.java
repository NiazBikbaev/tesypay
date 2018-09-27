package com.splat_spb.rest;

import com.splat_spb.dto.payment.PaymentDto;
import com.splat_spb.exception.ApplicationApiException;
import com.splat_spb.service.PaymentService;
import org.quartz.SchedulerException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.splat_spb.dto.error.ErrorCode.INVALID_REQUEST;

/**
 * TestPay REST API.
 *
 * @author Niyaz_Bikbaev
 **/
@RestController
@RequestMapping("/payments")
@PreAuthorize("#oauth2.hasScope('payments')")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentDto createPayment(@RequestBody @Valid PaymentDto payment,
                                    BindingResult bindingResult) throws SchedulerException {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String defaultMessage = fieldError.getDefaultMessage();
            throw new ApplicationApiException(defaultMessage, INVALID_REQUEST);
        }
        PaymentDto paymentDto = paymentService.save(payment);
        paymentService.processPayment(paymentDto);
        return paymentDto;
    }

}
