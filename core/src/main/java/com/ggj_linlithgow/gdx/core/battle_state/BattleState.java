package com.ggj_linlithgow.gdx.core.battle_state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ggj_linlithgow.gdx.core.Display;

public class BattleState extends Stage implements Screen{

	private final Display display;
	
	public BattleState(Display display) {
		this.display = display;
		
		setViewport( new StretchViewport(800, 600));
	}
	
	@Override
	public void render(float delta) {
		
		getCamera().update();
		
	    getBatch().setProjectionMatrix(getCamera().combined);
	    getRenderer().setProjectionMatrix(getCamera().combined);
		
		Gdx.gl.glEnable(GL30.GL_BLEND);
        getRenderer().begin(ShapeType.Filled);
        
        getRenderer().setColor(Color.WHITE);
        getRenderer().rect(25, 25, 50, 50);
        
        getRenderer().end();
        Gdx.gl.glDisable(GL30.GL_BLEND);
		
	}

	@Override
	public void resize(int width, int height) {
		getViewport().update(width, height, true);
		getCamera().position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
	
	private ShapeRenderer getRenderer() {
		return display.getRenderer();
	}
}
