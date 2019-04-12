package com.example.chortoqsanatoriyasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class BolimlarActivity extends AppCompatActivity {

    private CardView massaj,balchiq,omillar,tasir,rejim,vanna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolimlar);

        massaj=(CardView)findViewById(R.id.massaj);
        balchiq=(CardView)findViewById(R.id.balchiq);
        omillar=(CardView)findViewById(R.id.omillar);
        tasir=(CardView)findViewById(R.id.tasir);
        rejim=(CardView)findViewById(R.id.rejim);
        vanna=(CardView)findViewById(R.id.vanna);

        massaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BolimlarActivity.this,MassajActivity.class);
                startActivity(intent);
                finish();
            }
        });

        balchiq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BolimlarActivity.this,BalchiqActivity.class);
                startActivity(intent);
                finish();

            }
        });

        omillar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BolimlarActivity.this,KurortDavolashActivity.class);
                startActivity(intent);
                finish();

            }
        });

        tasir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BolimlarActivity.this,InsonOrganizmiActivity.class);
                startActivity(intent);
                finish();

            }
        });

        rejim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BolimlarActivity.this,KurortDavolashActivity.class);
                startActivity(intent);
                finish();

            }
        });

        vanna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BolimlarActivity.this,VannaActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
