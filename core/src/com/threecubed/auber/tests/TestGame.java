package com.threecubed.auber.tests;

import com.threecubed.auber.AuberGame;
import com.threecubed.auber.World;

public class TestGame {
    public World world;
    public AuberGame game;

public TestGame(){
    game= new AuberGame();
    world = new World(game, false);
}
}
