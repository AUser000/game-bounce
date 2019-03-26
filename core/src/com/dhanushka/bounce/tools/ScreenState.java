package com.dhanushka.bounce.tools;

import com.badlogic.gdx.Screen;

public interface ScreenState extends Screen {

    public void update(float dt);

    public void activeInputProcessor();
}
