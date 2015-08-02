package com.lcw.screenbrightnesscontrol.light;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import com.lcw.screenbrightnesscontrol.BaseActivity;
import com.lcw.screenbrightnesscontrol.R;
import com.lcw.screenbrightnesscontrol.util.SharePerfenceUtil;


/**
 * Created by luchangwei on 2015/7/30.
 */
public class LightSettingActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private Button main_exit_btn;
    private Button main_back_btn;

    private SeekBar seekBar_alpha;
    private SeekBar seekBar_red;
    private SeekBar seekBar_green;
    private SeekBar seekBar_blue;
    private SeekBar seekBar_light;

    SharePerfenceUtil spfUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.light_setting_activity);
        spfUtil = SharePerfenceUtil.getInstance(getApplicationContext());
        initView();
        intent = new Intent();
        intent.setClass(getApplicationContext(), LightService.class);
        startService(intent);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus==true) {
            seekBar_alpha.setProgress(spfUtil.getInt("alpha"));
            seekBar_red.setProgress(spfUtil.getInt("red"));
            seekBar_green.setProgress(spfUtil.getInt("green"));
            seekBar_blue.setProgress(spfUtil.getInt("blue"));
            seekBar_light.setProgress(spfUtil.getInt("light"));
        }else {
            onBackPressed();
        }
    }

    private static Intent intent;

    private void initView() {
        main_exit_btn = findView(R.id.main_exit_btn);
        main_exit_btn.setOnClickListener(this);
        main_back_btn = findView(R.id.main_back_btn);
        main_back_btn.setOnClickListener(this);

        seekBar_alpha = findView(R.id.seekBar_alpha);
        seekBar_alpha.setOnSeekBarChangeListener(this);
        seekBar_red = findView(R.id.seekBar_red);
        seekBar_red.setOnSeekBarChangeListener(this);
        seekBar_green = findView(R.id.seekBar_green);
        seekBar_green.setOnSeekBarChangeListener(this);
        seekBar_blue = findView(R.id.seekBar_blue);
        seekBar_blue.setOnSeekBarChangeListener(this);
        seekBar_light = findView(R.id.seekBar_light);
        seekBar_light.setOnSeekBarChangeListener(this);
        try {
            seekBar_light.setProgress(Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS));
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
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
    private int alpha;
    private int red;
    private int green;
    private int blue;
    private int light;
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        alpha = seekBar_alpha.getProgress();
        red = seekBar_red.getProgress();
        green = seekBar_green.getProgress();
        blue = seekBar_blue.getProgress();
        switch (seekBar.getId()) {
            case R.id.seekBar_alpha:
                alpha = seekBar_alpha.getProgress();
                LightService.view.setBackgroundColor(Color.argb(alpha, red, green, blue));
                break;

            case R.id.seekBar_red:
                red = seekBar_red.getProgress();
                LightService.view.setBackgroundColor(Color.argb(alpha, red, green, blue));
                break;

            case R.id.seekBar_green:
                green = seekBar_green.getProgress();
                LightService.view.setBackgroundColor(Color.argb(alpha, red, green, blue));
                break;

            case R.id.seekBar_blue:
                blue = seekBar_blue.getProgress();
                LightService.view.setBackgroundColor(Color.argb(alpha, red, green, blue));
                break;
            case R.id.seekBar_light:
                light = seekBar_light.getProgress();
                Settings.System.putInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS_MODE,Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, light);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        spfUtil.putInt("alpha", alpha);
        spfUtil.putInt("red", red);
        spfUtil.putInt("green", green);
        spfUtil.putInt("blue", blue);
        spfUtil.putInt("light", light);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
