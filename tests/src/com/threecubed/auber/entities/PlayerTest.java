package com.threecubed.auber.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class PlayerTest {

    @Test
    public void powerOn() {
        //Create Test Player
        ShapeRenderer renderer = Mockito.mock(ShapeRenderer.class);
        TextureAtlas atlas = new TextureAtlas("src/de/tomgrill/gdxtesting/assets/auber.atlas");
        Sprite sprite = atlas.createSprite("player");
        Player player = new Player(0, 0, sprite, renderer);

       //Test power usage
        Assert.assertEquals(false,player.powerBeamUsed);
        player.powerOn(1);
        Assert.assertEquals(true,player.powerBeamUsed);

        Assert.assertEquals(false,player.powerMaxHealth);
        player.powerOn(2);
        Assert.assertEquals(true,player.powerMaxHealth);

        Assert.assertEquals(false,player.powerRevealUsed);
        player.powerOn(4);
        Assert.assertEquals(true,player.powerRevealUsed);

        Assert.assertEquals(false,player.powerSlowUsed);
        player.powerOn(5);
        Assert.assertEquals(true,player.powerSlowUsed);

        //Test powerStopInfiltrator
        Assert.assertEquals(false,player.powerStopInfiltratorPowerUsed);

        player.confused=true;
        player.blinded=true;
        player.slowed=true;

        player.powerOn(3);
        Assert.assertEquals(true,player.powerStopInfiltratorPowerUsed);
        Assert.assertEquals(false,player.confused);
        Assert.assertEquals(false,player.blinded);
        Assert.assertEquals(false,player.slowed);

        //Test that power can't be used again
        player.confused=true;
        player.blinded=true;
        player.slowed=true;
        player.powerOn(3);
        Assert.assertEquals(true,player.confused);
        Assert.assertEquals(true,player.blinded);
        Assert.assertEquals(true,player.slowed);

        atlas.dispose();


    }


    @Test
    public void powerStopInfiltratorPower() {
        //Create Test Player
        ShapeRenderer renderer = Mockito.mock(ShapeRenderer.class);
        TextureAtlas atlas = new TextureAtlas("src/de/tomgrill/gdxtesting/assets/auber.atlas");
        Sprite sprite = atlas.createSprite("player");
        Player player = new Player(0, 0, sprite, renderer);


        player.confused=true;
        player.blinded=true;
        player.slowed=true;

        player.powerStopInfiltratorPower();
        Assert.assertEquals(false,player.confused);
        Assert.assertEquals(false,player.blinded);
        Assert.assertEquals(false,player.slowed);

        atlas.dispose();
    }

    @Test
    public void respawn(){
        //Create Test Player
        ShapeRenderer renderer = Mockito.mock(ShapeRenderer.class);
        TextureAtlas atlas = new TextureAtlas("src/de/tomgrill/gdxtesting/assets/auber.atlas");
        Sprite sprite = atlas.createSprite("player");
        Player player = new Player(0, 0, sprite, renderer);

        player.confused=true;
        player.slowed=true;

        float[] MEDBAY_COORDINATES = {96f, 640f};
        player.respawn(MEDBAY_COORDINATES[0],MEDBAY_COORDINATES[1]);

        //check player is in medbay
        Assert.assertEquals(96f,player.position.x,0.01);
        Assert.assertEquals(640f,player.position.y,0.01);
        Assert.assertEquals(false,player.confused);
        Assert.assertEquals(false,player.slowed);
    }

    @Test
    public void heal(){
        //Create Test Player
        ShapeRenderer renderer = Mockito.mock(ShapeRenderer.class);
        TextureAtlas atlas = new TextureAtlas("src/de/tomgrill/gdxtesting/assets/auber.atlas");
        Sprite sprite = atlas.createSprite("player");
        Player player = new Player(0, 0, sprite, renderer);

        //test player healing by rate
        float rate=0.1f;
        player.health=0.5f;
        player.heal(rate);
        Assert.assertEquals(0.6f,player.health,0.01);

        //test health can not go over max
        player.health=player.maxHealth;
        player.heal(rate);
        Assert.assertEquals(player.maxHealth,player.health,0.01);

    }




}