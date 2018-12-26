package com.wiseyq.shadowbtn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View drawView1, drawView2, selectorView;
    int normalColor = Color.parseColor("#959595");
    int enableColor = Color.parseColor("#ff9b1a");
    int shadowSize = 6;//dp
    int cornerSize = 5;//dp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initShadow();
    }

    private void initShadow() {
        ShadowUtil.setShadowBg(drawView1, normalColor, normalColor, cornerSize, shadowSize, shadowSize);
        ShadowUtil.setShadowBg(drawView2, enableColor, enableColor, cornerSize, shadowSize, shadowSize);
        ShadowUtil.setShadowSelectorBg(selectorView, normalColor, enableColor, normalColor, enableColor, cornerSize, shadowSize, shadowSize);
    }


    private void initView() {
        drawView1 = findViewById(R.id.relay_draw1);
        drawView2 = findViewById(R.id.relay_draw2);
        selectorView = findViewById(R.id.relay_draw3);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CardView实现方式！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListener() {
        drawView1.setOnClickListener(this);
        drawView2.setOnClickListener(this);
        selectorView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.relay_draw1:

                break;
            case R.id.relay_draw2:

                break;
            case R.id.relay_draw3:

                break;
            default:
                break;
        }
    }
}
