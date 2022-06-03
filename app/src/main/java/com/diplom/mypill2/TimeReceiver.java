package com.diplom.mypill2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeReceiver extends BroadcastReceiver {
    Context context;
    private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";
    private NotificationChannel channel1;
    public static final String CHANNEL_1_ID = "channel1";
    private NotificationManager notifyClass;

    public TimeReceiver(NotificationManager notifyClass) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");
            this.notifyClass = notifyClass;
            NotificationManager manager = notifyClass;
            manager.createNotificationChannel(channel1);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String action = intent.getAction();

        StringBuilder msgStr = new StringBuilder("Текущее время: ");
        Format formatter = new SimpleDateFormat("hh:mm:ss a");
        msgStr.append(formatter.format(new Date()));
        Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show();
        Log.d("Time", String.valueOf(msgStr));

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_1_ID)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("Напоминание")
                        .setContentText("Пора пить таблетки, а то получишь по жопе!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(101, builder.build());

        if (action.equalsIgnoreCase(BOOT_ACTION)) {
            Intent intentNew = new Intent(context, NotificationService.class);
            context.startService(intentNew);
        }
    }
}