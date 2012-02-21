package com.codegears.toursmthai;

import com.codegears.toursmthai.scene.WelcomeScene2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeSceneLoadData extends Activity implements LoadListener {
	
	private MyApp app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.welcomescene1 );
		
		app = (MyApp)this.getApplication();
		app.setLoadListener( this );
		app.load();
	}

	@Override
	public void onLoadComplete(Object obj) {
		Intent newIntent = new Intent( this, WelcomeScene2.class );
		startActivity( newIntent );
	}
}
