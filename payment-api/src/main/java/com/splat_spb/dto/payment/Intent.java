package com.splat_spb.dto.payment;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Payment intent.
 *
 * @author Niyaz_Bikbaev
 */
public enum Intent {
    ORDER;

    @JsonCreator
    public static Intent setValue(String value) {
        return Arrays.stream(Intent.values())
                     .filter(intent -> intent.toString().equalsIgnoreCase(value))
                     .findAny()
                     .orElse(null);
    }
}
