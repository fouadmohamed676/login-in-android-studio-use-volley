package com.example.bydone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Create_Acount extends AppCompatActivity {
    Button done;
    EditText emi,nam,pass,comm,phonee,bloo,addr,datee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__acount);
        done=findViewById(R.id.log_done);



        nam=findViewById(R.id.ednaame);
        emi=findViewById(R.id.edemail);
        pass=findViewById(R.id.edpassword);
        comm=findViewById(R.id.edcomment);
        phonee=findViewById(R.id.edphone);
        bloo=findViewById(R.id.edblood);
        addr=findViewById(R.id.edaddress);
        datee=findViewById(R.id.eddate);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regiter_api();

            }
        });
    }

    private void regiter_api() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://192.168.43.103/Project_Api/register.php",
                new Response.Listener<String>() {

                    ProgressDialog mProgressDialog = ProgressDialog.show(Create_Acount.this,
                            "جاري ...", "التحقق من اسم المستخدم وكلمة المرور", false, false);

                    @Override

                    public void onResponse(String response) {
                        Log.e("respnnnnnnnnhhf",response.toString());


                        tost_sussecc() ;
                        mProgressDialog.dismiss();
                        onBackPressed() ;


                        if (response.equals("SUCCSESS")) {

                            tost_sussecc() ;
                            mProgressDialog.dismiss();
                            onBackPressed() ;

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("erroer res",error.toString());

                        //   callme2();
                    }

                })

        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                String user_name = nam.getText().toString() ;
                String user_pass = pass.getText().toString() ;
                String user_emi = emi.getText().toString() ;
                String user_comment = comm.getText().toString() ;
                String user_phone = phonee.getText().toString() ;
                String user_address = addr.getText().toString() ;
                String user_blood = bloo.getText().toString() ;
                String user_date = datee.getText().toString() ;


                params.put("name", user_name);
                params.put("password", user_pass);
                params.put("email", user_emi);
                params.put("comment", user_comment);  //السجل الطبي
                params.put("phone", user_phone);
                params.put("blood", user_blood);
                params.put("address", user_address);
                params.put("date", user_date);


                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(Create_Acount.this);

        if (requestQueue ==null)
            requestQueue.add(stringRequest);

        else
        {
            requestQueue.cancelAll(Create_Acount.this);
            requestQueue.add(stringRequest);

        }


    }

    private void tost() {
        Toast toast=Toast.makeText(this,"Error Password Try Again",Toast.LENGTH_SHORT);
        toast.show();
    }

    private void tost_sussecc() {
        Toast toast=Toast.makeText(this,"تم تسجيل الدخول بنجاح !!",Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}