package com.codegears.toursmthai.scene;

import java.util.ArrayList;

import com.codegears.toursmthai.MyApp;
import com.codegears.toursmthai.R;
import com.codegears.toursmthai.data.CategoryData;
import com.codegears.toursmthai.data.CategoryManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;

public class CategoryScene extends Activity implements OnClickListener {
	
	private MyApp app;
	private CategoryManager cManager;
	private ArrayList<CategoryData> category;
	private ImageButton categoryButton1;
	private ImageButton categoryButton2;
	private ImageButton categoryButton3;
	private ImageButton categoryButton4;
	private ImageButton categoryButton5;
	private ImageButton categoryButton6;
	private ImageButton categoryButton7;
	private ImageButton categoryButton8;
	private ImageButton categoryButton9;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.categoryscene );
		
		app = (MyApp)getApplication();
		cManager = app.getCategoryManager();
		category = cManager.getCategory();
		
		categoryButton1 = (ImageButton) findViewById( R.id.categoryButton1 );
		categoryButton2 = (ImageButton) findViewById( R.id.categoryButton2 );
		categoryButton3 = (ImageButton) findViewById( R.id.categoryButton3 );
		categoryButton4 = (ImageButton) findViewById( R.id.categoryButton4 );
		categoryButton5 = (ImageButton) findViewById( R.id.categoryButton5 );
		categoryButton6 = (ImageButton) findViewById( R.id.categoryButton6 );
		categoryButton7 = (ImageButton) findViewById( R.id.categoryButton7 );
		categoryButton8 = (ImageButton) findViewById( R.id.categoryButton8 );
		categoryButton9 = (ImageButton) findViewById( R.id.categoryButton9 );
		
		categoryButton1.setOnClickListener( this );
		categoryButton2.setOnClickListener( this );
		categoryButton3.setOnClickListener( this );
		categoryButton4.setOnClickListener( this );
		categoryButton5.setOnClickListener( this );
		categoryButton6.setOnClickListener( this );
		categoryButton7.setOnClickListener( this );
		categoryButton8.setOnClickListener( this );
		categoryButton9.setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		Animation buttonScale = AnimationUtils.loadAnimation( this.getApplicationContext(), R.anim.scale );
		v.startAnimation( buttonScale );
		if( v.equals( categoryButton1 ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_1 );
			startActivity( newIntent );
		}else if( v.equals( categoryButton2 ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_2 );
			startActivity( newIntent );
		}else if( v.equals( categoryButton3 ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_3 );
			startActivity( newIntent );
		}else if( v.equals( categoryButton4 ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_4 );
			startActivity( newIntent );
		}else if( v.equals( categoryButton5 ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_5 );
			startActivity( newIntent );
		}else if( v.equals( categoryButton6 ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_6 );
			startActivity( newIntent );
		}else if( v.equals( categoryButton7 ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_7 );
			startActivity( newIntent );
		}else if( v.equals( categoryButton8 ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_8 );
			startActivity( newIntent );
		}else if( v.equals( categoryButton9 ) ){
			Intent newIntent = new Intent( this, ListScene.class );
			newIntent.putExtra( ListScene.PUT_EXTRA_FAVOURITE_SCENE, ListScene.FAVOURITE_SCENE );
			startActivity( newIntent );
		}
	}
}
