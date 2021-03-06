package android.example.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClickScreen extends Activity {

    private String mphoneNumber;

    private String mEmail;
    private String firstName;
    private int machinceNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clickscreen);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.machineInfo);
        Scanner messageScan = new Scanner(message);
        String num="";
        machinceNum = Integer.parseInt(messageScan.next());
        System.out.println(machinceNum);
        if(machinceNum>=3){
            TextView displayName= findViewById(R.id.machine);
            displayName.setText("Dryer:");
            num = String.valueOf(machinceNum-2);
        }
        else{
            int extra=machinceNum+1;
            num=String.valueOf(extra);
        }
        TextView displayedNum = findViewById(R.id.machineNumber);
        displayedNum.setText(num);

        TextView displayName = findViewById(R.id.name);

        firstName = messageScan.next();

        displayName.setText(getString(R.string.userName) + firstName + " " + messageScan.next());

        TextView displayBond = findViewById(R.id.bondNumber);
        displayBond.setText(getString(R.string.bondNumber) + messageScan.next());

        mphoneNumber = messageScan.next();

        mEmail = messageScan.next();


    }


    public void clearScreen(View view) {

        //create an alert diaglog box
        AlertDialog.Builder resetCheck = new AlertDialog.Builder(this);
        resetCheck.setMessage("Are you sure?")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do Nothing
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(machinceNum);
                        TunnelConnector.sendServerMessage("r,"+(machinceNum)+",");
                        // go back to main menu
                        finish();
                    }
                });
        AlertDialog dialog = resetCheck.create();
        dialog.show();

    }

    //have an implicit intent to text user
    public void textUser(View view) {
        // Create the text message with a string
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:"));
        sendIntent.putExtra("address", mphoneNumber);
        sendIntent.putExtra("sms_body", firstName + ", please move your clothes.");
        //start the intent
        startActivity(sendIntent);
    }

    public void emailUser(View view) {
        String[] address = {mEmail};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("*/*");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Laundry system");
        emailIntent.putExtra(Intent.EXTRA_TEXT, firstName + ", please move your clothes.");
        //Start the intent
        startActivity(emailIntent);
    }




}
