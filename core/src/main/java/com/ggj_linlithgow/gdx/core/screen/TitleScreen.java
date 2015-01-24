package com.ggj_linlithgow.gdx.core.screen;

import static com.ggj_linlithgow.gdx.core.screen.ScreenManager.getLoadingScreen;
import static com.ggj_linlithgow.gdx.core.sound.SoundManager.playThemeTune;

import com.ggj_linlithgow.gdx.core.FontManager;
import com.ggj_linlithgow.gdx.core.actor.TextActor;
import com.ggj_linlithgow.gdx.core.terrain.ImageActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ggj_linlithgow.gdx.core.DisplayOld;

public class TitleScreen extends Stage implements Screen, InputProcessor {

	private final DisplayOld game;
	private int screenWidth;
	private int screenHeight;
	
	public static final String GAME_TITLE = "Jail Break!";
	public static final String GAME_BLURB = "It's 2050 and the prisoners of the largest jail on the planet (population 20,000) have escaped. You are the last guard alive! The safety of the country is in your hands - eliminate all the escaped convicts before they make it out the gate! Watch for civilians mixed up in the stampede - rely on the searchlight to show convincts in orange jumpsuits!";
	
	public static final String BUTTON_TEXT_PLAY = "PLAY";
	public static final String BUTTON_TEXT_CREDITS = "CREDITS"; 
	
	public TitleScreen(final DisplayOld game) {
		this.game = game;
		
		setViewport(new StretchViewport(800, 600));
	}
	
	@Override
	public void dispose() {
		
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void resize(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
		
		getViewport().update(width, height, true);
		getCamera().position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		getCamera().update();
		
		getBatch().setProjectionMatrix(getCamera().combined);
		
        act(Gdx.graphics.getDeltaTime());
		draw();
	}

	@Override
	public void show() {
		
		playThemeTune();
		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		setViewport(new StretchViewport(screenWidth, screenHeight));
		
        final ImageActor bannerImage = new ImageActor("image/prision.png", 800, 424);
        
        float actualBannerImageWidth = screenWidth*bannerImage.getAspect();
        bannerImage.setSize(screenWidth, actualBannerImageWidth);
        System.out.println("w: "+screenWidth+" h: "+actualBannerImageWidth);
        bannerImage.setOrigin(bannerImage.getWidth()/2, bannerImage.getHeight()/2);
        bannerImage.setPosition(0, screenHeight-bannerImage.getHeight());
        addActor(bannerImage);
        
        TextActor titleText = new TextActor(GAME_TITLE, bannerImage.getY(), screenWidth, FontManager.getLargeLabel());
        addActor(titleText);
		
        String[] blurbWords = GAME_BLURB.split(" ");
        
        StringBuilder blurbLine = new StringBuilder();
        float blurbTextYPosStart = titleText.getY();
        
		for (int i=0; i<blurbWords.length; i++) {
        	
			String blurbString = blurbLine.toString() + " " + blurbWords[i];
			
			if (FontManager.getNormalLabel().font.getBounds(blurbString).width < screenWidth && !isLastItem(i, blurbWords.length)) {
        		blurbLine.append(" " + blurbWords[i]); 
        	} else {
        		
        		boolean additionalLineRequired = false;
        		
        		if (isLastItem(i, blurbWords.length)) {
        			if (FontManager.getNormalLabel().font.getBounds(blurbString).width < screenWidth) {
        				blurbLine.append(" " + blurbWords[i]);
        			} else {
        				additionalLineRequired= true;
        			}
        		} 
        		
        		Actor blurbText = buildTextField(blurbLine.toString(), FontManager.getNormalLabel(), FontManager.getNormalLabel().font, blurbTextYPosStart);
        		blurbTextYPosStart = blurbText.getY();
        		blurbLine.setLength(0);
        		blurbLine.append(" " + blurbWords[i]); 
        		blurbString = " " + blurbWords[i];
        		
        		addActor(blurbText);
        		
        		if (additionalLineRequired) {
        			blurbText = buildTextField(blurbWords[i], FontManager.getNormalLabel(), FontManager.getNormalLabel().font, blurbTextYPosStart);
        			addActor(blurbText);
        		}
        	}
        }
        
		TextActor playText = new TextActor(BUTTON_TEXT_PLAY, 128, screenWidth, FontManager.getLargeLabel());
		playText.addListener(new InputListener() {
		
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			
				game.setScreen(getLoadingScreen());
				return false;
			}
		});
		playText.setTouchable(Touchable.enabled);
        addActor(playText);
        
		Gdx.input.setInputProcessor(this);
	}

	private Actor buildTextField(String theBlurb, LabelStyle labelStyle, BitmapFont font, float blurbTextYPosStart) {
		
		Label blurbText = new Label(theBlurb, labelStyle);
		blurbText.setSize(font.getBounds(theBlurb).width, font.getBounds(theBlurb).height);
		blurbText.setOrigin(blurbText.getWidth()/2, blurbText.getHeight()/2);
		blurbText.setPosition((screenWidth/2) - (font.getBounds(theBlurb).width/2), blurbTextYPosStart-font.getBounds(theBlurb).height);
		
		return blurbText;
	}
	
	private boolean isLastItem(int i, int countOfItems) {
		return i == (countOfItems-1);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}
	
	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void hide() { }
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}

