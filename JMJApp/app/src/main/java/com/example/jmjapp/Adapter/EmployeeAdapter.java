package com.example.jmjapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Employee;
import com.example.jmjapp.user.CustomerServiceViewHolder;

import java.util.ArrayList;

import lombok.NonNull;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ItemViewHolder> {


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_empitemprofile;
        TextView tv_empitemname;
        TextView tv_empitemphone;
        TextView tv_empitemtime;
        TextView sw_empitemwork;
        View v_emp;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_empitemprofile = itemView.findViewById(R.id.iv_empitemprofile);
            tv_empitemname = itemView.findViewById(R.id.tv_empitemname);
            tv_empitemphone = itemView.findViewById(R.id.tv_empitemphone);
            tv_empitemtime = itemView.findViewById(R.id.tv_empitemtime);
            sw_empitemwork = itemView.findViewById(R.id.sw_empitemwork);
            v_emp = itemView.findViewById(R.id.v_emp);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음
    private ArrayList<Employee> recordData;
    public EmployeeAdapter(ArrayList<Employee> recordData) {this.recordData = recordData;}


    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_employees_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return (recordData==null) ?0:recordData.size();
    }

    // 포지션에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Employee record = recordData.get(position);
        holder.tv_empitemname.setText(record.getEmpName());
        holder.tv_empitemphone.setText(record.getPhone());

    }



}
