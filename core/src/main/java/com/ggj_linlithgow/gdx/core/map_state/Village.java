package com.ggj_linlithgow.gdx.core.map_state;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ggj_linlithgow.gdx.core.FontManager;
import com.ggj_linlithgow.gdx.core.actor.TextActor;

public class Village extends Actor {
	private static Texture texture = new Texture(Gdx.files.internal("image/town2.png"));
	private static Texture shadow = new Texture(Gdx.files.internal("image/town1.png"));
	
	final String[] names = new String[] {
	 "Hasgaard",
	 "Moberg",
	 "Kjallaksstaoir",
	 "Krumsholar",
	 "Alfsoss",
	 "Gotaloekr",
	 "Orrastaoir",
	 "Heinabergsar",
	 "Blaskeggsa",
	 "Hvatastaoir",
	 "Salteyraross",
	 "Hlioum",
	 "Kaldaross",
	 "Oleifsborg",
	 "Holtastaoir",
	 "Alptanes",
	 "Lon",
	 "Bravollr",
	 "Hvatastaoir",
	 "Sandfell",
	 "Buoardalsa"
	};
	TextActor textName;
	
	final Sound selected = Gdx.audio.newSound(Gdx.files
			.internal("sound/188815__porphyr__battle-horn.ogg"));
	
	private static final List<Village> villages = new ArrayList<>();
	public static List<Village> getAllVillages() {
		return villages;
	}
	private List<Village> nextVillages = new ArrayList<>();
	final int x;
	final int y;
	
	public Village (final int x, final int y) {
		this.x = x;
		this.y = y;
		
		// set width so that it can actually be TOUCHED
		setWidth(texture.getWidth());
		setHeight(texture.getHeight());
		// AND set bounds because touch events are finiky that way - jeez
		setBounds(x, y, getWidth(), getHeight());
		setTouchable(Touchable.enabled);
		
		
		villages.add(this);
		// next line will produce java.lang.ArrayIndexOutOfBoundsException: 21 if you place too many villages
		textName = new TextActor(names[villages.indexOf(this)], x, y, FontManager.getNormalLabel());
		textName.setTouchable(Touchable.disabled);
		
		this.addListener(new InputListener() {
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			switch (event.getButton()) {
    		case 0: // LMB
    			System.out.println("LMB VILLAGE INFO/SCOUT REPORT: x,y: "+x+", "+y);
    			selected.play();
    			break;
    		case 1: // RMB
    			System.out.println("RMB VILLAGE INFO/SCOUT REPORT: x,y: "+x+", "+y);
    			selected.play();
    			break;
    	}
			
			return true;
		}});
	}
		
	TextActor getTextName() {
		return textName;
	}
	private void next() {
		
		int margin = 100;
		int wide = 10;
		int deep = 5;
		int perX = (Gdx.graphics.getWidth()-margin)/wide;
		int perY = (Gdx.graphics.getHeight()-margin)/deep;
		final Random random=new Random();
		//while (villages.s)
		for (int i=0; i < wide; i++) {
			//plonk((margin>>1)+(i*perX), (Gdx.graphics.getHeight()/2)+(random.nextInt(400)-(200)));
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		batch.draw(shadow, x-4, y+4);
		
		if (this == Village.getAllVillages().get(Village.getAllVillages().size()-1)) {
			 batch.setColor(Color.YELLOW);
		} else if (Village.getAllVillages().indexOf(this) < MapState.current) {
			 batch.setColor(Color.GREEN);
		} else {
			 batch.setColor(Color.RED);
		}
		 
          // batch.setColor(color.r, 1.0f, color.b, color.a * parentAlpha);
		batch.draw(texture, x, y);
	}
}
