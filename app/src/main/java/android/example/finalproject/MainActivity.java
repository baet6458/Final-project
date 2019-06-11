package android.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public ArrayList<Member> machines =new ArrayList<Member>();
    int washerNum=3;
    int dryerNum=3;
    public final static String machineInfo="com.mycompany.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //replace with pulling data from the socket
        machines.add(new Member(1302,"Elijsha Baetiong","Baet6458@kettering.edu",1,"9999999999"));
        machines.add(new Member(1302,"Elijsha Baetiong","Baet6458@kettering.edu",3,"9999999999"));
        machines.add(new Member(1302,"Elijsha Baetiong","Baet6458@kettering.edu",4,"9999999999"));

        //go through the loop and set colors of backgrounds
        int id=0;

        for(int i=0;i<machines.size();i++){
            int machineNum=machines.get(i).getMachineNumber();

            switch(machineNum){
                case 0:
                    id=R.id.Machine0;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    washerNum--;
                    break;
                case 1:
                     id=R.id.Machine1;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    washerNum--;
                    break;
                case 2:
                    id=R.id.Machine2;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    washerNum--;
                    break;
                case 3:
                    id=R.id.Machine3;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    dryerNum--;
                    break;
                case 4:
                    id=R.id.Machine4;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    dryerNum--;
                    break;
                case 5:
                    id=R.id.Machine5;
                    findViewById(id).setBackgroundColor(getResources().getColor(R.color.red));
                    dryerNum--;
                    break;
            }
        }
        TextView washNum = findViewById(R.id.washerNumber);
        washNum.setText( Integer.toString(washerNum));
        TextView dryers =findViewById(R.id.dryerNumber);
        dryers.setText(Integer.toString(dryerNum));

        new Thread(new clientThread()).start();


    }

    public void loadMachine(View view) {
        Intent intent= new Intent(MainActivity.this,ClickScreen.class);
        String value=view.getTag().toString();
        String message=null;
        for (Member x:machines) {
            if(x.getMachineNumber()==Integer.parseInt(value)) {
                message = x.getInfo();
            }
        }
        //if message is empty
        if(message==null){
            Intent noclass = new Intent(MainActivity.this,EmptyMachine.class);
            message=value;
            noclass.putExtra(machineInfo,message);
            startActivity(noclass);

        }else{
            intent.putExtra(machineInfo,message);
            startActivity(intent);
        }

    }
    public  ArrayList<Member> getlist(){
        return machines;
    }

}

class clientThread implements Runnable {
        @Override
        public void run() {
            try {
                String servername="10.0.47.255";
                Socket trial = new Socket(servername, 12000);
                while(true){
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                            1024);
                    InputStream inputStream = trial.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(trial.getInputStream()));
                    String translatedMessage = br.readLine();
                    if(translatedMessage == "empty"){
                        //do nothing
                    }else{

                    }


                }


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true){

            }
        }
    }