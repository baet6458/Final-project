package android.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Scanner;

public class EmptyMachine extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emptymachine);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.machineInfo);
        int value = Integer.parseInt(message);
        if(value>=3){
            TextView displayName= findViewById(R.id.machine);
            displayName.setText("Dryer:");
            message=String.valueOf(value-2);
        }
        else{
            value++;
            message=String.valueOf(value);
        }
        TextView displayedNum = findViewById(R.id.machineNumber);
        displayedNum.setText(message);
    }
}
