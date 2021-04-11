package com.example.jmjapplication2.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.jmjapplication2.Adapter.NoticeAdapter;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Notice;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {

    private ArrayList<Notice> arrayList;
    private NoticeAdapter noticeAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        recyclerView = (RecyclerView)findViewById(R.id.rv_notice);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        arrayList = new ArrayList<>();

        noticeAdapter = new NoticeAdapter(arrayList);
        recyclerView.setAdapter(noticeAdapter);

        Notice notice = new Notice(R.drawable.hamburger_icon,R.drawable.down, "공지사항1", "1번 공지사항 입니다.");
        arrayList.add(notice);
        Notice notice1 = new Notice(R.drawable.hamburger_icon,R.drawable.down, "공지사항2", "2번 공지사항 입니다.");
        arrayList.add(notice1);
        Notice notice2 = new Notice(R.drawable.hamburger_icon,R.drawable.down, "공지사항3", "3번 공지사항 입니다.");
        arrayList.add(notice2);
        Notice notice3 = new Notice(R.drawable.hamburger_icon,R.drawable.down, "공지사항4", "4번 공지사항 입니다.");
        arrayList.add(notice3);
        Notice notice4 = new Notice(R.drawable.hamburger_icon,R.drawable.down, "공지사항5", "5번 공지사항 입니다.");
        arrayList.add(notice4);
        Notice notice5 = new Notice(R.drawable.hamburger_icon,R.drawable.down, "공지사항6", "6번 공지사항 입니다.");
        arrayList.add(notice5);

    }
}