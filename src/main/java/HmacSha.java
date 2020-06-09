import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSha {

    private String key;
    private String value;
    private String shaType;

    public String generateHmacSha() {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), shaType);
            Mac mac = Mac.getInstance(shaType);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(value.getBytes("UTF-8"));
            byte[] hexArray = {(byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6',
                    (byte) '7', (byte) '8', (byte) '9', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f'};

            byte[] hexChars = new byte[rawHmac.length * 2];
            for (int j = 0; j < rawHmac.length; j++) {
                int v = rawHmac[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public static class Builder {
        private HmacSha hmacSha;

        public Builder() {
            hmacSha = new HmacSha();
        }

        public Builder setKey(String key) {
            hmacSha.key = key;
            return this;
        }

        public Builder setValue(String value) {
            hmacSha.value = value;
            return this;
        }

        public Builder setShaType(String shaType) {
            hmacSha.shaType = shaType;
            return this;
        }

        public HmacSha build() {
            return hmacSha;
        }

    }
}