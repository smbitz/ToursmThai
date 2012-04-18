package com.codegears.toursmthai.scene;

import android.app.Activity;

import javax.microedition.khronos.opengles.GL10;

import android.os.Bundle;
import android.util.Log;

import com.android.panoramagl.PLTexture;
import com.android.panoramagl.PLView;
import com.android.panoramagl.enumeration.PLViewType;
import com.android.panoramagl.structs.PLRange;
import com.codegears.toursmthai.R;

public class View360Scene extends PLView {
	
	public static final String PUT_EXTRA_VIEW_360 = "PUT_EXTRA_VIEW_360";
	
	private String categoryId;
	private int image360BackId;
	private int image360BottomId;
	private int image360FrontId;
	private int image360LeftId;
	private int image360RightId;
	private int image360TopId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		categoryId = this.getIntent().getExtras().getString( PUT_EXTRA_VIEW_360 );
		
		System.out.println("This is : "+categoryId);
		
		//Set image for 360 view
		switch( Integer.parseInt( categoryId ) ){
			case 1 : image360BackId = R.drawable.view360back_chic;
					 image360BottomId = R.drawable.view360bottom_chic;
					 image360FrontId = R.drawable.view360front_chic;
					 image360LeftId = R.drawable.view360left_chic;
					 image360RightId = R.drawable.view360right_chic;
					 image360TopId = R.drawable.view360top_chic;
				break;
			case 2 : image360BackId = R.drawable.view360back_thainess;
					 image360BottomId = R.drawable.view360bottom_thainess;
					 image360FrontId = R.drawable.view360front_thainess;
					 image360LeftId = R.drawable.view360left_thainess;
					 image360RightId = R.drawable.view360right_thainess;
					 image360TopId = R.drawable.view360top_thainess;
				break;
			case 3 : image360BackId = R.drawable.view360back_wellness;
					 image360BottomId = R.drawable.view360bottom_wellness;
					 image360FrontId = R.drawable.view360front_wellness;
					 image360LeftId = R.drawable.view360left_wellness;
					 image360RightId = R.drawable.view360right_wellness;
					 image360TopId = R.drawable.view360top_wellness;
				break;
			case 4 : image360BackId = R.drawable.view360back_romantic;
					 image360BottomId = R.drawable.view360bottom_romantic;
					 image360FrontId = R.drawable.view360front_romantic;
					 image360LeftId = R.drawable.view360left_romantic;
					 image360RightId = R.drawable.view360right_romantic;
					 image360TopId = R.drawable.view360top_romantic;
				break;
			case 5 : image360BackId = R.drawable.view360back_adventure;
					 image360BottomId = R.drawable.view360bottom_adventure;
					 image360FrontId = R.drawable.view360front_adventure;
					 image360LeftId = R.drawable.view360left_adventure;
					 image360RightId = R.drawable.view360right_adventure;
					 image360TopId = R.drawable.view360top_adventure;
				break;
			case 6 : image360BackId = R.drawable.view360back_slow;
					 image360BottomId = R.drawable.view360bottom_slow;
					 image360FrontId = R.drawable.view360front_slow;
					 image360LeftId = R.drawable.view360left_slow;
					 image360RightId = R.drawable.view360right_slow;
					 image360TopId = R.drawable.view360top_slow;
				break;
			case 7 : image360BackId = R.drawable.view360back_breezy;
					 image360BottomId = R.drawable.view360bottom_breezy;
					 image360FrontId = R.drawable.view360front_breezy;
					 image360LeftId = R.drawable.view360left_breezy;
					 image360RightId = R.drawable.view360right_breezy;
					 image360TopId = R.drawable.view360top_breezy;
				break;
			case 8 : image360BackId = R.drawable.view360back_family;
					 image360BottomId = R.drawable.view360bottom_family;
					 image360FrontId = R.drawable.view360front_family;
					 image360LeftId = R.drawable.view360left_family;
					 image360RightId = R.drawable.view360right_family;
					 image360TopId = R.drawable.view360top_family;
				break;
		}
	}
	
	@Override
	protected void onGLContextCreated(GL10 gl)
	{
        super.onGLContextCreated(gl);
        
        try
        {
        	
        	//Important Note: You must edit AndroidManifest.xml and put android:configChanges="keyboardHidden|orientation" attribute in activity else you have memory problems
        	 
        	
        	//If you want to use setDeviceOrientationEnabled(true), activity orientation only must be portrait. Eg. android:screenOrientation="portrait"
	    	this.setDeviceOrientationEnabled(false);
	    	
	    	//You can use accelerometer
	    	this.setAccelerometerEnabled(false);
	    	this.setAccelerometerLeftRightEnabled(true);
	    	this.setAccelerometerUpDownEnabled(false);
	    	
	    	//Scrolling and Inertia
	    	this.setScrollingEnabled(true);
	    	this.setInertiaEnabled(true);
	    	
	    	//setFovRange determines Zoom range. Range values from -1.0f to 1.0f
	    	this.getCamera().setFovRange(PLRange.PLRangeMake(0.0f, 1.0f));
	    	
	    	//Example with Sphere type (you need one image)
	    	/*this.setType(PLViewType.PLViewTypeSpherical);
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce( R.drawable.view360_image_chic )));*/
	    	
	    	//Example with Cube Faces type (you need an image for each cube face)
	    	this.setType(PLViewType.PLViewTypeCubeFaces);
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce( image360FrontId )));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce( image360BackId )));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce( image360LeftId )));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce( image360RightId )));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce( image360TopId )));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce( image360BottomId )));
	    	
        }
        catch(Throwable ex)
        {
        	Log.e("View360::onGLContextCreated", "Error:" + ex.getMessage());
        }
	}
	
}
