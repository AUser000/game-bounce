package com.dhanushka.bounce.sprites.utils;

import com.badlogic.gdx.physics.box2d.Fixture;

public class KeyValue {
    public Fixture fixtureA;
    public Fixture fixtureB;

    public KeyValue(Fixture fixtureA, Fixture fixtureB) {
        this.fixtureA = fixtureA;
        this.fixtureB = fixtureB;
    }

    public Fixture getKey() {

        return fixtureA;
    }

    public Fixture getValue() {

        return fixtureB;
    }
}
