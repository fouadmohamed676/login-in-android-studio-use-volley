package com.example.bydone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class
MainActivity extends AppCompatActivity {
    Button btn, TestLogin, TestSingup;
    Button create;
    EditText pas;
    EditText email;
    //your URL Server  localhost or any server..
    String Url_data="http://192.168.43.103/Project_Api/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, Select_type.class));
        email = findViewById(R.id.edemail);
        pas = findViewById(R.id.edpass);
        btn = findViewById(R.id.btn_log);
        TestLogin = findViewById(R.id.btn_test);
        TestSingup = findViewById(R.id.btn_sin);
        create = findViewById(R.id.cr);

        create.setOnClickListener(view -> {
            // Avtivity To Create Account 
            Intent intent = new Intent(MainActivity.this, Create_Acount.class);
            startActivity(intent);
        });
        TestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RateScreen.class);
                startActivity(intent);
            }
        });
        TestSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Select_type.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserName = email.getText().toString();
                if (TextUtils.isEmpty(strUserName)) {
                    email.setError("not Empty");
                    return;
                }
                String strPassword = pas.getText().toString();
                if (TextUtils.isEmpty(strPassword)) {
                    pas.setError("not Empty");
                    return;
                }
                login_api();
            }
        });
    }
    private void login_api() {
        if (pas.equals("") && email.equals("")) {

            tost_fo();

        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST,Url_data,
                    new Response.Listener<String>() {
                        ProgressDialog mProgressDialog = ProgressDialog.show(MainActivity.this,
                                "جاري ...", "التحقق من اسم المستخدم وكلمة المرور", false, false);

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray user = jsonObject.getJSONArray("login_result");
                                JSONObject res2 = user.getJSONObject(0);
                                int b = res2.getInt("id");
                                if (b >= 1) {
                                    mProgressDialog.dismiss();

                                    Intent open_new_screen = new Intent(MainActivity.this, second.class);
                                    pas.setText("");
                                    email.setText("");
                                    startActivity(open_new_screen);


                                } else if (user.equals("[]")) {

                                    tost();
                                    Log.e("volley", "pass or email");
                                    mProgressDialog.dismiss();
                                } else {
                                    tost();
                                    Log.e("volley", "pass or email");
                                    mProgressDialog.dismiss();

                                }

                            } catch (JSONException e) {
                                tost();
                                e.printStackTrace();
                                mProgressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("خطأ في كلمة السر", error.toString());
                        }

                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    String user_email = email.getText().toString();
                    String user_pass = pas.getText().toString();
                    params.put("email", user_email);
                    params.put("password", user_pass);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            if (requestQueue == null)
                requestQueue.add(stringRequest);
            else {
                requestQueue.cancelAll(MainActivity.this);
                requestQueue.add(stringRequest);
            }
        }
    }
    void tost() {
        Toast toast = Toast.makeText(this, "خطأ في كلمة السر", Toast.LENGTH_SHORT);
        toast.show();
    }

    void tost_fo() {
        Toast toast = Toast.makeText(this, "أدخل اسم المستخدم وكلمة المرور", Toast.LENGTH_SHORT);
        toast.show();
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "إضغط مره اخرى للخروج", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
