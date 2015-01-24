package com.ggj_linlithgow.gdx.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.ggj_linlithgow.gdx.core.Display;

public class DisplayDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1600;
		config.height = 900;
		config.fullscreen = true;
//		config.width = 1440;
//		config.height = 800;
//		config.fullscreen = false;
		new LwjglApplication(new Display(), config);
	}
}
