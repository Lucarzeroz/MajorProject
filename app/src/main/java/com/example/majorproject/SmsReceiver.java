//package com.example.scammagram40;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.telephony.SmsMessage;
//import android.widget.Toast;
//
//public class SmsReceiver extends BroadcastReceiver {
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // Get the SMS message data
//        Bundle bundle = intent.getExtras();
//        if (bundle != null) {
//            // A PDU is a "protocol data unit". This is the industrial standard for SMS message
//            Object[] pdus = (Object[]) bundle.get("pdus");
//            final SmsMessage[] messages = new SmsMessage[pdus.length];
//            for (int i = 0; i < pdus.length; i++) {
//                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//            }
//            // Check if the message is complete or a multi-part message
//            if (messages.length == 1 || messages[0].isReplace()) {
//                // A single message or the first part of a multi-part message
//                String messageBody = messages[0].getMessageBody();
//                String messageSender = messages[0].getOriginatingAddress();
//                // Display the SMS message
//                Toast.makeText(context, "From: " + messageSender + "\nMessage: " + messageBody, Toast.LENGTH_LONG).show();
//            } else {
//                // A multi-part message
//                String messageBody = "";
//                String messageSender = messages[0].getOriginatingAddress();
//                for (SmsMessage message : messages) {
//                    messageBody += message.getMessageBody();
//                }
//
//                // Display the SMS message
//                Toast.makeText(context, "From: " + messageSender + "\nMessage: " + messageBody, Toast.LENGTH_LONG).show();
//
//            }
//        }
//    }
//}


//package com.example.scammagram40;
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.telephony.SmsMessage;
//import android.util.Log;
//import android.widget.TextView;
//import android.view.View;
//
//public class SmsReceiver extends BroadcastReceiver {
//
//    private static final String TAG = "SmsReceiver";
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Log.d(TAG, "onReceive called");
//
//        // Get the SMS message received
//        Bundle bundle = intent.getExtras();
//        SmsMessage[] msgs = null;
//        String strMessage = "";
//        if (bundle != null) {
//            // Retrieve the SMS message received
//            Object[] pdus = (Object[]) bundle.get("pdus");
//            msgs = new SmsMessage[pdus.length];
//            for (int i = 0; i < msgs.length; i++) {
//                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//                // Build the message to show
//                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
//                strMessage += " :" + msgs[i].getMessageBody() + "\n";
//            }
//            // Display the message
//
//
//                // Get the root view of the activity
//                View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
//                // Display the message in a TextView
//                TextView textView = rootView.findViewById(R.id.text_view);
//
//            textView.setText(strMessage);
//            }
//        }
//    }

package com.example.majorproject;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
//import org.python.util.PythonInterpreter;
import android.view.View;
import android.widget.Toast;
//import com.google.i18n.phonenumbers.PhoneNumberOfflineGeocoder;
//import com.google.i18n.phonenumbers.Phonenumber;
//import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;


// C:\Users\siamu\AndroidStudioProjects\scammagram40\pythonProject\main.py"


public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive called");
//        PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

//        Log.d("hello", "test1");
        // Get the SMS message received
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;

        String strMessage = "";


        String phonenumber = null;
        if (bundle != null) {
            // Retrieve the SMS message received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];


            for (int i = 0; i < msgs.length; i++) {


                Intent broadcastIntent = new Intent("SMS_RECEIVED_ACTION");
                broadcastIntent.putExtra("sms_message", strMessage);
                context.sendBroadcast(broadcastIntent);


                byte[] pdu = (byte[]) pdus[i];
                msgs[i] = SmsMessage.createFromPdu(pdu, "3gpp");

                String message = msgs[i].getMessageBody();


                phonenumber = msgs[i].getOriginatingAddress();
                String countrycode = phonenumber.substring(0, 3);

                // Build the message to show
                strMessage += "  SMS from      :     " + phonenumber + "\n\n";

                if (countrycode.startsWith("65", 1)) {
                    strMessage += "  Origin        :     " + countrycode + " (Local)" + "\n\n";
                } else {
                    strMessage += "  Origin        :     " + countrycode + " (Overseas)" + "\n\n";
                }


//              strMessage += "Origin : " + countrycode + "\n";
                strMessage += "  Message       :     " + message + "\n\n";
                Log.d("hello", phonenumber);
                Log.d("hello2", message);


                // Check if the phone number is in the user's contacts
                Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phonenumber));
                String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};
                Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
                String incontacts = "";
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
//                        strMessage+="\n in Contacts: yes";
                        incontacts = "yes";
                    } else {
//                        strMessage+="\n in Contacts: no";
                        incontacts = "No";
                    }
                    cursor.close();
                }

                strMessage += "  In Contacts   :     " + incontacts;
                Log.d("hello", incontacts);

                // Send a notification
                NotificationSender.sendNotification(context, incontacts, message);
            }


        }
        //            Display the message in a toast
//            Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
//            Log.d("hello",strMessage);


        // Display the message in a TextView
        TextView textView = ((Activity) context).findViewById(R.id.text_view);
        textView.setText(strMessage);
        Log.d("hello", strMessage);




    }
}