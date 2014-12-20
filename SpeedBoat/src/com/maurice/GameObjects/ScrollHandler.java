package com.maurice.GameObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.text.Position;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.maurice.GameWorld.GameRenderer;
import com.maurice.GameWorld.GameWorld;

public class ScrollHandler {
	
	private ArrayList<Scrollable> blocks= new ArrayList<Scrollable>();
	private ArrayList<Coin> coins= new ArrayList<Coin>();
	private ArrayList<Bubble> bubbles= new ArrayList<Bubble>();
	private ArrayList<Wave> waves= new ArrayList<Wave>();
    // Capital letters are used by convention when naming constants.
    public static final int SCROLL_SPEED = 400;
    //public static final int GROUND_HEIGHT = 200;
    Random rand = new Random();
    private int lastObject=0;
    private ArrayList<Background> bg;
    private Background bg1;
    private GameWorld gameWorld;
    private Scrollable c;
    
    //MAP RALATED
    private Array<Vector2> mapArray =new Array<Vector2>();
    private int currMap=0, tempInt;
    private boolean levelOver=false;
    
    //CHECKPOINT RELATED
    private int checkPointActive=0;
    private int checkPointPassed=0;
    private int checkPointTotal=0;
    private int passLeft=0;
    private int passRight=0;
    private boolean checkPoint=false;
    private boolean pointScrolledDown=true;
    private boolean scanPoint=false;
    private boolean alreadyScanPoint=false;
    private Player player;
    
