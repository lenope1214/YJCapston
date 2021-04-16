package com.example.jmjapplication2.user;

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

import com.example.jmjapplication2.JMJApplication;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Shop;
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
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    ArrayList<Shop> mItems = new ArrayList<>();

    private AlertDialog dialog;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.jmjapplication2.R.layout.activity_login2);

        mContext = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(com.example.jmjapplication2.R.id.login_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(com.example.jmjapplication2.R.drawable.close);

        et_login_id = (EditText) findViewById(com.example.jmjapplication2.R.id.et_login_id);
        et_login_pw = (EditText) findViewById(com.example.jmjapplication2.R.id.et_login_pw);
        btn_login = findViewById(com.example.jmjapplication2.R.id.btn_login);
        naver_login = findViewById(com.example.jmjapplication2.R.id.naver_login);
        btn_logout = findViewById(com.example.jmjapplication2.R.id.btn_logout);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_login_id.getText().toString().length() == 0 || et_login_pw.getText().toString().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("아이디와 비밀번호를 확인해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                } else {
                    String id = et_login_id.getText().toString();
                    String password = et_login_pw.getText().toString();
                    Map<String, String> map = new HashMap();
                    map.put("id", id);
                    map.put("password", password);

                    dataService.read.LoginOne(map).enqueue(new Callback<ResponseBody>() {
                        @SneakyThrows
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.code() == 200) { // 로그인성공
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                Log.d("result ", String.valueOf(jsonObject));
                                String jwt = (String) jsonObject.get("access_token");
                                String role = (String) jsonObject.get("role");
                                Log.d("jsonobject :: jwt >> ", jwt);
                                Log.d("jsonobject :: role >> ", role);
                                Log.d("jsonobject :: userid >> ", et_login_id.getText().toString());

                                if(role.equals("ROLE_USER")) {
                                    SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("token", jwt);
                                    editor.putString("user_id", et_login_id.getText().toString());
                                    editor.putString("role", "ROLE_USER");
                                    editor.apply();

                                    ((JMJApplication)getApplication()).setId(et_login_id.getText().toString());
                                    ((JMJApplication)getApplication()).setJwt(jwt);

                                    Log.d("result : " , "일반사용자 로그인성공!");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    dialog = builder.setMessage("로그인되었습니다").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).create();
                                    builder.setCancelable(false);
                                    dialog.show();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    dialog = builder.setMessage("아이디와 비밀번호를 확인해 주세요.").setPositiveButton("확인", null).create();
                                    dialog.show();
                                }
                            } else if(response.code() == 400){
                                Log.d("result @@@: " , "로그인실패");
                                Log.d("resul@#$%#@t : " , response.errorBody().string());
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("아이디와 비밀번호를 확인해 주세요.").setPositiveButton("확인", null).create();
                                dialog.show();
                            } else {
                                Log.d("resul@#$%#@t : " , response.errorBody().string());
                                Log.d("result : " , "연결실패");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

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
                        , getString(com.example.jmjapplication2.R.string.naver_client_id)
                        , getString(com.example.jmjapplication2.R.string.naver_client_secret)
                        , getString(com.example.jmjapplication2.R.string.naver_client_name)
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

        signInButton = findViewById(com.example.jmjapplication2.R.id.google_login);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(com.example.jmjapplication2.R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        btn_signup = (LinearLayout) findViewById(com.example.jmjapplication2.R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
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
                            Snackbar.make(findViewById(com.example.jmjapplication2.R.id.login_layout), "Authentication Successed.", Snackbar.LENGTH_SHORT).show();
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
