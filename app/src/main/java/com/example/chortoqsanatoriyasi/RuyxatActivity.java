package com.example.chortoqsanatoriyasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RuyxatActivity extends AppCompatActivity {
    private TextView txtariza;
    private EditText et_ar_ism,et_ar_phone,et_ar_ariza;
    private Button btn_ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruyxat);

        txtariza=(TextView)findViewById(R.id.txtariza);

        et_ar_ism=(EditText)findViewById(R.id.et_ar_ism);
        et_ar_phone=(EditText)findViewById(R.id.et_ar_phone);
        et_ar_ariza=(EditText)findViewById(R.id.et_ar_ariza);

        btn_ar=(Button)findViewById(R.id.btn_ar);


    }
}
