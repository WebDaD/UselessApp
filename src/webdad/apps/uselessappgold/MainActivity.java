package webdad.apps.uselessappgold;

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

public class MainActivity extends Activity {

	public ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (ImageView)findViewById(R.id.btn_switch);
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
	    	img.setImageResource(R.drawable.switch_on_gold);
	    }
	};
	
	Runnable swapImageOff = new Runnable() {
	    public void run() {
	    	img.setImageResource(R.drawable.switch_off_gold);
	    }
	};
	private void play_click(){
		MediaPlayer m = new MediaPlayer();
		   try{
		       AssetFileDescriptor descriptor = this.getAssets().openFd("cash-register2.mp3");
		       m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength() );
		       descriptor.close();
		       m.prepare();
		       m.start();
		   } catch(Exception e){
		       // handle error here.. 
		   }
	}

}
