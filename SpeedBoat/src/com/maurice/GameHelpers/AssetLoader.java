package com.maurice.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maurice.GameObjects.BigWave;
import com.maurice.GameObjects.BuoyLeft;
import com.maurice.GameObjects.BuoyRight;
import com.maurice.GameObjects.Dock;
import com.maurice.GameObjects.Mine;
import com.maurice.GameObjects.Ramp;
import com.maurice.GameObjects.Rock;
import com.maurice.GameObjects.Coin;
import com.maurice.GameObjects.Player;
import com.maurice.GameObjects.Rocket;
import com.maurice.GameObjects.Wave;

public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation waterAnimation, rampAnimation;
    public static TextureRegion water1, water2, water3, water4, water5, water6, water7, water8, water9;
    public static TextureRegion coin, upButton, cover, shield, boat,wave, rock, rocket, rocketshadow;
    public static TextureRegion boatshadow, bigwave, buoyleft, buoyright, mine, dock, logo, ramp1, ramp2, ramp3;
    public static TextureRegion mainLogo;
    
    public static BitmapFont font, font2, shadow;
    private static int playerWidth=Player.WIDTH;
    private static int playerHeight=Player.HEIGHT;

    public static void load() {
    	
    	//LOAD FONT
    	font = new BitmapFont(Gdx.files.internal("data/devgothic.fnt"),true);//true means solves upside-down text problem
        font.setScale(2);
        font.setColor(Color.GRAY);
        
        //SETTINGS FONT
        font2 = new BitmapFont(Gdx.files.internal("ui/neuropol.fnt"),true);
        font2.setScale(2);
        font2.setColor(Color.GRAY);
    	
    	
        //texture.setEnforcePotImages(false);
        //texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        //bg = new TextureRegion(texture, 0, 0, 136, 43);
        //bg.flip(false, true);
        
        //LOAD BACKGROUND
        texture.setEnforcePotImages(false);
        texture = new Texture(Gdx.files.internal("data/bg3.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bg = new TextureRegion(texture, 0, 0, 240, 100);
        bg.flip(false, true);
        
        //grass = new TextureRegion(texture, 0, 43, 143, 11);
        //grass.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/watergif1.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        water1 = new TextureRegion(texture, 0, 0, 240, 100);
        water1.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/watergif2.png"));
        water2 = new TextureRegion(texture, 0, 0, 240, 100);
        water2.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/watergif3.png"));
        water3 = new TextureRegion(texture, 0, 0, 240, 100);
        water3.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/watergif4.png"));
        water4 = new TextureRegion(texture, 0, 0, 240, 100);
        water4.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/watergif5.png"));
        water5 = new TextureRegion(texture, 0, 0, 240, 100);
        water5.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/watergif6.png"));
        water6 = new TextureRegion(texture, 0, 0, 240, 100);
        water6.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/watergif7.png"));
        water7 = new TextureRegion(texture, 0, 0, 240, 100);
        water7.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/watergif8.png"));
        water8 = new TextureRegion(texture, 0, 0, 240, 100);
        water8.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/watergif9.png"));
        water9 = new TextureRegion(texture, 0, 0, 240, 100);
        water9.flip(false, true);
        
        TextureRegion[] waters = { water1, water2, water3, water4, water5, water6, water7, water8, water9};
        waterAnimation = new Animation(0.06f, waters);
        waterAnimation.setPlayMode(Animation.LOOP);
        
        //LOAD ROCK
        texture = new Texture(Gdx.files.internal("data/rockbm.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        rock = new TextureRegion(texture, 0, 0, Rock.WIDTH/2, Rock.HEIGHT/2);
        rock.flip(false, true);
        
        //LOAD COIN
        texture = new Texture(Gdx.files.internal("data/coin2.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        coin = new TextureRegion(texture, 0, 0, Coin.WIDTH, Coin.HEIGHT);
        coin.flip(false, true);
        
        //LOAD ROCKET
        texture = new Texture(Gdx.files.internal("data/rocket.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        rocket = new TextureRegion(texture, 0, 0, Rocket.WIDTH, Rocket.HEIGHT);
        rocket.flip(false, true);
        
        //LOAD ROCKET SHADOW
        texture = new Texture(Gdx.files.internal("data/rocketShadowbm.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        rocketshadow = new TextureRegion(texture, 0, 0, Rocket.WIDTH, Rocket.HEIGHT);
        rocketshadow.flip(false, true);
        
        //LOAD UPBUTTON
        texture = new Texture(Gdx.files.internal("data/upbutton.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        upButton = new TextureRegion(texture, 0, 0, 80, 80);
        upButton.flip(false, true);
        
        //LOAD CHARACTER SHIELD COVER
        texture = new Texture(Gdx.files.internal("data/shieldcover.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        cover = new TextureRegion(texture, 0, 0, 80, 80);
        cover.flip(false, true);
        
        //LOAD CHARACTER SHIELD
        texture = new Texture(Gdx.files.internal("data/shield.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        shield = new TextureRegion(texture, 0, 0, 20, 20);
        shield.flip(false, true);
        
        //LOAD WAVES
        texture = new Texture(Gdx.files.internal("data/wavesbm.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        wave = new TextureRegion(texture, 0, 0, 40,5 );
        wave.flip(false, true);
        
        //LOAD BIG WAVES
        texture = new Texture(Gdx.files.internal("data/bigwave.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bigwave = new TextureRegion(texture, 0, 0, BigWave.WIDTH, BigWave.HEIGHT);
        bigwave.flip(false, true);
        
        //LOAD RAMP
        texture = new Texture(Gdx.files.internal("data/ramp1.png"));
        ramp1 = new TextureRegion(texture, 0, 0, Ramp.WIDTH, Ramp.HEIGHT);
        ramp1.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/ramp2.png"));
        ramp2 = new TextureRegion(texture, 0, 0, Ramp.WIDTH, Ramp.HEIGHT);
        ramp2.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/ramp3.png"));
        ramp3 = new TextureRegion(texture, 0, 0, Ramp.WIDTH, Ramp.HEIGHT);
        ramp3.flip(false, true);
        TextureRegion[] ramps = { ramp1, ramp2, ramp3};
        rampAnimation = new Animation(0.06f, ramps);
        rampAnimation.setPlayMode(Animation.LOOP);
        
        
        //LOAD BOAT
        texture = new Texture(Gdx.files.internal("data/boatxs.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        boat =  new TextureRegion(texture, 0, 0, Player.WIDTH, Player.HEIGHT);
        boat.flip(false, true);

        //LOAD BOAT SHADOW
        texture = new Texture(Gdx.files.internal("data/boatshadowbm.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        boatshadow = new TextureRegion(texture, 0, 0, Player.WIDTH/2, Player.HEIGHT/2);
        boatshadow.flip(false, true);
        
        //LOAD BUOY LEFT
        texture = new Texture(Gdx.files.internal("data/buoyleft.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        buoyleft = new TextureRegion(texture, 0, 0, BuoyLeft.WIDTH , BuoyLeft.HEIGHT);
        buoyleft.flip(false, true);
        
        //LOAD BUOY RIGHT
        texture = new Texture(Gdx.files.internal("data/buoyright.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        buoyright = new TextureRegion(texture, 0, 0, BuoyRight.WIDTH , BuoyRight.HEIGHT);
        buoyright.flip(false, true);
        
        //LOAD MINE
        texture = new Texture(Gdx.files.internal("data/mine.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        mine = new TextureRegion(texture, 0, 0, Mine.WIDTH , Mine.HEIGHT);
        mine.flip(false, true);
        
        //LOAD DOCK
        texture = new Texture(Gdx.files.internal("data/dock.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        dock = new TextureRegion(texture, 0, 0, Dock.WIDTH , Dock.HEIGHT);
        dock.flip(false, true);
        
        //LOAD LOGO
        texture = new Texture(Gdx.files.internal("data/logo.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        logo = new TextureRegion(texture, 0, 0, 400 , 150);
        logo.flip(false, true);
        
        //MAIN LOGO
        texture = new Texture(Gdx.files.internal("data/MauriceLogo.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        mainLogo =  new TextureRegion(texture, 0, 0, 512, 128);
        mainLogo.flip(false,false);
        /*
        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        TextureRegion[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);
    	 */
    }
    
    public static void dispose() {
        // We must dispose of the texture when we are finished.
        //texture.dispose();
    	font.dispose();
    }

}
