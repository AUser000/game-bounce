package com.dhanushka.bounce;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dhanushka.bounce.Screens.PlayScreen;
import com.dhanushka.bounce.tools.GameScreenManager;

public class Bounce extends Game {
	public static final int V_WIDTH = 17*32;
	public static final int V_HEIGHT = 9*32;
	public static final float PPM = 100;
	public SpriteBatch batch;
	public GameScreenManager gsm;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameScreenManager(this);
		gsm.setScreen(GameScreenManager.STATE.START);
	}

	@Override
	public void render () {
		super.render();
	}

}
