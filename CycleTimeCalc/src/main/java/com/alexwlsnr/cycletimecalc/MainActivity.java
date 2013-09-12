package com.alexwlsnr.cycletimecalc;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        configureSpinners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void configureSpinners()
    {
        Spinner startHourSpinner = (Spinner) findViewById(R.id.startHourSpinner);
    // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hours_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
        startHourSpinner.setAdapter(adapter);



        Spinner endHourSpinner = (Spinner) findViewById(R.id.endHourSpinner);
        // Apply the adapter to the spinner
        endHourSpinner.setAdapter(adapter);

    }



    
}
