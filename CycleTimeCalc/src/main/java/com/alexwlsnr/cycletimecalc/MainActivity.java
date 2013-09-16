package com.alexwlsnr.cycletimecalc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.Calendar;

import static com.alexwlsnr.cycletimecalc.CycleTimeUtils.getCycleTime;

public class MainActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.setProperty("org.joda.time.DateTimeZone.Provider",
                "com.alexwlsnr.cycletimecalc.FastDateTimeZoneProvider");
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
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String[] hoursArray = getResources().getStringArray(R.array.hours_array);

        Spinner startHourSpinner = (Spinner) findViewById(R.id.startHourSpinner);
    // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hours_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
        startHourSpinner.setAdapter(adapter);

        int defaultIndex = Arrays.asList(hoursArray).indexOf(Integer.toString(currentHour));

        if(defaultIndex == -1)
        {
            if(currentHour > Integer.parseInt(hoursArray[hoursArray.length-1]))
            {

                defaultIndex = hoursArray.length-1;
            }
            else
            {
                defaultIndex = 0;
            }
        }



        startHourSpinner.setSelection(defaultIndex);



        Spinner endHourSpinner = (Spinner) findViewById(R.id.endHourSpinner);
        // Apply the adapter to the spinner
        endHourSpinner.setAdapter(adapter);
        endHourSpinner.setSelection(adapter.getPosition(Integer.toString(currentHour)));
        endHourSpinner.setSelection(defaultIndex);

    }
    public void resetUi(View v)
    {
        configureSpinners();
        Calendar currentDate = Calendar.getInstance();
        DatePicker startDatePicker = (DatePicker) findViewById(R.id.startDatePicker);
        DatePicker endDatePicker = (DatePicker) findViewById(R.id.endDatePicker);
        startDatePicker.getCalendarView().setDate(currentDate.getTimeInMillis());
        endDatePicker.getCalendarView().setDate(currentDate.getTimeInMillis());
    }


    public void calculate(View v)
    {
        Spinner startHourSpinner = (Spinner) findViewById(R.id.startHourSpinner);
        int startHour = Integer.parseInt((String)startHourSpinner.getSelectedItem());
        DatePicker startDatePicker = (DatePicker) findViewById(R.id.startDatePicker);
        DateTime startDate = new DateTime(startDatePicker.getYear(), startDatePicker.getMonth(), startDatePicker.getDayOfMonth(), startHour, 0);

        Spinner endHourSpinner = (Spinner) findViewById(R.id.endHourSpinner);
        int endHour = Integer.parseInt((String)endHourSpinner.getSelectedItem());
        DatePicker endDatePicker = (DatePicker) findViewById(R.id.endDatePicker);
        DateTime endDate = new DateTime(endDatePicker.getYear(), endDatePicker.getMonth(), endDatePicker.getDayOfMonth(), endHour, 0); //GregorianCalendar(endDatePicker.getYear(), endDatePicker.getMonth(), endDatePicker.getDayOfMonth(), endHour, 0);


        int cycleTime = getCycleTime(startDate, endDate);
        TextView resultsArea = (TextView) findViewById(R.id.cycleTimeTextView);

        resultsArea.setText(Integer.toString(cycleTime));
    }
    
}
