package com.dhanushka.bounce.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.dhanushka.bounce.Bounce;
import com.dhanushka.bounce.Screens.PlayScreen;
import com.dhanushka.bounce.sprites.utils.KeyValue;
import com.dhanushka.bounce.sprites.utils.States;
import com.dhanushka.bounce.tools.Constants;
import com.dhanushka.bounce.tools.GameScreenManager;

public class Ball extends Sprite {

    private World world;
    TextureRegion ballpng;
    public Body smallBallBody;
    public Body   bigBallBody;
    States stateY;
    boolean canBounce;
    private float density = 1f;
    private static KeyValue fixturePair;
    private TextureRegion ballRegion;
    int maxTime = 3;
    int time = 0;
    public GameScreenManager gsm;
    private Vector2 savedPosition;
    public static boolean goNextLvl      = false;
    public static boolean svdBallIsSmall = true;
    public static boolean isBig          = false;
    public static boolean change         = false;
    public static boolean ballIsInWater  = false;
    public static boolean headHit        = false;
    public static boolean up             = false;
    public static boolean left           = false;
    public static boolean right          = false;
    public static boolean isOut          = false;
    private int lvl;
    //Array<Body> bodies = new Array<Body>(world.getBodyCount());

    public Ball(World world, PlayScreen screen, int lvl,GameScreenManager gsm) {
        super(new Texture(Constants.SMALL_BALL)); //android/assets/
        this.lvl = lvl;
        this.world = world;
        if(gsm == null) {
            Gdx.app.log("gsm", "isnt null");
        }
        this.gsm = gsm;
        defineBall(); //"android/assets/ball-small.png"
        savedPosition = new Vector2(64/ Bounce.PPM, 128/ Bounce.PPM);
        fixturePair = new KeyValue(null, null);
        canBounce = true;
    }

    public Body getBody() {
        if(isBig) return bigBallBody;
        else return smallBallBody;
    }

    private void defineBall() {
        BodyDef bodyDef = new BodyDef();
        savedPosition = new Vector2(64/ Bounce.PPM, 128/ Bounce.PPM);
        bodyDef.position.set(savedPosition);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        smallBallBody = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15/ Bounce.PPM);

        fixtureDef.shape = shape;
        smallBallBody.createFixture(fixtureDef).setUserData(this);
        fixtureDef.isSensor = true;

