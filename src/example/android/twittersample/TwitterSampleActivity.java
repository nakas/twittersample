package example.android.twittersample;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TwitterSampleActivity extends Activity {
    public static RequestToken _req = null;
    public static OAuthAuthorization _oauth = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
                executeOauth();
            }
        });
    }
 
    private void executeOauth(){
        //Twitetr4j�̐ݒ��ǂݍ���
        Configuration conf = ConfigurationContext.getInstance();
 
        //Oauth�F�؃I�u�W�F�N�g�쐬
        _oauth = new OAuthAuthorization(conf);
        //Oauth�F�؃I�u�W�F�N�g��consumerKey��consumerSecret��ݒ�
        _oauth.setOAuthConsumer("Yz5qw1xkbvYUOT2qU9sVhw", "aQT0nuzESn5qrHt60qkywZsdDda8LMpo272v50vQZs");
        //�A�v���̔F�؃I�u�W�F�N�g�쐬
        try {
            _req = _oauth.getOAuthRequestToken("Callback://CallBackActivity");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        String _uri;
        _uri = _req.getAuthorizationURL();
        startActivityForResult(new Intent(Intent.ACTION_VIEW , Uri.parse(_uri)), 0);
    }
}