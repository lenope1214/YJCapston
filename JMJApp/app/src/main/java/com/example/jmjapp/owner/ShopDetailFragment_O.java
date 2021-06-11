package com.example.jmjapp.owner;

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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.jmjapp.IntroScreen;
import com.example.jmjapp.JMJApplication;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;


public class ShopDetailFragment_O extends Fragment {
    Button toggle_button_on, toggle_button_off, toggle_button_res_on, toggle_button_res_off;
    TextView owner_logout_btn, owner_name_tv;
    ConstraintLayout shop_detail_menu_button, shop_detail_update_button, move_employee_option, move_review_option, move_res_option, move_chat_option;
    boolean is_check = true;
    boolean is_check2 = true;
    private android.app.AlertDialog dialog;
    private String jwt, shopNumber, owner_id;
    private String isOpen, isRsPos;
    private Map<String, String> map = new HashMap();

    private Call<Shop> shopCall;
    private Call<ResponseBody> responseBodyCall;

    Context context;

    public ShopDetailFragment_O() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.shop_detail_fragment_o2, container, false);

        toggle_button_on = rootView.findViewById(R.id.toggle_button_on);
        toggle_button_off = rootView.findViewById(R.id.toggle_button_off);
        toggle_button_res_on = rootView.findViewById(R.id.toggle_button_res_on);
        toggle_button_res_off = rootView.findViewById(R.id.toggle_button_res_off);
        owner_logout_btn = rootView.findViewById(R.id.owner_logout_btn);
        owner_name_tv = rootView.findViewById(R.id.owner_name_tv);
        move_employee_option = rootView.findViewById(R.id.move_employee_option);
        move_chat_option = rootView.findViewById(R.id.move_chat_option);

        Bundle bundle = getArguments();
        shopNumber = bundle.getString("shopNumber", "dwad");

        //jwt = ((JMJApplication) this.getActivity().getApplication()).getJwt();

        SharedPreferences pref = getActivity().getSharedPreferences("auth_o", Context.MODE_PRIVATE);
        owner_id = pref.getString("owner_id", null);
        jwt = pref.getString("token", null);

        owner_name_tv.setText(owner_id + "님");

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
                        SharedPreferences pref = context.getSharedPreferences("auth_o", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.remove("token");
                        editor.remove("owner_id");
                        editor.remove("role");
                        editor.apply();

                        // 앱 변수버리기
                        ((JMJApplication) getContext().getApplicationContext()).setId(null);
                        ((JMJApplication) getContext().getApplicationContext()).setJwt(null);
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

        shopCall = Server.getInstance().getApi().shop(shopNumber);
        shopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.code() == 200) {
                    Log.d("re", "성공");
                    Log.d("RES", String.valueOf(response.body().getIsRsPos()));
                    Log.d("open", String.valueOf(response.body().getIsOpen()));
                    isOpen = String.valueOf(response.body().getIsOpen());
                    isRsPos = String.valueOf(response.body().getIsRsPos());

                    if (isOpen.equals("Y")) {
                        toggle_button_on.setBackgroundColor(Color.GREEN);
                        toggle_button_off.setBackgroundColor(Color.LTGRAY);
                        is_check = false;
                    } else {
                        toggle_button_on.setBackgroundColor(Color.LTGRAY);
                        toggle_button_off.setBackgroundColor(Color.RED);
                        is_check = true;
                    }

                    if (isRsPos.equals("Y")) {
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
                if (is_check == true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("매장을 열겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            responseBodyCall = Server.getInstance().getApi().updateOpen("Bearer " + jwt, shopNumber);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
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
                if (is_check == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("매장을 닫겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            responseBodyCall = Server.getInstance().getApi().updateOpen("Bearer " + jwt, shopNumber);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
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
                if (is_check2 == true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("예약을 열겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            responseBodyCall = Server.getInstance().getApi().updateIsRsPos("Bearer " + jwt, shopNumber);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
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
                if (is_check2 == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("예약을 닫겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            responseBodyCall = Server.getInstance().getApi().updateIsRsPos("Bearer " + jwt, shopNumber);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
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
                        intent.putExtra("shopNumber", shopNumber);
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

        shop_detail_update_button = rootView.findViewById(R.id.shop_detail_update_button);
        shop_detail_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShopUpdateActivity.class);
                intent.putExtra("shopNumber", shopNumber);
                startActivity(intent);
            }
        });

        move_employee_option = rootView.findViewById(R.id.move_employee_option);
        move_employee_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmployeesManagementActivity.class);
                intent.putExtra("shopNumber", shopNumber);
                startActivity(intent);
            }
        });

        move_chat_option.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChatbotManagementActivity.class);
            intent.putExtra("shopNumber", shopNumber);
            startActivity(intent);
            });

            move_review_option = rootView.findViewById(R.id.move_review_option);
            move_review_option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ReviewOwnerActivity.class);
                    startActivity(intent);
                }
            });

            move_res_option = rootView.findViewById(R.id.move_res_option);
            move_res_option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ReservationManagementActivity.class);
                    startActivity(intent);
                }
            });

            return rootView;
        }

        @Override
        public void onDestroy () {
            super.onDestroy();
            if (responseBodyCall != null)
                responseBodyCall.cancel();
            if (shopCall != null)
                shopCall.cancel();
        }

    }
