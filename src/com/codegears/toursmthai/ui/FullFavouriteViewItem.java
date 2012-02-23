package com.codegears.toursmthai.ui;

import com.codegears.toursmthai.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FullFavouriteViewItem extends LinearLayout {

	private ImageView itemImage;
	private TextView itemDescriptionText;
	private TextView itemURLText;
	private String itemId;
	
	public FullFavouriteViewItem(Context context) {
		super(context);
		View.inflate( context, R.layout.fullfavouriteview, this );
		
		itemImage = (ImageView) findViewById( R.id.fullFavouriteImageView );
		itemDescriptionText = (TextView) findViewById( R.id.fullFavouriteTextDescription );
		itemURLText = (TextView) findViewById( R.id.fullFavouriteTextURL );
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
	
	public String getItemId(){
		return this.itemId;
	}
}
