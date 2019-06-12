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

    public static void endConnection(){
        try {
            clientThread.connectionPoint.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendServerMessage(String message)  {
        ChatThread.enqueueMessage(message);
    }

    private static class clientThread extends Thread {
        private volatile ArrayList<String> response = new ArrayList<String>();
        private volatile MainActivity main;
        private static volatile Socket connectionPoint;

        public clientThread(MainActivity item) {
            main = item;
        }

        @Override
        public void run() {
            try {
                String servername = "3.15.19.93";
                connectionPoint = new Socket(servername, 12000);
                new ChatThread().start();
                Scanner scanner = new Scanner(connectionPoint.getInputStream());
                while (scanner.hasNextLine()) {
                    String incomingMessage = scanner.nextLine();
                    main.updateMachineStates(incomingMessage);
                }
                System.exit(-1);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        public ArrayList<String> getResponse() {
            return response;
        }
    }
    private static class ChatThread extends Thread {
        private static volatile ArrayList<String> messageQueue = new ArrayList();
        private static volatile PrintStream outStream;

        @Override
        public void run() {
            try {
                outStream = new PrintStream(clientThread.connectionPoint.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (true) {
                clearMessageQueue();
            }
        }

        private static synchronized void clearMessageQueue() {
            ArrayList<String> queue = getMessageQueue();
            for (String message : queue) {
                outStream.print(message);
            }
            getMessageQueue().clear();
        }

        private static synchronized void enqueueMessage(String message) {
            getMessageQueue().add(message);
        }

        private static synchronized ArrayList<String> getMessageQueue() {
            return messageQueue;
        }
    }
}

