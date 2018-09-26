package com.spb_splat.checksum;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Default comment.
 **/
public class BasicChecksumCalculator implements ChecksumCalculator {
    private final MessageDigest messageDigest;

    public BasicChecksumCalculator(String algorithm) throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance(algorithm);
    }

    @Override
    public String calculateChecksum(byte[] rawBytes) {
        byte[] hashedBytes = messageDigest.digest(rawBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
