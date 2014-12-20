package com.maurice.GameWorld;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.maurice.GameHelpers.AssetLoader;
import com.maurice.GameObjects.Background;
import com.maurice.GameObjects.Ball;
import com.maurice.GameObjects.BigWave;
import com.maurice.GameObjects.BuoyLeft;
import com.maurice.GameObjects.BuoyRight;
import com.maurice.GameObjects.Dock;
import com.maurice.GameObjects.Mine;
import com.maurice.GameObjects.Ramp;
import com.maurice.GameObjects.Rock;
import com.maurice.GameObjects.Bubble;
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
import com.maurice.GameObjects.Wave;

public class GameRenderer {
    
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    
    private int midPointX;
    private int midPointY;
    private int gameHeight;
    private int gameWidth;
    //colors-light
    private Color color1=colorFromHex(0xE60000L);//0xrrggbb//red
	private Color color2=colorFromHex(0x0099FFL);//blue
	private Color color3=colorFromHex(0xF28080L);//light red
	private Color color4=colorFromHex(0x80CCFFL);//light blue
	private Color[] colors = new Color[]{color1,color2,color3,color4};
	//colors-faded
	private Color color1d=colorFromHex(0xA10000L);//0xaarrggbb
	private Color color2d=colorFromHex(0xB26B00L);
	private Color color3d=colorFromHex(0x005C99L);
	private Color[] colorsfaded = new Color[]{color1d,color3d,color2d};
	
	private SpriteBatch batch, batchTime;
	Texture texture;
	Texture rock;
	private Animation waterAnimation;
	int margin=2;
	Sprite sprite;
	private TextureRegion region;
	private TextureRegion bg, grass, coin;

