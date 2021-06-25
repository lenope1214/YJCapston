package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ItemViewHolder> {

    Context context;
    ArrayList<Employee> recordData;

    private String jwt;

    // 생성자에서 데이터 리스트 객체를 전달받음

    public EmployeeAdapter(Context context, ArrayList<Employee> recordData) {
        this.context = context;
        this.recordData = recordData;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cl_empItem;
        ImageView iv_empitemprofile;
        TextView tv_empitemname;
        TextView tv_empitemphone;
        TextView tv_empitemtime;
        TextView sw_empitemwork;
        TextView tv_empno;
        View v_emp;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cl_empItem = itemView.findViewById(R.id.cl_zzimItem);
            iv_empitemprofile = itemView.findViewById(R.id.iv_zzimImg);
            tv_empitemname = itemView.findViewById(R.id.tv_zzimCategory);
            tv_empitemphone = itemView.findViewById(R.id.tv_empitemphone);
            tv_empitemtime = itemView.findViewById(R.id.tv_zzimAddress);
            sw_empitemwork = itemView.findViewById(R.id.sw_empitemwork);
            tv_empno = itemView.findViewById(R.id.tv_zzimName);
//            v_emp = itemView.findViewById(R.id.v_emp);
        }
    }


    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_employees_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return recordData.size();
    }

    // 포지션에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tv_empitemname.setText(recordData.get(position).getEmpName());
        holder.tv_empitemphone.setText(recordData.get(position).getPhone());
        holder.tv_empno.setText(recordData.get(position).getEmpNo());
        holder.iv_empitemprofile.setImageResource(recordData.get(position).getGender() == 'M' ? R.drawable.male : R.drawable.female);

        SharedPreferences pref = context.getSharedPreferences("auth_o", Context.MODE_PRIVATE);
        jwt = pref.getString("token", null);

        holder.cl_empItem.setOnClickListener(v -> {
        });
    }


}
