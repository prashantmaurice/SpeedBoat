package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.maurice.GameWorld.GameRenderer;
import com.maurice.GameWorld.GameWorld;
import com.maurice.GameWorld.GameWorld.GameState;
import com.maurice.Screens.GameScreen;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

public class Player {
	private Vector3 position;
	private Vector3 velocity;
	private Vector3 acceleration;
	
	public static int HEIGHT=52;
	public static int WIDTH=40;
	public static int COLLISION_HEIGHT=48;
	public static int COLLISION_ANTI_HEIGHT=4;//at back side
	public static int COLLISION_WIDTH=34;
	public static int COLLISION_ANTI_WIDTH=6;//WIDTH FROM OTHER SIDE
	private boolean isAlive=true;
	private boolean inAir=true;
	private GameWorld world;
	
	private float TURN_SENSITIVITY=1f;
	private float GYRO_SENSITIVITY=0.2f;//[-.5f-2f]experimenta'
	private float MAX_HORIZONTAL_SPEED=10F;
	private float TURN_ANGLE=3f;
	public static float GRAVITY=0.5F;
	private float VERTICAL_TOLERANCE=0.1F;
	private float DRAG=1.1f;
	
	private int GROUND_HEIGHT;
	private int surfaceHeight;
	private float jumpHeight;
	public int jumpNum=0;
	private boolean goLeft=false;
	private boolean goRight=false;
	private float angle=0;
	private float gyroVel;
	public Player(float x, float y, int velX, int velY, GameWorld gameWorld) {
		world=gameWorld;
		gyroVel=world.getGyroX();
		GROUND_HEIGHT=GameWorld.PLAYER_POSY;
		position = new Vector3(x, GROUND_HEIGHT,0);
		velocity = new Vector3(velX, velY,0);
		acceleration = new Vector3(0, 0,GRAVITY);
		setAlive(true);
	}
	public void update(){
		//System.out.println("Boat Velocity="+velocity.x);
		if(goLeft){				
			velocity.x-=TURN_SENSITIVITY;
		}
		else if(goRight){				
			velocity.x+=TURN_SENSITIVITY;
		}
		else{
			velocity.x/=DRAG;
		}
		
			
		//ADD GYRO COMPONENT
		gyroVel=world.getGyroX();
		velocity.x-=gyroVel*GYRO_SENSITIVITY*MAX_HORIZONTAL_SPEED;
		
		
		//CHECK FOR VELOCITY LIMITS
		if(Math.abs(velocity.x)>MAX_HORIZONTAL_SPEED){
			if(velocity.x<0)
				velocity.x=-MAX_HORIZONTAL_SPEED;
			else
				velocity.x=MAX_HORIZONTAL_SPEED;
		}
			
			
		
		if(((position.x+velocity.x)<GameRenderer.VIRTUAL_WIDTH-WIDTH)&&((position.x+velocity.x)>=0)){			
			//velocity.x+=acceleration.x;
			//velocity.y+=acceleration.y;
			position.x+=velocity.x;
			position.z+=velocity.z;
			//position.y+=velocity.y;
			//inAir=true;;
		}
		else{
			
			//velocity.x=-1;
			System.out.println("Boat out of bounds=");
			//System.out.println("gamewith="+GameScreen.GAME_WIDTH);
			if(position.x*2>GameScreen.GAME_WIDTH/2){
				velocity.x=-1;
			}
			else
				velocity.x=1;
			/*position.y=surfaceHeight;
			jumpNum=0;
			inAir=false;*/
		}
		
		//ADD VERTICAL GRAVITY
		if(position.z>0)
			velocity.z-=acceleration.z;
		if(position.z<VERTICAL_TOLERANCE){			
			velocity.z=0;
			position.z=0;		
		}
		//System.out.println("Boat jump height="+position.z);
		
		angle=velocity.x*TURN_ANGLE;
		
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public Vector3 getPosition(){
		return position;
	}
	public Vector3 getVelocity(){
		return velocity;
	}
	public boolean isInAir() {
		//System.out.println("player height="+position.z);
		return position.z>0.4;
	}
	public void goLeft() {
		goLeft=true;
	}
	public void goRight() {
		goRight=true;
	}
	public void setLeft() {
		velocity.x=-4;
	}
	public void setRight() {
		velocity.x=4;
	}
	public void goStright() {
		goRight=false;
		goLeft=false;
	}
	public float getAngle() {
		return angle;
	}
	
	
}
