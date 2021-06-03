package com.example.jmjapp.Adapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.CustomerService;
import com.example.jmjapp.user.CustomerServiceViewHolder;
import com.example.jmjapp.user.OnViewHolderItemClickListener1;

import java.util.ArrayList;

//RecyclerViewAdapter
public class CustomerServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //adapter에 들어갈 list
    private ArrayList<CustomerService> arrayList = new ArrayList<>();

    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;

    @NonNull
    @Override
    // listView 가 처음으로 생성될 때 생명주기
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_customer_service_item, parent,false);
        return new CustomerServiceViewHolder(view);
    }

    @Override
    // listView 가 추가될 때 생명주기
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        CustomerServiceViewHolder customerServiceViewHolder = (CustomerServiceViewHolder) holder;
        customerServiceViewHolder.onBind(arrayList.get(position),position,selectedItems);

        //만약 안되면 1로 수정
        customerServiceViewHolder.setOnViewHolderItemClickListener1(new OnViewHolderItemClickListener1() {
            @Override
            public void onViewHolderItemClick1() {
                if (selectedItems.get(position)) {
                    // 펼쳐진 Item을 클릭 시
                    selectedItems.delete(position);
                } else {
                    // 직전의 클릭됐던 Item의 클릭상태를 지움
                    selectedItems.delete(prePosition);
                    // 클릭한 Item의 position을 저장
                    selectedItems.put(position, true);
                }
                // 해당 포지션의 변화를 알림
                if (prePosition != -1) notifyItemChanged(prePosition);
                notifyItemChanged(position);
                // 클릭된 position 저장
                prePosition = position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItem(CustomerService customerService){
        arrayList.add(customerService);

    }


}