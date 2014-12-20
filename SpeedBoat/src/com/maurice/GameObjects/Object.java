package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;

public class Object {
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private boolean isAlive=true;
	private GameWorld world;
	
	private int GROUND_HEIGHT;
	public Object(float x, float y, GameWorld gameWorld) {
		world=gameWorld;
		position = new Vector2(x, y);
		velocity = new Vector2(-world.getGameSpeed(), 0);
		acceleration = new Vector2(0, 0);
		setAlive(true);
	}
	public void update(){
		//System.out.println("test..!");
		if(position.x<=0) setAlive(false);	
		//velocity.x+=acceleration.x;
		//velocity.y+=acceleration.y;
		position.x+=velocity.x;
		//System.out.println("block updated..! position=");
		
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public Vector2 getPosition(){
		return position;
	}
	
}
