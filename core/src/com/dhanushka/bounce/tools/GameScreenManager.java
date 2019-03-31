package com.dhanushka.bounce.tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dhanushka.bounce.Bounce;
import com.dhanushka.bounce.Screens.GameOverScreen;
import com.dhanushka.bounce.Screens.MenuScreen;
import com.dhanushka.bounce.Screens.PlayScreen;
import com.dhanushka.bounce.Screens.StartScreen;

import java.util.HashMap;
import java.util.Stack;

public class GameScreenManager {

    Bounce bounce;
    private HashMap<STATE, ScreenState> gameScreens;
    private STATE previous;
    FileHandle file;

    public enum STATE {
        MAIN_MENU,
        PLAY,
        SETTINGS,
        START
    }

    public GameScreenManager(Bounce game) {
        bounce = game;
        initGameScreen();
        previous = STATE.PLAY;
    }

    private void initGameScreen() {
        gameScreens = new HashMap<STATE, ScreenState>();

        gameScreens.put(STATE.PLAY, new PlayScreen(bounce, getLevel()));
        gameScreens.put(STATE.START, new StartScreen(this, bounce.batch));
        gameScreens.put(STATE.MAIN_MENU, new MenuScreen(bounce.batch, this));
        gameScreens.put(STATE.SETTINGS, new GameOverScreen());
    }

    private int getLevel() {
        int level = 1;
        file = Gdx.files.internal(Constants.LVL_READER);
        String line = file.readString();
        try {
            level = Integer.valueOf(line);
            Gdx.app.log("File loading", "level passed");
        } catch (Exception e) {
            level = 1;
            Gdx.app.log("File loading", "level faild");
        }
        return level;
    }

    public void setScreen(STATE nextScreen) {
        bounce.setScreen(gameScreens.get(nextScreen));
        gameScreens.get(nextScreen).activeInputProcessor();
//        gameScreens.get(previous).dispose();
//        previous = nextScreen;
    }
}
