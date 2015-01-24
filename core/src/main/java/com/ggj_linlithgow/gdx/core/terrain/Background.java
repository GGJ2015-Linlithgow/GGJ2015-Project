package com.ggj_linlithgow.gdx.core.terrain;

import com.ggj_linlithgow.gdx.core.Display;
import com.ggj_linlithgow.gdx.core.Maths;
import com.ggj_linlithgow.gdx.core.map_state.Village;
import com.ggj_linlithgow.gdx.core.screen.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background extends Actor {
	final TextureRegion region;

//	final Sound soundOutOfRange = Gdx.audio.newSound(Gdx.files
//			.internal("sound/duff.ogg"));
	
	final Sound lightOn = Gdx.audio.newSound(Gdx.files
			.internal("sound/104960__glaneur-de-sons__neon-light-02.ogg"));
	
    public Background (String asset, final Display display) {
    	Texture texture = new Texture(Gdx.files.internal(asset));
    	texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	setBounds(getX(), getY(),texture.getWidth(),texture.getHeight());
		region = new TextureRegion(texture, 0, 0, 1600, 1080);
		setBounds(getX(), getY(),texture.getWidth(),texture.getHeight());

    }
	
    @Override
    public void act(float delta) {
    	super.act(delta);
    }
    
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
    	// TODO Auto-generated method stub
    	super.draw(batch, parentAlpha);
    	   Color color = getColor();
           batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
           batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
               getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }


}
