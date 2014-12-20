package com.maurice.speedboat;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "SpeedBoat";
		cfg.useGL20 = true;
		cfg.width = 480/2;
		cfg.height = 800/2;
		
		new LwjglApplication(new SpeedBoatGame(), cfg);
	}
}
