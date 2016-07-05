package cemayan.com.matbil;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.os.Bundle;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class Splash extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;


    private static final String TWITTER_KEY = "SPEMtkS3ikCEL23VCYWWou3KX";
    private static final String TWITTER_SECRET = "YdY1hncenBxTvJomUQawqVGMUYWkucILFBqqbD10bFuUfJK6Hr";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash.this,Anasayfa.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }


}
