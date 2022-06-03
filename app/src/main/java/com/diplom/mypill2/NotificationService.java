package com.diplom.mypill2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationService extends Service {
    private static final int NOTIFY_ID = 101;

    private NotificationChannel channel1;
    public static final String CHANNEL_1_ID = "channel1";
    private Context context;

    public NotificationService(Context context, NotificationManager notifyClass) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");
            NotificationManager manager = notifyClass;
            this.context = context;
            manager.createNotificationChannel(channel1);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_1_ID)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("Напоминание")
                        .setContentText("Пора пить таблетки, а то получишь по жопе!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(101, builder.build());
        return null;
    }
}