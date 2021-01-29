package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class FirstGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture recTextL, recTextR;
	private Sprite paddleLeft, paddleRight;
	private Vector2 leftPos, rightPos;
	private float xSpeed, ySpeed = 4f;


	@Override
	public void create () {
		batch = new SpriteBatch();
		recTextL = new Texture("paddle1.png");
		recTextR = new Texture("paddle2.png");
		paddleRight = new Sprite(recTextR);
		paddleLeft = new Sprite(recTextL);
		leftPos = new Vector2(paddleRight.getWidth(), Gdx.graphics.getHeight()/2);
		rightPos = new Vector2(Gdx.graphics.getWidth()-30, Gdx.graphics.getHeight()/2);
	}

	/*public boolean insideScreen(float y, Sprite paddle){
		if(y > Gdx.graphics.getHeight() - paddle.getHeight()/2 || y - paddle.getHeight()/2 < 0){
			return true;
		}
		return false;
	}*/

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(leftPos.y > Gdx.graphics.getHeight() - paddleLeft.getHeight()/2 || leftPos.y - paddleLeft.getHeight()/2 < 0){
			ySpeed = -ySpeed;
		}

		else if(rightPos.y > Gdx.graphics.getHeight() - paddleRight.getHeight()/2 || rightPos.y - paddleRight.getHeight()/2 < 0){
			ySpeed = -ySpeed;
		}

		leftPos.y += ySpeed;
		rightPos.y += ySpeed;

		batch.begin();
		batch.draw(paddleLeft, leftPos.x - paddleLeft.getWidth() / 2,leftPos.y - paddleLeft.getHeight() / 2);
		batch.draw(paddleRight, rightPos.x - paddleRight.getWidth() / 2,rightPos.y - paddleRight.getHeight() / 2);
		batch.end();
	}
	
	@Override
	public void dispose () { batch.dispose();}
}
