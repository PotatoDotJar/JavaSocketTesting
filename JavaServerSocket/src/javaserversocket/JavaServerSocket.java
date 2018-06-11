package javaserversocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Richard Nader <richard.nader@fynydd.com>
 */
public class JavaServerSocket {

    private final int port = 8082;

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

        private ObjectOutputStream out;
        private ObjectInputStream in;

        private boolean handshake = false;

        public ServerTread(Socket socket, int clientId) {
            this.socket = socket;
            this.clientId = clientId;

            System.out.println("Client " + clientId + " connected to server. "
                    + "IP: " + socket.getInetAddress());

        }

        @Override
        public void run() {

            try {

                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

            } catch (IOException e) {
                System.err.println("Error initializing in/out streams!");
                e.printStackTrace();
            }

            final String msgPrefix = "[Client " + clientId + "] ";

            while (threadRunning) {
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                out.writeObject(new DataPacket("Hell!", 2));
                            } catch (IOException ex) {
                                System.err.println("Error sending data packet!");
                                threadRunning = false;
                            }
                        }
                    };
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(task, 0, 1000);

            }
            }
        }

        public static void main(String[] args) {
            new JavaServerSocket();
        }
    }
