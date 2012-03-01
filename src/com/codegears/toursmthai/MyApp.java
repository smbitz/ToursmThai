package com.codegears.toursmthai;

import com.codegears.toursmthai.data.CategoryManager;
import com.codegears.toursmthai.util.Config;

import android.app.Application;
import android.graphics.Typeface;

public class MyApp extends Application implements LoadListener {
	
	private static final String FONT_POSITION = "font/2006_iannnnnBKK_2.ttf";
	
	private CategoryManager cManager;
	private Config config;
	private LoadListener listener;
	private Typeface textFont;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		cManager = new CategoryManager( this );
		config = new Config( getApplicationContext() );
		textFont = Typeface.createFromAsset(getAssets(), FONT_POSITION);
	}
	
	public Config getConfig(){
		return config;
	}
	
	public CategoryManager getCategoryManager(){
		return cManager;
	}
	
	public Typeface getTextFont(){
		return textFont;
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
