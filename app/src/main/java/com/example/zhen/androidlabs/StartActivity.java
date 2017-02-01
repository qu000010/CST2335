package com.example.zhen.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btToListItems = (Button) findViewById(R.id.button);
        btToListItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListItemsActivity = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(ListItemsActivity, 5);
            }
        });

        Button btChat = (Button) findViewById(R.id.button2);
        btChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatWindow = new Intent(StartActivity.this, ChatWindow.class);
                startActivity(chatWindow);
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
            }
        });

        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

    public void onActivityResult(int requestCode,int responseCode, Intent data){
        if(( requestCode == 5)&&(responseCode== Activity.RESULT_OK)) {
            String messagePassed = data.getStringExtra("Response");
            Toast toast = Toast.makeText(this , messagePassed, Toast.LENGTH_LONG);
            toast.show(); //display your message box
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}
