/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

/**
 *
 * @author M-5
 */
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

public class AESencrp {

    private static final String ALGO = "AES";
//    private static final byte[] keyValue = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

//    public static void main(String[] args) {
//        try {
////            System.out.println("original text "+"");
////            
////             String encrtext = encrypt(keyValue,"2");
////             System.out.println("encrtext "+encrtext);
////             
////             String  decrtext =  decrypt(keyValue, encrtext);
////             System.out.println("decrtext "+decrtext);
//        } catch (Exception ex) {
//            Logger.getLogger(AESencrp.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public static String encrypt(byte[] keyValue, String Data) throws Exception {
        Key key = generateKey(keyValue);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key );
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(byte[] keyValue, String encryptedData) throws Exception {
        Key key = generateKey(keyValue);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private static Key generateKey(byte[] keyValue) throws Exception {
        Key key = new SecretKeySpec(keyValue, "AES");
        return key;
    }

}
