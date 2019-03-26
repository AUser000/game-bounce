package com.dhanushka.bounce.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dhanushka.bounce.Bounce;
import com.dhanushka.bounce.hud.Hud;
import com.dhanushka.bounce.hud.Hudson;
import com.dhanushka.bounce.sprites.SmallBall;
import com.dhanushka.bounce.tools.Constants;
import com.dhanushka.bounce.tools.GameScreenManager;
import com.dhanushka.bounce.tools.ScreenState;

import static com.badlogic.gdx.Gdx.app;

public class MenuScreen implements ScreenState {
    Stage stage;
    Viewport viewport;
    final GameScreenManager gsm;
    SpriteBatch sb;
    OrthographicCamera cam;

    private BitmapFont font;
    private Hudson hud;

    public MenuScreen(SpriteBatch sb, final GameScreenManager gsm) {
        this.sb = sb;
        hud = new Hudson(sb, gsm);
        this.gsm = gsm;
        cam = new OrthographicCamera();
        viewport = new FitViewport(Bounce.V_WIDTH, Bounce.V_HEIGHT, cam);
    }

    @Override
    public void show() {

    }

    public void update() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        hud.despose();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void activeInputProcessor() {
        Gdx.input.setInputProcessor(hud.stage);
    }
}
