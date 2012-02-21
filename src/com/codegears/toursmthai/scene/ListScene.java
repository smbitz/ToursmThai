package com.codegears.toursmthai.scene;

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
import com.codegears.toursmthai.util.NetworkThreadUtil;
import com.codegears.toursmthai.util.NetworkThreadUtil.NetworkThreadListener;
import com.codegears.toursmthai.util.NetworkUtil;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

public class ListScene extends Activity implements NetworkThreadListener, OnClickListener {
	
	private static final String URL_GET_PLACE_BY_SUB_CATEGORY = "URL_GET_PLACE_BY_SUB_CATEGORY";
	public static final String PUT_EXTRA_FAVOURITE_SCENE = "CALL_FAVOURITE_SCENE";
	public static final String FAVOURITE_SCENE = "FAVOURITE_SCENE";
	
	private String categoryId;
	private String subCategoryId;
	private MyApp app;
	private ArrayList<CategoryPlaceGroup> categoryPlaceGroup;
	private ListView listTopView;
	private ListView listMoreView;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.listscene );
		
		app = (MyApp) getApplication();
		callFavourite = getIntent().getExtras().get( PUT_EXTRA_FAVOURITE_SCENE );
		if( callFavourite == null ){
			subCategoryId = getIntent().getExtras().get( SubCategoryScene.PUT_EXTRA_SUB_CATEGORY_ID ).toString();
		}
		
		listTopView = (ListView) findViewById( R.id.listTopListView );
		listMoreView = (ListView) findViewById( R.id.listMoreView );
		
		placeData = new ArrayList<PlaceData>();
		
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
		
		if( callFavourite != null ){
			favouriteButton.setVisibility( favouriteButton.GONE );
			addFavouriteButton.setVisibility( favouriteButton.GONE );
		}
		
		HashMap< String, String > dataMap = new HashMap<String, String>();
		//Not set subCategoryId !!!
		dataMap.put( "subcategory_id", "1" );
		String postData = NetworkUtil.createPostData( dataMap );
		NetworkThreadUtil.getXml( app.getConfig().get( URL_GET_PLACE_BY_SUB_CATEGORY ).toString(),
				postData, this );
	}
	
	private void onXmlComplete( Document document ){
		NodeList fetchXml = document.getDocumentElement().getElementsByTagName( "place" );
		for(int i = 0; i < fetchXml.getLength(); i++){
			PlaceData newPlaceData = new PlaceData();
			newPlaceData.setDataFromXmlNode( fetchXml.item(i) );
			placeData.add( newPlaceData );
		}
		
		final String[] items = {"test1", "test2", "test3"};
		
		this.runOnUiThread(new Runnable(){
			public void run(){
				ArrayAdapter newArrayAdapter = new ArrayAdapter<String>(ListScene.this, android.R.layout.simple_list_item_1, items);
				listTopView.setAdapter( newArrayAdapter );
				listMoreView.setAdapter( newArrayAdapter );
		}});
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

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			return null;
		}
	}

	@Override
	public void onClick(View v) {
		if( v.equals( backButton ) ){
			finish();
		}else if( v.equals( homeButton ) ){
			Intent newIntent = new Intent( this, CategoryScene.class );
			startActivity( newIntent );
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
}
