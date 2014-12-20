package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;

public class Ramp extends Scrollable{
	public static int WIDTH=80;
	public static int HEIGHT=80;
	private boolean isAlive;
	public Ramp(float x, float y,  float scrollSpeed) {
		super(x, y, WIDTH, HEIGHT, 0, scrollSpeed);
		//System.out.println("Rock created at="+x+"="+y);
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
