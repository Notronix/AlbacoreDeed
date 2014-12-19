package com.notronix.albacore.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public abstract class EncryptionUtils
{
    private static final int pswdIterations = 65536;
    private static final int keySize = 256;

    public static String encrypt(String plainText) throws CryptoException
    {
        byte[] salt = getSalt(plainText);
        PBEKeySpec spec = new PBEKeySpec(plainText.toCharArray(), salt, pswdIterations, keySize);
        SecretKeySpec secret = getSecretKey(spec);

        return encrypt(getCipher(secret, Cipher.ENCRYPT_MODE), salt);
    }

    public static String decrypt(String encryptedText, String key) throws CryptoException
    {
        byte[] salt = getSalt(key);
        PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), salt, pswdIterations, keySize);
        SecretKeySpec secret = getSecretKey(spec);

        return decrypt(getCipher(secret, Cipher.DECRYPT_MODE), Base64.decodeBase64(encryptedText));
    }

    private static String decrypt(Cipher cipher, byte[] crypto) throws CryptoException
    {
        try
        {
            return new String(cipher.doFinal(crypto));
        }
        catch (IllegalBlockSizeException | BadPaddingException e)
        {
            throw new CryptoException("An error occurred trying to decrypt.", e);
        }
    }

    private static String encrypt(Cipher cipher, byte[] salt) throws CryptoException
    {
        try
        {
            return new Base64().encodeAsString(cipher.doFinal(salt));
        }
        catch (IllegalBlockSizeException | BadPaddingException e)
        {
            throw new CryptoException("An error occurred trying to encrypt.", e);
        }
    }

    private static Cipher getCipher(SecretKeySpec secret, int mode) throws CryptoException
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(mode, secret);

            return cipher;
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e)
        {
            throw new CryptoException("An error occurred trying to get encryption cipher.", e);
        }
    }

    private static SecretKeySpec getSecretKey(PBEKeySpec keySpec) throws CryptoException
    {
        try
        {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey secretKey = factory.generateSecret(keySpec);

            return new SecretKeySpec(secretKey.getEncoded(), "AES");
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new CryptoException("An error occurred getting secret key.", e);
        }
    }

    private static byte[] getSalt(String key) throws CryptoException
    {
        try
        {
            return key.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException uee)
        {
            throw new CryptoException("An error occurred getting encryption salt.", uee);
        }
    }
}
