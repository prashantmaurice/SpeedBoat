package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;

public class Bubble extends Scrollable{
	public static int WIDTH=1;
	public static int HEIGHT=1;
	private boolean isAlive;
	public Bubble(float x, float y, int width, int height,float velX, float scrollSpeed) {
		super(x, y, width, height, velX, scrollSpeed);
		//System.out.println("Bubble created at="+x+"="+y);
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
