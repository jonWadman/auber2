package com.threecubed.auber.entities;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.threecubed.auber.AuberGame;
import com.threecubed.auber.World;
import com.threecubed.auber.entities.Player;
import com.threecubed.auber.screens.GameScreen;
import com.threecubed.auber.screens.MenuScreen;

import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;

@RunWith(GdxTestRunner.class)
public class GameEntityTest {


    @Test
    public void getCenterX() {
        ShapeRenderer renderer = Mockito.mock(ShapeRenderer.class);
        TextureAtlas atlas = new TextureAtlas("src/de/tomgrill/gdxtesting/assets/auber.atlas");
        Sprite sprite = atlas.createSprite("player");
        Player player = new Player(0, 0, sprite, renderer);
        Assert.assertEquals(player.getCenterX(), player.sprite.getWidth() / 2,0.001);
        atlas.dispose();

    }

    @Test
    public void getCenterY() {
        ShapeRenderer renderer = Mockito.mock(ShapeRenderer.class);
        TextureAtlas atlas = new TextureAtlas("src/de/tomgrill/gdxtesting/assets/auber.atlas");
        Sprite sprite = atlas.createSprite("player");
        Player player = new Player(0, 0, sprite, renderer);
        Assert.assertEquals(player.getCenterY(), player.sprite.getHeight() / 2,0.001);
        atlas.dispose();

    }

    @Test
    public void getCenter() {
        ShapeRenderer renderer = Mockito.mock(ShapeRenderer.class);
        TextureAtlas atlas = new TextureAtlas("src/de/tomgrill/gdxtesting/assets/auber.atlas");
        Sprite sprite = atlas.createSprite("player");
        Player player = new Player(0, 0, sprite, renderer);
        Assert.assertEquals(player.getCenter().x,player.sprite.getWidth()/2,001);
        Assert.assertEquals(player.getCenter().y,player.sprite.getHeight()/2,001);
        atlas.dispose();
    }
}


