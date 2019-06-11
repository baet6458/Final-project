package android.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

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

    //Write to the sockets output stream
    public static void sendServerMessage(String message) {



    }

    private static class clientThread extends Thread {
        private volatile ArrayList<String> response = new ArrayList<String>();
        private volatile MainActivity main;
        private volatile Socket connectionPoint;

        public clientThread(MainActivity item) {
            main = item;
        }

        @Override
        public void run() {
            try {
                String servername = "3.15.19.93";
                connectionPoint = new Socket(servername, 12000);
                BufferedReader br = new BufferedReader(new InputStreamReader(connectionPoint.getInputStream()));
                String temp = br.readLine();
                while ((!temp.equals(""))) {
                    response.add(temp);
                    TunnelConnector.mainThread.updateMachineStates(temp);
                    temp = br.readLine();
                }
                connectionPoint.close();
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
}

