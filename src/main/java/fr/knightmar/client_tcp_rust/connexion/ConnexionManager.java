package fr.knightmar.client_tcp_rust.connexion;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ConnexionManager {
    public Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ConnexionManager(InetAddress serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);


        Thread thread = new Thread(() -> {
            while (true){
                try {
                    out = new DataOutputStream(socket.getOutputStream());
                    in = new DataInputStream(socket.getInputStream());
                    if (!in.readUTF().equals("")){
                        System.out.println(in.readUTF());
                    }

                    getLogs();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }


    public void sendMessage(String command) throws IOException {
        PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true);
        writer.println(command);
        writer.flush();
        if (command.equals("exit")) {
            System.out.println("Bye bye");
            writer.println(command);
            writer.flush();
            writer.close();
            out.flush();
            out.close();
            in.close();
            this.socket.close();
            System.exit(0);

        }
    }
    public void getLogs() throws IOException {
        FileOutputStream fos = new FileOutputStream("logs.txt");
        fos.write(in.readUTF().getBytes());
    }
}
