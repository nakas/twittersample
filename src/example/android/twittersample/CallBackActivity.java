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
        
        //Twitter�̔F�؉�ʂ��甭�s�����Intent����Uri���擾
        Uri uri = getIntent().getData();
 
        if(uri != null && uri.toString().startsWith("Callback://CallBackActivity")){
            //oauth_verifier���擾����
            String verifier = uri.getQueryParameter("oauth_verifier");
            try {
                //AccessToken�I�u�W�F�N�g���擾
                token = TwitterSampleActivity._oauth.getOAuthAccessToken(TwitterSampleActivity._req, verifier);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
 
        TextView tv = (TextView)findViewById(R.id.textView1);
        CharSequence cs = "token�F" + token.getToken() + "\r\n" + "token secret�F" + token.getTokenSecret();
        tv.setText(cs);
      //twitter�I�u�W�F�N�g�̍쐬
        Twitter tw = new TwitterFactory().getInstance();
         
        //AccessToken�I�u�W�F�N�g�̍쐬
        AccessToken at = new AccessToken(token.getToken(), token.getTokenSecret());
         
        //Consumer key��Consumer key seacret�̐ݒ�
        tw.setOAuthConsumer("Yz5qw1xkbvYUOT2qU9sVhw", "aQT0nuzESn5qrHt60qkywZsdDda8LMpo272v50vQZs");
         
        //AccessToken�I�u�W�F�N�g��ݒ�
        tw.setOAuthAccessToken(at);
        
        try {
            tw.updateStatus("�Ԃ₫����������");
        } catch (TwitterException e) {
            e.printStackTrace();
            if(e.isCausedByNetworkIssue()){
                 Toast.makeText(this, "�l�b�g�[���[�N�̖��ł�", Toast.LENGTH_LONG);
            }
        }
    }
}