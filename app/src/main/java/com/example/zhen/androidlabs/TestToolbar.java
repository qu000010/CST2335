package com.example.zhen.androidlabs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.id.edit;

public class TestToolbar extends AppCompatActivity {
    Context ctx;
    String  item1Snackbar = "You select item 1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctx = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Check your Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu m) { //put the menu in the toolbar

        getMenuInflater().inflate(R.menu.toolbar_menu,  m); //makes m look like toolbar_menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {       //when an item was clicked
        // Handle presses on the action bar items
        switch (mi.getItemId()) {
            case R.id.action_one:
                //show a Snackbar
                Log.i("Navigation", "1");
                //Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                Snackbar.make(fab, item1Snackbar, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

            case R.id.action_two:
                Log.i("Navigation", "2");
                //launch another Activity
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Do you want to go back?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("No", "No");
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Yes", "Yes");
                                startActivity(new Intent(TestToolbar.this, StartActivity.class));
                            }
                        });
                builder.create().show();
                break;

            case R.id.action_three:
                Log.i("Navigation", "3");
                AlertDialog.Builder builder2 = new AlertDialog.Builder(ctx);
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();
                final View v = inflater.inflate(R.layout.activity_custom_dialog, null);
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder2.setView(inflater.inflate(R.layout.activity_custom_dialog, null))
                        // Add action buttons
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Yes", "Yes");
                                EditText inputMessage = (EditText)v.findViewById(R.id.dialogMessageInput);
                                Log.i("Text is:", inputMessage.getText().toString());
                                item1Snackbar = inputMessage.getText().toString();
                                //FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
                                //Snackbar.make(fab, inputMessage.getText().toString(), Snackbar.LENGTH_LONG)
                                //.setAction("Action", null).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("No", "No");
                            }
                        });
                builder2.setView(v);
                builder2.create().show();
                break;

            case R.id.action_four:
                Log.i("Navigation", "4");
                Toast.makeText(this, "Version 1.0, by Zhen Qu", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
