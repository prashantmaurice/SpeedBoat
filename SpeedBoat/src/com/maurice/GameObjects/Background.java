package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameRenderer;
import com.maurice.GameWorld.GameWorld;

public class Background extends Scrollable{
	public static int WIDTH=240;
	public static int HEIGHT=400;
	private boolean isAlive;
	public Background(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, 0, scrollSpeed);
		System.out.println("Rock created at="+x+"="+y);
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
