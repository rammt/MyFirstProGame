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
	private float xFart = 4;
	private float yFart = 4;

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
//|| vec.y > Gdx.graphics.getHeight()

		if(vec.x > Gdx.graphics.getWidth() - sprite.getWidth()/2){
			xFart -= xFart;

		}
		if(vec.y > Gdx.graphics.getHeight()- sprite.getHeight()/2){
			yFart -= yFart;
		}
		/*
		if(vec.y > Gdx.graphics.getHeight() && vec.x < Gdx.graphics.getWidth()){
			yFart -= yFart;
			xFart += xFart;
			//sprite.flip(false, true);
		}
		if(vec.x > Gdx.graphics.getWidth() && vec.y < Gdx.graphics.getHeight()){
			yFart += yFart;
			xFart -= xFart;
			sprite.flip(true, false);
		}*/
		vec.x += xFart;
		vec.y += yFart;

		batch.begin();
		batch.draw(sprite, vec.x-sprite.getWidth()/2, vec.y-sprite.getHeight()/2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}