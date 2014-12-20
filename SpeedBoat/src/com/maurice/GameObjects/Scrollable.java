package com.maurice.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameRenderer;

public class Scrollable {
	
    // Protected is similar to private, but allows inheritance by subclasses.
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledDown;

    public Scrollable(float x, float y, int width, int height, float velX, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(velX,scrollSpeed);
        this.width = width;
        this.height = height;
        isScrolledDown = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        //System.out.println("position set="+position.x);
        // If the Scrollable object is no longer visible:
        if (position.y >= 800) {
        	isScrolledDown = true;
        }
    }

    // Reset: Should Override in subclass for more specific behavior.
    public void reset(float newX) {
        this.position.y = newX;
        isScrolledDown = false;
        //System.out.println("background reset");
        
    }

    // Getters for instance variables
    public boolean isScrolledDown() {
        return isScrolledDown;
    }
    
    public Vector2 getPosition(){
    	return position;
    }

    public float getTailX() {
        return position.y;
    	//return ;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
