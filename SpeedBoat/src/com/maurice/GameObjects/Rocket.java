package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;

public class Rocket extends Scrollable{
	public static int WIDTH=20;
	public static int HEIGHT=45;
	private boolean isAlive;
	public Rocket(float x, float y, float scrollSpeed) {
		super(x, y, WIDTH, HEIGHT, 0, scrollSpeed);
		//System.out.println("Rocket created at="+x+"="+y);
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
