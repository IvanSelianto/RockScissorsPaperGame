import java.security.SecureRandom;

public class RandomKey {
    public String generateRandomKey() {
        SecureRandom secureRandom = new SecureRandom();
        String key = "";
        for (int i = 0; i < 32; i++) {
            int randomInt = secureRandom.nextInt(42) + 48;
            while (randomInt < 65 && randomInt > 57) {
                randomInt = secureRandom.nextInt(42) + 48;
            }

            key += (char) randomInt;
        }


        return key;
    }
}
