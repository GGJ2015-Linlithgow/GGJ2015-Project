package com.ggj_linlithgow.gdx.core.screen;

import com.ggj_linlithgow.gdx.core.FontManager;
import com.ggj_linlithgow.gdx.core.DisplayOld;
import com.ggj_linlithgow.gdx.core.actor.TextActor;
import com.ggj_linlithgow.gdx.core.sound.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class LoadingScreen implements Screen {

	private final DisplayOld game;
	private int screenWidth;
	private int screenHeight;
	private Stage stage;
	private long rendercount = 0;
	
	public static final String LOADING_TEXT = "LOADING....";
	
	public LoadingScreen(final DisplayOld game) {
		this.game = game;
	}
	
	@Override
	public void dispose() {
		if (stage!=null) {
			stage.dispose();
		}
		
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void resize(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
		stage.getViewport().update(width, height, true);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		if (rendercount == 2) {
			game.setScreen(ScreenManager.getGameScreen());
		}
		rendercount++;
	}

	@Override
	public void show() {
		
		SoundManager.stopThemeTune();
		SoundManager.playLoadingTune();
		
		stage = new Stage();
		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		stage.setViewport(new ExtendViewport(screenWidth, screenHeight));
		TextActor loadingText = new TextActor(LOADING_TEXT, (screenHeight/2)+FontManager.getLargeLabel().font.getBounds(LOADING_TEXT).height, screenWidth, FontManager.getLargeLabel());
        stage.addActor(loadingText);
	}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void hide() { }
}