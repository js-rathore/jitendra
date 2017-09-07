/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
7.    This program tests the AES cipher. Usage:
8.    java AESecryption -genkey keyfile
9.    java AESecryption -encrypt plaintext encrypted keyfile
10.    java AESecryption -decrypt encrypted decrypted keyfile
11. */
/**
 *
 * @author Navneet
 */
public class AESecryption
{

    
    /**
     * 
     * @param tempkey
     */
    public void genKey(String tempkey)
          {
            try
            {
              KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom random = new SecureRandom();
            keygen.init(random);
            SecretKey key = keygen.generateKey();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(tempkey));
            out.writeObject(key);
            out.close();
            }
            catch(Exception ex)
            {
                System.out.println("Error while generating Key...");
            }
          }

    /**
     * 
     * @param source
     * @param destination
     * @param tempkey
     */
    public void encrypt(String source,String destination,String tempkey)
    {
        ObjectInputStream keyIn = null;
        try {
            int mode = Cipher.ENCRYPT_MODE;
            keyIn = new ObjectInputStream(new FileInputStream(tempkey));
            Key key = (Key) keyIn.readObject();
            keyIn.close();
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, key);
            crypt(in, out, cipher);
           
            in.close();
            out.close();
             File f=new File(source);
            System.out.println("Status::"+f.exists());
            if(f.exists())
            {
            f.delete();
            }
        }
        catch (Exception ex)
        {
        }
        finally
        {
            try
            {
                keyIn.close();
            }
            catch (IOException ex)
            {

            }
        }

    }

    /**
     * 
     * @param source
     * @param destination
     * @param tempKey
     */
    public void decrypt(String source,String destination,String tempKey)
    {
        ObjectInputStream keyIn = null;
        try {
            int mode;
            mode = Cipher.DECRYPT_MODE;
            keyIn = new ObjectInputStream(new FileInputStream(tempKey));
            Key key = (Key) keyIn.readObject();
            keyIn.close();
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, key);
            crypt(in, out, cipher);in.close();
            out.close();
            File f = new File(source);
            f.delete();
        }
        catch (Exception ex) {}
        finally
        {try
            {keyIn.close();}
            catch (Exception ex){}
        }
    }

    /**
     * 
     * @param in
     * @param out
     * @param cipher
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static void crypt(InputStream in, OutputStream out, Cipher cipher)
            throws IOException, GeneralSecurityException {
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];

        int inLength = 0;
        boolean more = true;
        while (more) {
            inLength = in.read(inBytes);
            if (inLength == blockSize) {
                int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
                out.write(outBytes, 0, outLength);
            } else {
                more = false;
            }
        }
        if (inLength > 0) {
            outBytes = cipher.doFinal(inBytes, 0, inLength);
        } else {
            outBytes = cipher.doFinal();
        }
        out.write(outBytes);
    }
}
