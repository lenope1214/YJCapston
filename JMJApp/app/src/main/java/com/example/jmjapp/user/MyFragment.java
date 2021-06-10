package com.example.jmjapp.user;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.jmjapp.JMJApplication;
import com.example.jmjapp.R;

public class MyFragment extends Fragment {
    ImageButton loginButton;
    ConstraintLayout login_btn;
    ConstraintLayout profile_btn;
    TextView profile_text;

    ConstraintLayout point_record;
    ConstraintLayout visit_record;
    ConstraintLayout review_record;
    ConstraintLayout zzim_record;

    ConstraintLayout notice_constraint;
    ConstraintLayout service_constraint;
    ConstraintLayout info_constraint;

    private String user_id;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.my_fragment, container, false);

        Bundle bundle = getArguments();
        user_id = bundle.getString("user_id", "nothing");

        profile_text = rootView.findViewById(R.id.profile_text);

        point_record = rootView.findViewById(R.id.point_record);
        visit_record = rootView.findViewById(R.id.visit_record);
        review_record = rootView.findViewById(R.id.review_record);
        zzim_record = rootView.findViewById(R.id.zzim_record);

        login_btn = rootView.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        profile_btn = rootView.findViewById(R.id.linear_btn);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileUpdateActivity.class);
                startActivity(intent);
            }
        });

        point_record.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user_id.equals("nothing")) {
                    new AlertDialog.Builder(getActivity()) // TestActivity 부분에는 현재 Activity의 이름 입력.
                            .setMessage("로그인이 필요한 서비스입니다.")     // 제목 부분 (직접 작성)
                            .setPositiveButton("로그인", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent); // 실행할 코드
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                                public void onClick(DialogInterface dialog, int which) {
                                    // 실행할 코드
                                }
                            })
                            .show();
                } else {
                    Intent intent = new Intent(getActivity(), RecordPoint.class);
                    startActivity(intent);
                }

            }
        });


        visit_record.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user_id.equals("nothing")) {
                    new AlertDialog.Builder(getActivity()) // TestActivity 부분에는 현재 Activity의 이름 입력.
                            .setMessage("로그인이 필요한 서비스입니다.")     // 제목 부분 (직접 작성)
                            .setPositiveButton("로그인", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent); // 실행할 코드
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                                public void onClick(DialogInterface dialog, int which) {
                                    // 실행할 코드
                                }
                            })
                            .show();
                } else {
                    Intent intent = new Intent(getActivity(), RecordVisit.class);
                    startActivity(intent);
                }

            }
        });


        review_record.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user_id.equals("nothing")) {
                    new AlertDialog.Builder(getActivity()) // TestActivity 부분에는 현재 Activity의 이름 입력.
                            .setMessage("로그인이 필요한 서비스입니다.")     // 제목 부분 (직접 작성)
                            .setPositiveButton("로그인", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent); // 실행할 코드
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                                public void onClick(DialogInterface dialog, int which) {
                                    // 실행할 코드
                                }
                            })
                            .show();
                } else {
                    Intent intent = new Intent(getActivity(), RecordReview.class);
                    startActivity(intent);
                }

            }
        });


        zzim_record.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user_id.equals("nothing")) {
                    new AlertDialog.Builder(getActivity()) // TestActivity 부분에는 현재 Activity의 이름 입력.
                            .setMessage("로그인이 필요한 서비스입니다.")     // 제목 부분 (직접 작성)
                            .setPositiveButton("로그인", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent); // 실행할 코드
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                                public void onClick(DialogInterface dialog, int which) {
                                    // 실행할 코드
                                }
                            })
                            .show();
                } else {
                    Intent intent = new Intent(getActivity(), RecordZzim.class);
                    startActivity(intent);
                }

            }
        });

        notice_constraint = rootView.findViewById(R.id.notice_constraint);
        notice_constraint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });

        service_constraint = rootView.findViewById(R.id.service_constraint);
        service_constraint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CustomerServiceActivity.class);
                startActivity(intent);
            }
        });

        info_constraint = rootView.findViewById(R.id.info_constraint);
        info_constraint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OperationGuideActivity.class);
//                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (user_id.equals("nothing")) {
            login_btn.setVisibility(View.VISIBLE);
            profile_btn.setVisibility(View.GONE);
        } else {
            login_btn.setVisibility(View.GONE);
            profile_btn.setVisibility(View.VISIBLE);
            profile_text.setText(user_id + "님 안녕하세요");
        }
    }
}
