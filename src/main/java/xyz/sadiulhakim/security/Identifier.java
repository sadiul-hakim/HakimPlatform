package xyz.sadiulhakim.security;

import xyz.sadiulhakim.util.SettingUtil;

public class Identifier {
    private Identifier() {
    }

    public static boolean validatePin(String raw) {
        String pin = (String) SettingUtil.getSetting().get(SettingUtil.ACCESS_PIN);
        String newPin = Crypto.hash(raw, Crypto.ALGORITHM_SHA256);
        return pin.equals(newPin);
    }
}
