package com.example.majorproject;



import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.text.Html;

import androidx.core.app.NotificationCompat;

public class NotificationSender {



    public static void sendNotification(Context context, String incontacts, String message) {




        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String id = "my_channel_id";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, "SMS Notifications", importance);
            notificationManager.createNotificationChannel(mChannel);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, id);
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();



            // Create an Intent for the main activity of your app
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("message", message);

            //Add FLAG_ACTIVITY_CLEAR_TOP and FLAG_ACTIVITY_SINGLE_TOP flag to Intent
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            // Create a PendingIntent to be fired when the notification is tapped
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Set the PendingIntent to be fired when the notification is tapped
            notificationBuilder.setContentIntent(pendingIntent);

            //set the message
            if (incontacts.equals("yes")) {
//                notificationBuilder.setContentText("SMS from known contact"+"\n Message: " + message + "\n\n Tap for more details " )
                // Use Html.fromHtml() method to format the text
                notificationBuilder.setContentText(Html.fromHtml("SMS from known contact <br> <br> Message: " + message + " <br> <br> Tap for more details ", Html.FROM_HTML_MODE_LEGACY))
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setAutoCancel(true);
                notificationBuilder.setStyle(bigTextStyle);

            } else {
//                notificationBuilder.setContentText("SMS from unknown sender may be a Scam"+ "\n Message: " + message + "\n\n Tap for more details ")
                notificationBuilder.setContentText(Html.fromHtml("SMS from unknown sender...may be a Scam <br> <br> Message: " + message + " <br> <br> Tap for more details ", Html.FROM_HTML_MODE_LEGACY))
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setAutoCancel(true);
                notificationBuilder.setStyle(bigTextStyle);
            }
            notificationBuilder.setStyle(bigTextStyle);
            notificationManager.notify(1, notificationBuilder.build());
        }
    }
}
