package com.codegears.toursmthai;

import com.codegears.toursmthai.scene.WelcomeScene2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeSceneLoadData extends Activity implements LoadListener, Runnable {
	
	private Thread nextPage;
	private MyApp app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.welcomescene1 );
		
		nextPage = new Thread(this);
		nextPage.start();
		
		app = (MyApp) this.getApplication();
		app.setLoadListener( this );
	}

	@Override
	public void onLoadComplete(Object obj) {
		Intent newIntent = new Intent( this, WelcomeScene2.class );
		startActivity( newIntent );
	}

	@Override
	public void run() {
		try {
			nextPage.sleep(3000);
		} catch (InterruptedException e) {
		
		}
		
		app.load();
	}
}
