package com.ggj_linlithgow.gdx.core.map_state;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ggj_linlithgow.gdx.core.Display;
import com.ggj_linlithgow.gdx.core.actor.Beastie;
import com.ggj_linlithgow.gdx.core.battle_state.LoadBattleActors;
import com.ggj_linlithgow.gdx.core.sound.SoundManager;
import com.ggj_linlithgow.gdx.core.terrain.Background;
import com.ggj_linlithgow.gdx.core.terrain.ImageActor;

public class MapState implements Screen{

	private final Display display;

	final Sound soundOutOfRange = Gdx.audio.newSound(Gdx.files
			.internal("sound/duff.ogg"));
	SpriteBatch batch;
	Stage backStage = new Stage();
    static int current = 2;
    
	//private static Texture texture = new Texture(Gdx.files.internal("image/arras___terraining_nearly_complete_by_aceanuu-d4ng1bd.jpg"));
	
	public MapState(final Display display) {
		batch = new SpriteBatch();
		SoundManager.playLoadingTune();
		this.display = display;

		// first background
		Actor background = new Background("image/arras___terraining_nearly_complete_by_aceanuu-d4ng1bd.jpg", display);
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		background.setSize(w, h);
		background.setOrigin(w / 2, h / 2);
		background.setPosition(0, 0);
		background.setTouchable(Touchable.enabled);
		backStage.addActor(background);
		
//        // then villages	        
		display.getStage().addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("STAGE EVENT: x,y: "+x+", "+y);
				switch (event.getButton()) {
        		case 0: // LMB

        			break;
        		case 1: // RMB
        			final Village v = new Village((int)x , (int)y);
        			display.getStage().addActor(v);
        			display.getStage().addActor(v.getTextName());
        			soundOutOfRange.play();
        			break;
        	}
				return false; // false = propagate (consume = false)
			}});
		
		Gdx.input.setInputProcessor(display.getStage());
		//Gdx.input.setInputProcessor(backStage);
	}

	
	@Override
	public void render(float delta) {
	       Gdx.gl.glClearColor(0.5f, 0.5f, 0, 1); // don't clear the colour bit if full screen overdraw of course :)
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	        backStage.act(delta);
	        backStage.draw();
	          
			Gdx.gl.glEnable(GL30.GL_BLEND);
			 Gdx.gl20.glLineWidth(5); // don't use this. it's bad.
	        
	        renderer().begin(ShapeType.Line);

	        // draw lines between villages
	        if (!Village.getAllVillages().isEmpty()) {
	        	Village root = Village.getAllVillages().get(0);
	        	for (final Village v : Village.getAllVillages()) {
	        		if (v == root) continue;
	        		if (v == Village.getAllVillages().get(Village.getAllVillages().size()-1)) {
	        			renderer().setColor(Color.YELLOW);
	        		} else if (Village.getAllVillages().indexOf(v) < current) {
	        			renderer().setColor(Color.GREEN);
	        		} else {
	        			renderer().setColor(Color.RED);
	        		}
	        		
	        		renderer().line(root.x, root.y, v.x, v.y);
	        		root = v;
	        	}
	        }
	        
	        renderer().end();
	        Gdx.gl.glDisable(GL30.GL_BLEND);
	        
	        display.getStage().act(delta);
			display.getStage().draw();

	}
	
	private ShapeRenderer renderer() {
		return display.getRenderer();
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
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
