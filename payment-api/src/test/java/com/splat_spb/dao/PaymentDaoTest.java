package com.splat_spb.dao;

import com.splat_spb.dto.payment.PaymentDto;
import com.splat_spb.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Default comment.
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@Transactional
public class PaymentDaoTest {
    @Autowired
    private PaymentDao paymentDao;

    @Test
    @Rollback
    public void saveTest() {
        PaymentDto paymentDto = TestUtils.populate(PaymentDto::new, true);
        paymentDto.setId(null);
        paymentDao.save(paymentDto);
        PaymentDto savedDto = paymentDao.findAll().iterator().next();
        assertNotNull(savedDto.getId());
        assertNotNull(savedDto.getCreateTime());
        assertEquals(paymentDto.getPayer(), savedDto.getPayer());
        assertEquals(paymentDto.getState(), savedDto.getState());
        assertEquals(paymentDto.getIntent(), savedDto.getIntent());
        assertEquals(paymentDto.getTransaction(), savedDto.getTransaction());
        assertEquals(paymentDto.getNotificationUrl(), savedDto.getNotificationUrl());
    }
}
