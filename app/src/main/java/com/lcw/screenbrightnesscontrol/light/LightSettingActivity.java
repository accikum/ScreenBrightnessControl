package com.lcw.screenbrightnesscontrol.light;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.lcw.screenbrightnesscontrol.BaseActivity;
import com.lcw.screenbrightnesscontrol.R;


/**
 * Created by luchangwei on 2015/7/30.
 */
public class LightSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.light_setting_activity);
    }
}
