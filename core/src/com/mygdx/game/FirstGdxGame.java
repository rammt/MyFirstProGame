package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FirstGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	//Texture img2;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		/*TextureRegion tex1 = new TextureRegion(new Texture("heli1.png"));
		TextureRegion tex2 = new TextureRegion(new Texture("heli2.png"));
		TextureRegion tex3 = new TextureRegion(new Texture("heli3.png"));
		TextureRegion tex4 = new TextureRegion(new Texture("heli4.png"));

		Animation playerAnimation = new Animation(0.1f, tex1, tex2, tex3, tex4);
		*/
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
