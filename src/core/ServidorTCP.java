package core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorTCP implements Runnable{
    PaqueteReq paqueteReq;
    private ArrayList arreglo = new ArrayList<String>();

    public void start(){
        int puerto = 7000;
        try {
            ServerSocket server = new ServerSocket(puerto);
            System.out.println("Server escuchando...");
            while (true) {
                Socket socket = server.accept();
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                ObjectInputStream stream = new ObjectInputStream(socket.getInputStream());
                paqueteReq = (PaqueteReq) stream.readObject();
                System.out.println("Comando recibido: " + paqueteReq.getComando());
                System.out.println("Elemento recibido: " + paqueteReq.getElemento());
                Thread hilo = new Thread(this);
                hilo.start();
                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if(paqueteReq.getComando().equals("add"))
            if(!arreglo.contains(paqueteReq.getElemento()))
                arreglo.add(paqueteReq.getElemento());
            else
                System.out.println("El elemento " + paqueteReq.getElemento() + " ya existe...");
        else{
            if(arreglo.size() > 0){
                int index = arreglo.indexOf(paqueteReq.getElemento());
                if(index >= 0) {
                    arreglo.remove(index);
                    System.out.println("El elemento " + paqueteReq.getElemento() + " se elimino...");
                }
            }
        }
        System.out.println("Arreglo = " + arreglo);
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    }
}
