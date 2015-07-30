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

import com.lcw.screenbrightnesscontrol.BaseActivity;
import com.lcw.screenbrightnesscontrol.R;


/**
 * Created by luchangwei on 2015/7/30.
 */
public class LightSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.light_setting_activity);

        initView();
        initLightMode();
    }

    public static View view;
    public static WindowManager manager;

    private void initLightMode() {
        if (view == null && manager == null) {
            manager = (WindowManager) getApplicationContext().getApplicationContext()
                    .getSystemService(Context.WINDOW_SERVICE);

            WindowManager.LayoutParams lp = lp = new WindowManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                            | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    PixelFormat.TRANSLUCENT
                            | WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW);

            view = new View(getApplicationContext());
            view.setBackgroundColor(getResources().getColor(R.color.system_color_tran));
            manager.addView(view, lp);

            final Intent intent = new Intent();
            intent.setClass(getApplicationContext(), LightService.class);
            startService(intent);

//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (view != null && manager != null) {
//                        manager.removeView(view);
//                        manager = null;
//                        stopService(intent);
//                    }
//                    finish();
//                }
//            }, 20000);
        }
    }
    public Handler handler = new Handler();
    private void initView() {

    }
}
class LightMode{

}
