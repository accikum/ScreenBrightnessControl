package com.lcw.screenbrightnesscontrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.lcw.screenbrightnesscontrol.light.LightService;

/**
 * Created by luchangwei on 2015/7/27.
 */
public class BaseActivity extends Activity {
    private View view;
    private WindowManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                manager.removeView(view);
                manager = null;
                stopService(intent);
                finish();
            }
        }, 30000);

    }


    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
