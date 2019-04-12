package com.example.chortoqsanatoriyasi;

        import android.animation.ArgbEvaluator;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.example.chortoqsanatoriyasi.Adapter.Adapter;
        import com.example.chortoqsanatoriyasi.Model.Model;

        import java.util.ArrayList;
        import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors=null;
    ArgbEvaluator argbEvaluator=new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        models=new ArrayList<>();
        models.add(new Model(R.drawable.b,"ko`rinish","Bu Sanatoriyaning tashqi ko'rinishi"));
        models.add(new Model(R.drawable.a,"Sanatoriya haqida","Bu Sanatoriyaning tashqi ko'rinishi"));
        models.add(new Model(R.drawable.d,"ko`rinish","Bu Sanatoriyaning tashqi ko'rinishi"));
        models.add(new Model(R.drawable.f,"ko`rinish","Bu Sanatoriyaning tashqi ko'rinishi"));
        models.add(new Model(R.drawable.s,"ko`rinish","Bu Sanatoriyaning tashqi ko'rinishi"));
        models.add(new Model(R.drawable.r,"ko`rinish","Bu Sanatoriyaning tashqi ko'rinishi"));
        models.add(new Model(R.drawable.g,"ko`rinish","Bu Sanatoriyaning tashqi ko'rinishi"));
        models.add(new Model(R.drawable.h,"ko`rinish","Bu Sanatoriyaning tashqi ko'rinishi"));

        adapter=new Adapter(models,this);
        viewPager=findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1 ),
                getResources().getColor(R.color.color2 ),
                getResources().getColor(R.color.color3 ),
                getResources().getColor(R.color.color4 )

        };

        colors=colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

                if (i<(adapter.getCount()-1) && i<(colors.length-1))
                {
                    viewPager.setBackgroundColor
                            (
                            (Integer)argbEvaluator.evaluate(
                                    v,
                                    colors[i],
                                    colors[i+1]
                            )
                    );
                }
                else
                {
                    viewPager.setBackgroundColor(colors[colors.length -1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
