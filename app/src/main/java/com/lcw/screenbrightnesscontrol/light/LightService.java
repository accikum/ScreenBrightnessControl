package com.lcw.screenbrightnesscontrol.light;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.lcw.screenbrightnesscontrol.R;


/**
 * Created by luchangwei on 2015/7/30.
 */
public class LightService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("lcw", "create");
        Notification notification = new Notification.Builder(getApplicationContext()).getNotification();
        //.getNotification(); // 需要注意build()是在API level16
        Intent i = new Intent();
        i.setClass(getApplicationContext(), LightSettingActivity.class);
//        notification.contentView;
        PendingIntent intent = PendingIntent
                .getActivity(getApplicationContext(), 0, i,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        notification.icon = R.drawable.ic_launcher;
        notification.number=1;
//        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notification.setLatestEventInfo(getApplicationContext(),"新通知","nothink",intent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1, notification);
    }
}
