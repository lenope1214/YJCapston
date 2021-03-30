package com.example.jmjapplication2;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.example.jmjapplication2.dto.Shop;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopDetailFragment_O extends Fragment {
    Button toggle_button_on, toggle_button_off, toggle_button_res_on, toggle_button_res_off;
    DataService dataService = new DataService();
    boolean is_check = true;
    boolean is_check2 = true;
    private android.app.AlertDialog dialog;

    public ShopDetailFragment_O() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.shop_detail_fragment_o, container, false);

        toggle_button_on = rootView.findViewById(R.id.toggle_button_on);
        toggle_button_off = rootView.findViewById(R.id.toggle_button_off);
        toggle_button_res_on = rootView.findViewById(R.id.toggle_button_res_on);
        toggle_button_res_off = rootView.findViewById(R.id.toggle_button_res_off);

        String jwt = ((JMJApplication) this.getActivity().getApplication()).getJwt();

        toggle_button_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_check == true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("매장을 열겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataService.updateOpen.updateOpen("Bearer " + jwt).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        Log.d("result : ", "연결성공");
                                        toggle_button_on.setBackgroundColor(Color.GREEN);
                                        toggle_button_off.setBackgroundColor(Color.LTGRAY);
                                        is_check = false;
                                    } else {
                                        Log.d("result : ", "연결실패");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("result : ", "연결실패2");
                                }
                            });
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("result", "ㅇ");
                        }
                    });
                    builder.show();
                } else {
                    return;
                }
            }
        });


        toggle_button_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_check == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("매장을 닫겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataService.updateOpen.updateOpen("Bearer " + jwt).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        Log.d("result : ", "연결성공");
                                        toggle_button_on.setBackgroundColor(Color.LTGRAY);
                                        toggle_button_off.setBackgroundColor(Color.RED);
                                        is_check = true;
                                    } else {
                                        Log.d("result : ", "연결실패");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("result : ", "연결실패2");
                                }
                            });
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("result : ", "아니오");
                        }
                    });
                    builder.show();
                } else {
                    return;
                }
            }
        });


        toggle_button_res_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_check2 == true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("예약을 열겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataService.updateIsRsPos.updateIsRsPos("Bearer " + jwt).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        Log.d("result : ", "연결성공");
                                        toggle_button_res_on.setBackgroundColor(Color.GREEN);
                                        toggle_button_res_off.setBackgroundColor(Color.LTGRAY);
                                        is_check2 = false;
                                    } else {
                                        Log.d("result : ", "연결실패");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("result : ", "연결실패2");
                                }
                            });
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("result", "아니오");
                        }
                    });
                    builder.show();
                } else {
                    return;
                }
            }
        });


        toggle_button_res_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_check2 == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("예약을 닫겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataService.updateIsRsPos.updateIsRsPos("Bearer " + jwt).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        Log.d("result : ", "연결성공");
                                        toggle_button_res_on.setBackgroundColor(Color.LTGRAY);
                                        toggle_button_res_off.setBackgroundColor(Color.RED);
                                        is_check2 = true;
                                    } else {
                                        Log.d("result : ", "연결실패");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("result : ", "연결실패2");
                                }
                            });
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("result : ", "아니오");
                        }
                    });
                    builder.show();
                } else {
                    return;
                }
            }
        });

        return rootView;
    }
}
