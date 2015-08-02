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
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
