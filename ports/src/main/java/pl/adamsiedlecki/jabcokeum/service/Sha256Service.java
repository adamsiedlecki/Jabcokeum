package pl.adamsiedlecki.jabcokeum.service;

public interface Sha256Service {

    String hashToString(String text);
    byte[] hashToBytes(String text);
}
