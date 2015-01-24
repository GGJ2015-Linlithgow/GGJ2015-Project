package com.ggj_linlithgow.gdx.core.battle_state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ggj_linlithgow.gdx.core.Display;

public class BattleState implements Screen{

	private final Display display;
	private LoadBattleActors loadBattleActors;
	
	public BattleState(Display display) {
		this.display = display;
		loadBattleActors = new LoadBattleActors(display);
	}
	
	@Override
	public void render(float delta) {
	
		Gdx.gl.glEnable(GL30.GL_BLEND);
        renderer().begin(ShapeType.Filled);
        
        renderer().setColor(Color.WHITE);
        renderer().rect(0, display.getStage().getViewport().getWorldHeight()/2, display.getStage().getViewport().getWorldWidth(), 1);
        
        renderer().end();
        Gdx.gl.glDisable(GL30.GL_BLEND);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		pause();
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
	
	protected Display getDisplay() {
		return display;
	}
	
	protected LoadBattleActors getLoadBattleActors() {
		return loadBattleActors;
	}
	
	private ShapeRenderer renderer() {
		return display.getRenderer();
	}
}
