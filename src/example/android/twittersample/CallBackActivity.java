package example.android.twittersample;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CallBackActivity extends Activity {
 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callback);
        
        AccessToken token = null;
        
        //Twitterの認証画面から発行されるIntentからUriを取得
        Uri uri = getIntent().getData();
 
        if(uri != null && uri.toString().startsWith("Callback://CallBackActivity")){
            //oauth_verifierを取得する
            String verifier = uri.getQueryParameter("oauth_verifier");
            try {
                //AccessTokenオブジェクトを取得
                token = TwitterSampleActivity._oauth.getOAuthAccessToken(TwitterSampleActivity._req, verifier);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
 
        TextView tv = (TextView)findViewById(R.id.textView1);
        CharSequence cs = "token：" + token.getToken() + "\r\n" + "token secret：" + token.getTokenSecret();
        tv.setText(cs);
      //twitterオブジェクトの作成
        Twitter tw = new TwitterFactory().getInstance();
         
        //AccessTokenオブジェクトの作成
        AccessToken at = new AccessToken(token.getToken(), token.getTokenSecret());
         
        //Consumer keyとConsumer key seacretの設定
        tw.setOAuthConsumer("Yz5qw1xkbvYUOT2qU9sVhw", "aQT0nuzESn5qrHt60qkywZsdDda8LMpo272v50vQZs");
         
        //AccessTokenオブジェクトを設定
        tw.setOAuthAccessToken(at);
        
        try {
            tw.updateStatus("つぶやきたい文字列");
        } catch (TwitterException e) {
            e.printStackTrace();
            if(e.isCausedByNetworkIssue()){
                 Toast.makeText(this, "ネットーワークの問題です", Toast.LENGTH_LONG);
            }
        }
    }
}