package team6.g13it18k.gameworld;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import team6.g13it18k.TweenAccessors.Value;
import team6.g13it18k.TweenAccessors.ValueAccessor;
import team6.g13it18k.asHelpers.AssetLoader;
import team6.g13it18k.asHelpers.InputHandler;
import team6.g13it18k.gameobjects.Bird;
import team6.g13it18k.gameobjects.Grass;
import team6.g13it18k.gameobjects.Pipe;
import team6.g13it18k.gameobjects.ScrollHandler;
import team6.g13it18k.ui.SimpleButton;

public class GameRenderer {

    private GameWorld myWorld;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch spriteBatch;

    private int midPointY;

    // Game Objects
    private Bird bird;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    //Game Assets
    private TextureRegion bg, grass, birdMid, skullUp, skullDown, bar;
    private Animation<TextureRegion> birdAnimation;

    private TweenManager manager;
    private Value alpha = new Value();

    private List<SimpleButton> menuButtons;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;

        this.midPointY = midPointY;
        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMenuButtons();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, gameHeight);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
        setupTween();
    }

    private void setupTween() {
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, .5f).target(0)
                .ease(TweenEquations.easeOutQuad)
                .start(manager);
    }

    private void initGameObjects() {
        bird = myWorld.getBird();
        ScrollHandler scroller = myWorld.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdMid = AssetLoader.bird;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }

    private void drawGrass() {
        spriteBatch.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        spriteBatch.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        spriteBatch.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        spriteBatch.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        spriteBatch.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        spriteBatch.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        spriteBatch.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        spriteBatch.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        // Временный код, извините за кашу :)
        // Мы это починим, как только закончим с Pipe классом.
        spriteBatch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        spriteBatch.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        spriteBatch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        spriteBatch.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        spriteBatch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        spriteBatch.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }

    private void drawBirdCentered(float runTime) {
        spriteBatch.draw(birdAnimation.getKeyFrame(runTime), 59, bird.getY() - 15,
                bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
    }

    private void drawBird(float runTime) {

        if (bird.shouldntFlap()) {
            spriteBatch.draw(birdMid, bird.getX(), bird.getY(),
                    bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());

        } else {
            spriteBatch.draw(birdAnimation.getKeyFrame(runTime), bird.getX(),
                    bird.getY(), bird.getWidth() / 2.0f,
                    bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(),
                    1, 1, bird.getRotation());
        }

    }

    private void drawMenuUI() {
        spriteBatch.draw(AssetLoader.asLogo, 136 / 2 - 56, midPointY - 50,
                AssetLoader.asLogo.getRegionWidth() / 1.2f,
                AssetLoader.asLogo.getRegionHeight() / 1.2f);

        for (SimpleButton button : menuButtons) {
            button.draw(spriteBatch);
        }

    }

    private void drawScore() {
        int length = ("" + myWorld.getScore()).length();
        AssetLoader.shadow.draw(spriteBatch, "" + myWorld.getScore(),
                68 - (3 * length), midPointY - 82);
        AssetLoader.font.draw(spriteBatch, "" + myWorld.getScore(),
                68 - (3 * length), midPointY - 83);
    }



    public void render(float delta, float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        spriteBatch.begin();
        spriteBatch.disableBlending();

        spriteBatch.draw(bg, 0, midPointY + 23, 136, 43);

        drawGrass();
        drawPipes();

        spriteBatch.enableBlending();
        drawSkulls();

        if (myWorld.isRunning()) {
            drawBird(runTime);
            drawScore();
        } else if (myWorld.isReady()) {
            drawBird(runTime);
            drawScore();
        } else if (myWorld.isMenu()) {
            drawBirdCentered(runTime);
            drawMenuUI();
        } else if (myWorld.isGameOver()) {
            drawBird(runTime);
            drawScore();
        } else if (myWorld.isHighScore()) {
            drawBird(runTime);
            drawScore();
        }

        spriteBatch.end();
        drawTransition(delta);
    }

    private void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(1, 1, 1, alpha.getValue());
            shapeRenderer.rect(0, 0, 136, 300);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

        }
    }
}
