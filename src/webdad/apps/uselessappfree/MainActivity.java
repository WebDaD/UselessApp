package webdad.apps.uselessappfree;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MainActivity extends Activity {

	public ImageView img;
	private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (ImageView)findViewById(R.id.btn_switch);
		adView = new AdView(this, AdSize.BANNER, getString(R.string.admob_id));
		adView.loadAd(new AdRequest());

	}

	 @Override
	  public void onDestroy() {
	    adView.destroy();
	    super.onDestroy();
	  }

	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void btn_switch_onclick(View view){
		turnOn();
		new Handler().postDelayed(new Runnable() {
		    public void run() {
		       turnOff();
		    }
		}, 1000); // 1 second delay
	}

	private void turnOn(){
		img.post(swapImageOn);
		play_click();
		Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(50);
	}
	private void turnOff(){
		img.post(swapImageOff);
		play_click();
		Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(50);
	}
	
	Runnable swapImageOn = new Runnable() {
	    public void run() {
	    	img.setImageResource(R.drawable.switch_on);
	    }
	};
	
	Runnable swapImageOff = new Runnable() {
	    public void run() {
	    	img.setImageResource(R.drawable.switch_off);
	    }
	};
	private void play_click(){
		MediaPlayer m = new MediaPlayer();
		   try{
		       AssetFileDescriptor descriptor = this.getAssets().openFd("button-50.mp3");
		       m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength() );
		       descriptor.close();
		       m.prepare();
		       m.start();
		   } catch(Exception e){
		       // handle error here.. 
		   }
	}

}
