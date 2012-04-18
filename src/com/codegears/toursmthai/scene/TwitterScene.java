package com.codegears.toursmthai.scene;

import com.codegears.toursmthai.MyApp;
import com.codegears.toursmthai.R;
import com.codegears.toursmthai.util.TwitterApp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TwitterScene extends Activity implements OnClickListener {
	
	public static String PUT_EXTRA_TWITTER = "PUT_EXTRA_TWITTER";
	
	private Button tweetButton;
	private Button closeButton;
	private EditText tweetTextBox;
	private String tweetText;
	private TwitterApp mTwitter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.twitterscene );
		
		tweetText = this.getIntent().getExtras().getString( PUT_EXTRA_TWITTER );
		
		tweetButton = (Button) findViewById( R.id.twitterSceneTweetButton );
		closeButton = (Button) findViewById( R.id.twitterSceneCloseButton );
		tweetTextBox = (EditText) findViewById( R.id.twitterSceneTweetTextBox );
		
		tweetTextBox.setText( tweetText );
		
		tweetButton.setOnClickListener( this );
		closeButton.setOnClickListener( this );
		
		mTwitter = new TwitterApp(this, MyApp.TWITTER_CONSUMER_KEY, MyApp.TWITTER_SECRET_KEY);
	}

	@Override
	public void onClick(View v) {
		if( v.equals( tweetButton ) ){
			String review = tweetTextBox.getText().toString();
			
			if (review.equals("")){
				return;
			}
			
			postToTwitter(review);
			
			this.finish();
		}else if( v.equals( closeButton ) ){
			this.finish();
		}
	}
	
	private void postToTwitter(final String review) {
		new Thread() {
			@Override
			public void run() {
				int what = 0;
				
				try {
					mTwitter.updateStatus(review);
				} catch (Exception e) {
					what = 1;
				}
				
				mHandler.sendMessage(mHandler.obtainMessage(what));
			}
		}.start();
	}
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String text = "Posted to Twitter";
			
			Toast.makeText(TwitterScene.this, text, Toast.LENGTH_SHORT).show();
		}
	};
}
