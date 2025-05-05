package com.example.a1922;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean sendsms=checkSelfPermission(Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED;

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            String[] permissions=new String[1];
            if(!sendsms){
                permissions[0]=Manifest.permission.SEND_SMS;
            }else{
                Toast.makeText(this,"已取得傳送簡訊權限",Toast.LENGTH_SHORT).show();
            }
            requestPermissions(permissions,100);
        }
    }

    public void workbtnOnClick(View view) {

            //SmsManager smsManager=SmsManager.getDefault();
            //smsManager.sendTextMessage("+886 1922",null,"場所代碼：2067 8552 3880 282\n" +
                    //"本次簡訊實聯限防疫目的使用。",null,null);

            //Toast.makeText(this, "愛國大樓實聯制，簡訊已傳送", Toast.LENGTH_SHORT).show();
        Uri smstoUri = Uri.parse("smsto:"); // 解析地址
        Intent intent = new Intent(Intent.ACTION_VIEW,smstoUri);
        intent.putExtra("address","1922"); // 沒有電話號碼的話為預設的，即顯示的時候是為空的
        intent.putExtra("sms_body","場所代碼：2067 8552 3880 282\n" +
                "本次簡訊實聯限防疫目的使用。"); // 設定傳送的內容
        intent.setType("vnd.android-dir/mms-sms");
        try {
            startActivity(intent);
            finish();
        }catch (Exception exception){
            Toast.makeText(this, "簡訊發送失敗", Toast.LENGTH_SHORT).show();
        }

    }

    public void foodbtnOnClick(View view) {
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage("1922",null,"場所代碼：2322 6958 2821 008\n" +
                "本簡訊是簡訊實聯制發送，限防疫目的使用。",null,null);

        Toast.makeText(this,"瑞麟美而美實聯制，簡訊已傳送",Toast.LENGTH_SHORT).show();
    }



}