package com.codegears.toursmthai.ui;

import com.codegears.toursmthai.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShortFavouriteViewItem extends LinearLayout {

	private TextView itemTitle;
	private TextView itemDescriptionText;
	private String itemURL;
	
	public ShortFavouriteViewItem(Context context) {
		super(context);
		View.inflate( context, R.layout.shortfavouriteview, this );
		
		itemTitle = (TextView) findViewById( R.id.shortFavouriteItemTitle );
		itemDescriptionText = (TextView) findViewById( R.id.shortFavouriteTextView );
	}
	
	public void setItemTitle( String setTextTitle ){
		itemTitle.setText( setTextTitle );
	}
	
	public void setItemDescriptionText( String setTextDescription ){
		itemDescriptionText.setText( setTextDescription );
	}
	
	public void setItemURL( String setItemURL ){
		itemURL = setItemURL;
	}
	
	public String getItemURL(){
		return this.itemURL;
	}
}
