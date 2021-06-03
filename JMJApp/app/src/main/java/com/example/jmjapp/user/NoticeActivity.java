package com.example.jmjapp.user;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.NoticeAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Notice;

//MainActivity
public class NoticeActivity extends AppCompatActivity {

    NoticeAdapter noticeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bell_toolbar_customerservice);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        init();
        getData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.rv_notice);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        noticeAdapter = new NoticeAdapter();
        recyclerView.setAdapter(noticeAdapter);
    }

    private void getData() {
        Notice data = new Notice(R.drawable.noticeicon, R.drawable.down,"시스템 점검","주민사장님광장 신규 입점 신청 시스템 점검",
                "■ 주민사장님광장 광고 신청 시스템 점검 \n" +
                        "\n" +
                        "  - 작업 일시 : 2021년 4월 15일 (목) 오전 4시 30분 ~ 오전 8시 40분(약 4시간 10분)\n" +
                        "\n" +
                        "  - 작업 내용 : 주민 광고 입점 신청 프로세스 개선 \n" +
                        "\n" +
                        "  - 작업 영향 : 작업시간 내 주민사장님광장 및 주민셀프서비스 신규 입점 신청 일시 중단");
        noticeAdapter.addItem(data);
        data = new Notice(R.drawable.noticeicon, R.drawable.down,"시스템 점검","금융결제원 시스템 점검",
                "■ 금융결제원 시스템 점검 \n" +
                        "\n" +
                        "  - 작업 일시 : 2021년 4월 12일 (월) 오전 0시 ~ 오전 1시 (약 1시간)\n" +
                        "\n" +
                        "  - 작업 내용: 금융결제원 시스템 재기동\n" +
                        "\n" +
                        "  - 작업 영향: 작업시간 내 전 은행 타행 펌뱅킹 업무 서비스 중단(예금주 성명조회, 환불, 송금, 계좌점유인증 불가) ");
        noticeAdapter.addItem(data);
        data = new Notice(R.drawable.noticeicon, R.drawable.down,"변경사항","2021년 행정 경계 구역 업데이트 안내",
                "(!) 행정 경계 구역 업데이트란?\n" +
                        "\n" +
                        "    - 행정안전부 등 관련 정부기관에서는 인구 수와 지역개발, 지리적 여건 등을 고려하여 행정동을 추가 혹은 통합, 변경하고 있으며 수시로 조정하고 있습니다.\n" +
                        "\n" +
                        "    - 배달의민족은 정부 정책에 따라 행정동 정보를 정기적으로 최신화하여 서비스에 적용하고 있습니다. 이번 업데이트는 2020년 11월까지의 변경사항을 반영합니다."
                );
        noticeAdapter.addItem(data);
    }
}
