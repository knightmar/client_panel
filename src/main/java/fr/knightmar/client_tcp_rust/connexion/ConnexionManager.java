package fr.knightmar.client_tcp_rust.connexion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ConnexionManager {
    public Socket socket;
    public Scanner scanner;

    public ConnexionManager(InetAddress serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        String input;
        while (true) {
            input = scanner.nextLine();
            System.out.println(input);
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();
            if (input.equals("exit")) {
                System.out.println("Bye bye");
                out.println(input);
                out.flush();
                out.close();
                break;
            }

        }
    }
}
