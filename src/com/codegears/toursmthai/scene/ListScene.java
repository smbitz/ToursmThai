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
import com.codegears.toursmthai.data.CategoryData;
import com.codegears.toursmthai.data.CategoryPlaceGroup;
import com.codegears.toursmthai.data.PlaceData;
import com.codegears.toursmthai.data.SubCategoryData;
import com.codegears.toursmthai.ui.FullFavouriteViewItem;
import com.codegears.toursmthai.ui.ShortFavouriteViewItem;
import com.codegears.toursmthai.util.NetworkThreadUtil;
import com.codegears.toursmthai.util.NetworkThreadUtil.NetworkThreadListener;
import com.codegears.toursmthai.util.NetworkUtil;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ListScene extends Activity implements NetworkThreadListener, OnClickListener {
	
	private static final String URL_GET_PLACE_BY_SUB_CATEGORY = "URL_GET_PLACE_BY_SUB_CATEGORY";
	public static final String PUT_EXTRA_FAVOURITE_SCENE = "CALL_FAVOURITE_SCENE";
	public static final String FAVOURITE_SCENE = "FAVOURITE_SCENE";
	public static final String APP_FAVOURITE = "APP_FAVOURITE";
	public static final String FAVOURITE_SUB_CATEGORY = "FAVOURITE_SUB_CATEGORY";
	private static final String URL_GET_PLACE_BY_ID = "URL_GET_PLACE_BY_ID";
	private static final String DATA_TYPE_FULL = "full";
	private static final String DATA_TYPE_SHORT = "short";
	
	private String categoryId;
	private String subCategoryId;
	private MyApp app;
	private ArrayList<CategoryPlaceGroup> categoryPlaceGroup;
	private ListView listView1;
	private ListView listView2;
	private ArrayList<PlaceData> placeData;
	private ImageButton backButton;
	private ImageButton homeButton;
	private ImageButton addFavouriteButton;
	private ImageButton chicButton;
	private ImageButton thainessButton;
	private ImageButton wellnessButton;
	private ImageButton romanticButton;
	private ImageButton adventureButton;
	private ImageButton slowButton;
	private ImageButton seaBreezeButton;
	private ImageButton familyButton;
	private ImageButton favouriteButton;
	private Object callFavourite;
	private ArrayList<PlaceData> favouritePlaceData;
	private ArrayList<CategoryData> favouriteCategoryData;
	private ArrayList<SubCategoryData> favouriteSubCategoryData;
	private TextView textHead1;
	private TextView textHead2;
	private FrameLayout listNameHeader1;
	private FrameLayout listNameHeader2;
	private LinearLayout listItemLayout1;
	private LinearLayout listItemLayout2;
	private LinearLayout listSpaceButtom1;
	private LinearLayout listSpaceButtom2;
	private LinearLayout listLayoutIconHeader2;
	private LinearLayout listLayoutDotHeader2;
	private LinearLayout listLayoutFavouriteButton;
	private ProgressDialog loadingDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.listscene );
		
		loadingDialog = ProgressDialog.show(this, "", 
	               "Loading. Please wait...", true);
			
		app = (MyApp) getApplication();
		callFavourite = getIntent().getExtras().get( PUT_EXTRA_FAVOURITE_SCENE );
		
		listView1 = (ListView) findViewById( R.id.listTopListView );
		listView2 = (ListView) findViewById( R.id.listMoreView );
		
		placeData = new ArrayList<PlaceData>();
		favouritePlaceData = new ArrayList<PlaceData>();
		favouriteCategoryData = new ArrayList<CategoryData>();
		favouriteSubCategoryData = new ArrayList<SubCategoryData>();
		
		textHead1 = (TextView) findViewById( R.id.listTextHead1 );
		textHead2 = (TextView) findViewById( R.id.listTextHead2 );
		
		backButton = (ImageButton) findViewById( R.id.listBackButton );
		homeButton = (ImageButton) findViewById( R.id.listHomeButton );
		chicButton = (ImageButton) findViewById( R.id.listChicMenuButton);
		thainessButton = (ImageButton) findViewById( R.id.listThainessMenuButton );
		wellnessButton = (ImageButton) findViewById( R.id.listWellnessMenuButton );
		romanticButton = (ImageButton) findViewById( R.id.listRomanticMenuButton );
		adventureButton = (ImageButton) findViewById( R.id.listAdventureMenuButton );
		slowButton = (ImageButton) findViewById( R.id.listSlowMenuButton );
		seaBreezeButton = (ImageButton) findViewById( R.id.listSeaBreezeMenuButton );
		familyButton = (ImageButton) findViewById( R.id.listFamilyMenuButton );
		favouriteButton = (ImageButton) findViewById( R.id.listFavouriteMenuButton );
		addFavouriteButton = (ImageButton) findViewById( R.id.listAddFavoriteButton );
		listNameHeader1 = (FrameLayout) findViewById( R.id.listNameFrameLayout1 );
		listNameHeader2 = (FrameLayout) findViewById( R.id.listNameFrameLayout2 );
		listItemLayout1 = (LinearLayout) findViewById( R.id.listItemlinearLayout1 );
		listItemLayout2 = (LinearLayout) findViewById( R.id.listItemLinearLayout2 );
		listSpaceButtom1 = (LinearLayout) findViewById( R.id.listSpaceButtom1 );
		listSpaceButtom2 = (LinearLayout) findViewById( R.id.listSpaceButtom2 );
		listLayoutIconHeader2 = (LinearLayout) findViewById( R.id.listLayoutIconHeader2 );
		listLayoutDotHeader2 = (LinearLayout) findViewById( R.id.listLayoutDotHeader2 );
		listLayoutFavouriteButton = (LinearLayout) findViewById( R.id.listLayoutFavouriteButton );
		
		textHead1.setTypeface( app.getTextFont() );
		textHead2.setTypeface( app.getTextFont() );
		
		backButton.setOnClickListener( this );
		homeButton.setOnClickListener( this );
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
		
		//If ListView
		if( callFavourite == null ){
			subCategoryId = getIntent().getExtras().get( SubCategoryScene.PUT_EXTRA_SUB_CATEGORY_ID ).toString();
			
			String subCategoryName = app.getCategoryManager().getSubCategoryById( subCategoryId ).getTitle();
			textHead1.setText( "Top "+subCategoryName );
			textHead2.setText( "More "+subCategoryName );
			
			HashMap< String, String > dataMap = new HashMap<String, String>();
			dataMap.put( "subcategory_id", subCategoryId );
			String postData = NetworkUtil.createPostData( dataMap );
			NetworkThreadUtil.getXml( app.getConfig().get( URL_GET_PLACE_BY_SUB_CATEGORY ).toString(),
					postData, this );
		}else{
			textHead1.setText( "Favourite Place" );
			favouriteButton.setVisibility( View.GONE );
			addFavouriteButton.setVisibility( View.GONE );
			listLayoutFavouriteButton.setVisibility( View.GONE );
			listNameHeader1.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3.2f ) );
			listItemLayout1.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.7f ) );
			listSpaceButtom1.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3.6f ) );
			listNameHeader2.setVisibility( View.GONE );
			listItemLayout2.setVisibility( View.GONE );
			listSpaceButtom2.setVisibility( View.GONE );
			
			//Favourite Category
			SharedPreferences sharedCategoryPreferences = getSharedPreferences( APP_FAVOURITE, 0);
			String getCategoryFavourite = sharedCategoryPreferences.getString( SubCategoryScene.FAVOURITE_CATEGORY, "");
			if( !getCategoryFavourite.equals( "" ) ){
				getCategoryFavourite = getCategoryFavourite.substring(1);
				for( String fetchCategoryId:getCategoryFavourite.split(",") ){
					CategoryData newCategoryData = app.getCategoryManager().getCategoryById( fetchCategoryId );
					favouriteCategoryData.add( newCategoryData );
				}
			}
			
			//Favourite Sub Category
			SharedPreferences sharedSubCategoryPreferences = getSharedPreferences( APP_FAVOURITE, 0);
			String getSubCategoryFavourite = sharedSubCategoryPreferences.getString( FAVOURITE_SUB_CATEGORY, "");
			if( !getSubCategoryFavourite.equals( "" ) ){
				getSubCategoryFavourite = getSubCategoryFavourite.substring(1);
				for( String fetchSubCategoryId:getSubCategoryFavourite.split(",") ){
					SubCategoryData newSubCategoryData = app.getCategoryManager().getSubCategoryById( fetchSubCategoryId );
					favouriteSubCategoryData.add( newSubCategoryData );
				}
			}
			
			//Favourite Place Data
			SharedPreferences sharedPlacePreferences = getSharedPreferences( APP_FAVOURITE, 0);
			String getPlaceFavourite = sharedPlacePreferences.getString( DetailScene.FAVOURITE_PLACE, "");

			if( !getPlaceFavourite.equals( "" ) ){
				getPlaceFavourite = getPlaceFavourite.substring(1);
				
				HashMap< String, String > dataMap = new HashMap<String, String>();
				dataMap.put( "place_id", getPlaceFavourite );
				String postData = NetworkUtil.createPostData( dataMap );
				NetworkThreadUtil.getXml( app.getConfig().get( URL_GET_PLACE_BY_ID ).toString(),
						postData, this );
			}
		}
	}
	
	private void onXmlComplete( Document document ){
		if( callFavourite == null ){
			final ArrayList<PlaceData> fullItemData = new ArrayList<PlaceData>();
			final ArrayList<PlaceData> shortItemData = new ArrayList<PlaceData>();
			NodeList fetchXml = document.getDocumentElement().getElementsByTagName( "place" );
			for(int i = 0; i < fetchXml.getLength(); i++){
				PlaceData newPlaceData = new PlaceData();
				newPlaceData.setDataFromXmlNode( fetchXml.item(i) );
				placeData.add( newPlaceData );
				
				if( newPlaceData.getType().equals( DATA_TYPE_FULL ) ){
					fullItemData.add( newPlaceData );
				}else{
					shortItemData.add( newPlaceData );
				}
			}
			
			this.runOnUiThread(new Runnable(){
				public void run(){
				if( fullItemData.size() == 0 && shortItemData.size() == 0 ){
					listNameHeader1.setVisibility( View.GONE );
					listItemLayout1.setVisibility( View.GONE );
					listLayoutIconHeader2.setVisibility( View.GONE );
					listLayoutDotHeader2.setVisibility( View.GONE );
					textHead2.setText( "Sorry, no data." );
					listNameHeader2.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f ) );
					listSpaceButtom1.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f ) );
					listSpaceButtom2.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 2f ) );
				}else if( fullItemData.size() == 0 ){
					listNameHeader1.setVisibility( View.GONE );
					listItemLayout1.setVisibility( View.GONE );
					listSpaceButtom1.setVisibility( View.GONE );
					listNameHeader2.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3.2f ) );
					listItemLayout2.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.7f ) );
					listSpaceButtom2.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3.6f ) );
				}else if( shortItemData.size() == 0 ){
					listNameHeader2.setVisibility( View.GONE );
					listItemLayout2.setVisibility( View.GONE );
					listSpaceButtom2.setVisibility( View.GONE );
					listNameHeader1.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3.2f ) );
					listItemLayout1.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.7f ) );
					listSpaceButtom1.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3.6f ) );
				}
				
				//Set List Adapter
				MyListAdapter fullMyListAdapter = new MyListAdapter();
				fullMyListAdapter.setData( getApplicationContext(), fullItemData );
				listView1.setAdapter( fullMyListAdapter );
				
				MyListAdapter shortMyListAdapter = new MyListAdapter();
				shortMyListAdapter.setData( getApplicationContext(), shortItemData );
				listView2.setAdapter( shortMyListAdapter );
				loadingDialog.dismiss();
			}});
		
		}else{
			NodeList fetchXml = document.getDocumentElement().getElementsByTagName( "place" );
			for(int i = 0; i < fetchXml.getLength(); i++){
				PlaceData newPlaceData = new PlaceData();
				newPlaceData.setDataFromXmlNode( fetchXml.item(i) );
				favouritePlaceData.add( newPlaceData );
			}
			
			//Set Favourite Adapter
			this.runOnUiThread(new Runnable(){
				public void run(){
					MyListAdapter fullMyListAdapter = new MyListAdapter();
					fullMyListAdapter.setData( getApplicationContext(), favouritePlaceData );
					listView1.setAdapter( fullMyListAdapter );
					loadingDialog.dismiss();
			}});
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
	
	public class MyListAdapter extends BaseAdapter {
		
		private Context context;
		private ArrayList<PlaceData> data;
		private ArrayList<FullFavouriteViewItem> fullViews;
		private ArrayList<ShortFavouriteViewItem> shortViews;
		
		public void setData( Context setContext, ArrayList<PlaceData> setData ){
			context = setContext;
			data = setData;
			fullViews = new ArrayList<FullFavouriteViewItem>( data.size() );
			shortViews = new ArrayList<ShortFavouriteViewItem>( data.size() );
		}
		
		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem( int position ) {
			PlaceData currentData = data.get( position );
			
			if( currentData.getType().equals( DATA_TYPE_FULL ) ){
				return fullViews.get( position );
			}else{
				return shortViews.get( position );
			}
		}

		@Override
		public long getItemId( int position ) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			PlaceData currentData = data.get( position );
			URL mainPictureURL = null;
			Bitmap imageBitmap = null;
			
			try {
				mainPictureURL = new URL( currentData.getMainPictureURL() );
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			try {
				imageBitmap = BitmapFactory.decodeStream( mainPictureURL.openConnection().getInputStream() );
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String itemURLText = currentData.getURL();
			String itemDescriptionText = currentData.getDescription();
			String itemId = currentData.getId();
			String itemTitle = currentData.getTitle();
			
			//If ListView
			if( callFavourite == null ){
				if( currentData.getType().equals( DATA_TYPE_FULL ) ){
					//Set full view item
					FullFavouriteViewItem newFullItem = new FullFavouriteViewItem( context );
					newFullItem.setImageView( imageBitmap );
					newFullItem.setItemURLText( itemURLText );
					newFullItem.setItemDescriptionText( itemDescriptionText );
					newFullItem.setItemId( itemId );
					newFullItem.setOnClickListener( ListScene.this );
					fullViews.add( newFullItem );
					
					return newFullItem;
				}else{
					//Set short view item
					ShortFavouriteViewItem newShortItem = new ShortFavouriteViewItem( context );
					newShortItem.setItemTitle( itemTitle );
					newShortItem.setItemDescriptionText( itemURLText+"/ - "+itemDescriptionText );
					newShortItem.setItemURL( itemURLText );
					newShortItem.setOnClickListener( ListScene.this );
					shortViews.add( newShortItem );
					
					return newShortItem;
				}
			}else{
				//If favourite set all full view item
				FullFavouriteViewItem newFullItem = new FullFavouriteViewItem( context );
				newFullItem.setImageView( imageBitmap );
				newFullItem.setItemURLText( itemURLText );
				newFullItem.setItemDescriptionText( itemDescriptionText );
				newFullItem.setItemId( itemId );
				newFullItem.setOnClickListener( ListScene.this );
				fullViews.add( newFullItem );
				
				return newFullItem;
			}
		}
	}

	@Override
	public void onClick(View v) {
		if( v.equals( backButton ) ){
			finish();
		}else if( v.equals( homeButton ) ){
			Intent newIntent = new Intent( this, CategoryScene.class );
			startActivity( newIntent );
		}else if( v.equals( addFavouriteButton ) ){
			Boolean isNotInFav = true;
			SharedPreferences sharedPreferences = getSharedPreferences( APP_FAVOURITE, 0);
			String getFavourite = sharedPreferences.getString( FAVOURITE_SUB_CATEGORY, "");
			
			for( String checkValue:getFavourite.split(",") ){
				if( checkValue.equals( subCategoryId ) ){
					isNotInFav = false;
				}
			}
			
			if( isNotInFav ){
				getFavourite = getFavourite+","+subCategoryId;
			}
			
			SharedPreferences.Editor editor = sharedPreferences.edit();
		    editor.putString(  FAVOURITE_SUB_CATEGORY, getFavourite );
		    editor.commit();
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
		}else if( v instanceof FullFavouriteViewItem ){
			FullFavouriteViewItem newFullItem = (FullFavouriteViewItem) v;

			Intent newIntent = new Intent( this, DetailScene.class );
			newIntent.putExtra(  DetailScene.PUT_EXTRA_PLACE_ID, newFullItem.getItemId() );
			startActivity( newIntent );
		}else if( v instanceof ShortFavouriteViewItem ){
			ShortFavouriteViewItem newShortItem = (ShortFavouriteViewItem) v;
			
			String itemUrl = newShortItem.getItemURL();
			
			if (!itemUrl.startsWith("http://") && !itemUrl.startsWith("https://")){
				itemUrl = "http://" + itemUrl;
			}
			
			Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( itemUrl ));
			startActivity( newIntent );
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
    		System.out.println("On Back Button Click !!!");
		}
		return super.onKeyDown(keyCode, event);
	}
}
