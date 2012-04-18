package com.codegears.toursmthai.ui;

import com.codegears.toursmthai.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HotdealItem extends LinearLayout {
	
	private TextView name;
	private TextView detail;
	private ImageView image;
	private ImageView nextButton;
	private ImageButton facebookButton;
	private ImageButton mapButton;
	private ImageButton twitterButton;
	private String imageURL;

	public HotdealItem(Context context) {
		super(context);
		View.inflate( context, R.layout.hotdealitem, this );
		
		name = (TextView) findViewById( R.id.hotdealItemName );
		detail = (TextView) findViewById( R.id.hotdealItemText );
		image = (ImageView) findViewById( R.id.hotdealItemImage );
		nextButton = (ImageView) findViewById( R.id.hotdealItemNextButton );
		facebookButton =(ImageButton) findViewById( R.id.hotdealItemFacebookButton );
		mapButton = (ImageButton) findViewById( R.id.hotdealItemMapButton );
		twitterButton = (ImageButton) findViewById( R.id.hotdealItemTwitterButton );
	}
	
	public void setName( String setNameValue ){
		name.setText( setNameValue );
	}
	
	public void setDetail( String setDetailValue ){
		detail.setText( setDetailValue );
	}
	
	public void setImage( Bitmap setImageValue ){
		image.setImageBitmap( setImageValue );
	}
	
	public void setNextButtonVisibility( int setVisibilityValue ){
		nextButton.setVisibility( setVisibilityValue );
	}
	
	public void setFontName( Typeface setFontValue ){
		name.setTypeface( setFontValue );
	}
	
	public void setFontDetail( Typeface setFontValue ){
		detail.setTypeface( setFontValue );
	}
	
	public void setImageURL( String setImageURLValue ){
		imageURL = setImageURLValue;
	}
	
	public ImageButton getFacebookButton(){
		return this.facebookButton;
	}
	
	public ImageButton getMapButton(){
		return this.mapButton;
	}
	
	public ImageButton getTwitterButton(){
		return this.twitterButton;
	}
	
	public String getName(){
		return this.name.getText().toString();
	}
	
	public String getDetail(){
		return this.detail.getText().toString();
	}
	
	public String getImageURL(){
		return this.imageURL;
	}
	
}