        // now head
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-5/Bounce.PPM, 15/Bounce.PPM), new Vector2(5/Bounce.PPM, 15/Bounce.PPM));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        smallBallBody.createFixture(fixtureDef).setUserData("head");
        //ball.setLinearDamping(3f);
        ballRegion = new TextureRegion(getTexture(), 0, 0, 31, 31);
        setBounds(0, 0, 31/Bounce.PPM, 31/Bounce.PPM);
        setRegion(ballRegion);
    }

    private void defineSmallBall() {
        Vector2 position = bigBallBody.getPosition();
        world.destroyBody(bigBallBody);
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        smallBallBody = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15/ Bounce.PPM);

        fixtureDef.shape = shape;
        smallBallBody.createFixture(fixtureDef);

        // now head
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-5/Bounce.PPM, 15/Bounce.PPM), new Vector2(5/Bounce.PPM, 15/Bounce.PPM));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        smallBallBody.createFixture(fixtureDef).setUserData("head");
        //ball.setLinearDamping(3f);
        ballRegion = new TextureRegion(getTexture(), 0, 0, 31, 31);
        setBounds(0, 0, 31/Bounce.PPM, 31/Bounce.PPM);
        setRegion(ballRegion);
    }

    private void defineBigBall() {
        Vector2 position = smallBallBody.getPosition();
        world.destroyBody(smallBallBody);
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position); // position
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bigBallBody = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(21/ Bounce.PPM);

        fixtureDef.shape = shape;
        bigBallBody.createFixture(fixtureDef);

        // now head
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-5/Bounce.PPM, 21/Bounce.PPM), new Vector2(5/Bounce.PPM, 21/Bounce.PPM));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        bigBallBody.createFixture(fixtureDef).setUserData("head");
        //ball.setLinearDamping(3f);
        ballRegion = new TextureRegion(getTexture(), 0, 0, 31, 31);
        setBounds(0, 0, 42/Bounce.PPM, 42/Bounce.PPM);
        setRegion(ballRegion);
    }

    private void defineSmallBall(Vector2 position) {
        world.destroyBody(bigBallBody);
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        smallBallBody = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15/ Bounce.PPM);

        fixtureDef.shape = shape;
        smallBallBody.createFixture(fixtureDef);

        // now head
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-5/Bounce.PPM, 15/Bounce.PPM), new Vector2(5/Bounce.PPM, 15/Bounce.PPM));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        smallBallBody.createFixture(fixtureDef).setUserData("head");
        //ball.setLinearDamping(3f);
        ballRegion = new TextureRegion(getTexture(), 0, 0, 31, 31);
        setBounds(0, 0, 31/Bounce.PPM, 31/Bounce.PPM);
        setRegion(ballRegion);
    }

    private void defineBigBall(Vector2 position) {
        world.destroyBody(smallBallBody);
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position); // position
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bigBallBody = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(21/ Bounce.PPM);

        fixtureDef.shape = shape;
        bigBallBody.createFixture(fixtureDef);

        // now head
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-5/Bounce.PPM, 21/Bounce.PPM), new Vector2(5/Bounce.PPM, 21/Bounce.PPM));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        bigBallBody.createFixture(fixtureDef).setUserData("head");
        //ball.setLinearDamping(3f);
        ballRegion = new TextureRegion(getTexture(), 0, 0, 31, 31);
        setBounds(0, 0, 42/Bounce.PPM, 42/Bounce.PPM);
        setRegion(ballRegion);
    }

    @Deprecated
    public void handleInputs(float dt) {
        if(change) {
            if(isBig) {
                defineBigBall();
            } else {
                defineSmallBall();
            }
            change = false;
        }
        if (isBig) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && smallBallBody.getLinearVelocity().x <= 4f) {
                smallBallBody.applyLinearImpulse(new Vector2(0.25f, 0), smallBallBody.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && smallBallBody.getLinearVelocity().x >= -4f) {
                smallBallBody.applyLinearImpulse(new Vector2(-0.25f, 0), smallBallBody.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) && smallBallBody.getLinearVelocity().y == 0 && !headHit) { //&& state == States.ONTHEGROUND) {
                stateY = States.JUMPING;
                smallBallBody.applyLinearImpulse(new Vector2(0, 5f), smallBallBody.getWorldCenter(), true);
                canBounce = true;
            }
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && smallBallBody.getLinearVelocity().x <= 4f) {
                smallBallBody.applyLinearImpulse(new Vector2(0.25f, 0), smallBallBody.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && smallBallBody.getLinearVelocity().x >= -4f) {
                smallBallBody.applyLinearImpulse(new Vector2(-0.25f, 0), smallBallBody.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) && smallBallBody.getLinearVelocity().y == 0 && !headHit) { //&& state == States.ONTHEGROUND) {
                stateY = States.JUMPING;
                smallBallBody.applyLinearImpulse(new Vector2(0, 5f), smallBallBody.getWorldCenter(), true);
                canBounce = true;
            }
        }
    }

    public void handleButtonInputs(float dt) {
        if(change) {
            if(isBig) {
                defineBigBall();
            } else {
                defineSmallBall();
            }
            change = false;
        }
        if(isBig) {
            if (right && bigBallBody.getLinearVelocity().x <= 4f) {
                bigBallBody.applyLinearImpulse(new Vector2(0.25f, 0), bigBallBody.getWorldCenter(), true);
            }
            if (left && bigBallBody.getLinearVelocity().x >= -4f) {
                bigBallBody.applyLinearImpulse(new Vector2(-0.25f, 0), bigBallBody.getWorldCenter(), true);
            }
            if (up && bigBallBody.getLinearVelocity().y == 0 && !headHit) { //&& state == States.ONTHEGROUND) {
                stateY = States.JUMPING;
                bigBallBody.applyLinearImpulse(new Vector2(0, 5f), bigBallBody.getWorldCenter(), true);
                canBounce = true;
            }
        } else {
            if (right && smallBallBody.getLinearVelocity().x <= 4f) {
                smallBallBody.applyLinearImpulse(new Vector2(0.25f, 0), smallBallBody.getWorldCenter(), true);
            }
            if (left && smallBallBody.getLinearVelocity().x >= -4f) {
                smallBallBody.applyLinearImpulse(new Vector2(-0.25f, 0), smallBallBody.getWorldCenter(), true);
            }
            if (up && smallBallBody.getLinearVelocity().y == 0 && !headHit) { //&& state == States.ONTHEGROUND) {
                stateY = States.JUMPING;
                smallBallBody.applyLinearImpulse(new Vector2(0, 5f), smallBallBody.getWorldCenter(), true);
                canBounce = true;
            }
        }
    }

    public void update(float dt) {
        if(!isBig) {
            setPosition(smallBallBody.getPosition().x - getWidth() / 2, smallBallBody.getPosition().y - getHeight() / 2);
            if (smallBallBody.getLinearVelocity().y < 0) {
                stateY = States.FALLING;
            }
            if (canBounce && stateY == States.FALLING && smallBallBody.getLinearVelocity().y == 0) {
                canBounce = false;
                smallBallBody.applyLinearImpulse(new Vector2(0, 2f), smallBallBody.getWorldCenter(), true);
            }
        } else {
            setPosition(bigBallBody.getPosition().x - getWidth() / 2, bigBallBody.getPosition().y - getHeight() / 2);
            if (bigBallBody.getLinearVelocity().y < 0) {
                stateY = States.FALLING;
            }
            if (canBounce && stateY == States.FALLING && bigBallBody.getLinearVelocity().y == 0) {
                canBounce = false;
                bigBallBody.applyLinearImpulse(new Vector2(0, 2f), bigBallBody.getWorldCenter(), true);
            }
        }
        if(isOut) {
            isOut = false;
            if(svdBallIsSmall) {
                defineSmallBall(savedPosition);
            } else {
                defineBigBall(savedPosition);
            }
        }
        if(goNextLvl) {
            gsm.loadNextPlayScreen(2);
            goNextLvl = false;
        }
    }

    // below codes should work in second release
    public void outItter(float dt) {
        if(time > maxTime) {
            time += dt;
            // set
        } else {
            // get saved position and restart
            // remove a life
            // if lives is over show game over
        }
    }

    public static KeyValue getFixturePair() {
        return fixturePair;
    }

    public static void setFixturePair(KeyValue fixturePair1) {
        fixturePair = fixturePair1;
    }

    public static void removeFixturePair() {
        fixturePair.fixtureA = null;
        fixturePair.fixtureA = null;
    }
}
