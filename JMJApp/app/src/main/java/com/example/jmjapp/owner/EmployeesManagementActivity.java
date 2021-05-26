package com.example.jmjapp.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jmjapp.Adapter.EmployeeAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Employee;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesManagementActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_employees;
    FloatingActionButton fab_addemployees;
    ArrayList<Employee> mItems = new ArrayList<>();

    private String jwt;

    private Call<List<Employee>> listEmpCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_management);

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.employees_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        fab_addemployees = findViewById(R.id.fab_addemployees);
        fab_addemployees.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEmployeesActivity.class);
            startActivity(intent);
        });

        rv_employees = findViewById(R.id.rv_employees);

        showEmpList();

    }

    private void showEmpList() {
        listEmpCall = Server.getInstance().getApi().empList("Bearer "+jwt,MainActivity_O.shopNumber);
        listEmpCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 200) {
                        List<Employee> employeeList = response.body();
                        for (Employee list : employeeList){
                            mItems.add(new Employee(list.getShopId(), list.getEmpNo(),
                                    list.getEmpName(), list.getBirthday(), list.getHiredate(),
                                    list.getPhone(), list.getGender()));
                            rv_employees.setHasFixedSize(true);
                            adapter = new EmployeeAdapter(getApplicationContext(), mItems);
                            rv_employees.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rv_employees.setAdapter(adapter);
                        }
                    } else {
                        System.out.println("4004004004004000400040");
                        Toast.makeText(getApplicationContext(), "조회 실패", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                System.out.println("kkkkkkkkkkkkkkkkkkkk");
                Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(listEmpCall!=null)
            listEmpCall.cancel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}