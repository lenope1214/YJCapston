package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.payment.PaymentResultActivity;
import com.example.jmjapp.user.OrderActivity;
import com.example.jmjapp.user.ShopDetailActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmListRecyclerAdapter extends RecyclerView.Adapter<AlarmListRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Order> mItems;
    static RequestQueue requestQueue;

    private Call<ResponseBody> responseBodyCall;
    private Call<List<OrderMenu>> orderMenuCall;
    private AlertDialog dialog;
    private String device_token, userId, orderId, jwt, orderRequest, jwtUser;
    private String sum = "";
    private Long arriveTime;
    private Call<Order.OrderMenuList> getOrderMenus;

    public AlarmListRecyclerAdapter(Context context, ArrayList<Order> alarm, String jwtUser1) {
        this.context = context;
        mItems = alarm;
        this.jwtUser = jwtUser1;
    }

//    public AlarmListRecyclerAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void setItems(ArrayList<Order> alarm) {
//        this.mItems = alarm;
//    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_alarm_list, parent, false);
        return new ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        String acceptOrReject = String.valueOf(mItems.get(position).getAccept());

        SharedPreferences pref = context.getSharedPreferences("auth_o", Context.MODE_PRIVATE);
        jwt = pref.getString("token", "");

        holder.userId.setText(mItems.get(position).getUserName()+"님 외"+mItems.get(position).getPeople()+"명");


        holder.orderRequest.setText(mItems.get(position).getOrderRequest());

        userId = mItems.get(position).getUserId();
        orderId = mItems.get(position).getOrderId();
        arriveTime = mItems.get(position).getArriveTime();
        orderRequest = mItems.get(position).getOrderRequest();
        Log.d("arc", String.valueOf(arriveTime));

        String resTime = String.valueOf(new Timestamp(arriveTime));
        String year = resTime.substring(0,4);
        String month = resTime.substring(5,7);
        String day = resTime.substring(8,10);

        String hour = resTime.substring(11,13);
        String min = resTime.substring(14,16);

        holder.resTime.setText(year+"년"+month+"월"+day+"일 " +hour+"시"+min+"분");

        if (orderRequest == null) {
            holder.orderRequest.setText("요청사항이 없습니다.");
        } else {
            holder.orderRequest.setText(orderRequest);
        }

        responseBodyCall = Server.getInstance().getApi().deviceToken(userId);
        responseBodyCall.enqueue(new Callback<ResponseBody>() { // 기기토큰 조회
            @SneakyThrows
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("token성공", "token성공");
                    device_token = response.body().string();
                    Log.d("token성공", device_token);
                } else {
                    Log.d("token실패1", "token실패1");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("token실패2", "token실패2");
            }
        });

        String acceptMsg = "accept";
        String rejectMsg = "reject";

        Log.d("jwt", jwt+orderId);

        holder.orderMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderMenus = Server.getInstance().getApi().orderOneMenu(orderId);
                getOrderMenus.enqueue(new Callback<Order.OrderMenuList>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<Order.OrderMenuList> call, Response<Order.OrderMenuList> response) {
                        if (response.isSuccessful()) {
                            Log.d("orderMenu 성공", "orderMenu 성공");
                            List<OrderMenu> orderMenuList = response.body().getOrderMenuList();

                            String[] list_menuName = new String[orderMenuList.size()];
                            int[] list_menuCount = new int[orderMenuList.size()];

                            int index = 0;

                            for (OrderMenu list : orderMenuList) {
                                list_menuName[index] = list.getMenuName();
                                list_menuCount[index] = Integer.parseInt(list.getQuantity());
                                index++;
                            }

                            for (int i = 0; i < orderMenuList.size(); i++) {
                                String value = list_menuName[i] + "\t\t\t\t" + list_menuCount[i] + "개\n";
                                sum = sum + value;
                            }

                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setTitle("주문메뉴");
                            builder.setMessage(sum).setPositiveButton("확인", null).create();
                            builder.show();
                        } else {
                            Log.d("orderMenu 실패1", "orderMenu 실패1"+response.errorBody().string());
                        }
                    }

                    @Override
                    public void onFailure(Call<Order.OrderMenuList> call, Throwable t) {
                        Log.d("orderMenu 실패2", "orderMenu 실패2");
                    }
                });


            }
        });

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap();
                map.put("shopId", mItems.get(position).getShopId());
                map.put("orderId", mItems.get(position).getOrderId());
                Log.d("jwt",jwt+mItems.get(position).getShopId());
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("알림");
                builder.setMessage("수락하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        responseBodyCall = Server.getInstance().getApi().orderAccept("Bearer " + jwt, map);
                        responseBodyCall.enqueue(new Callback<ResponseBody>() {
                            @SneakyThrows
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    Log.d("accept성공", "accept성공");
                                    mItems.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, mItems.size());

                                    Map<String, String> map = new HashMap();
                                    map.put("orderId2", orderId);
                                    map.put("msg", acceptMsg);
                                    map.put("status", "toUser");
                                    send(map);
                                } else {
                                    Log.d("accept실패1", "accept실패1"+response.errorBody().string());
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d("accept실패2", "accept실패2");
                            }
                        });

                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("알림");
                builder.setMessage("거절하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mItems.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mItems.size());

                        Map<String, String> map = new HashMap();
                        map.put("orderId2", orderId);
                        map.put("msg", rejectMsg);
                        map.put("status", "toUser");
                        send(map);
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    private void send(Map<String, String> map) {
        Log.d("token", device_token);
        Log.d("orderId", orderId);
        JSONObject requestData = new JSONObject();

        try {
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();

            dataObj.put("orderId2", map.get("orderId2"));
            dataObj.put("msg", map.get("msg"));
            dataObj.put("status", map.get("status"));

            requestData.put("data", dataObj);
            JSONArray idArray = new JSONArray();

            idArray.put(0, "dBQhgexlcJ0:APA91bGYorRaPqrOSnu8V1YIJWUp1YjNbIxQRpkXKno2bPISev8H0j9IGVnVxp7_yaTf6r26PC056hOpw0ok2lrJftrkDjOCprZUD36Z-n4cCVQkQdjznKdBNr8T9razUkQemycgL1eB");
            requestData.put("registration_ids", idArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendData(requestData, new SendResponseListener() {
            @Override
            public void onRequestCompleted() {
                Log.d("onRequestCompleted()","onRequestCompleted() 호출됨.");
            }

            @Override
            public void onRequestStarted() {
                Log.d("onRequestStarted()","onRequestStarted() 호출됨.");
            }

            @Override
            public void onRequestWithError(VolleyError error) {
                Log.d("onRequestWithError()","onRequestWithError() 호출됨.");
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

                Request.Method.POST, "https://fcm.googleapis.com/fcm/send", requestData, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onRequestCompleted();
            }
        }, new com.android.volley.Response.ErrorListener() {
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
                headers.put("Authorization", "key=AAAASNKJ6g4:APA91bEbZnK-PXSqFhJ1CVQ4DIa1NZ1NUHCtRyJ9fIWJPJBebuQosfVDa75uI0Nl7qQKZ8RFFb-s2H9bGVIBYw3od5zg6fKxPUgpms2Hk1O_IMxNAGU-8P4ir-Og-Z1lGIK1-ZYptD_t");
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

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView resTime, resDate, userId, orderRequest, orderMenu;
        private Button accept, reject;
        public ItemViewHolder(View itemView) {
            super(itemView);

            resTime = (TextView) itemView.findViewById(R.id.alarm_resTime);
            userId = (TextView) itemView.findViewById(R.id.alarm_userId);
            orderRequest = (TextView) itemView.findViewById(R.id.alarm_request);
            orderMenu = (TextView) itemView.findViewById(R.id.alarm_orderMenu);
            accept = (Button) itemView.findViewById(R.id.alarm_accept);
            reject = (Button) itemView.findViewById(R.id.alarm_reject);
        }
    }
}
