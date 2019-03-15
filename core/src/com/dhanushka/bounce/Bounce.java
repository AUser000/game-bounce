package com.dhanushka.bounce;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dhanushka.bounce.Screens.PlayScreen;

public class Bounce extends Game {
	public static final int V_WIDTH = 17*32;
	public static final int V_HEIGHT = 9*32;
	public static final float PPM = 100;
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this, 1));
	}

	@Override
	public void render () {
		super.render();
	}

}
