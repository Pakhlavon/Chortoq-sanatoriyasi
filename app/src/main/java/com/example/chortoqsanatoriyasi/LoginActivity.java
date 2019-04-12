package com.example.chortoqsanatoriyasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chortoqsanatoriyasi.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private ImageView logo;
    private EditText et_login,et_parol;
    private Button btn_login,btn_reg;
    private CheckBox checkBox;
    private TextView forget;
    private ProgressDialog loading;

    private String parentdbName="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo=(ImageView)findViewById(R.id.logo);

        et_login=(EditText)findViewById(R.id.et_login);
        et_parol=(EditText)findViewById(R.id.et_parol);

        btn_login=(Button)findViewById(R.id.btn_login);
        btn_reg=(Button)findViewById(R.id.btn_reg);

        checkBox=(CheckBox)findViewById(R.id.checkbox);
        forget=(TextView)findViewById(R.id.forget);
        loading=new ProgressDialog(this);

        Paper.init(this);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,RegistratsiyaActivity.class);
                startActivity(intent);

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                loginuser();
            }
        });

        Paper.init(this);

        String UserPhoneKey=Paper.book().read(Prevalent.UserPhoneKey);
        String UserParolKey=Paper.book().read(Prevalent.UserParolKey);

        if (UserPhoneKey!= "" && UserParolKey != "")
        {
            if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserParolKey))
            {
                allowAccess(UserPhoneKey,UserParolKey);

                loading.setTitle("Account Oldin Yaratilgan");
                loading.setMessage("Iltimos kuting! Xozir profilingiz ochiladi!!!");
                loading.setCanceledOnTouchOutside(false);
                loading.show();
            }
        }

    }



    private void allowAccess(final String phone, final String parol)
    {

        final DatabaseReference rootref;
        rootref= FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                if (dataSnapshot.child(parentdbName).child(phone).exists())
                {
                    Users usersdate=dataSnapshot.child(parentdbName).child(phone).getValue(Users.class);
                    if (usersdate.getPhone().equals(phone))
                    {
                        if (usersdate.getParol().equals(parol)){
                            Toast.makeText(LoginActivity.this, "Kirish muvaffaqiyatli amalga oshirildi...", Toast.LENGTH_SHORT).show();
                            loading.dismiss();

                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            Prevalent.CurrentOnlineUser =usersdate;
                            startActivity(intent);


                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login yoki Parol xato kiritilgan", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Accountda bu"+phone+"numer mavjud", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    private void loginuser()
    {
        String phone =et_login.getText().toString();
        String parol=et_parol.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Iltimos telefon raqamingizni kiriting", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(parol))
        {
            Toast.makeText(this, "Iltimos parolni kiriting", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loading.setTitle("Account yaratildi");
            loading.setMessage("Iltimos kuting! Sizning ma'lumotlaringiz tekshirilyapti");
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            kiritish(phone,parol);
        }
    }

    private void kiritish(final String phone, final String parol)
    {

        if (checkBox.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserParolKey,parol);
        }
        final DatabaseReference rootref;
        rootref= FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                if (dataSnapshot.child(parentdbName).child(phone).exists())
                {
                    Users usersdate=dataSnapshot.child(parentdbName).child(phone).getValue(Users.class);
                    if (usersdate.getPhone().equals(phone))
                    {
                        if (usersdate.getParol().equals(parol)){
                            Toast.makeText(LoginActivity.this, "Kirish muvaffaqiyatli amalga oshirildi...", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                           Prevalent.CurrentOnlineUser =usersdate;
                        startActivity(intent);
                        finish();


                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login yoki Parol xato kiritilgan", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }
                else 
                    {
                        Toast.makeText(LoginActivity.this, "Accountda bu"+phone+"numer mavjud", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
