package com.ggj_linlithgow.gdx.core.battle_state;

import com.ggj_linlithgow.gdx.core.Display;

public class LoadBattleActors {

	private Archer archer;
	
	public LoadBattleActors(Display display) {
		archer = new Archer();
		display.getStage().addActor(archer);
	}
}
