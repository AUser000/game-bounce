package com.dhanushka.bounce.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dhanushka.bounce.Bounce;
import com.dhanushka.bounce.sprites.SmallBall;
import com.dhanushka.bounce.tools.FileLoaderConstants;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Skin skin;
    private TextureAtlas atlas;

    TextButton buttonUp;
    TextButton buttonLeft;
    TextButton buttonRight;
    private BitmapFont font;
    TextButton.TextButtonStyle textButtonStyle;

    public Hud(SpriteBatch sb) {
        viewport = new FitViewport(Bounce.V_WIDTH, Bounce.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        atlas = new TextureAtlas(FileLoaderConstants.BIG_BUTTON_PACK);
        skin = new Skin(atlas);

        Gdx.input.setInputProcessor(stage);


        font = new BitmapFont();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable(FileLoaderConstants.BUTTON_UP);
        style.font = font;
        buttonUp = new TextButton("", style);
        buttonUp.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                SmallBall.up = false;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                SmallBall.up = true;
                return true;
            }
        });

        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
        style2.up = skin.getDrawable(FileLoaderConstants.BUTTON_LEFT);
        style2.font = font;

        buttonLeft = new TextButton("", style2);
        buttonLeft.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                SmallBall.left = false;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                SmallBall.left = true;
                return true;
            }
        });

        TextButton.TextButtonStyle style3 = new TextButton.TextButtonStyle();
        style3.up = skin.getDrawable(FileLoaderConstants.BUTTON_RIGHT);
        style3.font = font;

        buttonRight = new TextButton("", style3);
        buttonRight.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                SmallBall.right = false;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                SmallBall.right = true;
                return true;
            }
        });


        Table table = new Table();
        table.bottom();
        table.setFillParent(true);
        table.add(buttonLeft).expandX().padBottom(12);
        table.add(buttonRight).expandX().padBottom(12);
        table.add().expandX();
        table.add().expandX();
        table.add().expandX();
        table.add(buttonUp).expandX().padBottom(12);

        stage.addActor(table);
    }
}
