package com.lcw.screenbrightnesscontrol.light;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.lcw.screenbrightnesscontrol.BaseActivity;
import com.lcw.screenbrightnesscontrol.R;


/**
 * Created by luchangwei on 2015/7/30.
 */
public class LightSettingActivity extends BaseActivity implements View.OnClickListener {
    private Button main_exit_btn;
    private Button main_back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.light_setting_activity);

        initView();
        initLightMode();

        intent = new Intent();
        intent.setClass(getApplicationContext(), LightService.class);
        startService(intent);
    }

    public static View view;
    public static WindowManager manager;
    private static Intent intent;

    private void initLightMode() {
        manager = (WindowManager) getApplicationContext().getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams lp = lp = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        // | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
                        | WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW);

        view = new View(getApplicationContext());
        view.setBackgroundColor(getResources().getColor(R.color.system_color_tran));
        manager.addView(view, lp);
    }

    public Handler handler = new Handler();

    private void initView() {
        main_exit_btn = findView(R.id.main_exit_btn);
        main_exit_btn.setOnClickListener(this);
        main_back_btn = findView(R.id.main_back_btn);
        main_back_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_exit_btn:
                stopService(intent);
                finish();
                break;
            case R.id.main_back_btn:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
