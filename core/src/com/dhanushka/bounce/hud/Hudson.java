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
import com.dhanushka.bounce.tools.Constants;
import com.dhanushka.bounce.tools.GameScreenManager;

public class Hudson {
    public Stage stage;
    private Viewport viewport;
    private Skin skin;
    private TextureAtlas atlas;

    TextButton playButton;
    TextButton exitButton;
    TextButton scoreButton;
    private BitmapFont font;
    private final GameScreenManager gsm;
    TextButton.TextButtonStyle textButtonStyle;

    public Hudson(SpriteBatch sb, final GameScreenManager gsm) {
        this.gsm = gsm;
        viewport = new FitViewport(Bounce.V_WIDTH, Bounce.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        atlas = new TextureAtlas(Constants.BIG_BUTTON_PACK);
        skin = new Skin(atlas);

        Gdx.input.setInputProcessor(stage);


        font = new BitmapFont();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        playButton = new TextButton("PLAY", style);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.setScreen(GameScreenManager.STATE.PLAY);
                return true;
            }
        });

        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
        style2.font = font;

        exitButton = new TextButton("EXIT", style2);
        exitButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //
                return false;
            }
        });

        TextButton.TextButtonStyle style3 = new TextButton.TextButtonStyle();
        style3.font = font;

        scoreButton = new TextButton("HIGH SCORE", style3);
        scoreButton.addListener(new InputListener(){
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
        table.center();
        table.setFillParent(true);
        table.add(playButton).expandX().padBottom(20);
        table.row();
        table.add(scoreButton).expandX().padBottom(20);
        table.row();
        table.add(exitButton).expandX().padBottom(20);

        stage.addActor(table);
    }

    public void despose() {
        skin.dispose();
        stage.dispose();
        atlas.dispose();
        font.dispose();
    }
}
