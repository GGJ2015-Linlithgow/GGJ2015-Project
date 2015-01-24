package com.ggj_linlithgow.gdx.core.screen;

import com.ggj_linlithgow.gdx.core.DisplayOld;

public class ScreenManager {

	private static TitleScreen titleScreen;
	private static GameScreen gameScreen;
	private static LoadingScreen loadingScreen;
	
	public static void initialiseGameScreens(final DisplayOld game) {
		titleScreen = new TitleScreen(game);
		gameScreen = new GameScreen(game);
		loadingScreen = new LoadingScreen(game);
	}
	
	public static TitleScreen getTitleScreen() {
		return titleScreen;
	}
	
	public static GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public static LoadingScreen getLoadingScreen() {
		return loadingScreen;
	}
	
	public static void dispose() {
		if (titleScreen != null) {
			titleScreen.dispose();
		}
		
		if (gameScreen != null) {
			gameScreen.dispose();
		}
		
		if (loadingScreen != null) {
			loadingScreen.dispose();
		}
	}
}
