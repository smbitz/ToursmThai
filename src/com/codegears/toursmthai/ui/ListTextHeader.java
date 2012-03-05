package com.codegears.toursmthai.ui;

import com.codegears.toursmthai.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListTextHeader extends LinearLayout {

	private TextView textHeader;
	private ImageView iconHeader;
	
	public ListTextHeader(Context context) {
		super(context);
		View.inflate(context, R.layout.listtextheaderview, this);
		
		textHeader = ( TextView ) findViewById( R.id.listTextHeader );
		iconHeader = ( ImageView ) findViewById( R.id.listIconHeader );
	}
	
	public void setTextHeader( String setText ){
		textHeader.setText( setText );
	}
	
	public void setIconHeader( int setResId ){
		iconHeader.setImageResource( setResId );
	}
	
	public void setTextFont( Typeface setTypeface ){
		textHeader.setTypeface( setTypeface );
	}
}
