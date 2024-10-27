package encryption;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionGen {
	private static final String ALGORITHM_TYPE = "AES";
	
	public static SecretKey generateKey() throws Exception{
		KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM_TYPE);
		keyGen.init(128);
		return keyGen.generateKey();
	}
	
	public static byte[] encrypt(String data, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM_TYPE);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data.getBytes());
	}
	
	public static String decrypt(byte[] encryptedData, SecretKey key) throws Exception{
		Cipher cipher = Cipher.getInstance(ALGORITHM_TYPE);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(encryptedData));
	}
}
