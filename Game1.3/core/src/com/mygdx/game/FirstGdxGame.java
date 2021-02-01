package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class FirstGdxGame extends ApplicationAdapter implements ApplicationListener {
	private static final int FRAME_COLS = 4, FRAME_ROWS = 1;
	private static final float RUNNING_FRAME_DURATION = 0.1f;


	Animation<TextureRegion> animation;
	Animation<TextureRegion> animation2;
	private Texture movingHeli;
	private SpriteBatch batch;
	float stateTime;
	private int frameWidth;
	private int frameHeight;

	private Sprite sprite;
	private Vector3 vec;
	private Vector3 vec2;
	private Rectangle bounds1, bounds2, intersection;

	// Generate random initial speed
	private Random r = new Random();
	private float minSpeed = 0.5f;
	private float maxSpeed = 5f;
	private float xSpeed = minSpeed + r.nextFloat() * (maxSpeed - minSpeed);
	private float ySpeed =  minSpeed + r.nextFloat() * (maxSpeed - minSpeed);
	private float xSpeed2 =  -minSpeed + r.nextFloat() * (maxSpeed - minSpeed);
	private float ySpeed2 =  -minSpeed + r.nextFloat() * (maxSpeed - minSpeed);

	@Override
	public void create () {

		movingHeli = new Texture(Gdx.files.internal("heli.png"));

		frameWidth = movingHeli.getWidth() / FRAME_COLS;
		frameHeight = movingHeli.getHeight() / FRAME_ROWS;

		TextureRegion[][] tmp = TextureRegion.split(movingHeli, frameWidth, frameHeight);

		TextureRegion[] heliFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				heliFrames[index++] = tmp[i][j];
			}
		}

		animation = new Animation<TextureRegion>(RUNNING_FRAME_DURATION, heliFrames);
		animation2 = new Animation<TextureRegion>(RUNNING_FRAME_DURATION, heliFrames);

		batch = new SpriteBatch();
		stateTime = 0f;

		sprite = new Sprite(movingHeli);
		vec = new Vector3(Gdx.graphics.getWidth()/(2*FRAME_COLS), Gdx.graphics.getHeight()/(2*FRAME_ROWS),0);
		vec2 = new Vector3(frameWidth, frameHeight,0);

		// Set initial facing for helocopter
		sprite.flip(xSpeed > 0, false);

		bounds1 = new Rectangle(vec.x, vec.y, frameWidth, frameHeight);
		bounds2 = new Rectangle(vec2.x, vec2.y, frameWidth, frameHeight);
		intersection = new Rectangle();
	}

	public boolean collides(Rectangle helicopter, Rectangle helicopter2) {
		return helicopter.overlaps(helicopter2);
	}

	public boolean collidesX(Rectangle helicopter, Rectangle helicopter2) {
		if (Intersector.intersectRectangles(helicopter, helicopter2, intersection)) {
			return intersection.x > bounds1.x || intersection.x + intersection.width < helicopter.x + frameWidth;
		}
		return false;
	}

	public boolean collidesY(Rectangle helicopter, Rectangle helicopter2) {
		if (Intersector.intersectRectangles(helicopter, helicopter2, intersection)) {
			return intersection.y > bounds1.y || intersection.y + intersection.height < helicopter.y + frameHeight;
		}
		return false;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		// Reposition invisible rectangles
		bounds1.setPosition(vec.x, vec.y);
		bounds2.setPosition(vec2.x, vec2.y);

		// Change direction
		if(vec.x > Gdx.graphics.getWidth() - frameWidth/2 || vec.x - frameWidth/2 < 0 || collidesX(bounds1, bounds2)){
			xSpeed *= -1;
			sprite.flip(true,false);
		}
		if(vec.y > Gdx.graphics.getHeight() - frameHeight/2 || vec.y - frameHeight/2 < 0 || collidesY(bounds1, bounds2)){
			ySpeed *= -1;
		}
		if(vec2.x > Gdx.graphics.getWidth() - frameWidth/2 || vec2.x - frameWidth/2 < 0 || collidesX(bounds1, bounds2)){
			xSpeed2 *= -1;
		}
		if(vec2.y > Gdx.graphics.getHeight() - frameHeight/2 || vec2.y - frameHeight/2 < 0 || collidesY(bounds1, bounds2)){
			ySpeed2 *= -1;
		}

		vec.x += xSpeed;
		vec.y += ySpeed;
		vec2.x += xSpeed2;
		vec2.y += ySpeed2;

		TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
		TextureRegion currentFrame2 = animation2.getKeyFrame(stateTime, true);
		batch.begin();
		batch.draw(currentFrame, vec.x-frameWidth/2, vec.y-frameHeight/2);
		batch.draw(currentFrame2, vec2.x-frameWidth/2, vec2.y-frameHeight/2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		movingHeli.dispose();
	}
}
