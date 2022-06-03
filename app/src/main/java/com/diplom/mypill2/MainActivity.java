package com.diplom.mypill2;





import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Context;

import android.content.Intent;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.view.Menu;

import android.widget.Button;

import android.widget.CalendarView;

import android.widget.ImageView;

import android.widget.Toast;



import com.google.android.material.snackbar.Snackbar;

import com.google.android.material.navigation.NavigationView;



import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;

import androidx.navigation.NavController;

import androidx.navigation.Navigation;

import androidx.navigation.ui.AppBarConfiguration;

import androidx.navigation.ui.NavigationUI;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;



import com.diplom.mypill2.databinding.ActivityMainBinding;

import com.google.android.material.timepicker.MaterialTimePicker;

import com.google.android.material.timepicker.TimeFormat;



import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Locale;



public class MainActivity extends AppCompatActivity {



    private AppBarConfiguration mAppBarConfiguration;

    private ActivityMainBinding binding;

    protected static FragmentManager fragmentManager;



    Button setAlarm;





    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());



        setAlarm = findViewById(R.id.alarm_button);



        setAlarm.setOnClickListener(v -> {

            MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()

                    .setTimeFormat(TimeFormat.CLOCK_24H)

                    .setHour(12)

                    .setMinute(0)

                    .setTitleText("Выберите время для будильника")

                    .build();



            materialTimePicker.addOnPositiveButtonClickListener(view -> {

                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.SECOND, 0);

                calendar.set(Calendar.MILLISECOND, 0);

                calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());

                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());



                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);



                AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), getAlarmInfoPendingIntent());



                alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent());

                Toast.makeText(this, "Будильник установлен на " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();



            });



            materialTimePicker.show(getSupportFragmentManager(), "tag_picker");

        });



        fragmentManager = getSupportFragmentManager();



        loadFragment(new EnterFragment());



        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());



        setSupportActionBar(binding.appBarMain.toolbar);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)

                        .setAction("Action", null).show();

            }

        });



        DrawerLayout drawer = binding.drawerLayout;

        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(

                R.id.nav_home, R.id.nav_calendar, R.id.nav_settings, R.id.nav_registration, R.id.nav_enter)

                .setOpenableLayout(drawer)

                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);



        View.OnClickListener listener = new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "CHELIK", Toast.LENGTH_SHORT).show();

                Log.d("MAIN", "image");

            }

        };



        binding.navView.getHeaderView(0).findViewById(R.id.imageView).setOnClickListener(listener);

        binding.navView.getHeaderView(0).setBackgroundResource(R.mipmap.ic_launcher);

    }



    private PendingIntent getAlarmInfoPendingIntent(){

        Intent alarmInfoIntent = new Intent(this, MainActivity.class);

        alarmInfoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        return PendingIntent.getActivity(this, 0, alarmInfoIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private PendingIntent getAlarmActionPendingIntent (){

        Intent intent = new Intent(this, AlarmActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        return PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    }



    public static void loadFragment(Fragment fragment) {

        fragmentManager

                .beginTransaction()

                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)

                .addToBackStack(null).commit();

    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }



    @Override

    public boolean onSupportNavigateUp() {

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();

    }

}



