package com.codegears.toursmthai;

import com.codegears.toursmthai.data.CategoryManager;
import com.codegears.toursmthai.util.Config;

import android.app.Application;
import android.graphics.Typeface;

public class MyApp extends Application implements LoadListener {
	
	private static final String FONT_LIST_HEADER = "font/2006_iannnnnBKK_2.ttf";
	private static final String FONT_LIBERATIONSANS = "font/LiberationSans_Regular.ttf";
	
	private CategoryManager cManager;
	private Config config;
	private LoadListener listener;
	private Typeface textListHeaderFont;
	private Typeface textLiberationFont;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		cManager = new CategoryManager( this );
		config = new Config( getApplicationContext() );
		textListHeaderFont = Typeface.createFromAsset(getAssets(), FONT_LIST_HEADER);
		textLiberationFont = Typeface.createFromAsset(getAssets(), FONT_LIBERATIONSANS);
	}
	
	public Config getConfig(){
		return config;
	}
	
	public CategoryManager getCategoryManager(){
		return cManager;
	}
	
	public Typeface getTextListHeaderFont(){
		return textListHeaderFont;
	}
	
	public Typeface getTextLiberationFont(){
		return textLiberationFont;
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
