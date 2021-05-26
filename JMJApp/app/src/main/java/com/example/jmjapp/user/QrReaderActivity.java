package com.example.jmjapp.user;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.jmjapp.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLOutput;

import lombok.SneakyThrows;

public class QrReaderActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String orderNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_reader);

        scanCode();
    }

    private void scanCode() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setCaptureActivity(CaptureAct.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning Code");
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            //QRcode 결과가 있으면
            if(result.getContents() != null) {
                String str = result.getContents();
                String shopnum1 = str.substring(str.lastIndexOf("shopcontent/")+12);
                String shopnum2 = shopnum1.substring(0,10);
                String tablenum = shopnum1.substring(11,12);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("move", new DialogInterface.OnClickListener() {
                    @SneakyThrows
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            System.out.println(result.getContents());
                            System.out.println(shopnum1+"3333333333333333");
                            System.out.println(shopnum2);
                            System.out.println(tablenum);

                            finish();

//                            pref = getSharedPreferences("orderCheck", MODE_PRIVATE);
//                            editor = pref.edit();
//                            editor.putInt("OrderNumber",1);
//                            editor.apply();

                            orderNumber = "1";

                            Intent intent = new Intent(QrReaderActivity.this,ShopDetailActivity.class);
                            intent.putExtra("shopNumber",shopnum2);
                            intent.putExtra("tableNumber", tablenum);
                            intent.putExtra("orderCheck", orderNumber);


                            startActivity(intent);
                        } catch (Exception e ) {
                            e.getStackTrace();
                        }

                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            //QRcode 결과가 없으면
            else {
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
