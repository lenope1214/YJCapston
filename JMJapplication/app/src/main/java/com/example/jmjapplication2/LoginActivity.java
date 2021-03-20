package com.example.jmjapplication2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jmjapplication2.dto.MemberDTO;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.*;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    DataService dataService = new DataService();

    LinearLayout btn_signup;
    LinearLayout naver_login;

    Button btn_logout;
    Button btn_login;

    OAuthLogin mOAuthLoginModule;
    Context mContext;

    EditText et_login_id;
    EditText et_login_pw;

    FirebaseAuth mAuth = null;
    GoogleSignInClient mGoogleSignInClient;


    private AlertDialog dialog;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);

        et_login_id = (EditText) findViewById(R.id.et_login_id);
        et_login_pw = (EditText) findViewById(R.id.et_login_pw);
        btn_login = findViewById(R.id.btn_login);
        naver_login = findViewById(R.id.naver_login);
        btn_logout = findViewById(R.id.btn_logout);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_login_id.getText().toString().length() == 0 || et_login_pw.getText().toString().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("아이디와 비밀번호를 확인해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                } else {
                    String id = et_login_id.getText().toString();
                    String pw = et_login_pw.getText().toString();
                    dataService.login.LoginOne(id, pw).enqueue(new Callback<MemberDTO>() {
                        @Override
                        public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getCode() == 200) {
                                    Log.d("result : ", "로그인 성공!");

                                    SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("token", response.body().getId());
                                    Log.d("getid:", response.body().getId());
                                    editor.apply();

                                    ((JMJApplication) getApplication()).setId(response.body().getId());
                                    ((JMJApplication) getApplication()).setRole(response.body().getRole());

                                    if (response.body().getRole().equals("ROLE_USER")) {
                                        Log.d("result : ", "일반 회원 로그인 성공" + response.body().getId() + "님");

                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                        dialog = builder.setMessage("로그인되었습니다").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }).create();
                                        builder.setCancelable(false);
                                        dialog.show();
                                    } else {
                                        Log.d("result : ", "사업자 회원 로그인 성공" + response.body().getId() + "님");

                                        dataService.findshop.findshopOne(response.body().getId()).enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.isSuccessful()) {
                                                    Log.d("result : ", "연결 성공");

                                                    ResponseBody body = response.body();
                                                    String result = null;
                                                    try {
                                                        result = body.string();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }

                                                    if (result.equals("가게없음")) {
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                                        dialog = builder.setMessage("로그인되었습니다").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                Intent intent = new Intent(LoginActivity.this, RegisterNoActivity.class);
                                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                startActivity(intent);
                                                                finish();
                                                            }
                                                        }).create();
                                                        builder.setCancelable(false);
                                                        dialog.show();
                                                    } else {
                                                        Log.d("result : ", String.valueOf(response.body()));
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                                        dialog = builder.setMessage("로그인되었습니다").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                Intent intent = new Intent(LoginActivity.this, MainActivity_O.class);
                                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                startActivity(intent);
                                                                finish();
                                                            }
                                                        }).create();
                                                        builder.setCancelable(false);
                                                        dialog.show();
                                                    }
                                                } else {
                                                    Log.d("result : ", "네트워크 오류");
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                            }
                                        });
                                    }
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    dialog = builder.setMessage("아이디와 비밀번호를 확인해 주세요.").setPositiveButton("확인", null).create();
                                    dialog.show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MemberDTO> call, Throwable t) {

                        }
                    });
                }
            }
        });

        naver_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOAuthLoginModule = OAuthLogin.getInstance();
                mOAuthLoginModule.init(
                        mContext
                        , getString(R.string.naver_client_id)
                        , getString(R.string.naver_client_secret)
                        , getString(R.string.naver_client_name)
                );

                @SuppressLint("HandlerLeak")
                OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
                    @Override
                    public void run(boolean success) {
                        if (success) {
                            String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                            String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                            long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                            String tokenType = mOAuthLoginModule.getTokenType(mContext);

                            Log.i("LoginData", "accessToken : " + accessToken);
                            Log.i("LoginData", "refreshToken : " + refreshToken);
                            Log.i("LoginData", "expiresAt : " + expiresAt);
                            Log.i("LoginData", "tokenType : " + tokenType);
                        } else {
                            String errorCode = mOAuthLoginModule.getLastErrorCode(mContext).getCode();
                            String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                            Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                        }
                    }

                    ;
                };
                mOAuthLoginModule.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);


            }
        });

//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOAuthLoginModule.logout(mContext);
//                Toast.makeText(LoginActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
//            }
//        });

        signInButton = findViewById(R.id.google_login);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        btn_signup = (LinearLayout) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Snackbar.make(findViewById(R.id.login_layout), "Authentication Successed.", Snackbar.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Snackbar.make(findViewById(R.id.login_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this, LogoutActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
