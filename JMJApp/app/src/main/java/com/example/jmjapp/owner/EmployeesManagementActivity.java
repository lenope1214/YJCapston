package com.example.jmjapp.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.jmjapp.Adapter.EmployeeAdapter;
import com.example.jmjapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EmployeesManagementActivity extends AppCompatActivity {

    FloatingActionButton fab_addemployees;
    RecyclerView rv_employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_management);

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_employees.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}