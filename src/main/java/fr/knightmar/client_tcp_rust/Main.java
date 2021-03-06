package fr.knightmar.client_tcp_rust;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class Main {
    public Socket socket;
    public Scanner scanner;

    public Main(InetAddress serverAddress, int serverPort) throws IOException {
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

    public static void main(String[] args) {
        String input = "";
        Scanner scannerIP = new Scanner(System.in);
        System.out.println("Enter the IP ans the port of the server (ex: 192.168.10.63:7878): ");
        String s = scannerIP.nextLine();
        String[] ipPort = s.split(":");

        // Checking if the IP and the port are valid.
        if (ipPort.length != 2) {
            System.out.println("Invalid Entry");
        } else {
            if (ipPort[0].matches("(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}")) {
                System.out.println("IP is valid");

                if (Integer.parseInt(ipPort[1]) <= 0 || Integer.parseInt(ipPort[1]) > 65535) {
                    System.out.println("Port is invalid");
                } else {
                    System.out.println("Port is valid");
                    try {
                        connect(ipPort[0], Integer.parseInt(ipPort[1]));
                    } catch (IOException e) {
                        System.err.println("Probl??me durant la connexion ...");
                    }
                }
            } else {
                System.out.println("IP is not valid");
            }
        }
    }

    public static void connect(String ip, int port) throws IOException {
        System.out.println("Connecting to IP: " + ip + " Port: " + port);

        Main client = new Main(
                InetAddress.getByName(ip),
                port);

        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
    }
}
