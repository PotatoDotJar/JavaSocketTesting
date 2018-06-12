package javaserversocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Richard Nader <richard.nader@fynydd.com>
 */
public class JavaServerSocket {

    private final int port = 10200;

    private ServerSocket server;

    private boolean serverListenRunning = true;

    private int clientIdCounter = 0;

    public JavaServerSocket() {

        try {
            System.out.println("Starting server...");
            server = new ServerSocket(port);

            while (serverListenRunning) {
                new ServerTread(server.accept(), clientIdCounter++).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ServerTread extends Thread {

        private Socket socket;
        private int clientId;

        private boolean threadRunning = true;

        private PrintWriter out;
        private BufferedReader in;

        public ServerTread(Socket socket, int clientId) {
            this.socket = socket;
            this.clientId = clientId;

            try {

                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            } catch (IOException e) {
                System.err.println("Error initializing in/out streams!");
                e.printStackTrace();
            }

            System.out.println("Client " + clientId + " connected to server. "
                    + "IP: " + socket.getInetAddress());
        }

        @Override
        public void run() {

            final String msgPrefix = "[Client " + clientId + "] ";

            while (threadRunning) {
                //Scanner consoleIn = new Scanner(System.in);

                //int value = consoleIn.nextInt();
                System.out.println(msgPrefix + "Sent >34/31/2");

                out.println("34/31/2");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JavaServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        }
    }

    public static void main(String[] args) {
        new JavaServerSocket();
    }
}
