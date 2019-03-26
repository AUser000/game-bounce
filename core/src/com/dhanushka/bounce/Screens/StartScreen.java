package com.dhanushka.bounce.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dhanushka.bounce.Bounce;
import com.dhanushka.bounce.hud.StartHud;
import com.dhanushka.bounce.tools.GameScreenManager;
import com.dhanushka.bounce.tools.ScreenState;

public class StartScreen implements ScreenState {

    private GameScreenManager gsm;
    private SpriteBatch sb;
    private float maxtime;
    StartHud hud;
    Viewport viewport;
    OrthographicCamera cam;


    public StartScreen(GameScreenManager gsm, SpriteBatch sb) {
        this.gsm = gsm;
        this.sb = sb;
        maxtime = 0;
        hud = new StartHud(sb);
        cam = new OrthographicCamera();
        viewport = new FitViewport(Bounce.V_WIDTH, Bounce.V_HEIGHT, cam);
    }

    @Override
    public void update(float dt) {
        if (dt > 5000) {
            gsm.setScreen(GameScreenManager.STATE.MAIN_MENU);
        }
        maxtime += maxtime;
    }

    @Override
    public void activeInputProcessor() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        hud.dispose();
    }
}
