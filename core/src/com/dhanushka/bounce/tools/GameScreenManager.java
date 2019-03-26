package com.dhanushka.bounce.tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
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


        gameScreens.put(STATE.PLAY, new PlayScreen(bounce, 1));
        gameScreens.put(STATE.START, new StartScreen(this, bounce.batch));
        gameScreens.put(STATE.MAIN_MENU, new MenuScreen(bounce.batch, this));
        gameScreens.put(STATE.SETTINGS, new GameOverScreen());
    }

    public void setScreen(STATE nextScreen) {
        bounce.setScreen(gameScreens.get(nextScreen));
        gameScreens.get(nextScreen).activeInputProcessor();
//        gameScreens.get(previous).dispose();
//        previous = nextScreen;

    }
}
