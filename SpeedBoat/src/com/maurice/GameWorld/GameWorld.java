package com.maurice.GameWorld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameObjects.Ball;
import com.maurice.GameObjects.BigWave;
import com.maurice.GameObjects.BuoyLeft;
import com.maurice.GameObjects.BuoyRight;
import com.maurice.GameObjects.Mine;
import com.maurice.GameObjects.Ramp;
import com.maurice.GameObjects.Rock;
import com.maurice.GameObjects.Cluster;
import com.maurice.GameObjects.Coin;
import com.maurice.GameObjects.ConnectLine;
import com.maurice.GameObjects.Pin;
import com.maurice.GameObjects.Player;
import com.maurice.GameObjects.Rocket;
import com.maurice.GameObjects.ScrollHandler;
import com.maurice.GameObjects.Scrollable;
import com.maurice.GameObjects.Shield;
import com.maurice.GameObjects.Tile;
import com.maurice.GameObjects.Wheel;
import com.maurice.Screens.GameScreen;
import com.maurice.speedboat.SpeedBoatGame;
import com.sun.xml.internal.ws.api.pipe.NextAction;


public class GameWorld{
	
	private int midPointX;
    private int midPointY;
    private int gameHeight;
    private int gameWidth;
    public static int PLAYER_POSY=600;
    public static float RAMP_INCLINATION=0.8F;
    public int score=0;
    
    public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	private GameState currentState;
    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
    private SpeedBoatGame game;
    private GameScreen gameScreen;
    private float runtime=0;
    private static final int GROUND_HEIGHT = 200;//FROM TOP
    public static float COLLISION_OBJECTS_HEIGHT=4F;
    private int gameSpeed=5;//set speed
    private ArrayList<Scrollable> blocks;
    private int lastBlock=0;
    public boolean isSheild=false;
    
    private float gyroX=0, temp;

	Random rand = new Random();
	private Player player;
	private ScrollHandler scroller;
	private BigWave tempBigWave;
	String toast="";
	int lastToast=0;
	
