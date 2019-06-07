package android.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Scanner;

public class ClickScreen extends Activity {

    private String machineNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clickscreen);
        Intent intent = getIntent();
        String message =intent.getStringExtra(MainActivity.machineInfo);
        Scanner messageScan = new Scanner(message);
        int i=0;
        while(messageScan.hasNext()){
            switch(i){
                case 1:
                    machineNumber=messageScan.next();
            }
            i++;
        }

        TextView displayedNum=findViewById(R.id.machineNumber);
        displayedNum.setText("5");


    }
}
