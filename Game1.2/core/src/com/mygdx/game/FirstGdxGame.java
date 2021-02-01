package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class FirstGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Sprite sprite;
	private Vector3 vec;
	private BitmapFont font;
	private float width,height=0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		sprite = new Sprite(new Texture(Gdx.files.internal("heli1.png")));
		vec = new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0);
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		}

		public boolean IsInsideScreen(float x, float y){
			if(x < width-sprite.getWidth()/2 ||x > 0+sprite.getWidth()/2 &&
					y< height-sprite.getHeight()/2 || y > 0+sprite.getHeight()/2) {
				return true;
			}
			return false;
		}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isTouched() && IsInsideScreen(vec.x, vec.y)) {
					vec.x = Gdx.input.getX();
					vec.y = height-Gdx.input.getY();

				if (vec.x > width - sprite.getWidth() / 2) {
					vec.x = width - sprite.getWidth() / 2 ;
				}
				if (vec.y > height - sprite.getHeight() / 2) {
					vec.y = height - sprite.getHeight()/2;
				}
				if (vec.x < 0+ sprite.getWidth()/2 ){
					vec.x = 0 + sprite.getWidth()/2;
				}
				if (vec.y < 0+ sprite.getHeight()/2) {
					vec.y = 0 + sprite.getHeight()/2;
				}
			}
		batch.begin();
		batch.draw(sprite, vec.x-sprite.getWidth()/2, vec.y-sprite.getHeight()/2);
		font.draw(batch, "Position: " + "x: " + vec.x + " y: " + vec.y, 40, Gdx.graphics.getHeight()-40);
		font.getData().setScale(4, 4);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
