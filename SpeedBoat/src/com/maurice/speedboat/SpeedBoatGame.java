package com.maurice.speedboat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Interpolation;
import com.maurice.GameHelpers.AssetLoader;
import com.maurice.GameHelpers.ScreenTransition;
import com.maurice.Screens.AbstractGameScreen;
import com.maurice.Screens.GameScreen;
import com.maurice.Screens.SettingsScreen;
import com.maurice.Screens.SplashScreen;

public class SpeedBoatGame extends Game {
	private AbstractGameScreen currScreen;
	private AbstractGameScreen nextScreen;
	private boolean init;
	private FrameBuffer currFbo;
	private FrameBuffer nextFbo;
	private SpriteBatch batch;
	private float t;
	private ScreenTransition screenTransition;


	//PREFERENCES RELATED
	private static final String PREFS_NAME = "nocturnal";
	public static final String GYRO_KEY = "gyroSensitivity";
	public static int GYROSENSITIVITY;
	@Override
	public void create() {
		System.out.println("intro created Created!");
		//mainMenuScreen = new MainMenuScreen(this);
		AssetLoader.load();
        //gameScreen = new GameScreen(this);
        //restartScreen = new RestartScreen(this);
        //finishScreen = new FinishedScreen(this);
		//setScreen(new SettingsScreen(this));
		//setScreen(new SplashScreen(this));
		//setScreen(new testone(this));
		System.out.println("intro created Created!");
		loadPreferences();
		currScreen=new GameScreen(this);
		setScreen(currScreen);
	}
	
	//SCREEN TRANSITION RELATED
	public void setScreen (AbstractGameScreen screen) {
		 setScreen(screen, null);
	}
	 public void setScreen (AbstractGameScreen testtwo, ScreenTransition screenTransition) {
		 int w = Gdx.graphics.getWidth();
		 int h = Gdx.graphics.getHeight();
		 if (!init) {
			 currFbo = new FrameBuffer(Format.RGB888, w, h, false);
			 nextFbo = new FrameBuffer(Format.RGB888, w, h, false);
			 batch = new SpriteBatch();
			 init = true;
			 System.out.println("framebuffer init");
		 }
		 // start new transition
		 nextScreen = testtwo;
		 nextScreen.show(); // activate next screen
		 nextScreen.resize(w, h);
		 nextScreen.render(0); // let screen update() once
		 if (currScreen != null) currScreen.pause();
		 nextScreen.pause();
		 Gdx.input.setInputProcessor(null); // disable input
		 System.out.println("Input processor diabled");
		 this.screenTransition = screenTransition;
		 t = 0;
	}
	 
	 @Override
	 public void render () {
		 // get delta time and ensure an upper limit of one 60th second
		 float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f);
		 if (nextScreen == null) {
			 // no ongoing transition
			 if (currScreen != null) currScreen.render(deltaTime);
		 	} else {
		 		// ongoing transition
				 float duration = 0;
				 if (screenTransition != null)
				 duration = screenTransition.getDuration();
				 // update progress of ongoing transition
				 t = Math.min(t + deltaTime, duration);
				 if (screenTransition == null || t >= duration) {
					 //no transition effect set or transition has just finished
					 if (currScreen != null) currScreen.hide();
					 nextScreen.resume();
					 // enable input for next screen
					 Gdx.input.setInputProcessor(
							 nextScreen.getInputProcessor());
					 // switch screens
					 currScreen = nextScreen;
					 nextScreen = null;
					 screenTransition = null;
				 } else {
					 // render screens to FBOs
					 currFbo.begin();
					 if (currScreen != null) currScreen.render(deltaTime);
					 currFbo.end();
					 nextFbo.begin();
					 nextScreen.render(deltaTime);
					 nextFbo.end();
					 // render transition effect to screen
					 float alpha = t / duration;
					 screenTransition.render(batch,
							 currFbo.getColorBufferTexture(),
							 nextFbo.getColorBufferTexture(),
							 alpha);
				 }
			 }
		}
	 @Override
	 public void resize (int width, int height) {
		if (currScreen != null) currScreen.resize(width, height);
		if (nextScreen != null) nextScreen.resize(width, height);
	 }
	 @Override
	 public void pause () {
		 if (currScreen != null) currScreen.pause();
	 }
	 @Override
	 public void resume () {
		 if (currScreen != null) currScreen.resume();
	 }
	 @Override
	 public void dispose () {
		 super.dispose();
	        AssetLoader.dispose();
		 if (currScreen != null) currScreen.hide();
		 if (nextScreen != null) nextScreen.hide();
		 if (init) {
			 currFbo.dispose();
			 currScreen = null;
			 nextFbo.dispose();
			 nextScreen = null;
			 batch.dispose();
			 init = false;
		 }
	 }
	
	 //PREFERENCES RELATED
     public void loadPreferences(){
    	 Preferences prefs = Gdx.app.getPreferences( PREFS_NAME );
    	 System.out.println(prefs.get());
    	 //prefs.putInteger( "gyroSensitivity", 1000 );
    	 //getPrefs().flush();
    	 GYROSENSITIVITY=prefs.getInteger(GYRO_KEY,5);
    	 System.out.println("gyro sensitivity="+GYROSENSITIVITY);
    	 
    	 prefs.flush();
    	 //getPrefs().putInteger( "high2", 23 );
         //getPrefs().flush();
    	 //System.out.println("high2="+getPrefs().getInteger( "high2", 0 ));
    	 //if(highScore>yourScore){
    		 //highScore=yourScore;
    		 //prefs.putInteger( "high1", yourScore );
    	 //}
    	 
     }
 	public static String getPrefsName() {
 		return PREFS_NAME;
 	}
}
