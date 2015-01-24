package com.ggj_linlithgow.gdx.core.battle_state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ArcherCard extends Actor{
	
	private Texture ticket;
	private Vector3 unproject = new Vector3(0, 0, 0);
	
	public ArcherCard(float xPos, float yPos, float width, float height, Texture ticket) {
		setX(xPos);
		setY(yPos);
		setWidth(width);
		setHeight(height);
		this.ticket = ticket;
	}
	
	@Override
	public void act(float delta) {
		unproject.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		getStage().getCamera().unproject(unproject);
		
		if (unproject.x > getX() && unproject.x < getX()+getWidth() 
				&& unproject.y > getY() && unproject.y < getY()+getHeight() 
				&& Gdx.input.isTouched()) {
			
			setX(unproject.x-(getWidth()/2));
			setY(unproject.y-(getHeight()/2));
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(ticket, getX(), getY(), getWidth(), getHeight());
	}
}
