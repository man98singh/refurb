package com.houstondirectauto.refurb.util;

import java.security.SecureRandom;

public class GenerateCodeUtil {
    public static int generateCode() {
        SecureRandom random = new SecureRandom();
        return 100000 + random.nextInt(900000);
    }
}
