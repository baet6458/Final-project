package android.example.finalproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    /* to do:
    Figure out how to grab data roughly every minute

    Send implicit message
     */
    public ArrayList<Member> machines = new ArrayList<Member>();
    int washerNum = 3;
    int dryerNum = 3;
    public final static String machineInfo = "com.mycompany.myfirstapp.MESSAGE";
    private ArrayList<Member> updateQueue = new ArrayList<Member>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TunnelConnector.startConnection(this);
        TunnelConnector.sendServerMessage("s,");

        int id;
        washerNum=3;
        dryerNum=3;
        for (int i = 0; i < machines.size(); i++) {
            int machineNum = machines.get(i).getMachineNumber();

            switch (machineNum) {
                case 0:
                    id = R.id.Machine0;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    washerNum=washerNum-1;
                    break;
                case 1:
                    id = R.id.Machine1;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    washerNum--;
                    break;
                case 2:
                    id = R.id.Machine2;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    washerNum=washerNum-1;
                    break;
                case 3:
                    id = R.id.Machine3;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    dryerNum--;
                    break;
                case 4:
                    id = R.id.Machine4;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    dryerNum--;
                    break;
                case 5:
                    id = R.id.Machine5;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    dryerNum--;
                    break;
            }
        }
        TextView washNum = findViewById(R.id.washerNumber);
        washNum.setText(Integer.toString(washerNum));
        TextView dryers = findViewById(R.id.dryerNumber);
        dryers.setText(Integer.toString(dryerNum));


    }
    @Override
    protected void onResume() {

        super.onResume();
        System.out.println("we are here");
        TunnelConnector.sendServerMessage("s");


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        TunnelConnector.endConnection();
    }

    public void loadMachine(View view) {
        Intent intent = new Intent(MainActivity.this, ClickScreen.class);
        String value = view.getTag().toString();
        String message = null;
        System.out.println(value);
        for (Member x : machines) {
            if (x.getMachineNumber() == Integer.parseInt(value)) {
                message = x.getInfo();
            }
        }
        //if message is empty
        if (message == null) {
            Intent noclass = new Intent(MainActivity.this, EmptyMachine.class);
            message = value;
            noclass.putExtra(machineInfo, message);
            startActivity(noclass);

        } else {
            intent.putExtra(machineInfo, message);
            startActivity(intent);
        }

    }

    public synchronized void updateMachineStates(String updateStr) {

        System.out.println("This is the string "+updateStr);

        if (!updateStr.equals("")) {
            String[] temp = updateStr.split("\n");
            for(String x: temp){
                if(x.equals("end")){
                    System.out.println("WE are here");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            machines.clear();
                            int id;
                            id = R.id.Machine0;
                            findViewById(id).setBackgroundColor(getResources().getColor(R.color.Green));
                            id = R.id.Machine1;
                            findViewById(id).setBackgroundColor(getResources().getColor(R.color.Green));
                            id = R.id.Machine2;
                            findViewById(id).setBackgroundColor(getResources().getColor(R.color.Green));
                            id = R.id.Machine3;
                            findViewById(id).setBackgroundColor(getResources().getColor(R.color.Green));
                            id = R.id.Machine4;
                            findViewById(id).setBackgroundColor(getResources().getColor(R.color.Green));
                            id = R.id.Machine5;
                            findViewById(id).setBackgroundColor(getResources().getColor(R.color.Green));
                            System.out.println("updated to green");

                            dryerNum=3;
                            washerNum=3;
                            for (int i = 0; i < updateQueue.size(); i++) {
                                int machineNum = updateQueue.get(i).getMachineNumber();

                                switch (machineNum) {
                                    case 0:
                                        id = R.id.Machine0;

                                        findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                                        System.out.println("updated to red 0");
                                        washerNum--;
                                        break;
                                    case 1:
                                        id = R.id.Machine1;
                                        findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                                        System.out.println("updated to red 1");
                                        washerNum--;
                                        break;
                                    case 2:
                                        id = R.id.Machine2;
                                        findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                                        System.out.println("updated to red 2");
                                        washerNum--;
                                        break;
                                    case 3:
                                        id = R.id.Machine3;
                                        findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                                        System.out.println("updated to red 3");
                                        dryerNum--;
                                        break;
                                    case 4:
                                        id = R.id.Machine4;
                                        findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                                        System.out.println("updated to red 4");
                                        dryerNum--;
                                        break;
                                    case 5:
                                        id = R.id.Machine5;
                                        findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                                        System.out.println("updated to red 5");
                                        dryerNum--;
                                        break;
                                }
                            }
                            TextView washNum = findViewById(R.id.washerNumber);
                            washNum.setText(Integer.toString(washerNum));
                            TextView dryers = findViewById(R.id.dryerNumber);
                            dryers.setText(Integer.toString(dryerNum));
                            machines.addAll(updateQueue);
                            updateQueue.clear();
                        }
                    });

                }else {
                    String[] y = x.split(",");
                    updateQueue.add(new Member(Integer.parseInt(y[0]), y[1], y[2], Integer.parseInt(y[3]), y[4]));
                }
            }

        }



    }

}
