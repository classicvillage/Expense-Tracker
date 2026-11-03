package com.smart.expensetracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format");
                if (pdus != null) {
                    StringBuilder fullBody = new StringBuilder();
                    String sender = "";

                    for (Object pdu : pdus) {
                        SmsMessage smsMessage;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            smsMessage = SmsMessage.createFromPdu((byte[]) pdu, format);
                        } else {
                            smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        }
                        if (smsMessage != null) {
                            sender = smsMessage.getDisplayOriginatingAddress();
                            fullBody.append(smsMessage.getDisplayMessageBody());
                        }
                    }

                    String messageText = fullBody.toString();
                    Log.d(TAG, "Received SMS from " + sender + ": " + messageText);

                    Intent forwardIntent = new Intent("com.smart.expensetracker.SMS_RECEIVED");
                    forwardIntent.putExtra("sms_body", messageText);
                    forwardIntent.putExtra("sms_sender", sender);
                    context.sendBroadcast(forwardIntent);
                }
            }
        }
    }
}
