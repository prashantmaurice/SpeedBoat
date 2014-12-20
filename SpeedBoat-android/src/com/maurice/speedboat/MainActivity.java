package com.maurice.speedboat;

import android.os.Bundle;
//import android.os.PowerManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	//private PowerManager.WakeLock wl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        cfg.useWakelock = true;
        
        initialize(new SpeedBoatGame(), cfg);
    }
}