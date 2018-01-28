package com.example.samuvelp.smsnotifier.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


/**
 * Created by samuvelp on 26/01/18.
 */

public class SmsReceiver extends BroadcastReceiver {
    SharedPreferences sharedPreferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle date = intent.getExtras();
        sharedPreferences = context.getSharedPreferences("myPref",Context.MODE_PRIVATE);
        Object[] pdus = (Object[]) date.get("pdus");
        for (int i = 0; i < pdus.length; ++i) {
            String phoneNumber = sharedPreferences.getString("phoneNumber",null);
            boolean isChecked = sharedPreferences.getBoolean("isChecked",false);
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String sender = smsMessage.getDisplayOriginatingAddress();
            String messageBody = smsMessage.getMessageBody();
            Long timeStamp = smsMessage.getTimestampMillis();
            HashMap messageObj = new HashMap();
            messageObj.put("senderId",sender);
            messageObj.put("messageBody",messageBody);
            messageObj.put("timeStamp",timeStamp);
            if(phoneNumber!=null && isChecked) {
                FirebaseDatabase.getInstance().getReference().child("messages").child(phoneNumber).push().setValue(messageObj);
            }
//            Toast.makeText(context, sender + "," + messageBody, Toast.LENGTH_SHORT).show();
        }
    }

}
