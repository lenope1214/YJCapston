//package com.example.jmjapp.user;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import androidx.appcompat.app.AppCompatActivity;
//import com.example.jmjapp.R;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class LogoutActivity extends AppCompatActivity implements View.OnClickListener {
//    Button btnRevoke, btnLogout;
//    private FirebaseAuth mAuth ;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_after_login);
//
//        btnLogout = (Button)findViewById(R.id.btn_logout);
//        btnRevoke = (Button)findViewById(R.id.btn_revoke);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        btnLogout.setOnClickListener(this);
//        btnRevoke.setOnClickListener(this);
//    }
//
//    private void signOut() {
//        FirebaseAuth.getInstance().signOut();
//    }
//
//    private void revokeAccess() {
//        mAuth.getCurrentUser().delete();
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_logout:
//                signOut();
//                finishAffinity();
//                break;
//            case R.id.btn_revoke:
//                revokeAccess();
//                finishAffinity();
//                break;
//        }
//    }
//}