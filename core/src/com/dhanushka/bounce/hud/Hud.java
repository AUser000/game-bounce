package com.dhanushka.bounce.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dhanushka.bounce.Bounce;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    TextButton button;
    TextButton button2;
    TextButton button3;
    private BitmapFont font;
    TextButton.TextButtonStyle textButtonStyle;

    public Hud(SpriteBatch sb) {
        viewport = new FitViewport(Bounce.V_WIDTH, Bounce.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.bottom();
        table.setFillParent(true);


        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        button = new TextButton("Button1", textButtonStyle);
        button2 = new TextButton("Button2", textButtonStyle);
        button3 = new TextButton("Button3", textButtonStyle);
        table.add(button).expandX().padBottom(12);
        table.add(button2).expandX().padBottom(12);
        table.add().expandX();
        table.add().expandX();
        table.add(button3).expandX().padBottom(12);

        stage.addActor(table);
    }
}
