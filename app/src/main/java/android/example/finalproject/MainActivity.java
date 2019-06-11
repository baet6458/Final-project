package android.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /* to do:
    Figure out how to make socket connection
    Figure out how to grab data roughly every minute
    Update layout per person

    Send implicit message
     */
    ArrayList<Member> machines =new ArrayList<Member>();
    int washerNum=3;
    int dryerNum=3;
    public final static String machineInfo="com.mycompany.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //replace with pulling data from the socket
        machines.add(new Member(1302,"Elijsha Baetiong","Baet6458@kettering.edu",0,"2487209551"));
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
    }

    public void loadWasher1(View view) {
        Intent intent= new Intent(MainActivity.this,ClickScreen.class);
        String message ="";
        for (Member x:machines) {
            if(x.getMachineNumber()==0)
                message=x.getInfo();

        }
        intent.putExtra(machineInfo,message);
        startActivity(intent);
    }
}
