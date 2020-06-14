import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class RandomKey {
    public String generateRandomKey() {
        byte[] bytes = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        byte[] key = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            key = md.digest(bytes);
            key = Arrays.copyOf(key, 16);
        } catch (NoSuchAlgorithmException e) {
        }

        return bytesToHex(key);
    }

    private String bytesToHex(byte[] bytes) {

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
