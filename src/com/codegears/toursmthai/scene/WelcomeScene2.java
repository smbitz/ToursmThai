package com.codegears.toursmthai.scene;

import com.codegears.toursmthai.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeScene2 extends Activity implements OnClickListener {
	
	private Button fullSceneButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.welcomescene2 );
		
		fullSceneButton = (Button) findViewById( R.id.welcomeScene2FullSceneButton );
		fullSceneButton.setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		if( v.equals( fullSceneButton ) ){
			Intent newIntent = new Intent( this, CategoryScene.class );
			startActivity( newIntent );
		}
	}
}
