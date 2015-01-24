package com.ggj_linlithgow.gdx.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ggj_linlithgow.gdx.core.battle_state.BattleState;

public class Display extends Game {

	private ShapeRenderer renderer;
	
	@Override
	public void create () {
		
		renderer = new ShapeRenderer();
		
		BattleState battleState = new BattleState(this);
		setScreen(battleState);
	}
	
	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.0f, .0f, .0f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		
        
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
	}
	
	public ShapeRenderer getRenderer() {
		return renderer;
	}
}
