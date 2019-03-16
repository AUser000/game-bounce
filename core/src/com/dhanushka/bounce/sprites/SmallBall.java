package com.dhanushka.bounce.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.dhanushka.bounce.Bounce;
import com.dhanushka.bounce.sprites.utils.States;
import com.dhanushka.bounce.tools.FileLoaderConstants;

public class SmallBall extends Sprite {

    private World world;
    TextureRegion ballpng;
    public Body ball;
    boolean notBounced = false;
    float previouse;
    States stateY;
    States stateX;
    boolean canBounce;
    TiledMap map;
    public static boolean up = false;
    public static boolean left = false;
    public static boolean right = false;
    //Array<Body> bodies = new Array<Body>(world.getBodyCount());

    public SmallBall(World world) {
        super(new Texture(FileLoaderConstants.SMALL_BALL)); //android/assets/
        this.world = world;
        defineBall(); //"android/assets/ball-small.png"
        ballpng = new TextureRegion(getTexture(), 0, 0, 31, 31);
        setBounds(0, 0, 31/Bounce.PPM, 31/Bounce.PPM);
        setRegion(ballpng);
        canBounce = true;
    }

    private void defineBall() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(256/ Bounce.PPM, 128/ Bounce.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        ball = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15/ Bounce.PPM);

        fixtureDef.shape = shape;
        ball.createFixture(fixtureDef);
        //ball.setLinearDamping(3f);
    }

    @Deprecated
    public void handleInputs(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && ball.getLinearVelocity().x <= 4f) {
            ball.applyLinearImpulse(new Vector2(0.25f, 0), ball.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && ball.getLinearVelocity().x >= -4f) {
            ball.applyLinearImpulse(new Vector2(-0.25f, 0), ball.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && ball.getLinearVelocity().y == 0) { //&& state == States.ONTHEGROUND) {
            stateY = States.JUMPING;
            ball.applyLinearImpulse(new Vector2(0, 5f), ball.getWorldCenter(), true);
            canBounce = true;
        }
    }

    public void handleButtonInputs(float dt) {
        if (right && ball.getLinearVelocity().x <= 4f) {
            ball.applyLinearImpulse(new Vector2(0.25f, 0), ball.getWorldCenter(), true);
        }
        if (left && ball.getLinearVelocity().x >= -4f) {
            ball.applyLinearImpulse(new Vector2(-0.25f, 0), ball.getWorldCenter(), true);
        }
        if (up && ball.getLinearVelocity().y == 0) { //&& state == States.ONTHEGROUND) {
            stateY = States.JUMPING;
            ball.applyLinearImpulse(new Vector2(0, 5f), ball.getWorldCenter(), true);
            canBounce = true;
        }
    }

    public void update(float dt) {
        setPosition(ball.getPosition().x - getWidth()/2, ball.getPosition().y - getHeight()/2);
        if(ball.getLinearVelocity().y < 0) {
            stateY = States.FALLING;
        }
        if(canBounce && stateY == States.FALLING && ball.getLinearVelocity().y == 0) {
            canBounce = false;
            ball.applyLinearImpulse(new Vector2(0, 2f), ball.getWorldCenter(), true);
        }
    }

    public static void out() {
        System.out.print("-out-");
    }

}
