package com.codegears.toursmthai.scene;

import java.util.ArrayList;

import com.codegears.toursmthai.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SubCategoryScene extends Activity implements OnClickListener {
	
	public static final String CATEGORY_MENU_1 = "1";
	public static final String CATEGORY_MENU_2 = "2";
	public static final String CATEGORY_MENU_3 = "3";
	public static final String CATEGORY_MENU_4 = "4";
	public static final String CATEGORY_MENU_5 = "5";
	public static final String CATEGORY_MENU_6 = "6";
	public static final String CATEGORY_MENU_7 = "7";
	public static final String CATEGORY_MENU_8 = "8";
	public static final String CATEGORY_MENU_9 = "9";
	
	private static final String CATEGORY_1_SUB_CATEGORY_1_ID = "11";
	private static final String CATEGORY_1_SUB_CATEGORY_2_ID = "12";
	private static final String CATEGORY_1_SUB_CATEGORY_3_ID = "13";
	private static final String CATEGORY_1_SUB_CATEGORY_4_ID = "14";
	private static final String CATEGORY_1_SUB_CATEGORY_5_ID = "15";
	private static final String CATEGORY_2_SUB_CATEGORY_1_ID = "21";
	private static final String CATEGORY_2_SUB_CATEGORY_2_ID = "22";
	private static final String CATEGORY_2_SUB_CATEGORY_3_ID = "23";
	private static final String CATEGORY_2_SUB_CATEGORY_4_ID = "24";
	private static final String CATEGORY_3_SUB_CATEGORY_1_ID = "31";
	private static final String CATEGORY_3_SUB_CATEGORY_2_ID = "32";
	private static final String CATEGORY_3_SUB_CATEGORY_3_ID = "33";
	private static final String CATEGORY_4_SUB_CATEGORY_1_ID = "41";
	private static final String CATEGORY_4_SUB_CATEGORY_2_ID = "42";
	private static final String CATEGORY_4_SUB_CATEGORY_3_ID = "43";
	private static final String CATEGORY_4_SUB_CATEGORY_4_ID = "44";
	private static final String CATEGORY_5_SUB_CATEGORY_1_ID = "51";
	private static final String CATEGORY_5_SUB_CATEGORY_2_ID = "52";
	private static final String CATEGORY_5_SUB_CATEGORY_3_ID = "53";
	private static final String CATEGORY_5_SUB_CATEGORY_4_ID = "54";
	private static final String CATEGORY_6_SUB_CATEGORY_1_ID = "61";
	private static final String CATEGORY_6_SUB_CATEGORY_2_ID = "62";
	private static final String CATEGORY_6_SUB_CATEGORY_3_ID = "63";
	private static final String CATEGORY_6_SUB_CATEGORY_4_ID = "64";
	private static final String CATEGORY_7_SUB_CATEGORY_1_ID = "71";
	private static final String CATEGORY_7_SUB_CATEGORY_2_ID = "72";
	private static final String CATEGORY_7_SUB_CATEGORY_3_ID = "73";
	private static final String CATEGORY_7_SUB_CATEGORY_4_ID = "74";
	private static final String CATEGORY_8_SUB_CATEGORY_1_ID = "81";
	private static final String CATEGORY_8_SUB_CATEGORY_2_ID = "82";
	private static final String CATEGORY_8_SUB_CATEGORY_3_ID = "83";
	private static final String CATEGORY_8_SUB_CATEGORY_4_ID = "84";
	
	public static final String PUT_EXTRA_CATEGORY_ID = "PUT_EXTRA_CATEGORY_ID";
	public static final String PUT_EXTRA_SUB_CATEGORY_ID = "PUT_EXTRA_SUB_CATEGORY_ID";
	
	private String categoryId;
	private ImageButton subCategoryButton1;
	private ImageButton subCategoryButton2;
	private ImageButton subCategoryButton3;
	private ImageButton subCategoryButton4;
	private ImageButton subCategoryButton5;
	private Bundle putExtraBundle;
	private ImageButton backButton;
	private ImageButton view360Button;
	private ImageButton homeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		putExtraBundle = new Bundle();
		categoryId = getIntent().getExtras().get( PUT_EXTRA_CATEGORY_ID ).toString();
		
		if( categoryId.equals( CATEGORY_MENU_1 ) ){
			setContentView( R.layout.subcategorychicscene );
			
			subCategoryButton1 = (ImageButton) findViewById( R.id.subCategoryChicButton1 );
			subCategoryButton2 = (ImageButton) findViewById( R.id.subCategoryChicButton2 );
			subCategoryButton3 = (ImageButton) findViewById( R.id.subCategoryChicButton3 );
			subCategoryButton4 = (ImageButton) findViewById( R.id.subCategoryChicButton4 );
			subCategoryButton5 = (ImageButton) findViewById( R.id.subCategoryChicButton5 );
			
			backButton = (ImageButton) findViewById( R.id.subCategoryChicBackButton );
			view360Button = (ImageButton) findViewById( R.id.subCategoryChic360ViewButton );
			homeButton = (ImageButton) findViewById( R.id.subCategoryChicHomeButton );
			
			subCategoryButton1.setOnClickListener( this );
			subCategoryButton2.setOnClickListener( this );
			subCategoryButton3.setOnClickListener( this );
			subCategoryButton4.setOnClickListener( this );
			subCategoryButton5.setOnClickListener( this );
		}else if( categoryId.equals( CATEGORY_MENU_2 ) ){
			setContentView( R.layout.subcategorythainessscene );
			
			subCategoryButton1 = (ImageButton) findViewById( R.id.subCategoryThainessButton1 );
			subCategoryButton2 = (ImageButton) findViewById( R.id.subCategoryThainessButton2 );
			subCategoryButton3 = (ImageButton) findViewById( R.id.subCategoryThainessButton3 );
			subCategoryButton4 = (ImageButton) findViewById( R.id.subCategoryThainessButton4 );
			
			backButton = (ImageButton) findViewById( R.id.subCategoryThainessBackButton );
			view360Button = (ImageButton) findViewById( R.id.subCategoryThainess360ViewButton );
			homeButton = (ImageButton) findViewById( R.id.subCategoryThainessHomeButton );
			
			subCategoryButton1.setOnClickListener( this );
			subCategoryButton2.setOnClickListener( this );
			subCategoryButton3.setOnClickListener( this );
			subCategoryButton4.setOnClickListener( this );
		}else if( categoryId.equals( CATEGORY_MENU_3 ) ){
			setContentView( R.layout.subcategorywellnessscene );
			
			subCategoryButton1 = (ImageButton) findViewById( R.id.subCategoryWellnessButton1 );
			subCategoryButton2 = (ImageButton) findViewById( R.id.subCategoryWellnessButton2 );
			subCategoryButton3 = (ImageButton) findViewById( R.id.subCategoryWellnessButton3 );
			
			backButton = (ImageButton) findViewById( R.id.subCategoryWellnessBackButton );
			view360Button = (ImageButton) findViewById( R.id.subCategoryWellness360ViewButton );
			homeButton = (ImageButton) findViewById( R.id.subCategoryWellnessHomeButton );
			
			subCategoryButton1.setOnClickListener( this );
			subCategoryButton2.setOnClickListener( this );
			subCategoryButton3.setOnClickListener( this );
		}else if( categoryId.equals( CATEGORY_MENU_4 ) ){
			setContentView( R.layout.subcategoryromanticscene );
			
			subCategoryButton1 = (ImageButton) findViewById( R.id.subCategoryRomanticButton1 );
			subCategoryButton2 = (ImageButton) findViewById( R.id.subCategoryRomanticButton2 );
			subCategoryButton3 = (ImageButton) findViewById( R.id.subCategoryRomanticButton3 );
			subCategoryButton4 = (ImageButton) findViewById( R.id.subCategoryRomanticButton4 );
			
			backButton = (ImageButton) findViewById( R.id.subCategoryRomanticBackButton );
			view360Button = (ImageButton) findViewById( R.id.subCategoryRomantic360ViewButton );
			homeButton = (ImageButton) findViewById( R.id.subCategoryRomanticHomeButton );
			
			subCategoryButton1.setOnClickListener( this );
			subCategoryButton2.setOnClickListener( this );
			subCategoryButton3.setOnClickListener( this );
			subCategoryButton4.setOnClickListener( this );
		}else if( categoryId.equals( CATEGORY_MENU_5 ) ){
			setContentView( R.layout.subcategoryadventurescene );
			
			subCategoryButton1 = (ImageButton) findViewById( R.id.subCategoryAdventureButton1 );
			subCategoryButton2 = (ImageButton) findViewById( R.id.subCategoryAdventureButton2 );
			subCategoryButton3 = (ImageButton) findViewById( R.id.subCategoryAdventureButton3 );
			subCategoryButton4 = (ImageButton) findViewById( R.id.subCategoryAdventureButton4 );
			
			backButton = (ImageButton) findViewById( R.id.subCategoryAdventureBackButton );
			view360Button = (ImageButton) findViewById( R.id.subCategoryAdventure360ViewButton );
			homeButton = (ImageButton) findViewById( R.id.subCategoryAdventureHomeButton );
			
			subCategoryButton1.setOnClickListener( this );
			subCategoryButton2.setOnClickListener( this );
			subCategoryButton3.setOnClickListener( this );
			subCategoryButton4.setOnClickListener( this );
		}else if( categoryId.equals( CATEGORY_MENU_6 ) ){
			setContentView( R.layout.subcategoryslowscene );
			
			subCategoryButton1 = (ImageButton) findViewById( R.id.subCategorySlowButton1 );
			subCategoryButton2 = (ImageButton) findViewById( R.id.subCategorySlowButton2 );
			subCategoryButton3 = (ImageButton) findViewById( R.id.subCategorySlowButton3 );
			subCategoryButton4 = (ImageButton) findViewById( R.id.subCategorySlowButton4 );
			
			backButton = (ImageButton) findViewById( R.id.subCategorySlowBackButton );
			view360Button = (ImageButton) findViewById( R.id.subCategorySlow360ViewButton );
			homeButton = (ImageButton) findViewById( R.id.subCategorySlowHomeButton );
			
			subCategoryButton1.setOnClickListener( this );
			subCategoryButton2.setOnClickListener( this );
			subCategoryButton3.setOnClickListener( this );
			subCategoryButton4.setOnClickListener( this );
		}else if( categoryId.equals( CATEGORY_MENU_7 ) ){
			setContentView( R.layout.subcategoryseabreezescene );
			
			subCategoryButton1 = (ImageButton) findViewById( R.id.subCategorySeaBreezeButton1 );
			subCategoryButton2 = (ImageButton) findViewById( R.id.subCategorySeaBreezeButton2 );
			subCategoryButton3 = (ImageButton) findViewById( R.id.subCategorySeaBreezeButton3 );
			subCategoryButton4 = (ImageButton) findViewById( R.id.subCategorySeaBreezeButton4 );
			
			backButton = (ImageButton) findViewById( R.id.subCategorySeaBreezeBackButton );
			view360Button = (ImageButton) findViewById( R.id.subCategorySeaBreeze360ViewButton );
			homeButton = (ImageButton) findViewById( R.id.subCategorySeaBreezeHomeButton );
			
			subCategoryButton1.setOnClickListener( this );
			subCategoryButton2.setOnClickListener( this );
			subCategoryButton3.setOnClickListener( this );
			subCategoryButton4.setOnClickListener( this );
		}else if( categoryId.equals( CATEGORY_MENU_8 ) ){
			setContentView( R.layout.subcategoryfamilyscene );
			
			subCategoryButton1 = (ImageButton) findViewById( R.id.subCategoryFamilyButton1 );
			subCategoryButton2 = (ImageButton) findViewById( R.id.subCategoryFamilyButton2 );
			subCategoryButton3 = (ImageButton) findViewById( R.id.subCategoryFamilyButton3 );
			subCategoryButton4 = (ImageButton) findViewById( R.id.subCategoryFamilyButton4 );
			
			backButton = (ImageButton) findViewById( R.id.subCategoryFamilyBackButton );
			view360Button = (ImageButton) findViewById( R.id.subCategoryFamily360ViewButton );
			homeButton = (ImageButton) findViewById( R.id.subCategoryFamilyHomeButton );
			
			subCategoryButton1.setOnClickListener( this );
			subCategoryButton2.setOnClickListener( this );
			subCategoryButton3.setOnClickListener( this );
			subCategoryButton4.setOnClickListener( this );
		}else if( categoryId.equals( CATEGORY_MENU_9 ) ){
			setContentView( R.layout.subcategoryfavouritescene );
			
			backButton = (ImageButton) findViewById( R.id.subCategoryFavouriteBackButton );
			view360Button = (ImageButton) findViewById( R.id.subCategoryFavourite360ViewButton );
			homeButton = (ImageButton) findViewById( R.id.subCategoryFavouriteHomeButton );
		}
		
		backButton.setOnClickListener( this );
		view360Button.setOnClickListener( this );
		homeButton.setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		if( v.equals( backButton ) ){
			finish();
		}else if( v.equals( view360Button ) ){
			
		}else if( v.equals( homeButton ) ){
			Intent newIntent = new Intent( this, CategoryScene.class );
			startActivity( newIntent );
		}else if( categoryId.equals( CATEGORY_MENU_1 ) ){
			//Intent newIntent = new Intent( this, ListScene.class );
			Intent newIntent = new Intent( this, DetailScene.class );
			
			if( v.equals( subCategoryButton1 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_1_SUB_CATEGORY_1_ID );
			}else if( v.equals( subCategoryButton2 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_1_SUB_CATEGORY_2_ID );
			}else if( v.equals( subCategoryButton3 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_1_SUB_CATEGORY_3_ID );
			}else if( v.equals( subCategoryButton4 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_1_SUB_CATEGORY_4_ID );
			}else if( v.equals( subCategoryButton5 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_1_SUB_CATEGORY_5_ID );
			}
			
			newIntent.putExtra(  DetailScene.PUT_EXTRA_PLACE_ID, "32" );
			startActivity( newIntent );
		}else if( categoryId.equals( CATEGORY_MENU_2 ) ){
			Intent newIntent = new Intent( this, ListScene.class );
			
			if( v.equals( subCategoryButton1 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_2_SUB_CATEGORY_1_ID );
			}else if( v.equals( subCategoryButton2 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_2_SUB_CATEGORY_2_ID );
			}else if( v.equals( subCategoryButton3 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_2_SUB_CATEGORY_3_ID );
			}else if( v.equals( subCategoryButton4 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_2_SUB_CATEGORY_4_ID );
			}
			
			startActivity( newIntent );
		}else if( categoryId.equals( CATEGORY_MENU_3 ) ){
			Intent newIntent = new Intent( this, ListScene.class );
			
			if( v.equals( subCategoryButton1 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_3_SUB_CATEGORY_1_ID );
			}else if( v.equals( subCategoryButton2 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_3_SUB_CATEGORY_2_ID );
			}else if( v.equals( subCategoryButton3 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_3_SUB_CATEGORY_3_ID );
			}
			
			startActivity( newIntent );
		}else if( categoryId.equals( CATEGORY_MENU_4 ) ){
			Intent newIntent = new Intent( this, ListScene.class );
			
			if( v.equals( subCategoryButton1 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_4_SUB_CATEGORY_1_ID );
			}else if( v.equals( subCategoryButton2 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_4_SUB_CATEGORY_2_ID );
			}else if( v.equals( subCategoryButton3 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_4_SUB_CATEGORY_3_ID );
			}else if( v.equals( subCategoryButton4 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_4_SUB_CATEGORY_4_ID );
			}
			
			startActivity( newIntent );
		}else if( categoryId.equals( CATEGORY_MENU_5 ) ){
			Intent newIntent = new Intent( this, ListScene.class );
			
			if( v.equals( subCategoryButton1 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_5_SUB_CATEGORY_1_ID );
			}else if( v.equals( subCategoryButton2 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_5_SUB_CATEGORY_2_ID );
			}else if( v.equals( subCategoryButton3 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_5_SUB_CATEGORY_3_ID );
			}else if( v.equals( subCategoryButton4 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_5_SUB_CATEGORY_4_ID );
			}
			
			startActivity( newIntent );
		}else if( categoryId.equals( CATEGORY_MENU_6 ) ){
			Intent newIntent = new Intent( this, ListScene.class );
			
			if( v.equals( subCategoryButton1 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_6_SUB_CATEGORY_1_ID );
			}else if( v.equals( subCategoryButton2 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_6_SUB_CATEGORY_2_ID );
			}else if( v.equals( subCategoryButton3 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_6_SUB_CATEGORY_3_ID );
			}else if( v.equals( subCategoryButton4 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_6_SUB_CATEGORY_4_ID );
			}
			
			startActivity( newIntent );
		}else if( categoryId.equals( CATEGORY_MENU_7 ) ){
			Intent newIntent = new Intent( this, ListScene.class );
			
			if( v.equals( subCategoryButton1 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_7_SUB_CATEGORY_1_ID );
			}else if( v.equals( subCategoryButton2 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_7_SUB_CATEGORY_2_ID );
			}else if( v.equals( subCategoryButton3 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_7_SUB_CATEGORY_3_ID );
			}else if( v.equals( subCategoryButton4 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_7_SUB_CATEGORY_4_ID );
			}
			
			startActivity( newIntent );
		}else if( categoryId.equals( CATEGORY_MENU_8 ) ){
			Intent newIntent = new Intent( this, ListScene.class );
			
			if( v.equals( subCategoryButton1 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_8_SUB_CATEGORY_1_ID );
			}else if( v.equals( subCategoryButton2 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_8_SUB_CATEGORY_2_ID );
			}else if( v.equals( subCategoryButton3 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_8_SUB_CATEGORY_3_ID );
			}else if( v.equals( subCategoryButton4 ) ){
				newIntent.putExtra(  PUT_EXTRA_SUB_CATEGORY_ID, CATEGORY_8_SUB_CATEGORY_4_ID );
			}
			
			startActivity( newIntent );
		}
	}
}
