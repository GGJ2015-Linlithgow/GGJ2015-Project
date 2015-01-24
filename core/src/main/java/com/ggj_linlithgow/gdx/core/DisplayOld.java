package com.ggj_linlithgow.gdx.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ggj_linlithgow.gdx.core.screen.ScreenManager;
import com.ggj_linlithgow.gdx.core.sound.SoundManager;

public class DisplayOld extends Game {
	
	public static SpriteBatch batch;
	
	@Override
	public void create() {
		
		batch = new SpriteBatch();
		
		ScreenManager.initialiseGameScreens(this);
		
		setScreen(ScreenManager.getTitleScreen()); // entry point
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		SoundManager.dispose();
		ScreenManager.dispose();
		FontManager.dispose();
	}
}