	//SETTINGS RELATED
	public boolean settingsOn=false;
	public GameWorld(int midPointX,int midPointY, int gameHeight, int gameWidth, SpeedBoatGame game2, GameScreen gameScreen) {
		currentState=GameState.READY;
		this.midPointX=midPointX;
		this.midPointY=midPointY;
		this.gameHeight=gameHeight;
		this.gameWidth=gameWidth;
		this.gameScreen=gameScreen;
		
		System.out.println("GameWidth="+gameWidth);
		System.out.println("GameHeight="+gameHeight);
		//this.tileWidth=gcd(gameHeight,gameWidth);
		this.game=game2;
		player=new Player(244-Player.WIDTH,PLAYER_POSY,0,0,this);
		scroller = new ScrollHandler(gameHeight, this);
		blocks=scroller.getBlocks();
		//addBlockNow();
		//currentState=GameState.RUNNING;//enable this to auto start
		
	}
	public void update(float delta) {
		switch (currentState) {
        case READY:
        	updateReady(delta);
            break;
        case RUNNING:
        	updateRunning(delta);
        	break;
        case GAMEOVER:
        	updateGameover(delta);
        	break;
        default:
            break;
        }
	}
	public void updateReady(float delta){
		
	}
	public void updateRunning(float delta){
		runtime+=delta;
    	player.update();
    	checkCollision();
    	scroller.update(delta);
    	/*if(--lastToast<=0){
    		lastToast=0;
    		toast="";
    	}*/
    	/*updateAllBlocks();
    	if(lastBlock++>10){			
    		if(rand.nextInt(100)==1) {
    			addBlockNow();//change thi to increase blocks frequency
    			lastBlock=0;
    		}
    	}*/
    	
	}
	public void updateGameover(float delta){
	}
	public  SpeedBoatGame getGame(){
		return this.game;
	}
	public float getRuntime() {
		return runtime;
	}
	public void setRuntime(float runtime) {
		this.runtime = runtime;
	}
	public void roll() {
		System.out.println("rolled");
	}
	public Player getPlayer() {
		return player;
	}
	public int getGameSpeed() {
		return gameSpeed;
	}
	public ArrayList<Scrollable> getBlocks() {
		return blocks;
	}
	public void checkCollision(){
		//blocks=scroller.getBlocks();
		//System.out.println("Collision blocks="+blocks.size());
		
		for (int i = 0; i < blocks.size(); i++) {
			Scrollable c = (Scrollable) blocks.get(i);
			//if(c.getY()+c.getHeight()<PLAYER_POSY)
				//break;//not checking all blocks
			if((c.getX()<player.getPosition().x+Player.COLLISION_WIDTH)&((player.getPosition().x-c.getWidth())<c.getX()-Player.COLLISION_ANTI_WIDTH)){
				if((c.getY()<(GameWorld.PLAYER_POSY+Player.COLLISION_HEIGHT))&((GameWorld.PLAYER_POSY-c.getHeight())<c.getY()-Player.COLLISION_ANTI_HEIGHT)){
					//System.out.println("Collided...at ="+i+"th block");
					if(player.getPosition().z<COLLISION_OBJECTS_HEIGHT){
						if(c.getClass()==Coin.class){
							score+=10;
							blocks.remove(i);
							toast="+10";
							lastToast=30;
							continue;
						}
						if(c.getClass()==Shield.class){
							score+=30;
							blocks.remove(i);
							isSheild=true;
							toast="Shield acquired";
							lastToast=30;
							continue;
						}	
					}
					if(c.getClass()==Ramp.class){
						player.getVelocity().z+=RAMP_INCLINATION;
						continue;
					}
					if(c.getClass()==BigWave.class){
						tempBigWave=(BigWave) c;
						if(tempBigWave.getDir())//returns true if wave is coming from right							
							player.getVelocity().x-=BigWave.WAVE_SHIFTX;
						else
							player.getVelocity().x+=BigWave.WAVE_SHIFTX;
						if(player.getPosition().z<3)
							player.getVelocity().z+=BigWave.WAVE_SHIFTZ;
						continue;
					}
					
					if((c.getClass()==BuoyLeft.class)|(c.getClass()==BuoyRight.class)){
						if(player.getPosition().x<c.getX()){
							player.getPosition().x-=BuoyLeft.BUOYSHIFT;
							c.getPosition().x+=BuoyLeft.BUOYSHIFT;
						}
						else{
							player.getPosition().x+=BuoyLeft.BUOYSHIFT;
							c.getPosition().x-=BuoyLeft.BUOYSHIFT;
						}
						continue;
					}
					if(isSheild){
						score+=20;
						blocks.remove(i);
						isSheild=false;
						toast="Shield saved your ass";
						lastToast=30;
						continue;
					}
					if(player.getPosition().z<COLLISION_OBJECTS_HEIGHT){
						if(c.getClass()==Rocket.class){
							currentState=GameState.GAMEOVER;
						}
						if(c.getClass()==Rock.class){
							currentState=GameState.GAMEOVER;
						}
						if(c.getClass()==Mine.class){
							currentState=GameState.GAMEOVER;
						}
					}
				}
			}
		}
	}
	public boolean isReady(){
		return currentState==GameState.READY;
	}
	public boolean isRunning(){
		return currentState==GameState.RUNNING;
	}
	public boolean isGameover(){
		return currentState==GameState.GAMEOVER;
	}
	public void start(){
		currentState=GameState.RUNNING;
	}
	public void restart(){
		score=0;
		blocks.clear();
		//blocks.add(new Rock(300,GROUND_HEIGHT,10,10,scroller.SCROLL_SPEED));
    	//blocks.add(new Rock(600,GROUND_HEIGHT,10,10,scroller.SCROLL_SPEED));
		currentState=GameState.READY;
		//scroller.restart();
		player=new Player(244-Player.WIDTH,PLAYER_POSY,0,0,this);
		scroller = new ScrollHandler(gameHeight, this);
		blocks=scroller.getBlocks();
	}
	public ScrollHandler getScroller() {
        return scroller;
    }
	public void setToast(String msg){
		msg=toast;
		lastToast=30;
	}
	public String getToast(){
		return toast;
	}
	public void goLeft() {
		player.goLeft();
	}
	public void goRight() {
		player.goRight();
	}
	public void goStraight() {
		player.goStright();
	}
	public void setGyroX(float adjustedX) {
		if( adjustedX < - 2f ) adjustedX = - 2f; else if( adjustedX > 2f ) adjustedX = 2f;
		gyroX=adjustedX*gameScreen.getGame().GYROSENSITIVITY/14;//so that final gyroX is[-1,1]
	}
	public float getGyroX() {
		return gyroX;
	}
	

}
