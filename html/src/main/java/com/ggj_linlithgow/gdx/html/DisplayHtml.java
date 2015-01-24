package com.ggj_linlithgow.gdx.html;

import com.ggj_linlithgow.gdx.core.Display;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class DisplayHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Display();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
