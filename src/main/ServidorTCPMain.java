package main;

import core.ServidorTCP;

public class ServidorTCPMain {
    public static void main(String[] args) {
        ServidorTCP servidorTCP = new ServidorTCP();
        servidorTCP.start();
    }
}
