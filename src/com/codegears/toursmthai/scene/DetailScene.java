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
import com.codegears.toursmthai.util.TwitterApp;
import com.codegears.toursmthai.util.NetworkThreadUtil.NetworkThreadListener;
import com.codegears.toursmthai.util.TwitterApp.TwDialogListener;
import com.codegears.toursmthai.util.NetworkUtil;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.Toast;

public class DetailScene extends Activity implements OnClickListener, NetworkThreadListener {
	
	public static final String PUT_EXTRA_PLACE_ID = "PUT_EXTRA_PLACE_ID";
	public static final String URL_GET_PLACE_BY_ID = "URL_GET_PLACE_BY_ID";
	private static final String URL_GET_DETAIL = "URL_GET_DETAIL";
	public static final String APP_FAVOURITE = "APP_FAVOURITE";
	public static final String FAVOURITE_PLACE = "FAVOURITE_PLACE";
	
	private MyApp app;
	private String placeId;
	private String subCategoryId;
	private ImageButton backButton;
	private ImageButton homeButton;
	private ImageButton addFavouriteButton;
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
	private ImageButton view360Button;
	private TextView placeName;
	private TextView placeURL;
	private PlaceData placeData;
	private WebView detailWebView;
	private Gallery detailGallery;
	private ArrayList<URL> detailImageURL;
	private ArrayList<Bitmap> detailImage;
	private ProgressDialog loadingDialog;
	private ImageView detailImageIcon;
	private Facebook facebook;
	private TwitterApp mTwitter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.detailscene );
		
		loadingDialog = ProgressDialog.show(this, "", 
	               "Loading. Please wait...", true);
		
		subCategoryId = getIntent().getStringArrayExtra( PUT_EXTRA_PLACE_ID )[0].toString();
		placeId = getIntent().getStringArrayExtra( PUT_EXTRA_PLACE_ID )[1].toString();
		
		app = (MyApp) getApplication();
		detailImageURL = new ArrayList<URL>();
		detailImage = new ArrayList<Bitmap>();
		facebook = new Facebook( MyApp.FACEBOOK_APP_ID );
		
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
		addFavouriteButton = (ImageButton) findViewById( R.id.detailAddFavoriteButton );
		view360Button = (ImageButton) findViewById( R.id.detail360ViewButton );
		placeName = (TextView) findViewById( R.id.detailPlaceName );
		placeURL = (TextView) findViewById( R.id.detailPlaceURL );
		detailWebView = (WebView) findViewById( R.id.detailWebView );
		detailGallery = (Gallery) findViewById( R.id.detailGallery );
		detailImageIcon = (ImageView) findViewById( R.id.detailImageIcon );
		
		placeURL.setTypeface( app.getTextLiberationFont() );
		
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
		addFavouriteButton.setOnClickListener( this );
		placeURL.setOnClickListener( this );
		view360Button.setOnClickListener( this );
		
		placeName.setTypeface( app.getTextListHeaderFont() );
		
		//Set detail icon
		if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_2_ID ) ||
			subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_1_ID ) ||
			subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_1_ID ) || 
			subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_1_ID ) || 
			subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_1_ID ) || 
			subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_1_ID ) || 
			subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_1_ID ) || 
			subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_1_ID ) ){
			detailImageIcon.setImageResource( R.drawable.detail_accommodations );
		}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_1_ID ) ||
				  subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_2_ID ) ||
				  subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_2_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_2_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_2_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_2_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_2_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_2_ID ) ){
			detailImageIcon.setImageResource( R.drawable.detail_restaurants );
		}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_3_ID ) ||
				  subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_3_ID ) ||
				  subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_3_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_3_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_3_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_3_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_3_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_3_ID ) ){
			detailImageIcon.setImageResource( R.drawable.detail_things_to_do );
		}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_4_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_4_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_4_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_4_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_4_ID ) || 
				  subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_4_ID ) ){
			detailImageIcon.setImageResource( R.drawable.detail_attractions );
		}
		
		//Set twitter button
		mTwitter = new TwitterApp(this, MyApp.TWITTER_CONSUMER_KEY, MyApp.TWITTER_SECRET_KEY);
		mTwitter.setListener(mTwLoginDialogListener);
		
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
		}else if( v.equals( view360Button ) ){
			Intent newIntent = new Intent( this, View360Scene.class );
			startActivity( newIntent );
		}else if( v.equals( homeButton ) ){
			Intent newIntent = new Intent( this, CategoryScene.class );
			startActivity( newIntent );
		}else if( v.equals( addFavouriteButton ) ){
			Toast newToast = new Toast(this).makeText(this, "Add Favourite.", Toast.LENGTH_LONG);
			newToast.show();
			
			Boolean isNotInFav = true;
			SharedPreferences sharedPreferences = getSharedPreferences( APP_FAVOURITE, 0);
			String getFavourite = sharedPreferences.getString( FAVOURITE_PLACE, "");
			
			for( String checkValue:getFavourite.split(",") ){
				if( checkValue.equals( placeId ) ){
					isNotInFav = false;
				}
			}
			
			if( isNotInFav ){
				getFavourite = getFavourite+","+placeId;
			}
			
			SharedPreferences.Editor editor = sharedPreferences.edit();
		    editor.putString(  FAVOURITE_PLACE, getFavourite );
		    editor.commit();
		}else if( v.equals( hotDealButton ) ){
			Intent newIntent = new Intent( this, HotDealScene.class );
			String newArrayExtra[] = { subCategoryId, placeId };
			newIntent.putExtra( HotDealScene.PUT_HOT_DEAL_EXTRA, newArrayExtra );
			startActivity( newIntent );
		}else if( v.equals( mapButton ) ){
			Intent newIntent = new Intent( this, MapScene.class );
			String newArrayExtra[] = { placeData.getLatitude(), placeData.getLongitude() };
			//String newArrayExtra[] = { String.valueOf( 13.744949 ), String.valueOf( 100.53011 ) };
			newIntent.putExtra( MapScene.PUT_MAP_EXTRA, newArrayExtra );
			startActivity( newIntent );
		}else if( v.equals( facebookButton ) ){
			Bundle parameters = new Bundle();
			parameters.putString("app_id", MyApp.FACEBOOK_APP_ID);
			parameters.putString("link", placeData.getURL() );
			parameters.putString("name", placeData.getTitle() );
			parameters.putString("picture", detailImageURL.get(0).toString() );
			parameters.putString("caption", placeData.getTitle());
			parameters.putString("description", placeData.getDescription() );
			
			facebook.dialog(this, "feed", parameters, new DialogListener() {
				@Override
				public void onFacebookError(FacebookError e) {}
				
				@Override
				public void onError(DialogError e) {}
				
				@Override
				public void onComplete(Bundle values) {}
				
				@Override
				public void onCancel() {}
			});
		}else if( v.equals( twitterButton ) ){
			mTwitter.resetAccessToken();
			mTwitter.authorize();
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
		}else if ( v.equals( placeURL ) ){
			TextView newTextView = (TextView) v;
			String itemUrl = newTextView.getText().toString();
			
			if( !itemUrl.equals("")&&!itemUrl.equals("-") ){
				if (!itemUrl.startsWith("http://") && !itemUrl.startsWith("https://")){
					itemUrl = "http://" + itemUrl;
				}
				
				Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( itemUrl ));
				startActivity( newIntent );
			}
		}
	}

	private void onXmlComplete( Document document ){
		NodeList fetchXml = document.getDocumentElement().getElementsByTagName( "place" );
		placeData = new PlaceData();
		placeData.setDataFromXmlNode( fetchXml.item(0) );
		
		this.runOnUiThread( new Runnable() {
			@Override
			public void run() {
				//Show/NotShwow hotdeal icon
				if( placeData.getHotDeal().size() > 0 ){
					hotDealButton.setVisibility( View.VISIBLE );
				}
				
				placeName.setText( placeData.getTitle() );
				placeURL.setText( placeData.getURL() );
				
				//Chech map lat, long value
				try{
					Double.valueOf( placeData.getLatitude() );
					Double.valueOf( placeData.getLongitude() );
				}catch( Exception e ){
					mapButton.setVisibility( View.INVISIBLE );
				}
			}
		});
		
		String[] imageURLSplit = placeData.getSubPictureURL().split(",");
		
		for( String fetchURL:imageURLSplit ){
			try {
				URL newURL = new URL( fetchURL.replace( " ", "%20" ) );
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
		
		loadingDialog.dismiss();
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
	
	private final TwDialogListener mTwLoginDialogListener = new TwDialogListener() {
		@Override
		public void onComplete(String value) {
			/*String username = mTwitter.getUsername();
			username		= (username.equals("")) ? "No Name" : username;
			
			//Toast.makeText(WelcomeScene2.this, "Connected to Twitter as " + username, Toast.LENGTH_LONG).show();*/
			
			Intent newIntent = new Intent( DetailScene.this, TwitterScene.class );
			newIntent.putExtra(TwitterScene.PUT_EXTRA_TWITTER, placeData.getTitle());
			startActivity( newIntent );
		}
		
		@Override
		public void onError(String value) {
			//Toast.makeText(WelcomeScene2.this, "Twitter connection failed : "+value, Toast.LENGTH_LONG).show();
		}
	};

}
