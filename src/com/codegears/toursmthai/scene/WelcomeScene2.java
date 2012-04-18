package com.codegears.toursmthai.scene;

/*import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;*/

import com.codegears.toursmthai.MyApp;
import com.codegears.toursmthai.R;
import com.codegears.toursmthai.util.TwitterApp;
import com.codegears.toursmthai.util.TwitterApp.TwDialogListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class WelcomeScene2 extends Activity implements OnClickListener {
	
	private static final String FACEBOOK_LINK = "http://www.codegears.co.th/";
	private static final String FACEBOOK_TITLE = "Lifestyle Thailand";
	private static final String FACEBOOK_PICTURE_URL = "http://www.freehand.in.th/tourismthai/images_app/icon.png";
	private static final String FACEBOOK_CAPTION = "Lifestyle Thailand";
	private static final String FACEBOOK_DESCRIPTION = "Lifestyle Thailand";
	private static final String TWITTER_DESCRIPTION = "Lifestyle Thailand";
	
	private Button fullSceneButton;
	private ImageButton facebookButton;
	private ImageButton twitterButton;
	private Facebook facebook;
	private TwitterApp mTwitter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.welcomescene2 );

		facebook = new Facebook( MyApp.FACEBOOK_APP_ID );
		
		fullSceneButton = (Button) findViewById( R.id.welcomeScene2FullSceneButton );
		facebookButton = (ImageButton) findViewById( R.id.welcomSceneFacebookButton );
		twitterButton = (ImageButton) findViewById( R.id.welcomeSceneTwitterButton );
		
		fullSceneButton.setOnClickListener( this );
		facebookButton.setOnClickListener( this );
		twitterButton.setOnClickListener( this );
		
		mTwitter = new TwitterApp(this, MyApp.TWITTER_CONSUMER_KEY, MyApp.TWITTER_SECRET_KEY);
		mTwitter.setListener(mTwLoginDialogListener);
	}

	@Override
	public void onClick(View v) {
		if( v.equals( facebookButton ) ){
			Bundle parameters = new Bundle();
			parameters.putString("app_id", MyApp.FACEBOOK_APP_ID);
			parameters.putString("link", FACEBOOK_LINK);
			parameters.putString("name", FACEBOOK_TITLE);
			parameters.putString("picture", FACEBOOK_PICTURE_URL);
			parameters.putString("caption", FACEBOOK_CAPTION);
			parameters.putString("description", FACEBOOK_DESCRIPTION);
			
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
		}else if( v.equals( twitterButton ) ){
			mTwitter.resetAccessToken();
			mTwitter.authorize();
		}else if( v.equals( fullSceneButton ) ){
			Intent newIntent = new Intent( this, CategoryScene.class );
			startActivity( newIntent );
		}
	}
	
	private final TwDialogListener mTwLoginDialogListener = new TwDialogListener() {
		@Override
		public void onComplete(String value) {
			/*String username = mTwitter.getUsername();
			username		= (username.equals("")) ? "No Name" : username;
			
			//Toast.makeText(WelcomeScene2.this, "Connected to Twitter as " + username, Toast.LENGTH_LONG).show();*/
			
			Intent newIntent = new Intent( WelcomeScene2.this, TwitterScene.class );
			newIntent.putExtra(TwitterScene.PUT_EXTRA_TWITTER, TWITTER_DESCRIPTION);
			startActivity( newIntent );
		}
		
		@Override
		public void onError(String value) {
			//Toast.makeText(WelcomeScene2.this, "Twitter connection failed : "+value, Toast.LENGTH_LONG).show();
		}
	};
}
