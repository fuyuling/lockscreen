package com.htc.lockscreendemo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by lynnfu on 18-7-31.
 */

public class BootCompleteReceiver extends BroadcastReceiver{
    // Handle actions and display Lockscreen
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d("Lynn", "ACTION_BOOT_COMPLETED");
            Intent startLockscreenIntent = new Intent(context, LockScreenViewService.class);
            TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            boolean isPhoneIdle = tManager.getCallState() == TelephonyManager.CALL_STATE_IDLE;
            if (isPhoneIdle) {
                Intent startLockscreenActIntent = new Intent(context, LockscreenActivity.class);
                startLockscreenActIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startLockscreenActIntent);
            }
        }
    }
}
