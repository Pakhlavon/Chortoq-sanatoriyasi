package com.example.chortoqsanatoriyasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegistratsiyaActivity extends AppCompatActivity {
    private ImageView logo;
    private EditText et_login_reg,et_parol_reg,et_ism_reg;
    private Button btn_reg;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registratsiya);

        logo=(ImageView)findViewById(R.id.logo);

        et_login_reg=(EditText)findViewById(R.id.et_login_reg);
        et_parol_reg=(EditText)findViewById(R.id.et_parol_reg);
        et_ism_reg=(EditText)findViewById(R.id.et_ism_reg);

        btn_reg=(Button)findViewById(R.id.btn_reg);

        loading= new ProgressDialog(this);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccounted();
            }
        });

    }

    private void createAccounted()
    {
        String name= et_ism_reg.getText().toString();
        String phone =et_login_reg.getText().toString();
        String parol =et_parol_reg.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Iltimos Ismingiz va Familiyangizni kiriting!!!", Toast.LENGTH_SHORT).show();

        }else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Iltimos Telefon Raqamingizni kiriting!!!", Toast.LENGTH_SHORT).show();

        }else if (TextUtils.isEmpty(parol))
        {
            Toast.makeText(this, "Iltimos Parolni kiriting!!!", Toast.LENGTH_SHORT).show();

        }else
        {
            loading.setTitle("Account Yaratilyapti!!!");
            loading.setMessage("Iltimos kuting! Sizning ma'lumotlaringiz tekshirilyapti!!!");
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            Tasdiqlash(name,phone,parol);
        }

    }

    private void Tasdiqlash(final String name, final String phone, final String parol)
    {

       final DatabaseReference rootref;
       rootref=FirebaseDatabase.getInstance().getReference();

       rootref.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot)
           {

               if (!(dataSnapshot.child("Users").child(phone).exists()))
               {

                   HashMap<String,Object> userdataMap =new HashMap<>();
                   userdataMap.put("phone",phone);
                   userdataMap.put("parol",parol);
                   userdataMap.put("ism",name);

                   rootref.child("Users").child(phone).updateChildren(userdataMap)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful())
                                   {
                                       Toast.makeText(RegistratsiyaActivity.this, "Tabriklaymiz Yangi account yaratildi", Toast.LENGTH_SHORT).show();
                                       loading.dismiss();

                                       Intent intent=new Intent(RegistratsiyaActivity.this,LoginActivity.class);
                                       startActivity(intent);
                                   }
                                   else {

                                       loading.dismiss();
                                       Toast.makeText(RegistratsiyaActivity.this, "Internetga ulanishda xatolik!!! Iltimos tekshirib ko'ring", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
               }
               else
               {
                   Toast.makeText(RegistratsiyaActivity.this, "Bu"+phone+"oldindan mavjud", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                   Toast.makeText(RegistratsiyaActivity.this, "Iltimos yana qaytadan urinib ko'ring", Toast.LENGTH_SHORT).show();

                   Intent intent=new Intent(RegistratsiyaActivity.this,MainActivity.class);

                   finish();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
}
