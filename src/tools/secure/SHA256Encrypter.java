package tools.secure;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA256 encrypter to hash password
 * 
 * <p>
 * DISCLAIMER :
 * <p>
 * Those have been directly taken from the following website, I am not the
 * author of those methods
 * 
 * <p/>
 * <a href="https://www.geeksforgeeks.org/sha-256-hash-in-java/">Here is the
 * origin</a>
 * 
 *
 */
public class SHA256Encrypter {

	/** Simple constructor **/
	public SHA256Encrypter() {
	}

	/**
	 * Encrypt the input as a SHA256 byte array
	 * 
	 * @param input the string to encrypt
	 * @return the encrypted byte equivalent
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] getSHA(String input) throws NoSuchAlgorithmException {
		// Static getInstance method is called with hashing SHA
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// digest() method called
		// to calculate message digest of an input
		// and return array of byte
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Transforms the hash given as a byte array to a string
	 * 
	 * @param hash the byte array to transform as string
	 * @return the string equivalent of the byte array
	 */
	public String toHexString(byte[] hash) {
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}

}
