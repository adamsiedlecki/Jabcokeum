package pl.adamsiedlecki.jabcokeum.service;

import org.springframework.stereotype.Service;
import pl.adamsiedlecki.jabcokeum.exception.AlgorithmNotFoundException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class Sha256ServiceServiceImpl implements pl.adamsiedlecki.jabcokeum.service.Sha256Service {

    public String hashToString(String text) {
        return String.format("%064x", new BigInteger(1, hashToBytes(text)));
    }

    public byte[] hashToBytes(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new AlgorithmNotFoundException(e);
        }

        // Change this to UTF-16 if needed
        md.update(text.getBytes(StandardCharsets.UTF_8));
        return md.digest();
    }
}
