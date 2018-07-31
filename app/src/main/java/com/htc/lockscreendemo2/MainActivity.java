package com.htc.lockscreendemo2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;

public class MainActivity extends Activity {
    private SwitchCompat mSwitchd = null;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        SharedPreferencesUtil.init(mContext);

        mSwitchd = (SwitchCompat) this.findViewById(R.id.switch_locksetting);
        mSwitchd.setTextOn("yes");
        mSwitchd.setTextOff("no");
        boolean lockState = SharedPreferencesUtil.get(this, Lockscreen.ISLOCK);
        if (lockState) {
            mSwitchd.setChecked(true);

        } else {
            mSwitchd.setChecked(false);
        }

            mSwitchd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d("Lynn", ""+isChecked);
                    if (isChecked) {

                        SharedPreferencesUtil.setBoolean(Lockscreen.ISLOCK, true);
                        Lockscreen.getInstance(mContext).startLockscreenService();
                    } else {
                        SharedPreferencesUtil.setBoolean(Lockscreen.ISLOCK, false);
                        Lockscreen.getInstance(mContext).stopLockscreenService();
                    }

                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        SharedPreferencesUtil.init(mContext);
//    }
}
