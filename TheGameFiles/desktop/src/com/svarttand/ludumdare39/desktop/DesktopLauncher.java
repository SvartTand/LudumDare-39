package com.svarttand.ludumdare39.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.svarttand.ludumdare39.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 600;
		config.height = 400;
		config.title = "DomeRun";
		config.addIcon("Icon.png", FileType.Internal);
		new LwjglApplication(new Application(), config);
		
	}
}
