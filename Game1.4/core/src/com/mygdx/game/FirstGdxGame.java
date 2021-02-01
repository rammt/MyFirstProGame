package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;



public class FirstGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture recTextL, recTextR,ballText, background;
	private Sprite paddleLeft, paddleRight,ball, backgroundSprite;
	private float xSpeed = 6f;
	private float ySpeed = 6f;
	private float paddleSpeed = 10f;
	private Rectangle recL, recR, recB;
	private int score1,score2 = 0;
	private BitmapFont font;


	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		recTextL = new Texture("paddle1.png");
		recTextR = new Texture("paddle1.png");
		ballText = new Texture("ball.png");
		background = new Texture("background.png");
		paddleRight = new Sprite(recTextR);
		paddleLeft = new Sprite(recTextL);
		ball = new Sprite(ballText);
		backgroundSprite = new Sprite(background);

		recL = new Rectangle(0,Gdx.graphics.getHeight()/2,50, 250);
		recR = new Rectangle(Gdx.graphics.getWidth()-paddleRight.getWidth()/2, Gdx.graphics.getHeight()/2, 50,250);
		recB = new Rectangle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 30, 30);
	}

	public boolean IsInsideScreen(float x, float y){
		if(recL.y < Gdx.graphics.getHeight() || recL.y > 0) {
			return true;
		}
		return false;
	}

	public void startOver(){
		recB.x = Gdx.graphics.getWidth()/2;
		recB.y = Gdx.graphics.getHeight()/2;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isTouched() && IsInsideScreen(recL.x, recL.y)) {

			recL.y = Gdx.graphics.getHeight()-Gdx.input.getY();

			if (recL.x > paddleRight.getWidth() || recL.x < paddleRight.getWidth() ) {
				recL.x = 0;
			}
			if (recL.y > Gdx.graphics.getHeight() - paddleLeft.getHeight() / 2) {
				recL.y = Gdx.graphics.getHeight() - paddleLeft.getHeight()/2;
			}
			if (recL.y < 0) {
				recL.y = 0;
			}
		}

		if(recR.y > Gdx.graphics.getHeight()-recR.getHeight()|| recR.y < 0){
			paddleSpeed = -paddleSpeed;
		}

		if(recB.y > Gdx.graphics.getHeight() - ball.getHeight()/2 || recB.y - ball.getHeight()/2 < 0){
			ySpeed = -ySpeed;
		}
		if(recB.x > Gdx.graphics.getWidth()){
			startOver();
			score1 += 1;
		}
		if(recB.x - ball.getWidth()/2 < - 50){
			startOver();
			score2 += 1;
		}

		recB.y += ySpeed;
		recB.x += xSpeed;
		recR.y += paddleSpeed;


		if(recB.overlaps(recR) || recB.overlaps(recL)){
			xSpeed = -xSpeed;
		}


		batch.begin();
		batch.draw(paddleLeft, recL.x,recL.y, 50, 250);
		batch.draw(paddleRight, recR.x,recR.y, 50, 250);
		batch.draw(ball, recB.x,recB.y, 30, 30);
		font.draw(batch, "Score: " + "player1: " + score1+ " player2: " + score2, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-40);
		if(score1 > 5){
			batch.draw(backgroundSprite, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			font.draw(batch, "You Win", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 );
		}
		if(score2 > 5){
			batch.draw(backgroundSprite, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			font.draw(batch, "You Lose", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 );
		}
		font.getData().setScale(4, 4);
		batch.end();
	}
	
	@Override
	public void dispose () { batch.dispose(); font.dispose();}
}
