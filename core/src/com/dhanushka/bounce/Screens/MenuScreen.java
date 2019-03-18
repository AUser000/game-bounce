package com.dhanushka.bounce.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dhanushka.bounce.Bounce;

public class MenuScreen implements Screen {
    Stage stage;
    Viewport viewport;
    OrthographicCamera cam;

    public MenuScreen(SpriteBatch sb) {
        viewport = new FitViewport(Bounce.V_WIDTH/Bounce.PPM, Bounce.V_HEIGHT/Bounce.PPM, cam);
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
        stage = new Stage(viewport, sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.draw();
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

    }
}
