package com.ggj_linlithgow.gdx.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ggj_linlithgow.gdx.core.battle_state.BattleState;

public class Display extends Game {

	private ShapeRenderer renderer;
	private Stage stage;
	
	@Override
	public void create () {
		
		renderer = new ShapeRenderer();
		stage = new Stage(new StretchViewport(800, 600));
		
		BattleState battleState = new BattleState(this);
		setScreen(battleState);
	}
	
	@Override
	public void resize (int width, int height) {
		getStage().getViewport().update(width, height, true);
		getCamera().position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(.0f, .0f, .0f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		getStage().getCamera().update();
		
		getStage().getBatch().setProjectionMatrix(stage.getCamera().combined);
	    getRenderer().setProjectionMatrix(stage.getCamera().combined);
		
	    getStage().act(Gdx.graphics.getDeltaTime());
	    getStage().draw();
		
        super.render();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
		stage.dispose();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Camera getCamera() {
		return stage.getCamera();
	}
	
	public SpriteBatch getSpriteBatch() {
		return (SpriteBatch) stage.getBatch();
	}
	
	public ShapeRenderer getRenderer() {
		return renderer;
	}
}
