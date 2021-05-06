package com.example.jmjapp.push;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jmjapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Firebase2 extends AppCompatActivity {
    EditText editText;
    TextView textView;

    static RequestQueue requestQueue;
    static String regId = "dEg_zcKtK-A:APA91bH_EVX1cHNt36qTTsqrxHz6ervIT5H_2mCKUEaHII0x-InRrsOBaV7obGr0CB6-bDAn9WFXbw9lPloNAr26cxdd8qWP3iLVcmJuBlaBuSvkEX7wA3EFDuWZ8gKOpkdxKgibNc4C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase2);

        editText = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView42);

        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                send(input);
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void send(String input) {
        JSONObject requestData = new JSONObject();

        try {
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();

            dataObj.put("contents", input);
            requestData.put("data", dataObj);
            JSONArray idArray = new JSONArray();

            idArray.put(0, regId);
            requestData.put("registration_ids", idArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendData(requestData, new SendResponseListener() {

            @Override
            public void onRequestCompleted() {
                println("onRequestCompleted() 호출됨.");
            }

            @Override
            public void onRequestStarted() {
                println("onRequestStarted() 호출됨.");
            }

            @Override
            public void onRequestWithError(VolleyError error) {
                println("onRequestWithError() 호출됨.");
            }
        });
    }

    public interface SendResponseListener {
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestWithError(VolleyError error);
    }

    public void sendData(JSONObject requestData, final SendResponseListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(

                Request.Method.POST, "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onRequestCompleted();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestWithError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization",
                        "key=AAAAMsCw7yQ:APA91bGlT_VTfm9xHFMcguW36JMt6VXKVY2s-VgFnpUyrAZz2Gjhe8reRkLOMLp5dBG2kju74sUmeD4co8EQvZgszn4-4UUlaL0pdrttVcf9N50Jjk32Vdv1tGFASC0Nm_ss5VEdd68z");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        requestQueue.add(request);
    }

    public void println(String data) {
        textView.append(data+"\n");
    }
}