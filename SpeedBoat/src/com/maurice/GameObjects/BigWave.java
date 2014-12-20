package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;

public class BigWave extends Scrollable{
	public static int WIDTH=20;
	public static int HEIGHT=150;
	public static int WAVE_VEL=100;
	public static float WAVE_SHIFTX=0.13f;
	public static float WAVE_SHIFTZ=0.2f;
	public static int LENGTH=20;
	private boolean isAlive;
	private boolean dir;
	public BigWave(float x, float y,  float scrollSpeed, boolean isRight) {
		super(x, y, WIDTH, HEIGHT, (isRight)?-WAVE_VEL:WAVE_VEL, scrollSpeed);
		//System.out.println("Rock created at="+x+"="+y);
		dir=isRight;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public int getLen(){
		return LENGTH; 
	}
	public boolean getDir(){
		return dir; 
	}
}
