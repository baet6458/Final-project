package android.example.finalproject;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        TunnelConnector.startConnection(this);
        /*
        if (!trial.isEmpty() && trial.get(0).toLowerCase().equals("empty")) {
            // if the members list is empty
            findViewById(R.id.Machine0).setBackgroundColor(getResources().getColor(R.color.red));
        } else {
            for (String x : trial) {
                String[] temp = x.split(",");
                Log.d("CREATION", "we are checking here");
                machines.add(new Member(Integer.parseInt(temp[0]), temp[1], temp[2],
                        Integer.parseInt(temp[3]), temp[4]));
            }
        }

        */
        //go through the loop and set colors of backgrounds
        int id;

        for (int i = 0; i < machines.size(); i++) {
            int machineNum = machines.get(i).getMachineNumber();

            switch (machineNum) {
                case 0:
                    id = R.id.Machine0;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    washerNum--;
                    break;
                case 1:
                    id = R.id.Machine1;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    washerNum--;
                    break;
                case 2:
                    id = R.id.Machine2;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    washerNum--;
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

    public void loadMachine(View view) {
        Intent intent = new Intent(MainActivity.this, ClickScreen.class);
        String value = view.getTag().toString();
        String message = null;
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

    }

}
