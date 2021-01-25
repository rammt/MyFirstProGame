package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class FirstGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Sprite sprite;
	private Vector3 vec;
	private float xSpeed = 4;
	private float ySpeed = 4;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("heli1.png");
		sprite = new Sprite(img);
		vec = new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Change direction
		if(vec.x > Gdx.graphics.getWidth() - sprite.getWidth()/2 || vec.x - sprite.getWidth()/2 < 0){
			xSpeed = -xSpeed;
		}
		if(vec.y > Gdx.graphics.getHeight() - sprite.getHeight()/2 || vec.y - sprite.getHeight()/2 < 0){
			ySpeed = -ySpeed;
		}

		vec.x += xSpeed;
		vec.y += ySpeed;

		batch.begin();
		batch.draw(sprite, vec.x-sprite.getWidth()/2, vec.y-sprite.getHeight()/2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
