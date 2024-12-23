package xyz.sadiulhakim.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Crypto {
    public static final String ALGORITHM_SHA256 = "sha256";

    private Crypto() {
    }

    public static String hash(String raw, String alg) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest instance = MessageDigest.getInstance(alg);
            byte[] bytes = instance.digest(raw.getBytes(StandardCharsets.UTF_8));
            for (byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
        } catch (Exception ignore) {
        }
        return sb.toString();
    }

}
