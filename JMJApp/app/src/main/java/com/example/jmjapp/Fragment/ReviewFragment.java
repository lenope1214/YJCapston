package com.example.jmjapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.MenuRecyclerAdapter;
import com.example.jmjapp.Adapter.ReviewRecyclerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.Review;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.payment.PaymentResultActivity;
import com.example.jmjapp.user.ReviewRegisterActivity;
import com.example.jmjapp.user.ShopDetailActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewFragment extends Fragment {
    private static String shopNumber;
    Context context;
    View view;
    private ReviewRecyclerAdapter adapter;
    private RecyclerView rv_review_list;
    private ArrayList<Review> mItems = new ArrayList<>();
    private String jwt, shopId, orderId;
    private Button review_btn;

    private String[] str;
    private List<Order> orderList;

    private Call<List<Order>> listOrderCall;
    private Call<List<Review>> reviewCall;

    boolean isChecked = false;

    public static ReviewFragment newInstance() {
        Bundle args = new Bundle();
        ReviewFragment fragment = new ReviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_review, container, false);

        }



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        rv_review_list = (RecyclerView) view.findViewById(R.id.rv_review_list);
        adapter = new ReviewRecyclerAdapter(getContext());
        shopNumber = ShopDetailActivity.shopNumber;
        showReviewList();

        listOrderCall = Server.getInstance().getApi().myOrder("Bearer " + jwt);
        listOrderCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    Log.d("myOrder 성공", "myOrder 성공");
                    orderList = response.body();
                    for(Order list : orderList) {
                        str = new String[orderList.size()];
                        for (int i = 0; i < orderList.size(); i++) {
                            str[i] = list.getShopId();
                            if (shopNumber.equals(str[i])) {
                                shopId = list.getShopId();
                                orderId = list.getOrderId();
                            }
                        }
                    }
                } else {
                    Log.d("myOrder 실패1", "myOrder 실패1");
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("myOrder 실패2", "myOrder 실패2");
            }
        });

        review_btn = view.findViewById(R.id.review_btn);
        review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderList.size() == 0) {
                    Toast.makeText(getActivity(), "주문 후 리뷰작성이 가능합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < orderList.size(); i++) {
                        if (shopNumber.equals(str[i])) {
                            Log.d("주문됨", "ㅈ무ㅜㄴ됨");
                            Intent intent = new Intent(getActivity(), ReviewRegisterActivity.class);
                            intent.putExtra("shopId", shopId);
                            intent.putExtra("orderId", orderId);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "주문 후 리뷰작성이 가능합니다.", Toast.LENGTH_SHORT).show();
                            Log.d("주문안됨", "ㅈ무ㅜㄴ안됨");
                        }
                    }
                }

            }
        });

    }

    private void showReviewList() {
        SharedPreferences pref = this.getActivity().getSharedPreferences("auth", Context.MODE_PRIVATE);
        jwt = pref.getString("token", "");
        Log.d("매장", shopNumber);
        Log.d("토큰", jwt);

        mItems.clear();

        reviewCall = Server.getInstance().getApi().reviewList("Bearer " + jwt, shopNumber);
        reviewCall.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    Log.d("reviewList 성공", "reviewList 성공");
                    List<Review> reviewList = response.body();
                    for(Review list : reviewList) {
                        mItems.add(new Review(list.getReviewId(), list.getUserId(), list.getShopId(),
                                            list.getContent(), list.getParentId(), list.getRegDate(),
                                            list.getScore(), list.getImgUrl(), list.getOrderId()));
                        Log.d("list", response.body().toString());
                        rv_review_list.setHasFixedSize(true);
                        adapter.setItems(mItems);
                        rv_review_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rv_review_list.setAdapter(adapter);
                    }
                } else {
                    Log.d("reviewList 실패1", "reviewList 실패1");
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.d("reviewList 실패2", "reviewList 실패2");
            }
        });
    }
}
