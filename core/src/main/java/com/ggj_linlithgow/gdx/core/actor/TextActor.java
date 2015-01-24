package com.ggj_linlithgow.gdx.core.actor;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class TextActor extends Label {

	private BitmapFont font;
	private float x;
	private float y;

	public TextActor(String text, float x, float y, LabelStyle style) {
		super(text, style);

		font = style.font;
		this.x = x;
		this.y = y;
		resize();
	}

	@Override
	public void setText(CharSequence newText) {
		super.setText(newText);
		resize();
	}

	private void resize() {
		
		setSize(font.getBounds(getText()).width, font.getBounds(getText()).height);
		setOrigin(getWidth() / 2, getHeight() / 2);
		setPosition(x,y);
		setBounds(getX(), getY(), font.getBounds(getText()).width, font.getBounds(getText()).height);
	}

}
