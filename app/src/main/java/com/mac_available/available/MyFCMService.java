package com.mac_available.available;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFCMService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("TAG", "onMessageReceived....");

        //알림(Notification)으로 받은 메세지 보여주기
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("ch1", "push ch", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, "ch1");

        }else {
            builder = new NotificationCompat.Builder(this, null);
        }

        String fromWho = remoteMessage.getFrom();

        String notiTitle = "title"; //원격메세지에 알림제목정보가 없을때의 기본값
        String notiText = "message"; //원격메세지에 알림메세지정보가 없을때의 기본값

        if (remoteMessage.getNotification() != null){
            notiTitle = remoteMessage.getNotification().getTitle();
            notiText = remoteMessage.getNotification().getBody();
        }

        builder.setSmallIcon(R.drawable.direct);
        builder.setContentTitle(notiTitle);
        builder.setContentText(notiText);
        builder.setAutoCancel(true);

        Map<String, String> data = remoteMessage.getData();
        if (data != null){
            String title = data.get("name");
            String message = data.get("message");

            notiTitle= title;
            notiText= message;

            builder.setSmallIcon(R.drawable.direct);
            builder.setContentTitle(notiTitle);
            builder.setContentText(notiText);
            builder.setAutoCancel(true);


            Intent intent = new Intent(this, IntroActivity.class);
            intent.putExtra("name", title);
            intent.putExtra("msg", message);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 150, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
        }

        Notification notification = builder.build();
        //startForeground(200, notification);
        notificationManager.notify(1000, notification);
    }
}
