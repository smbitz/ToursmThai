package com.codegears.toursmthai;

import com.codegears.toursmthai.data.CategoryManager;
import com.codegears.toursmthai.util.Config;

import android.app.Application;

public class MyApp extends Application implements LoadListener {
	
	private CategoryManager cManager;
	private Config config;
	private LoadListener listener;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		cManager = new CategoryManager( this );
		config = new Config( getApplicationContext() );
	}
	
	public Config getConfig(){
		return config;
	}
	
	public CategoryManager getCategoryManager(){
		return cManager;
	}
	
	public void setLoadListener(LoadListener listener){
		this.listener = listener;
	}
	
	public void load(){
		cManager.setLoadListener( this );
		cManager.load();
	}

	@Override
	public void onLoadComplete(Object obj) {
		listener.onLoadComplete( this );
	}
}
