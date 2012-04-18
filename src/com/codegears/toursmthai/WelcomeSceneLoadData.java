package com.codegears.toursmthai;

import com.codegears.toursmthai.scene.WelcomeScene2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

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
		
		if( !(this.isOnline()) ){
			Dialog newDialog = new AlertDialog.Builder(this)
	        .setTitle("Please connect internet.")
	        .setNeutralButton("ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	WelcomeSceneLoadData.this.finish();
	            }
	        })
	        .create();
			
			newDialog.show();
		}
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
    
    public boolean isOnline() {
        ConnectivityManager cm =
            (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
