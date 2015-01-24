package com.ggj_linlithgow.gdx.core.battle_state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.ggj_linlithgow.gdx.core.Display;

public class LoadBattleActors {

	private ArcherCard archer;
	
	public LoadBattleActors(Display display) {
		archer = new ArcherCard(150, 150, 50, 100, new Texture(Gdx.files.internal("ATicket.png")));
		display.getStage().addActor(archer);
	}
}
