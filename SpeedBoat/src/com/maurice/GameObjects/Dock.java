package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;

public class Dock extends Scrollable{
	public static int WIDTH=480;
	public static int HEIGHT=250;
	private boolean isAlive;
	public Dock(float x, float y, float scrollSpeed) {
		super(x, y, WIDTH, HEIGHT, 0, scrollSpeed);
		//System.out.println("Coin created at="+x+"="+y);
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
