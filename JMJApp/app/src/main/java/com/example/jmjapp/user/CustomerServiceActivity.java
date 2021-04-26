package com.example.jmjapp.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.jmjapp.Adapter.CustomerServiceAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.CustomerService;

//고객센터
public class CustomerServiceActivity extends AppCompatActivity {

    CustomerServiceAdapter customerServiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bell_toolbar_customerservice);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        init();
        getData();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.rv_customerservice);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        customerServiceAdapter = new CustomerServiceAdapter();
        recyclerView.setAdapter(customerServiceAdapter);
    }

    private void getData() {
        CustomerService data = new CustomerService(R.drawable.customerservice, R.drawable.down,"자주 묻는 질문",
                "Q. 회원 탈퇴는 어떻게 하나요?\n" +
                                "A. 회원 탈퇴 시 주문의 민족 App 이용이 불가능하며 포인트 및 주문내역 등 모든 정보가 사라집니다.\n" +
                                "   탈퇴를 원하는 경우 [내정보 -> 회원탈퇴] 버튼을 클릭해주세요.\n" +
                                "\n" +
                                "Q. 리뷰 수정/삭제는 어떻게 하나요?\n" +
                                "A. 리뷰를 작성한 회원정보로 로그인하시면 리뷰 수정 및 삭제가 가능합니다.\n" +
                                "\n" +
                                "Q. 아이디/비밀번호를 잊어버렸어요.\n" +
                                "A. 아이디/비밀번호 찾기는 [로그인 -> 아이디/비밀번호 찾기] 에서 가능합니다.\n" +
                                "\n" +
                                "Q. 포인트 사용은 어떻게 하나요?\n" +
                                "A. 포인트 사용은 주문의 민족 앱을 통한 결제 시 이용할 수 있습니다.\n");

        customerServiceAdapter.addItem(data);
        data = new CustomerService(R.drawable.customerservice, R.drawable.down,"고객 안심센터",
                "<이용했던 가게에서 연락이 왔어요>\n" +
                                "노쇼 방지를 위해 가게로 전달된 주문정보는 개인정보 보호법에 따라 결제완료 후 파기하는 것이 원칙입니다.\n" +
                                "결제완료 후 전화,문자,메시지 등 연락이 지속된다면 고객 안심센터로 신고해 주세요.\n"+
                                "\n"+
                                "<저의 주소와 연락처가 공개되어 있어요>\n" +
                                "개인정보호호법에 따라 개인정보를 목적 외의 용도로 이용하거나 이를 제 3자에게 제공해선 안됩니다.\n" +
                                "개인정보가 일부라도 리뷰에 노출되었을 시 즉시 고객 안심센터로 신고해 주세요.\n"+
                                "\n"+
                                "<가게와의 갈등이 있었어요>\n" +
                                "위협적인 가게의 태도에 공포심을 느꼈거나 불미스러운 일이 발생했다면 바로 고객 안심센터로 신고해주세요.\n" +
                                "\n"+
                                "☎1111-2222 (24시간 연중무휴 고객센터)");
        customerServiceAdapter.addItem(data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}