package com.lcw.screenbrightnesscontrol.light;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.lcw.screenbrightnesscontrol.R;


/**
 * Created by luchangwei on 2015/7/30.
 */
public class LightService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static View view;//第一层的View
    public MWindowManager mWindowManager;

    /**
     * 应该在 OnCreate 的时候进行 manager 的创建。
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mWindowManager = new MWindowManager();
        view = new View(getApplicationContext());
        view.setBackgroundColor(getResources().getColor(R.color.system_color_tran));
        mWindowManager.addView(view);

    }
    NotificationManager manager;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification =new Notification();//API 10
        //Notification notification = new Notification.Builder(getApplicationContext()).getNotification();//API11
        //.getNotification(); // 需要注意build()是在API level16
        Intent i = new Intent();
        i.setClass(getApplicationContext(), LightSettingActivity.class);
//        notification.contentView;
        PendingIntent pendIntent = PendingIntent
                .getActivity(getApplicationContext(), 0, i,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        notification.icon = R.drawable.ic_launcher;
        notification.number = 1;
//        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notification.setLatestEventInfo(getApplicationContext(), "屏幕亮度调节", "屏幕亮度调节控制器正在运行,点击查看设置", pendIntent);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(111, notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭调节
        if (mWindowManager != null && view != null) {
            mWindowManager.removeView(view);
            manager.cancel(111);
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        }
    }

    private class MWindowManager {
        public WindowManager manager;
        WindowManager.LayoutParams lp;

        public MWindowManager() {
            manager = (WindowManager) getApplicationContext().getApplicationContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            lp = new WindowManager.LayoutParams(
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
        }
        public void addView(View v) {
            manager.addView(v, lp);
        }
        public void removeView(View v) {
            manager.removeView(v);
        }
    }
}
