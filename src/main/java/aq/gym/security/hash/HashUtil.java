package aq.gym.security.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class HashUtil {

	public static String getHash(String target, String hashAlg) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashAlg);

            byte[] sourceBytes = target.getBytes();

            byte[] digest = messageDigest.digest(sourceBytes);

            return new String(HexFormat.of().formatHex(digest).getBytes(), StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
