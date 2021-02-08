package com.threecubed.auber.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

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



    }


    @Test
    public void powerStopInfiltratorPower() {
    }
}