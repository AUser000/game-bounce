package com.dhanushka.bounce.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dhanushka.bounce.Bounce;
import com.dhanushka.bounce.hud.Hud;
import com.dhanushka.bounce.sprites.SmallBall;
import com.dhanushka.bounce.tools.Constants;
import com.dhanushka.bounce.tools.ScreenState;
import com.dhanushka.bounce.tools.WorldContactListener;
import com.dhanushka.bounce.tools.WorldCreator;

public class PlayScreen implements ScreenState {

    Logger log = new Logger(PlayScreen.class.getName());
    Bounce game;
    Texture texture;
    Viewport viewport;
    OrthographicCamera cam;
    Hud hud;
    private TextureAtlas atlas;

    TmxMapLoader mapLoader;
    TiledMap map;
    OrthogonalTiledMapRenderer mapRenderer;
    SmallBall ball;

    World world;
    Box2DDebugRenderer box2DDebugRenderer;
    boolean first;
    int level;


    public PlayScreen(Bounce game, int level) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(Bounce.V_WIDTH/Bounce.PPM, Bounce.V_HEIGHT/Bounce.PPM, cam);
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);

        hud = new Hud(game.batch);

        atlas = new TextureAtlas(Constants.BALLS_PACK);

        Label upLabel = new Label("Hello", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        upLabel.setSize(Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/8);
        upLabel.setPosition(0, Gdx.graphics.getHeight()/8);

        mapLoader = new TmxMapLoader();

        map = mapLoader.load(Constants.MAP_LOCATION + level + Constants.MAP_REST_TMX); //android/assets/
        System.out.println("map loading fine");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/Bounce.PPM);

        world = new World(new Vector2(0, -10), true);

        box2DDebugRenderer = new Box2DDebugRenderer();
        new WorldCreator(world, map);

        ball = new SmallBall(world, this);
        world.setContactListener(new WorldContactListener());
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInputs(float dt) {
        //ball.handleInputs(dt);
        ball.handleButtonInputs(dt);
    }

    @Override
    public void update(float dt) {
        world.step(1/60f, 6, 2);
        handleInputs(dt);
        ball.update(dt);
        cam.position.x = ball.getBody().getPosition().x;
        cam.update();
        mapRenderer.setView(cam);

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.render();


        //ball.ball.getPosition().x += 1;
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        ball.draw(game.batch);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        //box2DDebugRenderer.render(world, cam.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        hud.despose();
        world.dispose();
        map.dispose();
        mapRenderer.dispose();
    }

    public void activeInputProcessor() {
        Gdx.input.setInputProcessor(hud.stage);
    }
}
