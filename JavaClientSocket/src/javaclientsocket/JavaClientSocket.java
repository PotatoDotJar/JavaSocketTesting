package javaclientsocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javaserversocket.DataPacket;
import javax.swing.JOptionPane;

/**
 *
 * @author Richard Nader <richard.nader@fynydd.com>
 */
public class JavaClientSocket {

    private String host = "pi.potatosaucevfx.com";
    private int port = 8082;

    private Socket client;

    public JavaClientSocket() {

        try {
            client = new Socket(host, port);

            new NetThread(client).start();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error opening client socked!",
                    "Socket Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private class NetThread extends Thread {

        private Socket socket;

        private ObjectInputStream in;
        private ObjectOutputStream out;

        private boolean clientThreadRunning = true;

        public NetThread(Socket socket) {
            try {
                this.socket = socket;

                this.out = new ObjectOutputStream(this.socket.getOutputStream());

                this.in = new ObjectInputStream(this.socket.getInputStream());


            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error init client IO!",
                        "Socket Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        public void run() {
            try {
                while (clientThreadRunning) {
                    DataPacket dataIn;

                    if ((dataIn = (DataPacket) in.readObject()) != null) {
                        System.out.println(dataIn);
                    }

                }
            } catch (IOException e) {
                System.err.println("Error reading stream!");
            } catch (ClassNotFoundException e) {
                System.err.println("Recived unknown object!");
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new JavaClientSocket();
    }

}
