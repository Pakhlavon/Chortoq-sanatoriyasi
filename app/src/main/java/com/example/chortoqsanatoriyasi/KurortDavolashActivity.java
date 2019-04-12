package com.example.chortoqsanatoriyasi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class KurortDavolashActivity extends AppCompatActivity {
    private TextView txtmail,txtcall,txtfb,txtruy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kurortdavolashactivity);


        txtcall=(TextView)findViewById(R.id.txtcall);
        txtmail=(TextView)findViewById(R.id.txtmail);
        txtfb=(TextView)findViewById(R.id.txtfb);
        txtruy=(TextView)findViewById(R.id.txtruy);


        txtruy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(KurortDavolashActivity.this,RuyxatActivity.class);
                startActivity(intent);
            }
        });

    }

    public void txtcall(View view) {
        Intent i =new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:+998999923691"));
        startActivity(i);
    }

    public void txtmail(View view) {
        Intent i =new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("email"));
        String[] s={"sihatgoh_chortoq@mail.ru"};
        i.putExtra(Intent.EXTRA_EMAIL,s);
        i.putExtra(Intent.EXTRA_SUBJECT,"This is a subject");
        i.putExtra(Intent.EXTRA_TEXT,"Hii this is athe email body");
        i.setType("message/rfc822");
        Intent chooser =Intent.createChooser(i,"Launch email");
        startActivity(chooser);
    }

    public void txtfb(View view) {
        Intent sharingIntent=new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String uriString = "https://www.facebook.com/";
        sharingIntent.putExtra(Intent.EXTRA_TEXT,uriString);
        sharingIntent.setPackage("com.facebook.katana");
        startActivity(sharingIntent);

    }
}
