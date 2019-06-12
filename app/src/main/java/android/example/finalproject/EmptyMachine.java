package android.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class EmptyMachine extends Activity {

    private int machineNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emptymachine);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.machineInfo);
        machineNum = Integer.parseInt(message);
        System.out.println(machineNum);
        if(machineNum>=3){
            TextView displayName= findViewById(R.id.machine);
            displayName.setText("Dryer:");
            message=String.valueOf(machineNum-2);
        }
        else{
            int extra=machineNum+1;
            message=String.valueOf(extra);
        }
        TextView displayedNum = findViewById(R.id.machineNumber);
        displayedNum.setText(message);
    }



    //need to make this work
    public void submitNewUser(View view) {
        //grab the edit button data
        EditText bondNum = findViewById(R.id.bondText);
        String result = bondNum.getText().toString();

        System.out.println(machineNum);
        TunnelConnector.sendServerMessage("a,"+result+","+(machineNum)+",");

        // go back to main menu
        finish();
    }
}
