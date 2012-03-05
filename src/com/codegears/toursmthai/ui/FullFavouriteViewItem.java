package com.codegears.toursmthai.ui;

import com.codegears.toursmthai.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FullFavouriteViewItem extends LinearLayout {

	private ImageView itemImage;
	private TextView itemDescriptionText;
	private TextView itemURLText;
	private TextView itemName;
	private String itemId;
	private ImageView hotDealIcon;
	private LinearLayout layoutHotDealSpace;
	
	public FullFavouriteViewItem(Context context) {
		super(context);
		View.inflate( context, R.layout.fullfavouriteview, this );
		
		itemImage = (ImageView) findViewById( R.id.fullFavouriteImageView );
		itemDescriptionText = (TextView) findViewById( R.id.fullFavouriteTextDescription );
		itemURLText = (TextView) findViewById( R.id.fullFavouriteTextURL );
		itemName = (TextView) findViewById( R.id.fullFavouriteTextName );
		hotDealIcon = (ImageView) findViewById( R.id.fullFavouriteHotDealIcon );
		layoutHotDealSpace = (LinearLayout) findViewById( R.id.fullFavouriteHotDealSpace );
	}
	
	public void setImageView( Bitmap setImage ){
		itemImage.setImageBitmap( setImage );
	}
	
	public void setItemDescriptionText( String setTextDescription ){
		itemDescriptionText.setText( setTextDescription );
	}
	
	public void setItemURLText( String setTextURL ){
		itemURLText.setText( setTextURL );
	}
	
	public void setItemId( String setItemId ){
		itemId = setItemId;
	}
	
	public void setItemName( String setItemName ){
		itemName.setText( setItemName );
	}
	
	public void setFontItemName( Typeface setTypeFace ){
		itemName.setTypeface( setTypeFace );
	}
	
	public void setFontItemURLText( Typeface setTypeFace ){
		itemURLText.setTypeface( setTypeFace );
	}
	
	public void setFontItemDescriptionText( Typeface setTypeFace ){
		itemDescriptionText.setTypeface( setTypeFace );
	}
	
	public void setHotDealIconVisibility( int setVisibility ){
		hotDealIcon.setVisibility( setVisibility );
	}
	
	public void setHotDealLayoutSpaceVisibility( int setVisibility ){
		layoutHotDealSpace.setVisibility( setVisibility );
	}
	
	public String getItemId(){
		return this.itemId;
	}
}
