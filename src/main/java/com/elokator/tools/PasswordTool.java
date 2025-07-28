package com.elokator.tools;

import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class PasswordTool {
    private static final Logger LOG = LoggerFactory.getLogger(PasswordTool.class);

    private static final String SECRET_KEY = "/yyhAQQGCO/aTJ3m7c74N59g6oiXQYkFhAIltchMSwo=";

    private static SecretKeySpec getKeyFromString(final String myKey) throws Exception {
        final MessageDigest sha = MessageDigest.getInstance("SHA-256");
        final byte[] key = sha.digest(myKey.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(key, "AES");
    }

    public static String encryptPassword(final String password) throws AppCoreException {
        try {
            final SecretKeySpec secretKey = getKeyFromString(SECRET_KEY);
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final byte[] encryptedPassword = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedPassword);
        } catch (Exception e) {
            LOG.error("Error while encrypting password: {}", password, e);
            throw new AppCoreException(ApiError.CAN_NOT_ENCRYPT_PASSWORD);
        }
    }

    public static String decryptPassword(final String encryptedPassword) throws AppCoreException {
        try {
            final SecretKeySpec secretKey = getKeyFromString(SECRET_KEY);
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword);
            final byte[] decryptedPassword = cipher.doFinal(decodedPassword);
            return new String(decryptedPassword, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOG.error("Error while decrypting password: {}", encryptedPassword, e);
            throw new AppCoreException(ApiError.CAN_NOT_DECRYPT_PASSWORD);
        }
    }
}
