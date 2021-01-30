package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class FirstGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture recTextL, recTextR,ballText;
	private Sprite paddleLeft, paddleRight,ball;
	private Vector2 leftPos, rightPos;
	private Vector3 ballPos;
	private float xSpeed = 4f;
	private float ySpeed = 4f;
	private float paddleSpeed = 4f;
	private Rectangle recL, recR, recB;
	private int score1,score2 = 0;
	private BitmapFont font;
	private float countDown;

	private int randomNumber;

	public void randomSpeeed(float deltaTime) {
		countDown -= deltaTime;
		if (countDown <= 0) {
			Random random = new Random();
			randomNumber= random.nextInt(3) + 1;
			countDown += 1000; // add one second
		}
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		recTextL = new Texture("paddle1.png");
		recTextR = new Texture("paddle2.png");
		ballText = new Texture("ball.png");
		paddleRight = new Sprite(recTextR);
		paddleLeft = new Sprite(recTextL);
		ball = new Sprite(ballText);
		leftPos = new Vector2(paddleRight.getWidth(), Gdx.graphics.getHeight()/2);
		rightPos = new Vector2(Gdx.graphics.getWidth()-30, Gdx.graphics.getHeight()/2);
		ballPos = new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0);
	}

	/*public boolean insideScreen(float y, Sprite paddle){
		if(y > Gdx.graphics.getHeight() - paddle.getHeight()/2 || y - paddle.getHeight()/2 < 0){
			return true;
		}
		return false;
	}*/
	public boolean IsInsideScreen(float x, float y){
		if(leftPos.x < Gdx.graphics.getWidth()-paddleLeft.getWidth()/2 ||leftPos.x > 0+paddleLeft.getWidth()/2 &&
				leftPos.y< Gdx.graphics.getHeight()-paddleLeft.getHeight()/2 || leftPos.y > 0+paddleLeft.getHeight()/2) {
			return true;
		}
		return false;
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isTouched() && IsInsideScreen(leftPos.x, leftPos.y)) {
			leftPos.x = Gdx.input.getX();
			leftPos.y = Gdx.graphics.getHeight()-Gdx.input.getY();

			if (leftPos.x > Gdx.graphics.getWidth() - paddleLeft.getWidth() / 2) {
				leftPos.x = Gdx.graphics.getWidth() - paddleLeft.getWidth() / 2 ;
			}
			if (leftPos.y > Gdx.graphics.getHeight() - paddleLeft.getHeight() / 2) {
				leftPos.y = Gdx.graphics.getHeight() - paddleLeft.getHeight()/2;
			}
			if (leftPos.x < 0+ paddleLeft.getWidth()/2 ){
				leftPos.x = 0 + paddleLeft.getWidth()/2;
			}
			if (leftPos.y < 0+ paddleLeft.getHeight()/2) {
				leftPos.y = 0 + paddleLeft.getHeight()/2;
			}
		}

		if(rightPos.y > Gdx.graphics.getHeight() - paddleRight.getHeight()/2 || rightPos.y - paddleRight.getHeight()/2 < 0){
			paddleSpeed = -paddleSpeed;
		}

		// Change ball direction

		if(ballPos.y > Gdx.graphics.getHeight() - ball.getHeight()/2 || ballPos.y - ball.getHeight()/2 < 0){
			ySpeed = -ySpeed;
		}

		leftPos.y += paddleSpeed;
		rightPos.y += paddleSpeed;
		ballPos.y += ySpeed;
		ballPos.x += xSpeed;

		recL = new Rectangle(leftPos.x,leftPos.y,paddleLeft.getWidth(), paddleLeft.getHeight());
		recR = new Rectangle(rightPos.x, rightPos.y, paddleRight.getWidth(),paddleRight.getHeight());
		recB = new Rectangle(ballPos.x, ballPos.y, ball.getWidth(), ball.getHeight());

		if(recB.overlaps(recR) || ballPos.x < Gdx.graphics.getWidth() + recR.getWidth()/2){
			xSpeed = -xSpeed;
			ySpeed = -ySpeed;
			score2 += 1;
		}
		if(recB.overlaps(recL) || ballPos.x > 0 + recL.getWidth()/2){
			xSpeed = -xSpeed;
			ySpeed = -ySpeed;
			score1 +=1;
		}



		batch.begin();
		batch.draw(paddleLeft, leftPos.x - paddleLeft.getWidth() / 2,leftPos.y - paddleLeft.getHeight() / 2);
		batch.draw(paddleRight, rightPos.x - paddleRight.getWidth() / 2,rightPos.y - paddleRight.getHeight() / 2);
		batch.draw(ball, ballPos.x - ball.getWidth() / 2,ballPos.y - ball.getHeight() / 2);
		font.draw(batch, "Score: " + "player1: " + score1+ " player2: " + score2, 40, Gdx.graphics.getHeight()-40);
		font.getData().setScale(4, 4);
		batch.end();
	}
	
	@Override
	public void dispose () { batch.dispose();}
}
