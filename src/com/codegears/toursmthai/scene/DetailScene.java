package com.codegears.toursmthai.scene;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.codegears.toursmthai.MyApp;
import com.codegears.toursmthai.R;
import com.codegears.toursmthai.data.PlaceData;
import com.codegears.toursmthai.util.NetworkThreadUtil;
import com.codegears.toursmthai.util.NetworkThreadUtil.NetworkThreadListener;
import com.codegears.toursmthai.util.NetworkUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailScene extends Activity implements OnClickListener, NetworkThreadListener {
	
	public static final String PUT_EXTRA_PLACE_ID = "PUT_EXTRA_PLACE_ID";
	private static final String URL_GET_PLACE_BY_ID = "URL_GET_PLACE_BY_ID";
	private static final String URL_GET_DETAIL = "URL_GET_DETAIL";
	
	private MyApp app;
	private String placeId;
	private ImageButton backButton;
	private ImageButton homeButton;
	private ImageButton hotDealButton;
	private ImageButton mapButton;
	private ImageButton facebookButton;
	private ImageButton twitterButton;
	private ImageButton chicButton;
	private ImageButton thainessButton;
	private ImageButton wellnessButton;
	private ImageButton romanticButton;
	private ImageButton adventureButton;
	private ImageButton slowButton;
	private ImageButton seaBreezeButton;
	private ImageButton familyButton;
	private ImageButton favouriteButton;
	private TextView placeName;
	private TextView placeURL;
	private PlaceData placeData;
	private WebView detailWebView;
	private Gallery detailGallery;
	private ArrayList<URL> detailImageURL;
	private ArrayList<Bitmap> detailImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.detailscene );
		
		placeId = getIntent().getStringExtra( PUT_EXTRA_PLACE_ID ).toString();
		
		app = (MyApp) getApplication();
		detailImageURL = new ArrayList<URL>();
		detailImage = new ArrayList<Bitmap>();
		
		backButton = (ImageButton) findViewById( R.id.detailBackButton );
		homeButton = (ImageButton) findViewById( R.id.detailHomeButton );
		hotDealButton = (ImageButton) findViewById( R.id.detailHotDealButton );
		mapButton = (ImageButton) findViewById( R.id.detailMapButton );
		facebookButton = (ImageButton) findViewById( R.id.detailFacebookButton );
		twitterButton = (ImageButton) findViewById( R.id.detailTwitterButton );
		chicButton = (ImageButton) findViewById( R.id.detailChicMenuButton);
		thainessButton = (ImageButton) findViewById( R.id.detailThainessMenuButton );
		wellnessButton = (ImageButton) findViewById( R.id.detailWellnessMenuButton );
		romanticButton = (ImageButton) findViewById( R.id.detailRomanticMenuButton );
		adventureButton = (ImageButton) findViewById( R.id.detailAdventureMenuButton );
		slowButton = (ImageButton) findViewById( R.id.detailSlowMenuButton );
		seaBreezeButton = (ImageButton) findViewById( R.id.detailSeaBreezeMenuButton );
		familyButton = (ImageButton) findViewById( R.id.detailFamilyMenuButton );
		favouriteButton = (ImageButton) findViewById( R.id.detailFavouriteMenuButton );
		
		backButton.setOnClickListener( this );
		homeButton.setOnClickListener( this );
		hotDealButton.setOnClickListener( this );
		mapButton.setOnClickListener( this );
		facebookButton.setOnClickListener( this );
		twitterButton.setOnClickListener( this );
		chicButton.setOnClickListener( this );
		thainessButton.setOnClickListener( this );
		wellnessButton.setOnClickListener( this );
		romanticButton.setOnClickListener( this );
		adventureButton.setOnClickListener( this );
		slowButton.setOnClickListener( this );
		seaBreezeButton.setOnClickListener( this );
		familyButton.setOnClickListener( this );
		favouriteButton.setOnClickListener( this );
		
		placeName = (TextView) findViewById( R.id.detailPlaceName );
		placeURL = (TextView) findViewById( R.id.detailPlaceURL );
		detailWebView = (WebView) findViewById( R.id.detailWebView );
		detailGallery = (Gallery) findViewById( R.id.detailGallery );
		
		HashMap< String, String > dataMap = new HashMap<String, String>();
		dataMap.put( "place_id", placeId );
		String postData = NetworkUtil.createPostData( dataMap );
		NetworkThreadUtil.getXml( app.getConfig().get( URL_GET_PLACE_BY_ID ).toString(),
				postData, this );
		
		detailWebView.getSettings().setDefaultTextEncodingName("utf-8");
		detailWebView.loadUrl( app.getConfig().get( URL_GET_DETAIL ).toString()+"?place_id="+placeId );
	}

	@Override
	public void onClick(View v) {
		if( v.equals( backButton ) ){
			finish();
		}else if( v.equals( homeButton ) ){
			Intent newIntent = new Intent( this, CategoryScene.class );
			startActivity( newIntent );
		}else if( v.equals( hotDealButton ) ){
			
		}else if( v.equals( mapButton ) ){
			
		}else if( v.equals( facebookButton ) ){
			
		}else if( v.equals( twitterButton ) ){
			
		}else if( v.equals( chicButton ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_1 );
			startActivity( newIntent );
		}else if( v.equals( thainessButton ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_2 );
			startActivity( newIntent );
		}else if( v.equals( wellnessButton ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_3 );
			startActivity( newIntent );
		}else if( v.equals( romanticButton ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_4 );
			startActivity( newIntent );
		}else if( v.equals( adventureButton ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_5 );
			startActivity( newIntent );
		}else if( v.equals( slowButton ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_6 );
			startActivity( newIntent );
		}else if( v.equals( seaBreezeButton ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_7 );
			startActivity( newIntent );
		}else if( v.equals( familyButton ) ){
			Intent newIntent = new Intent( this, SubCategoryScene.class );
			newIntent.putExtra( SubCategoryScene.PUT_EXTRA_CATEGORY_ID, SubCategoryScene.CATEGORY_MENU_8 );
			startActivity( newIntent );
		}else if( v.equals( favouriteButton ) ){
			Intent newIntent = new Intent( this, ListScene.class );
			newIntent.putExtra( ListScene.PUT_EXTRA_FAVOURITE_SCENE, ListScene.FAVOURITE_SCENE );
			startActivity( newIntent );
		}
	}

	private void onXmlComplete( Document document ){
		NodeList fetchXml = document.getDocumentElement().getElementsByTagName( "place" );
		final PlaceData placeData = new PlaceData();
		placeData.setDataFromXmlNode( fetchXml.item(0) );
		
		this.runOnUiThread( new Runnable() {
			@Override
			public void run() {
				placeName.setText( placeData.getTitle() );
				placeURL.setText( placeData.getURL() );
			}
		});
		
		String[] imageURLSplit = placeData.getSubPicture().split(",");
		
		for( String fetchURL:imageURLSplit ){
			try {
				URL newURL = new URL( fetchURL );
				detailImageURL.add( newURL );
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		for( URL fetchNewURL:detailImageURL ){
			try {
				Bitmap newBitmap = BitmapFactory.decodeStream( fetchNewURL.openConnection().getInputStream() );
				detailImage.add( newBitmap );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		final GalleryAdapter newGalleryAdapter = new GalleryAdapter( this, detailImage );
		detailGallery.setSpacing( 5 );
		this.runOnUiThread( new Runnable() {
			@Override
			public void run() {
				detailGallery.setAdapter( newGalleryAdapter );
			}
		});
	}
	
	private class GalleryAdapter extends BaseAdapter {
		Context context;
		private ArrayList<Bitmap> data;
		private ArrayList<ImageView> views;
		
		public GalleryAdapter( Context setContext, ArrayList<Bitmap> setData ){
			this.context = setContext;
			this.data = setData;
			views = new ArrayList<ImageView>( data.size() );
		}
		
		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem( int position ) {
			return views.get( position );
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView newImageView = new ImageView(context);
			newImageView.setAdjustViewBounds( true );
			newImageView.setImageBitmap( data.get( position ) );
			views.add( newImageView );
			
			return newImageView;
		}
		
	}
	
	@Override
	public void onNetworkDocSuccess(String urlString, Document document) {
		onXmlComplete( document );
	}

	@Override
	public void onNetworkRawSuccess(String urlString, String result) {
		
	}

	@Override
	public void onNetworkFail(String urlString) {
		
	}
}
