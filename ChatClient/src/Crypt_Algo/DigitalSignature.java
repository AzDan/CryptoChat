package Crypt_Algo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;


public class DigitalSignature {
    public void signDigitally(String enc){
        try {
            // Get instance and initialize a KeyPairGenerator object.
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);

            // Get a PrivateKey from the generated key pair.
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();

            // Get an instance of Signature object and initialize it.
            Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
            signature.initSign(privateKey);

            // Supply the data to be signed to the Signature object
            // using the update() method and generate the digital
            // signature.
            byte[] bytes = enc.getBytes();
            signature.update(bytes);
            byte[] digitalSignature = signature.sign();

            // Save digital signature and the public key to a file.
            Files.write(Paths.get("signature"), digitalSignature);
            Files.write(Paths.get("publickey"), keyPair.getPublic().getEncoded());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean verifySignature(String crypt){
        try {
            byte[] publicKeyEncoded = Files.readAllBytes(Paths.get("publickey"));
            byte[] digitalSignature = Files.readAllBytes(Paths.get("signature"));

            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyEncoded);
            KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");

            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
            signature.initVerify(publicKey);

            byte[] bytes = crypt.getBytes();
            signature.update(bytes);

            boolean verified = signature.verify(digitalSignature);
            if (verified) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}