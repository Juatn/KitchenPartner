package com.mygdx.grisacius;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.grisacius.bdd.GrisaciusDataBase;
import com.mygdx.grisacius.bdd.GrisaciusDataBaseAndroid;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MainGame(new GrisaciusDataBaseAndroid(this) {
		}), config);
	}
}
