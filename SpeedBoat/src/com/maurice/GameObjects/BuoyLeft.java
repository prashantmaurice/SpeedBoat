package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;

public class BuoyLeft extends Scrollable{
	public static int WIDTH=15;
	public static int HEIGHT=20;
	public static float BUOYSHIFT=2f;
	public static int CHECKPOINT_WIDTH=120;
	private boolean isAlive;
	public BuoyLeft(float x, float y, float scrollSpeed) {
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
