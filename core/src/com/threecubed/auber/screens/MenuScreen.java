package com.threecubed.auber.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.threecubed.auber.AuberGame;
import com.threecubed.auber.World;
import com.threecubed.auber.ui.Button;


/**
 * The menu screen is the first screen that shows in the game and is responsible for controlling
 * when the game begins.
 *
 * @author Joseph Krystek-Walton
 * @version 1.0
 * @since 1.0
 * */
public class MenuScreen extends ScreenAdapter {
  World world;
  AuberGame game;

  Button playButton;
  Button easyButton;
  Button mediumButton;
  Button hardButton;
  Button demoButton;
  OrthogonalTiledMapRenderer renderer;
  Sprite background;
  Sprite instructions;
  Sprite title;
  SpriteBatch spriteBatch;

//EDITED CODE- difficulty levels added
  /**
   * Instantiate the screen with the {@link AuberGame} object. Set the title and button up to be
   * rendered.
   *
   * @param game The game object
   * */
  public MenuScreen(final AuberGame game) {
    this.game = game;

    spriteBatch = new SpriteBatch();

    background = game.atlas.createSprite("stars");
    instructions = game.atlas.createSprite("instructions");
    title = game.atlas.createSprite("auber_logo");

    Runnable onPlayClick = new Runnable() {
      @Override
      public void run() {
        game.setScreen(new GameScreen(game, 0,false));
      }
    };

    Runnable onEasyClick = new Runnable() {
      @Override
      public void run() {
        game.setScreen(new GameScreen(game, 0,false));
      }
    };

    Runnable onMediumClick = new Runnable() {
      @Override
      public void run() {
        game.setScreen(new GameScreen(game, 1,false));
      }
    };

    Runnable onHardClick = new Runnable() {
      @Override
      public void run() {
        game.setScreen(new GameScreen(game, 2,false));
      }
    };



    Sprite easySprite=new Sprite(new Texture(Gdx.files.internal("easy.png")));
    Sprite mediumSprite=new Sprite(new Texture(Gdx.files.internal("medium.png")));
    Sprite hardSprite=new Sprite(new Texture(Gdx.files.internal("hard.png")));
    Sprite demoSprite=new Sprite(new Texture(Gdx.files.internal("demo.png")));

    easyButton = new Button(
            new Vector2(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2+100),
            1f, easySprite, game, onEasyClick);

    mediumButton = new Button(
            new Vector2(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2-50f),
            1f, mediumSprite, game, onMediumClick);

    hardButton = new Button(
            new Vector2(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2-200f),
            1f, hardSprite, game, onHardClick);


    Runnable onDemoClick = new Runnable() {
      @Override
      public void run() {
        game.setScreen(new GameScreen(game,0, true));
      }
    };

    demoButton = new Button(
        new Vector2(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2 - 350f),
        1f,demoSprite, game, onDemoClick);
  }

  @Override
  public void render(float deltaTime) {
    if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
      DisplayMode currentDisplayMode = Gdx.graphics.getDisplayMode();
      Gdx.graphics.setFullscreenMode(currentDisplayMode);
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
      game.setScreen(new GameScreen(game, 0,true));
    }

    // Set the background color
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    spriteBatch.begin();

    background.setPosition(0f, 0f);
    background.draw(spriteBatch);

    instructions.setPosition(900f, 125f);
    instructions.draw(spriteBatch);

    title.setScale(0.5f);
    title.setPosition(0f, 550f);
    title.draw(spriteBatch);

    //playButton.render(spriteBatch);
    easyButton.render(spriteBatch);
    mediumButton.render(spriteBatch);
    hardButton.render(spriteBatch);
    demoButton.render(spriteBatch);

    spriteBatch.end();
  }
}
