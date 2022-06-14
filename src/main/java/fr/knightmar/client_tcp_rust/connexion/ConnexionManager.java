package fr.knightmar.client_tcp_rust.connexion;

import fr.knightmar.client_tcp_rust.utils.ConnexionManagerInstanceSaver;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ConnexionManager {
    public Socket socket;
    private final BufferedReader in;
    private final PrintStream out;

    public ConnexionManager(InetAddress serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);

        ConnexionManagerInstanceSaver.setConnexionManager(this);
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("set");


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

    public BufferedReader getIn() {
        return in;
    }
}
