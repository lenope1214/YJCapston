package com.example.jmjapp.owner.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.jmjapp.R;
import com.example.jmjapp.user.BellActivity;
import com.example.jmjapp.user.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.net.URLDecoder;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FMS";
    String id = "my_channel_02";
    CharSequence name = "fcm_nt";
    String description = "push";
    int importance = NotificationManager.IMPORTANCE_LOW;
    MediaPlayer mediaPlayer;

    private String resTime, resDate, resShop, sum, count, from;

    public MyFirebaseMessagingService() {

    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        Log.d(TAG, "onNewToken 호출됨 : " + token);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived 호출됨.");

        String from = remoteMessage.getFrom();
        Map<String, String> data = remoteMessage.getData();
//        String resTime = data.get("resTime");
//        String resDate = data.get("resDate");
//        String sum = data.get("sum");
//        String count = data.get("count");
//        String resShop = data.get("resShop");
        String orderId = data.get("orderId");
        String resId = data.get("resId");
        String orderId2 = data.get("orderId2");
        String msg = data.get("msg");
        String status = data.get("status");
        String jwt = data.get("jwt");

        //Log.d("status===",status);
        //Log.d(TAG, "from : " + from + ", contents : " + qwe);

//        sendToActivity(getApplicationContext(), from, resTime, resDate,
//                        sum, count, resShop);

//        if (!(msg.equals(""))) {
//            if (remoteMessage.getNotification() != null) { //포그라운드
//                sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle(),from, orderId2, msg);
//                Log.d("포그라운드","포그라운드");
//                //up_Nt(remoteMessage.getData().get("orderCnt1"),null,null);
//            }
//            else if (remoteMessage.getData().size() > 0) { //백그라운드
//                sendNotification(remoteMessage.getData().get("resTime"),remoteMessage.getData().get("resDate"),from, orderId2, msg);
//                Log.d("백그라운드","백그라운드");
//                //up_Nt(remoteMessage.getData().get("orderCnt1"),null,null);
//            }
//        }

        if (status.equals("toOwner")) {
            if (remoteMessage.getNotification() != null) { //포그라운드
                sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle(), from, orderId, resId, jwt);
                Log.d("owenr포그라운드", "owenr포그라운드");
                //up_Nt(remoteMessage.getData().get("orderCnt1"),null,null);
            } else if (remoteMessage.getData().size() > 0) { //백그라운드
                sendNotification(remoteMessage.getData().get("resTime"), remoteMessage.getData().get("resDate"), from, orderId, resId, jwt);
                Log.d("owenr백그라운드", "owenr백그라운드");
                //up_Nt(remoteMessage.getData().get("orderCnt1"),null,null);
            }
        } else if (status.equals("toUser")) {
            if (remoteMessage.getNotification() != null) { //포그라운드
                sendNotificationToUser(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle(), from, orderId2, msg, jwt);
                Log.d("user포그라운드", "user포그라운드");
            } else if (remoteMessage.getData().size() > 0) { //백그라운드
                sendNotificationToUser(remoteMessage.getData().get("orderId2"), remoteMessage.getData().get("msg"), from, orderId2, msg, jwt);
                Log.d("user백그라운드", "user백그라운드");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotificationToUser(String messageBody, String messageTitle, String from, String orderId2, String msg, String jwt) {
        Intent intent = new Intent(this, BellActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("orderId", orderId2);
        intent.putExtra("msg", msg);
        intent.putExtra("jwt", jwt);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);

        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mNotificationManager.createNotificationChannel(mChannel);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notifyID = 2;

        String CHANNEL_ID = "my_channel_02";

        try{
            if (msg.equals("accept")) {
                Notification notification = new Notification.Builder(MyFirebaseMessagingService.this)
                        .setContentTitle(URLDecoder.decode("주문의 민족", "UTF-8"))
                        .setContentText(URLDecoder.decode("예약주문이 수락되었습니다!", "UTF-8"))
                        .setSmallIcon(R.drawable.loginbackgroundblack)
                        .setChannelId(CHANNEL_ID)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();

                mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
                mediaPlayer.start();

                mNotificationManager.notify(notifyID, notification);
            } else if (msg.equals("reject")) {
                Notification notification = new Notification.Builder(MyFirebaseMessagingService.this)
                        .setContentTitle(URLDecoder.decode("주문의 민족", "UTF-8"))
                        .setContentText(URLDecoder.decode("예약주문이 거절되었습니다!", "UTF-8"))
                        .setSmallIcon(R.drawable.loginbackgroundblack)
                        .setChannelId(CHANNEL_ID)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();

                mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
                mediaPlayer.start();

                mNotificationManager.notify(notifyID, notification);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //////////////////////////// 포그라운드 및 백그라운드 푸시알림 처리 ////////////////////////////
    }

    private void sendToActivity(Context context, String from,
                                String resTime, String resDate,
                                String sum, String count, String resShop, String orderId, String resId, String jwt) {
        Intent intent = new Intent(context, BellActivity_O.class);
        intent.putExtra("from", from);
        intent.putExtra("resTime", resTime);
        intent.putExtra("resDate", resDate);
        intent.putExtra("sum", sum);
        intent.putExtra("count", count);
        intent.putExtra("resShop", resShop);
        intent.putExtra("orderId", orderId);
        intent.putExtra("resId", resId);
        intent.putExtra("jwt", jwt);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotification(String messageBody, String messageTitle, String from,
                                   String orderId, String resId, String jwt)  {
        //////////////////////////// 포그라운드 및 백그라운드 푸시알림 처리 ////////////////////////////
        Intent intent = new Intent(this, BellActivity_O.class);
        intent.putExtra("from", from);
        intent.putExtra("orderId", orderId);
        intent.putExtra("resId", resId);
        intent.putExtra("jwt", jwt);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);

        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mNotificationManager.createNotificationChannel(mChannel);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notifyID = 2;

        String CHANNEL_ID = "my_channel_02";

        try{
            Notification notification = new Notification.Builder(MyFirebaseMessagingService.this)
                    .setContentTitle(URLDecoder.decode("주문의 민족", "UTF-8"))
                    .setContentText(URLDecoder.decode("예약주문 요청이 왔습니다!", "UTF-8"))
                    .setSmallIcon(R.drawable.loginbackgroundblack)
                    .setChannelId(CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();

            mediaPlayer = MediaPlayer.create(this,R.raw.alarm);
            mediaPlayer.start();

            mNotificationManager.notify(notifyID, notification);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //////////////////////////// 포그라운드 및 백그라운드 푸시알림 처리 ////////////////////////////
    }

}