    //TESTING
    private int testing=3;
	public ScrollHandler(float yPos, GameWorld gameWorld) {
		this.gameWorld=gameWorld;
		player=gameWorld.getPlayer();
		bg= new ArrayList<Background>();
		for(int i=0;i<5;i++){
			bg.add(new Background(0, i*200, Background.WIDTH, Background.HEIGHT, SCROLL_SPEED));
		}
    	//blocks.add(new Rock(800,GROUND_HEIGHT,10,10,SCROLL_SPEED));
    	//blocks.add(new Rock(500,GROUND_HEIGHT,10,10,SCROLL_SPEED));
    	//blocks.add(new Rock(1000,GROUND_HEIGHT-50,10,10,SCROLL_SPEED));
		try {
            loadMap(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
		createDock();
		
    }
    public void update(float delta) {
        //ADD OBJECTS
    	lastObject--;
    	if((lastObject<0)&(!levelOver)){
    		//if((mapArray.get(currMap).x!=12)&(mapArray.get(currMap).x!=13))
    			//lastObject=80;
    		addObject(mapArray.get(currMap).x,mapArray.get(currMap).y);
    		currMap++;
    		if(currMap==mapArray.size) {
    			levelOver=true;
    			currMap--;
    			System.out.println("Level over");
    		}
    	}
    	if((lastObject<0)&(levelOver)){//PLAY LEEL IN REVERSE
    		//if((mapArray.get(currMap).x!=12)&(mapArray.get(currMap).x!=13))
    			//lastObject=80;
    		addObject(mapArray.get(currMap).x,mapArray.get(currMap).y);
    		currMap--;
    		if(currMap==0) {
    			levelOver=false;
    			System.out.println("Level over");
    		}
    		/*if(lastObject<0){
    			int temp=rand.nextInt(14);
    			if((temp!=12)&(temp!=13))
        			lastObject=80;
    			addObject(rand.nextInt(14), rand.nextInt(14)+1);
    		}*/
    	}
    	if(rand.nextInt(250)==1){//shield
			//lastObject=30;
			//blocks.add(new Shield((rand.nextInt(5)*50),0,SCROLL_SPEED));//random blocks generation
		}
	    		/*if(rand.nextInt(1)==0){//rocks
	    			lastObject=80;
	    			addThreeRockStr(rand.nextInt(1),rand.nextInt(14));;
	    		}
	    		else if(rand.nextInt(4)==1){//coins
	    			lastObject=30;
	    			blocks.add(new Coin((rand.nextInt(5)*50),0,SCROLL_SPEED));//random blocks generation
	    		}
	    		else if(rand.nextInt(17)==1){//shield
	    			lastObject=30;
	    			blocks.add(new Shield((rand.nextInt(5)*50),0,SCROLL_SPEED));//random blocks generation
	    		}
	    		else if(rand.nextInt(4)==0){//BigWave
	    			int wavelen=rand.nextInt(5)+2;
	    			lastObject=20*wavelen;
	    			for(int i=0;i<wavelen;i++){    				
	    				blocks.add(new BigWave(10,0-(i*BigWave.LENGTH),SCROLL_SPEED));
	    			}
	    		}
	    		else if(rand.nextInt(5)==1){//ramp
	    			lastObject=30;
	    			blocks.add(new Ramp((rand.nextInt(5)*50),0,SCROLL_SPEED));//random blocks generation
	    		}
	    		else if(rand.nextInt(9)==1){//rocket
	    			lastObject=30;
	    			blocks.add(new Rocket((rand.nextInt(5)*50),0,SCROLL_SPEED*2));//random blocks generation
	    		
	    		}*/
    		
    	
    	//UPDATE BLOCKS
    	
    	for (int i = 0; i < blocks.size(); i++) {
			c = blocks.get(i);	
			if (c.isScrolledDown()) {
				if(c.getClass()==BuoyRight.class){//make sure this is right
					scanPoint=false;
					alreadyScanPoint=false;
				}
				blocks.remove(i);
				//i--;
				System.out.println("removed="+i+"remaining="+blocks.size());
			}
			//else break;
		}
    	for (int i = 0; i < blocks.size(); i++) {
    		c = blocks.get(i);	
			c.update(delta);
			if(checkPoint){
				if((c.getClass()==BuoyLeft.class)|(c.getClass()==BuoyRight.class)){
					if((c.getPosition().y+BuoyLeft.HEIGHT>gameWorld.PLAYER_POSY)
							&&(c.getPosition().y<gameWorld.PLAYER_POSY+Player.HEIGHT)){
						//System.out.println("TEST..!");
						if(c.getClass()==BuoyLeft.class){
							if(player.getPosition().x>c.getX()+BuoyLeft.WIDTH/2)
								passLeft=1;
							//System.out.println("passed left");
							
						}
						if(c.getClass()==BuoyRight.class){
							if(player.getPosition().x+Player.WIDTH/2<c.getX())
								passRight=1;
							//System.out.println("passed right");
						}
					}
					if(c.getPosition().y-2>gameWorld.PLAYER_POSY+Player.HEIGHT){
						scanPoint=true;
						//System.out.println("scanpoint set rtue");
					}
				}
				//if(checkPointActive==checkPointPassed){					
					//System.out.println("TEST..!");
					
				//}
			}
		}
    	if((scanPoint)&&(!alreadyScanPoint)&&(checkPoint)){
    		if((passLeft==1)&&(1==passRight)){
        		//pointScrolledDown=true;
        		//passBeforePoints=true;
        		checkPointPassed++;
        	}
    		checkPointActive++;
    		alreadyScanPoint=true;
    		System.out.println("checkpoint passed"+checkPointActive+"="+checkPointTotal+"="+checkPointPassed);
    		passLeft=0;
    		passRight=0;
    		if(checkPointTotal==checkPointActive){
    			if(checkPointPassed==checkPointTotal){
    				gameWorld.setToast("ALL CHECK POINTS PASSED");
    			}
    			checkPoint=false;
    			checkPointTotal=0;
    			checkPointPassed=0;
    			checkPointActive=0;
    					
    		}
    	}
    	//System.out.println("curr="+passLeft+"="+passRight);
    	/*if((passLeft==1)&&(1==passRight)&&(!pointScrolledDown)){
    		System.out.println("checkpoint passed");
    		pointScrolledDown=true;
    		passBeforePoints=true;
    		passLeft=0;
    		passRight=0;
    	}*/
    	
    	//System.out.println("rocks="+blocks);
    	
    	//ADD WAVES AT BOAT
    	if(!gameWorld.getPlayer().isInAir()){    		
    		if(rand.nextInt(1)==0){
    			System.out.println("wave produced..!");
    			waves.add(new Wave(gameWorld.getPlayer().getPosition().x+Player.WIDTH/2-Wave.WIDTH/2+(rand.nextFloat()-0.5f)*2-2, 
    					GameWorld.PLAYER_POSY+Player.HEIGHT, 
    					Wave.WIDTH, Wave.HEIGHT,(rand.nextFloat()-0.5f)*1, SCROLL_SPEED*1.4f));
    		}
    	}
    	//ADD BUBBLES AT TOP
    	if(rand.nextInt(20)==1){
			bubbles.add(new Bubble(rand.nextInt(GameRenderer.VIRTUAL_WIDTH), 
					0, Bubble.WIDTH, Bubble.HEIGHT,0, SCROLL_SPEED));
		}
    	
    	//UPDATE BACKGROUND
    	for(int i=0;i<bg.size();i++){
    		bg1=bg.get(i);
    		bg1.update(delta);
    		if(bg1.isScrolledDown()){
    			bg1.reset(bg1.getTailX()-1000);
    		}
    	}
    	
    	//UPDATE BUBBLES
    	Bubble bubbleTemp;
    	//System.out.println("Bubbles="+bubbles.size());
    	for(int i=0;i<bubbles.size();i++){
    		bubbleTemp=bubbles.get(i);
    		bubbleTemp.update(delta);
    		if(bubbleTemp.isScrolledDown()){
    			bubbles.remove(i);
    		}
    	}
    	
    	//UPDATE WAVES
    	Wave waveTemp;
    	System.out.println("waves="+waves.size());
    	for(int i=0;i<waves.size();i++){
    		waveTemp=waves.get(i);
    		waveTemp.update(delta);
    		if(waveTemp.isScrolledDown()){
    			System.out.println("wave removes..!");
    			waves.remove(i);
    		}
    	}
    	
    }

	public ArrayList<Scrollable> getBlocks() {
		return blocks;
	}
    public ArrayList<Background> getBg() {
		return bg;
	}
    public ArrayList<Bubble> getBubbles() {
		return bubbles;
	}
	public ArrayList<Wave> getWaves() {
		return waves;
	}
	public void restart() {
		currMap=0;
		levelOver=false;
	}
	
	
	//READING MAPS
	public void loadMap(int level) throws IOException {
		System.out.println("loaded level="+level);
		String levelName="data/levels/level"+level+".txt";
		FileHandle file = Gdx.files.internal(levelName);
		String text = file.readString();
		Scanner reader=new Scanner(text);
		while(reader.nextLine().startsWith("!")){};
		
		//START LOADING OBJECTS IN AN ARRAY
		while(reader.hasNext()){
			mapArray.add(new Vector2(reader.nextInt(),reader.nextInt()));
			reader.next();
		}
		reader.close();
		System.out.println(mapArray);
	}
	private void addObject(float x, float y) {
		switch((int)x){
			case 1: addCoin(x,y);
					break;
			case 2: addRamp(x,y);
				break;
			case 3: addThreeRockStr(x,y);
				break;
			case 4: addThreeRockDia(x,y);
				break;
			case 5: addPartialDivider(x,y);
				break;
			case 6: addTotalDivider(x,y);
				break;
			case 7: addFoutCoins(x,y);
				break;
			case 8: addFourRockStr(x,y);
				break;
			case 9: addFourRockDia(x,y);
				break;
			case 10: addFourRockLon(x,y);
				break;
			case 11: addCoinsBig(x,y);
				break;
			case 12: addWave(x,y);
				break;
			case 13: addContWave(x, y);
				break;
			case 14: addRocket(x, y);
				break;
			case 15: addMultipleCheckpoint( x, y);
				break;
			case 16: addMineField( x, y);
				break;
			default :
				break;
				
		}
	}




	private void addRamp(float x, float y) {
		lastObject=10;
		blocks.add(new Ramp(16*(y-1),0,SCROLL_SPEED));
	}
	private void addCoin(float x, float y) {
		lastObject=10;
		blocks.add(new Coin(16*(y-1),0,SCROLL_SPEED));
	}
	private void addThreeRockStr(float x, float y) {
		lastObject=60;
		blocks.add(new Rock(32*(y-1),0,SCROLL_SPEED));
		blocks.add(new Rock(32*(y-1)+60,0,SCROLL_SPEED));
		blocks.add(new Rock(32*(y-1)+120,0,SCROLL_SPEED));
		//System.out.println("Test");
	}
	private void addThreeRockDia(float x, float y) {
		lastObject=60;
		int c=rand.nextInt(2); if(c==0) c=-1;
		blocks.add(new Rock(16*(y-1),0,SCROLL_SPEED));
		blocks.add(new Rock(16*(y-1)+60,-c*20,SCROLL_SPEED));
		blocks.add(new Rock(16*(y-1)+120,-c*40,SCROLL_SPEED));
	}
	private void addFourRockStr(float x, float y) {
		lastObject=60;
		blocks.add(new Rock(16*(y-1),0,SCROLL_SPEED));
		blocks.add(new Rock(16*(y-1)+60,0,SCROLL_SPEED));
		blocks.add(new Rock(16*(y-1)+120,0,SCROLL_SPEED));
		blocks.add(new Rock(16*(y-1)+180,0,SCROLL_SPEED));
	}
	private void addFourRockDia(float x, float y) {
		lastObject=60;
		int c=rand.nextInt(2); if(c==0) c=-1;
		blocks.add(new Rock(16*(y-1),0,SCROLL_SPEED));
		blocks.add(new Rock(16*(y-1)+60,-c*20,SCROLL_SPEED));
		blocks.add(new Rock(16*(y-1)+120,-c*40,SCROLL_SPEED));
		blocks.add(new Rock(16*(y-1)+180,-c*60,SCROLL_SPEED));
	}
	private void addFourRockLon(float x, float y) {
		lastObject=80;
		blocks.add(new Rock(32*(y-1),0,SCROLL_SPEED));
		blocks.add(new Rock(32*(y-1),60,SCROLL_SPEED));
		blocks.add(new Rock(32*(y-1),120,SCROLL_SPEED));
		blocks.add(new Rock(32*(y-1),180,SCROLL_SPEED));
	}
	private void addPartialDivider(float x, float y) {
		lastObject=70;
		blocks.add(new Rock(32*(y-1),0,SCROLL_SPEED));
		blocks.add(new Rock(32*(y-1)+60,0,SCROLL_SPEED));
		blocks.add(new Rock(32*(y-1)+120,0,SCROLL_SPEED));
		blocks.add(new Rock(32*(y-1)+180,0,SCROLL_SPEED));
		blocks.add(new Rock(32*(y-1)+240,0,SCROLL_SPEED));
	}
	private void addTotalDivider(float x, float y) {
		lastObject=80;
		int temp=-Ramp.HEIGHT;
		blocks.add(new Ramp(32*(y-1),0,SCROLL_SPEED));
		blocks.add(new Rock(0,temp,SCROLL_SPEED));
		blocks.add(new Rock(60,temp,SCROLL_SPEED));
		blocks.add(new Rock(120,temp,SCROLL_SPEED));
		blocks.add(new Rock(180,temp,SCROLL_SPEED));
		blocks.add(new Rock(240,temp,SCROLL_SPEED));
		blocks.add(new Rock(300,temp,SCROLL_SPEED));
		blocks.add(new Rock(360,temp,SCROLL_SPEED));
		blocks.add(new Rock(420,temp,SCROLL_SPEED));
	}
	private void addFoutCoins(float x, float y) {
		lastObject=80;
		blocks.add(new Coin(32*(y-1),0,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)-15,-15,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)+15,-15,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1),-30,SCROLL_SPEED));
	}
	private void addWave(float x, float y){
		addWaveDir(x, y,rand.nextInt(2)==1);
	}
	private void addWaveDir(float x, float y, boolean isRight){
		int wavelen=12;
		//for(int i=0;i<wavelen;i++){    				
			blocks.add(new BigWave(x,(y*20),SCROLL_SPEED,isRight));
			//blocks.add(new BigWave(x,(y*20)-(i*BigWave.LENGTH),SCROLL_SPEED));
		//}
	}
	private void addContWave(float x, float code){//core={number,y,distance}//all in single digit
		int distance=(int) code%10; code/=10f;
		int yPos=(int) code%10; code/=10f;
		int num=(int) code%10; code/=10f;
		if((int)(code%10)==1){
			for(int i=0;i<num;i++){
				addWaveDir(0-(distance*i*10),yPos*3f-5,false);
				System.out.println("Big wave created");
			}			
		}else{
			for(int i=0;i<num;i++){
				addWaveDir(GameRenderer.VIRTUAL_WIDTH+(distance*i*10),yPos*3f-5, true);
			}
		}
		System.out.println("contwave="+distance+"="+yPos+"="+num);
	}
	private void addCoinsBig(float x, float y) {
		lastObject=80;
		blocks.add(new Coin(32*(y-1),-60,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)-15,-45,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)+15,-45,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)-30,-30,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1),-30,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)+30,-30,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)-45,-15,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)-15,-15,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)+15,-15,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)+45,-15,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)-60,0,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)-30,0,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)+0,0,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)+30,0,SCROLL_SPEED));
		blocks.add(new Coin(32*(y-1)+60,0,SCROLL_SPEED));
	}
	private void addRocket(float x, float y) {
		blocks.add(new Rocket(32*(y-1),0,SCROLL_SPEED*2));
	}
	private void addMultipleCheckpoint(float x, float y) {
		lastObject=(int)y*80;
		//checkPointActive=(int)y;
		checkPoint=true;
		checkPointTotal=(int)y;
		checkPointActive=0;
		checkPointPassed=0;
		//lastObject=80;
		tempInt=0;
		for(int i=0;i<y;i++ )
			addCheckpoint( tempInt-=400, 2+rand.nextInt(10));
	}
	private void addCheckpoint(float x, float y) {
		blocks.add(new BuoyLeft(32*(y-1)+0,x,SCROLL_SPEED));
		blocks.add(new BuoyRight(32*(y-1)+BuoyLeft.CHECKPOINT_WIDTH,x,SCROLL_SPEED));
	}

	private void addMineField(float x, float y) {
		lastObject=(int)y*20;
		for(int i=0;i<y;i++){
			blocks.add(new Mine(rand.nextInt(440),-i*80,SCROLL_SPEED));
		}
	}
	public void createDock(){
		blocks.add(new Dock(0,GameRenderer.VIRTUAL_HEIGHT-Dock.HEIGHT,SCROLL_SPEED));
	}
}
