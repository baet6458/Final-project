package android.example.finalproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class TunnelConnector {
    private static volatile MainActivity mainThread = null;
    private static Thread socketThread;

    //Store the main activity and start a connection if the connection does not already exist.
    // This connection will go in a new thread
    public static void startConnection(MainActivity activity) {
        if (socketThread != null) {
            return;
        }
        //Create connection
        socketThread = new clientThread(activity);
        socketThread.start();
    }

    public static void sendServerMessage(String message)  {
        clientThread.enqueueMessage(message);
    }

    private static class clientThread extends Thread {
        private volatile ArrayList<String> response = new ArrayList<String>();
        private volatile MainActivity main;
        private static volatile Socket connectionPoint;
        private static volatile ArrayList<String> messageQueue = new ArrayList();
        private static volatile PrintStream outStream;

        public clientThread(MainActivity item) {
            main = item;
        }

        @Override
        public void run() {
            try {
                String servername = "10.0.48.47";
                connectionPoint = new Socket(servername, 12345);
                outStream = new PrintStream(connectionPoint.getOutputStream());
                Scanner scanner = new Scanner(connectionPoint.getInputStream());
                System.out.println("Hello world");
                while (scanner.hasNextLine()) {
                    System.out.println("we are in the statement");
                    String incomingMessage = scanner.nextLine();
                    clearMessageQueue();
                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        private static synchronized void clearMessageQueue() {
            ArrayList<String> queue = getMessageQueue();
            System.out.println("Queue is length "+ queue.size());
            for (String message : queue) {
                outStream.println(message);
            }
            getMessageQueue().clear();
        }

        private static synchronized void enqueueMessage(String message) {
            getMessageQueue().add(message);
        }

        private static synchronized ArrayList<String> getMessageQueue() {
            return messageQueue;
        }

        public ArrayList<String> getResponse() {
            return response;
        }
    }
}

