package org.koreait.samplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive() 메서드 호출됨");

        // 인덴트에서 Bundle 객체 가져오기
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle); // parseSmsMessage() 메서드 호출하기

        if (messages != null && messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "SMS sender : " + sender);

            String contents = messages[0].getMessageBody();
            Log.i(TAG, "SMS contents : " + contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.i(TAG, "SMS received date : " + receivedDate.toString());

            // 새로운 화면을 띄우기 위한 메서드 호출하기
            sendToActivity(context, sender, contents, receivedDate);
        }
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {
        Intent myIntent = new Intent(context, SmsActivity.class);
        //  인텐트에 플래그 추가하기
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender", sender);
        myIntent.putExtra("contents", contents);
        myIntent.putExtra("receivedDate", format.format(receivedDate));

        context.startActivity(myIntent);
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {

        // Bundle 객체에 들어가 있는 부가 데이터 중에서 pdus 가져오기
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i = 0; i < smsCount; i++) {
            // 단말 OS 버전에 따라 다른 방식으로 메서드 호출하기
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}