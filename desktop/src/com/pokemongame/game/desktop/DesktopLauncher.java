package com.pokemongame.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pokemongame.game.pokemongame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = pokemongame.HEIGHT;
		config.width = pokemongame.WIDTH;
		config.title = pokemongame.TITLE;
		new LwjglApplication(new pokemongame(), config);
	}
}
