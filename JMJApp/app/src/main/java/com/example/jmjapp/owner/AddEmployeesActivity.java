package com.example.jmjapp.owner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jmjapp.JMJApplication;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Employee;
import com.example.jmjapp.network.Server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmployeesActivity extends AppCompatActivity {

    DatePickerDialog.OnDateSetListener setListener;
    EditText et_empphone, et_empname, et_empbirth, et_emphire, et_empno;
    Button btn_addemp;
    RadioGroup rg_gender;
    RadioButton rb_male, rb_female;

    String gender;

    private AlertDialog dialog;
    private Call<ResponseBody> insertemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employees);

        Toolbar toolbar = (Toolbar) findViewById(R.id.addemployees_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        String member_id = ((JMJApplication) this.getApplication()).getId();
        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        String jwt = pref.getString("token", null);
        System.out.println(jwt);

        et_empname = findViewById(R.id.et_empname);
        et_empphone = findViewById(R.id.et_empphone);
        et_empbirth = findViewById(R.id.et_empbirth);
        et_emphire = findViewById(R.id.et_emphire);
        et_empno = findViewById(R.id.et_empno);

        btn_addemp = findViewById(R.id.btn_addemp);
        rg_gender = findViewById(R.id.rg_gender);
        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        //birthDate
        et_empbirth.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddEmployeesActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = (view, year1, month1, day1) -> {
            month1 = month1 + 1;
            String date = null;

            if (month1 < 10 && day1 < 10) {
                date = year1 + "/0" + month1 + "/0" + day1;
            } else if (day1 < 10) {
                date = year1 + "/" + month1 + "/0" + day1;
            } else if (month1 < 10) {
                date = year1 + "/0" + month1 + "/" + day1;
            } else {
                date = year1 + "/" + month1 + "/" + day1;
            }
            et_empbirth.setText(date);
        };

        et_empbirth.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddEmployeesActivity.this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year12, int month12, int day12) {
                    month12 = month12 + 1;
                    String date = null;

                    if (month12 < 10 && day12 < 10) {
                        date = year12 + "/0" + month12 + "/0" + day12;
                    } else if (day12 < 10) {
                        date = year12 + "/" + month12 + "/0" + day12;
                    } else if (month12 < 10) {
                        date = year12 + "/0" + month12 + "/" + day12;
                    } else {
                        date = year12 + "/" + month12 + "/" + day12;
                    }
                    et_empbirth.setText(date);
                }
            }, year, month, day);
            datePickerDialog.show();
        });

        //hireDate
        et_emphire.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddEmployeesActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = (view, year13, month13, day13) -> {
            month13 = month13 + 1;
            String date = null;

            if (month13 < 10 && day13 < 10) {
                date = year13 + "/0" + month13 + "/0" + day13;
            } else if (day13 < 10) {
                date = year13 + "/" + month13 + "/0" + day13;
            } else if (month13 < 10) {
                date = year13 + "/0" + month13 + "/" + day13;
            } else {
                date = year13 + "/" + month13 + "/" + day13;
            }

            et_emphire.setText(date);
        };

        et_emphire.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddEmployeesActivity.this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year14, int month14, int day14) {

                    month14 = month14 + 1;

                    String date = null;

                    if (month14 < 10 && day14 < 10) {
                        date = year14 + "/0" + month14 + "/0" + day14;
                    } else if (day14 < 10) {
                        date = year14 + "/" + month14 + "/0" + day14;
                    } else if (month14 < 10) {
                        date = year14 + "/0" + month14 + "/" + day14;
                    } else {
                        date = year14 + "/" + month14 + "/" + day14;
                    }

                    et_emphire.setText(date);
                }
            }, year, month, day);
            datePickerDialog.show();
        });
//
//        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId==0){
//                    Log.d("111111","asdasd");
//                } else {
//                    Log.d("222222","qweqwe");
//                }
//            }
//        });

        btn_addemp.setOnClickListener(v -> {

            if (!(et_empname.getText().toString().length() >= 2 && et_empname.getText().toString().length() <= 5)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                dialog = builder.setMessage("직원명은 2~5자로 입력해 주세요.").setPositiveButton("확인", null).create();
                dialog.show();
                et_empname.requestFocus();
            } else if (et_empphone.getText().toString().length() > 11) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                dialog = builder.setMessage("휴대폰번호는 11자 이내로 입력해 주세요.").setPositiveButton("확인", null).create();
                dialog.show();
                et_empphone.requestFocus();
            } else {

                if (rb_male.isChecked()) {
                    this.gender = "M";
                } else {
                    this.gender = "F";
                }

//                RequestBody shopId = RequestBody.create(MediaType.parse("text/plain"), "1111111112");
//                RequestBody empNo = RequestBody.create(MediaType.parse("text/plain"), "123");
//                RequestBody empName = RequestBody.create(MediaType.parse("text/plain"), et_empname.getText().toString());
//                RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), et_empphone.getText().toString());
//                RequestBody birthday = RequestBody.create(MediaType.parse("text/plain"), et_empbirth.getText().toString());
//                RequestBody hiredate = RequestBody.create(MediaType.parse("text/plain"), et_emphire.getText().toString());
//                RequestBody genderMF = RequestBody.create(MediaType.parse("text/plain"), gender);

                Date birthday = null, hiredate = null;

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    birthday = simpleDateFormat.parse(et_empbirth.getText().toString());
                    hiredate = simpleDateFormat.parse(et_emphire.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.d("1111111", et_empname.getText().toString());
                Log.d("2222222", et_empphone.getText().toString());
                System.out.println("33333333" + birthday.getTime());
                System.out.println("birthday : " + et_empbirth.getText().toString());
                System.out.println("77777777" + hiredate.getTime());
                Log.d("4444444", et_emphire.getText().toString());
                Log.d("5555555", gender);
                Log.d("6666666", et_empno.getText().toString());


                Map<String, Object> map = new HashMap();
                map.put("shopId", MainActivity_O.shopNumber);
                //TODO 직원번호도 보내주세요
                map.put("empNo", et_empno.getText().toString());
                map.put("empName", et_empname.getText().toString());
                map.put("birthday", birthday.getTime());
                map.put("hiredate", hiredate.getTime());
                map.put("phone", et_empphone.getText().toString());
                map.put("gender", gender);

                insertemp = Server.getInstance().getApi().insertemp("Bearer " + jwt, map);
                insertemp.enqueue(new Callback<ResponseBody>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddEmployeesActivity.this);
                            dialog = builder.setMessage("직원이 등록되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(AddEmployeesActivity.this, EmployeesManagementActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).create();
                            builder.setCancelable(false);
                            dialog.show();
                            Log.d("result : ", "직원등록 성공");

                        } else if (response.code() == 400) {
                            System.out.println(response.errorBody().string());
                            Log.d("result : ", "직원등록 실패");
                        } else if (response.code() == 409) {
                            System.out.println(response.errorBody().string());
                            Log.d("result : ", "이미 있는 직원이라 직원등록 실패");
                        } else {
                            Log.d("result : ", "연결실패" + "@@@@@");
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}