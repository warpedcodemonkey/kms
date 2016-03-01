package com.demo.services;

import org.apache.http.util.ByteArrayBuffer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


/**
 * Created by johnb on 2/29/16.
 */
@Service
public class EncryptionService {


    public void generateKey(String name) throws Exception {
        final int keySize = 2048;
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(keySize);

        KeyPair keyPair = generator.generateKeyPair();
        RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        this.write("/tmp/ssh/" + name + ".pem", priv, "RSA PRIVATE KEY");
        this.write("/tmp/ssh/" + name + ".pub.pem", publicKey, "RSA PUBLIC KEY");
//        String sshKey = convertToSSHAuthKey(publicKey).toString();
//
//
//        String sshRsa = new String(Base64.encode(sshKey.getBytes()));
        String sshRsa = generateRSAPublicKeyFormat(publicKey);
        this.writeTextToFile("/tmp/ssh/" + name + ".pub", sshRsa);
        System.out.println(sshRsa);


    }


    private String generateRSAPublicKeyFormat(RSAPublicKey key) throws Exception{

        byte[] data = key.getEncoded();

        String base64Encoded = new String(Base64.toBase64String(data));
        return "ssh-rsa " + base64Encoded;


    }


    private void write(String filename, Key key, String description) throws IOException {
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
        try {
            pemWriter.writeObject(new PemObject(description, key.getEncoded()));
        } finally {
            pemWriter.close();
        }
    }

    private void writeTextToFile(String fileName, String text) throws Exception{
        File file = new File(fileName);

        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(text);
        bw.close();
        writer.close();
    }
}
