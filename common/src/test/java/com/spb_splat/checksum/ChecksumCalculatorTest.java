package com.spb_splat.checksum;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * Default comment.
 **/
public class ChecksumCalculatorTest {

    /**
     * <a href="https://www.fileformat.info/tool/hash.htm"> Online hashing
     *
     * @throws NoSuchAlgorithmException if algorithm not exist
     */
    @Test
    public void shouldGenerateValidHash() throws NoSuchAlgorithmException {
        String expected = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";
        ChecksumCalculator checksumCalculator
                = new BasicChecksumCalculator("SHA-256");
        String actual = checksumCalculator.calculateChecksum("test".getBytes());
        Assert.assertEquals(expected, actual);
    }
}
