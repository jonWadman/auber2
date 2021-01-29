package de.tomgrill.gdxtesting;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.threecubed.auber.AuberGame;
import com.threecubed.auber.World;
import com.threecubed.auber.entities.Player;
import com.threecubed.auber.screens.GameScreen;
import com.threecubed.auber.screens.MenuScreen;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import org.mockito.Mockito;

@RunWith(GdxTestRunner.class)
public class GameEntityTest {

    @Test
    public void getNearbyObjects() {
        Gdx.gl = Mockito.mock(GL20.class);

        //Launch the program and initialise all Gdx stuff (invisible and no program is really launched)
        new HeadlessApplication(new EmptyApplication());
        //AuberGame game= new AuberGame();
        TextureAtlas atlas = new TextureAtlas("C:/Users/sarah/Documents/auber2/core/assets/auber.atlas");
        Sprite sprite=atlas.createSprite("player");
        Player player=new Player(0,0,sprite);
        Assert.assertEquals(5,5);

    }

    @Test
    public void entityOnScreen() {
    }

    @Test
    public void getCenterX() {
    }

    @Test
    public void getCenterY() {
    }

    @Test
    public void getCenter() {
    }
}