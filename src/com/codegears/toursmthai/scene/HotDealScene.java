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
import com.codegears.toursmthai.data.HotDealData;
import com.codegears.toursmthai.data.PlaceData;
import com.codegears.toursmthai.scene.ListScene.MyListAdapter;
import com.codegears.toursmthai.ui.HotdealItem;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HotDealScene extends Activity implements NetworkThreadListener, OnClickListener {
	
	public static final String PUT_HOT_DEAL_EXTRA = "PUT_HOT_DEAL_EXTRA";
	
	private String placeId;
	private String categoryId;
	private MyApp app;
	private ImageView bgImage;
	private ImageButton backButton;
	private ImageButton homeButton;
	private Gallery itemGallery;
	private ArrayList<URL> itemImageURL;
	private ArrayList<Bitmap> itemImage;
	private ProgressDialog loadingDialog;
	private Facebook facebook;
	private ArrayList<HotdealItem> arrayHotdealItem;
	private PlaceData placeData;
	private TwitterApp mTwitter;
	private String textToTweet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.hotdealscene );
		
		loadingDialog = ProgressDialog.show(this, "", 
	               "Loading. Please wait...", true);
		
		app = (MyApp) getApplication();
		categoryId = getIntent().getStringArrayExtra( PUT_HOT_DEAL_EXTRA )[0].toString().substring(0, 1);
		placeId = getIntent().getStringArrayExtra( PUT_HOT_DEAL_EXTRA )[1].toString();
		
		bgImage = (ImageView) findViewById( R.id.hotdealBgImage );
		itemGallery = (Gallery) findViewById( R.id.hotdealGallery );
		backButton = (ImageButton) findViewById( R.id.hotDealBackButton );
		homeButton = (ImageButton) findViewById( R.id.hotdealHomeButton );
		
		backButton.setOnClickListener( this );
		homeButton.setOnClickListener( this );
		
		itemImageURL = new ArrayList<URL>();
		itemImage = new ArrayList<Bitmap>();
		arrayHotdealItem = new ArrayList<HotdealItem>();
		facebook = new Facebook( MyApp.FACEBOOK_APP_ID );
		
		if( categoryId.equals( SubCategoryScene.CATEGORY_MENU_1 ) ){
			bgImage.setImageResource( R.drawable.hotdeal_bg_chic );
		}else if( categoryId.equals( SubCategoryScene.CATEGORY_MENU_2 ) ){
			bgImage.setImageResource( R.drawable.hotdeal_bg_thainess );
		}else if( categoryId.equals( SubCategoryScene.CATEGORY_MENU_3 ) ){
			bgImage.setImageResource( R.drawable.hotdeal_bg_wellness );
		}else if( categoryId.equals( SubCategoryScene.CATEGORY_MENU_4 ) ){
			bgImage.setImageResource( R.drawable.hotdeal_bg_romantic );
		}else if( categoryId.equals( SubCategoryScene.CATEGORY_MENU_5 ) ){
			bgImage.setImageResource( R.drawable.hotdeal_bg_adventure );
		}else if( categoryId.equals( SubCategoryScene.CATEGORY_MENU_6 ) ){
			bgImage.setImageResource( R.drawable.hotdeal_bg_slow );
		}else if( categoryId.equals( SubCategoryScene.CATEGORY_MENU_7 ) ){
			bgImage.setImageResource( R.drawable.hotdeal_bg_breezy );
		}else if( categoryId.equals( SubCategoryScene.CATEGORY_MENU_8 ) ){
			bgImage.setImageResource( R.drawable.hotdeal_bg_family );
		}
		
		//Set twitter button
		mTwitter = new TwitterApp(this, MyApp.TWITTER_CONSUMER_KEY, MyApp.TWITTER_SECRET_KEY);
		mTwitter.setListener(mTwLoginDialogListener);
		
		HashMap< String, String > dataMap = new HashMap<String, String>();
		dataMap.put( "place_id", placeId );
		String postData = NetworkUtil.createPostData( dataMap );
		NetworkThreadUtil.getXml( app.getConfig().get( DetailScene.URL_GET_PLACE_BY_ID ).toString(),
				postData, this );
	}
	
	private void onXmlComplete( Document document ){
		NodeList fetchXml = document.getDocumentElement().getElementsByTagName( "place" );
		placeData = new PlaceData();
		placeData.setDataFromXmlNode( fetchXml.item(0) );
		
		ArrayList<HotDealData> hotdealItem = placeData.getHotDeal();
		for( HotDealData fetchItem:hotdealItem ){
			try {
				URL newURL = new URL( fetchItem.getImageURL().replace( " ", "%20" ) );
				itemImageURL.add( newURL );
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		for( URL fetchURL:itemImageURL ){
			try {
				Bitmap newBitmap = BitmapFactory.decodeStream( fetchURL.openConnection().getInputStream() );
				itemImage.add( newBitmap );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Set Hotdeal Adapter
		final GalleryAdapter newGalleryAdapter = new GalleryAdapter();
		newGalleryAdapter.setData( getApplicationContext(), hotdealItem );
		itemGallery.setSpacing( 5 );
		this.runOnUiThread( new Runnable() {
			@Override
			public void run() {
				itemGallery.setAdapter( newGalleryAdapter );
				loadingDialog.dismiss();
			}
		});
	}
	
	public class GalleryAdapter extends BaseAdapter {
		
		private Context context;
		private ArrayList<HotDealData> data;
		private ArrayList<HotdealItem> galleryView;
		
		public void setData( Context setContext, ArrayList<HotDealData> setData ){
			context = setContext;
			data = setData;
			galleryView = new ArrayList<HotdealItem>( data.size() );
		}
		
		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem( int position ) {
			return galleryView.get( position );
		}

		@Override
		public long getItemId( int position ) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			HotDealData currentData = data.get( position );
			
			HotdealItem newHotdealItem = new HotdealItem( context );
			newHotdealItem.setName( currentData.getTitle() );
			newHotdealItem.setDetail(currentData.getDescription() );
			newHotdealItem.setFontName( app.getTextListHeaderFont() );
			newHotdealItem.setFontDetail( app.getTextLiberationFont() );
			newHotdealItem.setImageURL( currentData.getImageURL() );
			newHotdealItem.setImage( itemImage.get( position ) );
			newHotdealItem.getFacebookButton().setOnClickListener( HotDealScene.this );
			newHotdealItem.getMapButton().setOnClickListener( HotDealScene.this );
			newHotdealItem.getTwitterButton().setOnClickListener( HotDealScene.this );
			if( position==(data.size()-1) ){
				newHotdealItem.setNextButtonVisibility( View.INVISIBLE );
			}
			galleryView.add( newHotdealItem );
			arrayHotdealItem.add( newHotdealItem );
			
			return newHotdealItem;
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

	@Override
	public void onClick(View v) {
		if( v.equals( backButton ) ){
			finish();
		}else if( v.equals( homeButton ) ){
			Intent newIntent = new Intent( this, CategoryScene.class );
			startActivity( newIntent );
		}else {
			for( HotdealItem fetchItem:arrayHotdealItem ){
				if( v.equals( fetchItem.getFacebookButton() ) ){
					Bundle parameters = new Bundle();
					parameters.putString("app_id", MyApp.FACEBOOK_APP_ID);
					parameters.putString("name", fetchItem.getName() );
					parameters.putString("picture", fetchItem.getImageURL() );
					parameters.putString("caption", fetchItem.getName());
					parameters.putString("description", fetchItem.getDetail() );
					
					facebook.dialog(this, "feed", parameters,
						      new DialogListener() {
						           @Override
						           public void onComplete(Bundle values) {}
	
						           @Override
						           public void onFacebookError(FacebookError error) {}
	
						           @Override
						           public void onError(DialogError e) {}
	
						           @Override
						           public void onCancel() {}
						      }
					);
				}else if( v.equals( fetchItem.getMapButton() ) ){
					Intent newIntent = new Intent( this, MapScene.class );
					String newArrayExtra[] = { placeData.getLatitude(), placeData.getLongitude() };
					//String newArrayExtra[] = { String.valueOf( 13.744949 ), String.valueOf( 100.53011 ) };
					newIntent.putExtra( MapScene.PUT_MAP_EXTRA, newArrayExtra );
					startActivity( newIntent );
				}else if( v.equals( fetchItem.getTwitterButton() ) ){
					textToTweet = fetchItem.getName();
					
					mTwitter.resetAccessToken();
					mTwitter.authorize();
				}
			}
		}
	}
	
	private final TwDialogListener mTwLoginDialogListener = new TwDialogListener() {
		@Override
		public void onComplete(String value) {
			/*String username = mTwitter.getUsername();
			username		= (username.equals("")) ? "No Name" : username;
			
			//Toast.makeText(WelcomeScene2.this, "Connected to Twitter as " + username, Toast.LENGTH_LONG).show();*/
			
			Intent newIntent = new Intent( HotDealScene.this, TwitterScene.class );
			newIntent.putExtra(TwitterScene.PUT_EXTRA_TWITTER, placeData.getTitle()+", Hotdeal ! : "+textToTweet);
			startActivity( newIntent );
		}
		
		@Override
		public void onError(String value) {
			//Toast.makeText(WelcomeScene2.this, "Twitter connection failed : "+value, Toast.LENGTH_LONG).show();
		}
	};
}
