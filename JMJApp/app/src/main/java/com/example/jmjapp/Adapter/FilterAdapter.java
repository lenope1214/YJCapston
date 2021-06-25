package com.example.jmjapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Shop;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ItemViewHolder> implements Filterable {
    private List<String> unfilterList;
    private Context context;
    private List<String> filterList;

    public FilterAdapter(Context context, List<String> unfilterList) {
        this.unfilterList = unfilterList;
        this.filterList = unfilterList;
        this.context = context;
    }

//    public FilterAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void setItems(ArrayList<Shop> shopss){
//        this.mItems = shopss;
//    }

    // 새로운 뷰 홀더 생성
    @Override
    public FilterAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item, parent, false);
        return new FilterAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final FilterAdapter.ItemViewHolder holder, final int position) {
        holder.filter_tv.setText(filterList.get(position));
    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return filterList == null ? 0 : filterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String str = constraint.toString();
                if (str.isEmpty()) {
                    filterList = unfilterList;
                } else {
                    List<String> filteringList = new ArrayList<>();

                    for (String item : unfilterList) {
                        if (item.toLowerCase().contains(str)) {
                            filteringList.add(item);
                        }
                    }
                    filterList = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (List<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    // 커스텀 뷰홀더
    // item layout에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView filter_tv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            filter_tv = itemView.findViewById(R.id.filter_tv);
        }
    }
}