    public static final int VIRTUAL_WIDTH = 480;
    public static final int VIRTUAL_HEIGHT = 800;
    private static final float ASPECT_RATIO =(float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
    private int GROUND_HEIGHT;//FROM TOP
    private int ROCKET_SHADOW_HEIGHT=5;
    private Rectangle viewport;
    private SpriteBatch sb;
    
    private Player player;
    private ArrayList<Scrollable> blocks;
    private ScrollHandler scroller;
    private ArrayList<Background> background;
    private ArrayList<Bubble> bubbles;
    private ArrayList<Wave> waves;
    
    //TESTING FRAMERATE
    private float framerate;
    private float totalframerate;
    private float avgframerate;
    private float iterations=0;
    private float timerate;
    private float avgtimerate=0;
    private float f0,f1,f2,f3,f4,f5,f6,f7;
    
    //MENU RELATED
    Skin skin;
    public GameRenderer(GameWorld world, int midPointX,int midPointY,int gameHeight,int gameWidth) {
        myWorld = world;
        
        this.midPointX=midPointX;
        this.midPointY=midPointY;
        this.gameHeight=gameHeight;
        this.gameWidth=gameWidth;
        
        sb = new SpriteBatch();
        cam = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        cam.setToOrtho(true, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        //cam.rotate(45);
        
        batch = new SpriteBatch();  //text display
        batch.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
             
        initGameObjects();
        initAssets();
        cam.update();
        //cam.apply(Gdx.gl10);
    }
    private void initGameObjects() {
        player=myWorld.getPlayer();
        scroller = myWorld.getScroller();
        blocks = scroller.getBlocks();
        background=scroller.getBg();
        bubbles=scroller.getBubbles();
        waves = scroller.getWaves();
    }
    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        waterAnimation = AssetLoader.waterAnimation;
        coin = AssetLoader.coin;
        /*birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
        */
       
    }

    public void render(float runtime, float delta) {
    	 batch.begin();
    	
    	// update camera
        //cam.update();
        //cam.apply(Gdx.gl10);
        
        // set viewport to 
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);

        // clear previous frame
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        // DRAW EVERYTHING
        /*shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);	
        shapeRenderer.rect(0,0, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);//game virtual size
        shapeRenderer.end();*/
        batch.disableBlending();
        //DRAW BACKGROUND
        //f0=TimeUtils.nanoTime();
        for(Background bg1:background){
        	//batch.begin();
        	batch.draw(AssetLoader.bg,0,bg1.getY(), 480, 200);
        	//batch.draw(AssetLoader.waterAnimation.getKeyFrame(runtime/4),0,bg1.getY(), 240, 100);
        	//AssetLoader.sonicAnimation.getKeyFrame(runtime)
        	//batch.draw(AssetLoader.bg, 0, bg1.getY(), VIRTUAL_WIDTH/2, bg1.getY()+50, (240), 100, 1+(bg1.getY()*0.01f), 1, 0);
        	//batch.draw(AssetLoader.bg, tempWave.getX()+2+((Wave.WIDTH-tempWave.getSpread())/2-
	        	//	2*player.getVelocity().x), 
	        		//tempWave.getY()-Wave.HEIGHT, 
	        		//tempWave.getSpread(), Wave.HEIGHT)
        	//batch.end(); 
        	/*shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);	
            shapeRenderer.rect(0,bg1.getY(), 240, 400);//game virtual size
            shapeRenderer.end();*/
    	}
        
        
        //DRAW GROUND
        /*shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);	
        shapeRenderer.rect(0,GROUND_HEIGHT, 600, 100);
        shapeRenderer.end();*/
        
        
        //DRAW BUBBLES
        /*Bubble tempBubble;
        for (int i = 0; i < bubbles.size(); i++) {
        	tempBubble=bubbles.get(i);
	        shapeRenderer.begin(ShapeType.Filled);
	        shapeRenderer.setColor(Color.WHITE);	
	        shapeRenderer.rect(tempBubble.getX(),tempBubble.getY(), Bubble.WIDTH, Bubble.HEIGHT);//game virtual size
	        shapeRenderer.end();
        }*/
        
        //DRAW WAVES
        Wave tempWave;
        for (int i = 0; i < waves.size(); i++) {
        	tempWave=waves.get(i);
	        //batch.begin();
	        batch.draw(AssetLoader.wave, tempWave.getX()+2+((Wave.WIDTH-tempWave.getSpread())/2-
	        		2*player.getVelocity().x), 
	        		tempWave.getY()-Wave.HEIGHT, 
	        		tempWave.getSpread(), Wave.HEIGHT);
	        //batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
	        //batch.end(); 
        }
        
        //DRAW BOAT SHADOW
    	//if(player.isInAir()){  
    		//batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
        batch.enableBlending();
    		//batch.draw(AssetLoader.boatshadow, player.getPosition().x, GameWorld.PLAYER_POSY, 
    			//	Player.WIDTH/4, Player.HEIGHT/4,Player.WIDTH, Player.HEIGHT, 1, 1, player.getAngle());
        batch.disableBlending();
    	//}
        
        //DRAW SHIELD
        if(myWorld.isSheild){
        	//batch.begin();
        	batch.enableBlending();
        	batch.draw(AssetLoader.cover,
        			player.getPosition().x-30,player.getPosition().y-16//experimental
        			, 80, 80);
        	 batch.disableBlending();
        	//batch.end();
        }
        
        
    	
        /*shapeRenderer.setColor(Color.RED);
        //shapeRenderer.rect(player.getPosition().x,player.getPosition().y-Player.HEIGHT, Player.WIDTH, Player.HEIGHT);
        if(player.isInAir())
        	batch.draw(AssetLoader.sonicAnimation.getKeyFrame(1),
        	        		player.getPosition().x,player.getPosition().y-Player.HEIGHT, Player.WIDTH, Player.HEIGHT);
        else
        	batch.draw(AssetLoader.sonicAnimation.getKeyFrame(runtime),
        //batch.draw(AssetLoader.sonic1,
        		player.getPosition().x,player.getPosition().y-Player.HEIGHT, Player.WIDTH, Player.HEIGHT);
        batch.end();*/
        //System.out.println("t6player at="+player.getPosition().x+"="+player.getPosition().y);
        
        //DRAW OBJECTS AND COINS
        //int x=600;
        batch.enableBlending();
    	for (int i = 0; i < blocks.size(); i++) {
    		Scrollable c = (Scrollable) blocks.get(i);
			if(c.getClass()==Rock.class){				
				//batch.begin();
				batch.disableBlending();
				batch.draw(AssetLoader.rock,
		                c.getX(), c.getY(), Rock.WIDTH, Rock.HEIGHT);
				batch.enableBlending();
				//batch.end();
			}
			if(Coin.class==c.getClass()){
				//batch.begin();
				batch.draw(AssetLoader.coin,
		                c.getX(), c.getY(), Coin.WIDTH, Coin.HEIGHT);
				//batch.end();
			}
			if(Shield.class==c.getClass()){
				/*shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(Color.BLUE);	
				shapeRenderer.rect( c.getX(), c.getY(),Shield.WIDTH, Shield.HEIGHT);
				shapeRenderer.end();*/
				//batch.begin();
				batch.draw(AssetLoader.shield,
		                c.getX(), c.getY(), Coin.WIDTH, Coin.HEIGHT);
				//batch.end();
			}
			if(Rocket.class==c.getClass()){
				/*shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(Color.RED);	
				shapeRenderer.rect( c.getX(), c.getY(),Rocket.WIDTH, Rocket.HEIGHT);
				shapeRenderer.end();*/
				//batch.begin();
				batch.disableBlending();
				batch.draw(AssetLoader.rocketshadow, c.getX(), 
						c.getY()+ROCKET_SHADOW_HEIGHT, Rocket.WIDTH, Rocket.HEIGHT);
				batch.enableBlending();
				batch.draw(AssetLoader.rocket,
		                c.getX(), c.getY(), Rocket.WIDTH, Rocket.HEIGHT);
				//batch.end();
			}
			if(Ramp.class==c.getClass()){
				//batch.begin();
				batch.disableBlending();
				batch.draw(AssetLoader.rampAnimation.getKeyFrame(runtime/5), c.getX(), c.getY(), Ramp.WIDTH, Ramp.HEIGHT);
				batch.enableBlending();
				//batch.end();
			}
			if(BigWave.class==c.getClass()){
				BigWave tempWave1=(BigWave) c;
				//batch.begin();
				batch.disableBlending();
				batch.draw(AssetLoader.bigwave, c.getX(), c.getY(), BigWave.WIDTH, BigWave.HEIGHT);
				batch.enableBlending();
				//batch.end();
			}
			if(BuoyLeft.class==c.getClass()){
				batch.disableBlending();
				batch.draw(AssetLoader.buoyleft, c.getX(), c.getY(), BuoyLeft.WIDTH, BuoyLeft.HEIGHT);
			}
			if(BuoyRight.class==c.getClass()){
				batch.disableBlending();
				batch.draw(AssetLoader.buoyright, c.getX(), c.getY(), BuoyRight.WIDTH, BuoyRight.HEIGHT);
			}
			if(Mine.class==c.getClass()){
				batch.disableBlending();
				//batch.draw(AssetLoader.mine, c.getX(), c.getY(), Mine.WIDTH, Mine.HEIGHT);
				
				
				batch.end();
		    	shapeRenderer.begin(ShapeType.Filled);  
				shapeRenderer.setColor(Color.RED);	
				shapeRenderer.rect( c.getX(), c.getY(),Mine.WIDTH, Mine.HEIGHT);
				shapeRenderer.end();
				batch.begin();
				
				batch.draw(AssetLoader.mine, c.getX(), c.getY(), Mine.WIDTH, Mine.HEIGHT);
			}
			if(Dock.class==c.getClass()){
				batch.disableBlending();
				batch.draw(AssetLoader.dock, c.getX(), c.getY(), Dock.WIDTH, Dock.HEIGHT);
			}
		}
    	
    	//DRAW PLAYER
        //batch.begin();
    	//batch.draw(AssetLoader.boat,player.getPosition().x,GameWorld.PLAYER_POSY, 20, 30);
        //System.out.println("Boat render height="+player.getPosition().z);
    	//batch.disableBlending();

    	//DRAW PLAYER
    	batch.enableBlending();
    	batch.draw(AssetLoader.boat, player.getPosition().x, GameWorld.PLAYER_POSY-player.getPosition().z, 
    			Player.WIDTH/2, Player.HEIGHT/2,Player.WIDTH, Player.HEIGHT, 1, 1, player.getAngle());
    	//batch.end(); 
    	
    	
    	//DRAW SCORE
    	//batch.begin();
    	AssetLoader.font.setColor(Color.WHITE);
        AssetLoader.font.setScale((float) 0.5F);
        AssetLoader.font.draw(batch, "SCORE="+myWorld.getScore(), 5, 30);
        iterations++;
        totalframerate+=framerate;
        framerate=1/delta;
        avgframerate=(avgframerate*0.9f)+(framerate*0.1f);
        f1=TimeUtils.nanoTime()-f0;
        avgtimerate=(avgtimerate*0.99f)+(f1*0.01f);
        AssetLoader.font.setScale((float) 0.3F);
        AssetLoader.font.draw(batch, "FRAMERATE="+(int)avgframerate, 5, 60);
        //AssetLoader.font.draw(batch, "TESTTIME="+(int)(avgtimerate/1000), 5, 100);
        AssetLoader.font.setScale((float) 0.5F);
        if(framerate<30){
        	AssetLoader.font.setColor(Color.RED);
        	AssetLoader.font.draw(batch, "LOW FRAMERATE", 5, 80);
        	AssetLoader.font.setColor(Color.WHITE);
        	System.out.println("framerate = "+framerate);
        }
        //batch.end();
        
        //DRAW TOAST
        AssetLoader.font.setColor(Color.WHITE);
        AssetLoader.font.setScale((float) 1);
        AssetLoader.font.draw(batch, myWorld.getToast(), 10, 100);
        
        //DRAW UP BUTTON
        /*batch.begin();
		batch.draw(AssetLoader.upButton,200, 350, 80, 80);
		batch.end();*/
		
		//DRAW LOGO
        /*batch.begin();
        AssetLoader.font.setColor(Color.ORANGE);
        AssetLoader.font.setScale((float) 2);
        AssetLoader.font.draw(batch, "DASH", 50, 230);
        AssetLoader.font.setColor(Color.GRAY);
        AssetLoader.font.setScale((float) 0.5F);
        AssetLoader.font.draw(batch, "DEVELOPED BY MAURICE", 50, 280);
		batch.end();*/
        
    	//DRAW GAMEOVER
    	if(myWorld.isGameover()){
            //batch.begin();
            AssetLoader.font.setColor(Color.ORANGE);
            AssetLoader.font.setScale((float) 1.5f);
            AssetLoader.font.draw(batch, "GAME OVER", 10, 50);
            AssetLoader.font.setColor(Color.WHITE);
            AssetLoader.font.setScale((float) 0.8f);
            AssetLoader.font.draw(batch, "TRY AGAIN ?", 130, 100);
            //batch.end();
    	}
    	if(myWorld.isReady()){
            //batch.begin();
    		batch.draw(AssetLoader.logo, 40, 100, 400, 150);
            AssetLoader.font.setColor(Color.ORANGE);
            AssetLoader.font.setScale((float) 0.6F);
           // AssetLoader.font.draw(batch, "created by maurice", 70, 140);
            AssetLoader.font.setColor(Color.ORANGE);
            AssetLoader.font.setScale((float) 0.6F);
            //AssetLoader.font.draw(batch, "tap to start", 60, 340);
            
            /*AssetLoader.font.setColor(Color.BLACK);
            AssetLoader.font.setScale((float) 0.7F);
            AssetLoader.font.draw(batch, "HOW TO PLAY", 200, 100);
            AssetLoader.font.setScale((float) 0.5F);
            AssetLoader.font.draw(batch, "> TAP TO JUMP", 200, 140);
            AssetLoader.font.draw(batch, "> DOUBLE TAP TO JUMP MID AIR", 200, 160);*/
            //batch.end();
    	}
    	
    	batch.end();
        //draw time
    	//batchTime.begin();
        //font.draw(batchTime, timeString((int)(myWorld.getRuntime()*60)), 600, 300);
        //batchTime.end();
    }
    private String timeString(int time){
    	String temp="";
 		int temp2=0;
 		//temp2=(int)time/3600;
 		//temp+=((temp2<10)?"0":"")+temp2+":";//hours
 		temp2=(int)time/60;
 		temp+=((temp2<10)?"0":"")+temp2+":";//minutes
 		temp2=(int)time%60;
 		temp+=((temp2<10)?"0":"")+temp2;//seconds
 		//temp2=(int)(time*100)%60;
 		//temp+=((temp2<10)?"0":"")+temp2;//milliseconds
 		return temp;
     }

	private Color colorFromHex(long hex){
            float a = (hex & 0xFF000000L) >> 24;
            float r = (hex & 0xFF0000L) >> 16;
            float g = (hex & 0xFF00L) >> 8;
            float b = (hex & 0xFFL);   
            return new Color(r/255f, g/255f, b/255f, a/255f);
    }
    public void resize(int width, int height) { 
    	// set aspect rations and padding accordingly
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
        }

        float w = (float)VIRTUAL_WIDTH*scale;
        float h = (float)VIRTUAL_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }
    //PERSPECTIVE
    public float getWdith(float y){
    	return (y)*5;
    }
}