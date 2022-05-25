package fr.knightmar.client_tcp_rust.connexion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ConnexionManager {
    public Socket socket;
//    public Scanner scanner;

    public ConnexionManager(InetAddress serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
//        this.scanner = new Scanner(System.in);
    }

    public void sendMessage(String message) throws IOException {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(message);
            out.flush();
            if (message.equals("exit")) {
                System.out.println("Bye bye");
                out.println(message);
                out.flush();
                out.close();
                System.exit(0);

        }
    }
}
