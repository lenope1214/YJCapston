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
import com.example.jmjapp.network.Server;
import com.example.jmjapp.payment.PaymentResultActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    private String device_token, userId, orderId, jwt, orderRequest;
    private Long arriveTime;

    public AlarmListRecyclerAdapter(Context context, ArrayList<Order> alarm) {
        this.context = context;
        mItems = alarm;
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

        if (orderRequest.equals("")) {
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

        holder.orderMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
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

            idArray.put(0, device_token);
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
                headers.put("Authorization", "key=AAAAMsCw7yQ:APA91bGlT_VTfm9xHFMcguW36JMt6VXKVY2s-VgFnpUyrAZz2Gjhe8reRkLOMLp5dBG2kju74sUmeD4co8EQvZgszn4-4UUlaL0pdrttVcf9N50Jjk32Vdv1tGFASC0Nm_ss5VEdd68z");
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
