package com.htc.lockscreendemo2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by lynnfu on 18-7-30.
 */

public class Lockscreen {
    private Context mContext = null;
    public static final String ISSOFTKEY = "ISSOFTKEY";
    public static final String ISLOCK = "ISLOCK";
    private static Lockscreen mLockscreenInstance;

    public static Lockscreen getInstance(Context context) {
        if (mLockscreenInstance == null) {
            if (null != context) {
                mLockscreenInstance = new Lockscreen(context);
            } else {
                mLockscreenInstance = new Lockscreen();
            }
        }
        return mLockscreenInstance;
    }

    private Lockscreen() {
        mContext = null;
    }

    private Lockscreen(Context context) {
        mContext = context;
    }

    public void startLockscreenService() {
        Log.d("Lynn", "startLockscreenService");
        SharedPreferencesUtil.init(mContext);
        Intent startLockscreenIntent = new Intent(mContext, LockscreenService.class);
//        startLockscreenIntent.putExtra(LockscreenService.LOCKSCREENSERVICE_FIRST_START, true);
        mContext.startService(startLockscreenIntent);

    }

    public void stopLockscreenService() {
        Intent stopLockscreenViewIntent = new Intent(mContext, LockScreenViewService.class);
        mContext.stopService(stopLockscreenViewIntent);
        Intent stopLockscreenIntent = new Intent(mContext, LockscreenService.class);
        mContext.stopService(stopLockscreenIntent);
    }
}
