package com.example.jmjapplication2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.example.jmjapplication2.dto.Shop;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;


public class ShopDetailFragment_O extends Fragment {
    Button toggle_button_on, toggle_button_off, toggle_button_res_on, toggle_button_res_off, owner_logout_btn, shop_detail_menu_button;
    DataService dataService = new DataService();
    boolean is_check = true;
    boolean is_check2 = true;
    private android.app.AlertDialog dialog;
    private String jwt, shopNumber;
    private String isOpen, isRsPos;
    private Map<String, String> map = new HashMap();
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
        owner_logout_btn = rootView.findViewById(R.id.owner_logout_btn);

        Bundle bundle = getArguments();
        shopNumber = bundle.getString("shopNumber","dwad");
        
        jwt = ((JMJApplication) this.getActivity().getApplication()).getJwt();

        owner_logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("알림");
                builder.setMessage("로그아웃 하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), IntroScreen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        // 값버리기
                        Context context = getActivity();
                        SharedPreferences pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.remove("token");
                        editor.apply();

                        // 앱 변수버리기
                        ((JMJApplication)getContext().getApplicationContext()).setId(null);
                        ((JMJApplication)getContext().getApplicationContext()).setJwt(null);
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("result", "아니오");
                    }
                });
                builder.show();
            }
        });

        dataService.shop.shop(shopNumber).enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if(response.code() == 200) {
                    Log.d("re", "성공");
                    Log.d("RES", String.valueOf(response.body().getIsRsPos()));
                    Log.d("open", String.valueOf(response.body().getIsOpen()));
                    isOpen = String.valueOf(response.body().getIsOpen());
                    isRsPos = String.valueOf(response.body().getIsRsPos());

                    if(isOpen.equals("Y")) {
                        toggle_button_on.setBackgroundColor(Color.GREEN);
                        toggle_button_off.setBackgroundColor(Color.LTGRAY);
                        is_check = false;
                    } else {
                        toggle_button_on.setBackgroundColor(Color.LTGRAY);
                        toggle_button_off.setBackgroundColor(Color.RED);
                        is_check = true;
                    }

                    if(isRsPos.equals("Y")) {
                        toggle_button_res_on.setBackgroundColor(Color.GREEN);
                        toggle_button_res_off.setBackgroundColor(Color.LTGRAY);
                        is_check2 = false;
                    } else {
                        toggle_button_res_on.setBackgroundColor(Color.LTGRAY);
                        toggle_button_res_off.setBackgroundColor(Color.RED);
                        is_check2 = true;
                    }
                } else {
                    Log.d("re", "실패");

                }
            }
            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                Log.d("re", "실패");

            }
        });

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
                            dataService.updateOpen.updateOpen("Bearer " + jwt, shopNumber).enqueue(new Callback<ResponseBody>() {
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
                            Log.d("result", "아니오");
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
                            dataService.updateOpen.updateOpen("Bearer " + jwt, shopNumber).enqueue(new Callback<ResponseBody>() {
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
                            dataService.updateIsRsPos.updateIsRsPos("Bearer " + jwt, shopNumber).enqueue(new Callback<ResponseBody>() {
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
                            dataService.updateIsRsPos.updateIsRsPos("Bearer " + jwt, shopNumber).enqueue(new Callback<ResponseBody>() {
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

        shop_detail_menu_button = rootView.findViewById(R.id.shop_detail_menu_button);
        shop_detail_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("메뉴 관리");
                builder.setMessage("메뉴 관리로 이동하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("ㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂ");
                        Intent intent = new Intent(getActivity(), MenuDetailActivity.class);
                        intent.putExtra("shopNumber",shopNumber);
                        startActivity(intent);
                        System.out.println("ㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅ");
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("result", "아니오");
                    }
                });
                builder.show();
            }
        });

        return rootView;
    }

}
