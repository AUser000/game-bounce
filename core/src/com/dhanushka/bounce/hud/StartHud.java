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
import com.dhanushka.bounce.tools.Constants;
import com.dhanushka.bounce.tools.GameScreenManager;

public class StartHud {
    public Stage stage;
    private Viewport viewport;

    TextButton playButton;
    private BitmapFont font;

    public StartHud(SpriteBatch sb) {
        viewport = new FitViewport(Bounce.V_WIDTH, Bounce.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        font = new BitmapFont();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        playButton = new TextButton("BOUNCE TWO", style);


        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(playButton);

        stage.addActor(table);
    }

    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}
