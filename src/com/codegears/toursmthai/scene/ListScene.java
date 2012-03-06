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
import com.codegears.toursmthai.ui.ListTextHeader;
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
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Gallery;
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
	private ProgressDialog loadingDialog2;
	private ImageView imageHeader;
	private ArrayList<Bitmap> arrayImageItem;
	private ArrayList<PlaceData> fullItemData;
	private ArrayList<PlaceData> shortItemData;
	private ArrayList<PlaceData> sumArrayItemData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.listscene );
		
		loadingDialog = ProgressDialog.show(this, "", 
	               "Loading. Please wait...", true);
			
		app = (MyApp) getApplication();
		callFavourite = getIntent().getExtras().get( PUT_EXTRA_FAVOURITE_SCENE );
		
		listView1 = (ListView) findViewById( R.id.listTopListView );
		
		placeData = new ArrayList<PlaceData>();
		favouritePlaceData = new ArrayList<PlaceData>();
		favouriteCategoryData = new ArrayList<CategoryData>();
		favouriteSubCategoryData = new ArrayList<SubCategoryData>();
		arrayImageItem = new ArrayList<Bitmap>();
		fullItemData = new ArrayList<PlaceData>();
		shortItemData = new ArrayList<PlaceData>();
		sumArrayItemData = new ArrayList<PlaceData>();
		
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
		listItemLayout1 = (LinearLayout) findViewById( R.id.listItemlinearLayout1 );
		listLayoutFavouriteButton = (LinearLayout) findViewById( R.id.listLayoutFavouriteButton );
		imageHeader = (ImageView) findViewById( R.id.listImageHeader );
		
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
			
			if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_1_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_chic_1 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_2_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_chic_2 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_3_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_chic_3 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_4_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_chic_4 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_5_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_chic_5 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_1_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_thainess_1 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_2_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_thainess_2 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_3_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_thainess_3 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_4_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_thainess_4 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_1_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_wellness_1 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_2_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_wellness_2 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_3_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_wellness_3 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_1_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_romance_1 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_2_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_romance_2 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_3_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_romance_3 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_4_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_romance_4 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_1_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_adventure_1 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_2_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_adventure_2 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_3_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_adventure_3 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_4_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_adventure_4 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_1_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_slow_1 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_2_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_slow_2 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_3_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_slow_3 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_4_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_slow_4 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_1_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_breezy_1 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_2_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_breezy_2 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_3_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_breezy_3 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_4_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_breezy_4 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_1_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_family_1 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_2_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_family_2 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_3_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_family_3 );
			}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_4_ID ) ){
				imageHeader.setImageResource( R.drawable.toppic_family_4 );
			}
			
			HashMap< String, String > dataMap = new HashMap<String, String>();
			dataMap.put( "subcategory_id", subCategoryId );
			String postData = NetworkUtil.createPostData( dataMap );
			NetworkThreadUtil.getXml( app.getConfig().get( URL_GET_PLACE_BY_SUB_CATEGORY ).toString(),
					postData, this );
		}else{
			/*//Favourite Category
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
			}*/
			subCategoryId = SubCategoryScene.CATEGORY_9_SUB_CATEGORY_1_ID;
			
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
			}else{
				loadingDialog.dismiss();
			}
		}
	}
	
	private void onXmlComplete( Document document ){
		if( callFavourite == null ){
			URL mainPictureURL = null;
			Bitmap imageBitmap = null;
			NodeList fetchXml = document.getDocumentElement().getElementsByTagName( "place" );
			for(int i = 0; i < fetchXml.getLength(); i++){
				PlaceData newPlaceData = new PlaceData();
				newPlaceData.setDataFromXmlNode( fetchXml.item(i) );
				placeData.add( newPlaceData );
				
				if( newPlaceData.getType().equals( DATA_TYPE_FULL ) ){
					try {
						mainPictureURL = new URL( newPlaceData.getMainPictureURL() );
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					
					try {
						imageBitmap = BitmapFactory.decodeStream( mainPictureURL.openConnection().getInputStream() );
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					arrayImageItem.add( imageBitmap );
					fullItemData.add( newPlaceData );
				}else{
					shortItemData.add( newPlaceData );
				}
			}
			
			//Set SumPlaceData
			for( PlaceData fetchFullPlace:fullItemData ){
				sumArrayItemData.add( fetchFullPlace );
			}
			
			for( PlaceData fetchShortPlace:shortItemData ){
				sumArrayItemData.add( fetchShortPlace );
			}
			
			this.runOnUiThread(new Runnable(){
					public void run(){
						//Set List Adapter
						MyListAdapter fullMyListAdapter = new MyListAdapter();
						fullMyListAdapter.setData( getApplicationContext(), sumArrayItemData, fullItemData.size(), shortItemData.size() );
						listView1.setAdapter( fullMyListAdapter );
						loadingDialog.dismiss();
			}});
		
		}else{
			URL mainPictureURL = null;
			Bitmap imageBitmap = null;
			NodeList fetchXml = document.getDocumentElement().getElementsByTagName( "place" );
			for(int i = 0; i < fetchXml.getLength(); i++){
				PlaceData newPlaceData = new PlaceData();
				newPlaceData.setDataFromXmlNode( fetchXml.item(i) );
				placeData.add( newPlaceData );

				try {
					mainPictureURL = new URL( newPlaceData.getMainPictureURL() );
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				
				try {
					imageBitmap = BitmapFactory.decodeStream( mainPictureURL.openConnection().getInputStream() );
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				arrayImageItem.add( imageBitmap );
				favouritePlaceData.add( newPlaceData );
			}
			
			this.runOnUiThread(new Runnable(){
					public void run(){
						//Set Favourite Adapter
						MyListAdapter fullMyListAdapter = new MyListAdapter();
						fullMyListAdapter.setDataFavourite( getApplicationContext(), favouritePlaceData );
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
		private int fullDataSize;
		private int shortDataSize;
		
		public void setData( Context setContext, ArrayList<PlaceData> setData, int setFullDataSize, int setShortDataSize ){
			context = setContext;
			data = setData;
			fullDataSize = setFullDataSize;
			shortDataSize = setShortDataSize;
		}
		
		public void setDataFavourite( Context setContext, ArrayList<PlaceData> setData ){
			context = setContext;
			data = setData;
		}
		
		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem( int position ) {
			return data.get( position );
		}

		@Override
		public long getItemId( int position ) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			PlaceData currentData = data.get( position );
			LinearLayout newLayout = new LinearLayout( ListScene.this );
			newLayout.setOrientation( LinearLayout.VERTICAL );
			newLayout.setLayoutParams( new ListView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) );
			
			String itemURLText = currentData.getURL();
			String itemDescriptionText = currentData.getDescription();
			String itemId = currentData.getId();
			String itemTitle = currentData.getTitle();
			
			//If ListView
			if( callFavourite == null ){
				
				String subCategoryName = app.getCategoryManager().getSubCategoryById( subCategoryId ).getTitle();
				
				if( fullDataSize > 0 && position == 0 ){
					ListTextHeader newText = new ListTextHeader( context );
					newText.setTextHeader( "Top "+subCategoryName );
					newText.setTextFont( app.getTextListHeaderFont() );
					
					if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.top_restaurants_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.top_accomodations_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.top_things_to_do_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.top_night_life_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_5_ID ) ){
						newText.setIconHeader( R.drawable.top_exclusive_service_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.top_accommodations_2 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.top_restaurants_2 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.top_things_to_do_2 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.top_attractions_2 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.top_accommodations_3 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.top_restaurants_3 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.top_things_to_do_3 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.top_accommodations_4 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.top_restaurants_4 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.top_things_to_do_4 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.top_attractions_4 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.top_accommodations_5 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.top_restaurants_5 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.top_things_to_do_5 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.top_attractions_5 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.top_accommodations_6 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.top_restaurants_6 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.top_things_to_do_6 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.top_attractions_6 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.top_accommodations_7 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.top_restaurants_7 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.top_things_to_do_7 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.top_attractions_7 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.top_accommodations_8 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.top_restaurants_8 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.top_things_to_do_8 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.top_attractions_8 );
					}
					
					newLayout.addView( newText );
				}else if( shortDataSize > 0 && position == fullDataSize ){
					ListTextHeader newText = new ListTextHeader( context );
					newText.setTextHeader( "More "+subCategoryName );
					newText.setTextFont( app.getTextListHeaderFont() );
					
					if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.more_restaurants_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.more_accomodations_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.more_things_to_do_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.more_night_life_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_1_SUB_CATEGORY_5_ID ) ){
						newText.setIconHeader( R.drawable.more_exclusive_service_1 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.more_accommodations_2 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.more_restaurants_2 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.more_things_to_do_2 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_2_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.more_attractions_2 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.more_accommodations_3 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.more_restaurants_3 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_3_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.more_things_to_do_3 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.more_accommodations_4 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.more_restaurants_4 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.more_things_to_do_4 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_4_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.more_attractions_4 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.more_accommodations_5 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.more_restaurants_5 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.more_things_to_do_5 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_5_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.more_attractions_5 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.more_accommodations_6 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.more_restaurants_6 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_3_ID ) ){
						//Thing to do 6 image.
						//newText.setIconHeader( R.drawable.more_th );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_6_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.more_attractions_6 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.more_accommodations_7 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.more_restaurants_7 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.more_things_to_do_7 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_7_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.more_attractions_7 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_1_ID ) ){
						newText.setIconHeader( R.drawable.more_accommodations_8 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_2_ID ) ){
						newText.setIconHeader( R.drawable.more_restaurants_8 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_3_ID ) ){
						newText.setIconHeader( R.drawable.more_things_to_do_8 );
					}else if( subCategoryId.equals( SubCategoryScene.CATEGORY_8_SUB_CATEGORY_4_ID ) ){
						newText.setIconHeader( R.drawable.more_attractions_8 );
					}
					
					newLayout.addView( newText );
				}
				
				if( currentData.getType().equals( DATA_TYPE_FULL ) ){
					//Set full view item
					FullFavouriteViewItem newFullItem = new FullFavouriteViewItem( context );
					newFullItem.setImageView( arrayImageItem.get( position ) );
					newFullItem.setItemURLText( itemURLText );
					newFullItem.setItemDescriptionText( itemDescriptionText );
					newFullItem.setItemId( itemId );
					newFullItem.setItemName( itemTitle );
					newFullItem.setFontItemName( app.getTextLiberationFont() );
					newFullItem.setFontItemURLText( app.getTextLiberationFont() );
					newFullItem.setFontItemDescriptionText( app.getTextLiberationFont() );
					
					if( currentData.getHotDeal().size() > 0  ){
						newFullItem.setHotDealIconVisibility( View.VISIBLE );
						newFullItem.setHotDealLayoutSpaceVisibility( View.VISIBLE );
					}
					
					newFullItem.setOnClickListener( ListScene.this );
					
					newLayout.addView( newFullItem );
				}else{
					//Set short view item
					ShortFavouriteViewItem newShortItem = new ShortFavouriteViewItem( context );
					newShortItem.setItemTitle( itemTitle );
					newShortItem.setItemDescriptionText( itemURLText+"/ - "+itemDescriptionText );
					newShortItem.setItemURL( itemURLText );
					newShortItem.setFontItemTitle( app.getTextLiberationFont() );
					newShortItem.setFontItemDescriptionText( app.getTextLiberationFont() );
					newShortItem.setOnClickListener( ListScene.this );
					
					newLayout.addView( newShortItem );
				}
			}else{
				if( favouritePlaceData.size() > 0 && position == 0 ){
					//Header
					ListTextHeader newText = new ListTextHeader( context );
					newText.setTextHeader( "Favourite Place" );
					newText.setTextFont( app.getTextListHeaderFont() );
					newLayout.addView( newText );
				}
				
				//If favourite set all full view item
				FullFavouriteViewItem newFullItem = new FullFavouriteViewItem( context );
				newFullItem.setImageView( arrayImageItem.get( position ) );
				newFullItem.setItemURLText( itemURLText );
				newFullItem.setItemDescriptionText( itemDescriptionText );
				newFullItem.setItemId( itemId );
				newFullItem.setItemName( itemTitle );
				newFullItem.setOnClickListener( ListScene.this );
				
				newLayout.addView( newFullItem );
			}
			
			return newLayout;
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
			
			String newArrayExtra[] = { subCategoryId,newFullItem.getItemId() };
			
			Intent newIntent = new Intent( this, DetailScene.class );
			newIntent.putExtra(  DetailScene.PUT_EXTRA_PLACE_ID, newArrayExtra );
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
	protected void onPause() {
		listView1.setAdapter(null);
		for( Bitmap fetchImage:arrayImageItem ){
			fetchImage.recycle();
		}
		arrayImageItem.clear();
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		loadingDialog2 = ProgressDialog.show(this, "", 
	               "Loading. Please wait...", true);
		
		new Thread( new Runnable() {
			@Override
			public void run() {
				if( callFavourite == null ){
					for( PlaceData fetchPlace:placeData ){
						if( fetchPlace.getType().equals( DATA_TYPE_FULL ) ){
							URL mainPictureURL = null;
							Bitmap imageBitmap = null;
							try {
								mainPictureURL = new URL( fetchPlace.getMainPictureURL() );
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}
							
							try {
								imageBitmap = BitmapFactory.decodeStream( mainPictureURL.openConnection().getInputStream() );
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							arrayImageItem.add( imageBitmap );
						}
					}
					
					ListScene.this.runOnUiThread( new Runnable() {
						@Override
						public void run() {
							//Set List Adapter
							MyListAdapter fullMyListAdapter = new MyListAdapter();
							fullMyListAdapter.setData( getApplicationContext(), sumArrayItemData, fullItemData.size(), shortItemData.size() );
							listView1.setAdapter( fullMyListAdapter );
							loadingDialog2.dismiss();
						}
					});
				}else{
					for( PlaceData fetchPlace:favouritePlaceData ){
						URL mainPictureURL = null;
						Bitmap imageBitmap = null;
						try {
							mainPictureURL = new URL( fetchPlace.getMainPictureURL() );
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						
						try {
							imageBitmap = BitmapFactory.decodeStream( mainPictureURL.openConnection().getInputStream() );
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						arrayImageItem.add( imageBitmap );
					}
					
					ListScene.this.runOnUiThread( new Runnable() {
						@Override
						public void run() {
							//Set List Adapter
							MyListAdapter fullMyListAdapter = new MyListAdapter();
							fullMyListAdapter.setDataFavourite( getApplicationContext(), favouritePlaceData );
							listView1.setAdapter( fullMyListAdapter );
							loadingDialog2.dismiss();
						}
					});
				}
			}
		}).start();
		super.onResume();
	}

}
