package com.team.ispeakart;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
public class Menu extends Activity {
 
    protected static final int RESULT_SPEECH = 1;
 
    private ImageButton btnSpeak,btnShare;
    private TextView txtText;
    private ImageView img,faq,img1;
    String str,imgs;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
 
        txtText = (TextView) findViewById(R.id.txtText);
        
        img = (ImageView) findViewById(R.id.img);
        img1=(ImageView) findViewById(R.id.img1);
        faq = (ImageView) findViewById(R.id.imageView1);
 
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnShare = (ImageButton) findViewById(R.id.btnShare);
 
        btnSpeak.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
 
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
 
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
 
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Uri imageUri = Uri.parse("android.resource://com.team.ispeakart/drawable/"+imgs);
			      Intent intent = new Intent(Intent.ACTION_SEND);
			      intent.setType("image/png");
			      intent.putExtra(Intent.EXTRA_STREAM, imageUri);
			      startActivity(Intent.createChooser(intent , "Share"));
			}
		});
        faq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),"Existing Arts:\n\nHappy B'Day\nCongratulations\nSorry\nThank You\nWeekend Ventures\nHello\nLoveyou",
						Toast.LENGTH_LONG).show(); 
			}
		});
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         switch (requestCode) {
        case RESULT_SPEECH: {
            if (resultCode == RESULT_OK && null != data) {
            	str=txtText.getText().toString();
                ArrayList<String> text = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                txtText.setText(text.get(0));
                if(((String) txtText.getText()).contains("birthday") ||
                		((String) txtText.getText()).contains("happybirthday")){
                	img.setBackgroundResource(R.drawable.hpybday);
                	img1.setBackgroundResource(R.drawable.happysmiley);
                	imgs="hpybday";
                }else if (((String) txtText.getText()).contains("congratulations")
                		||((String) txtText.getText()).contains("congrats")){
                		img.setBackgroundResource(R.drawable.congratulations);
                		img1.setBackgroundResource(R.drawable.congossmiley);
                		imgs="congratulations";
                }else if (((String) txtText.getText()).contains("sorry")){
            		img.setBackgroundResource(R.drawable.sorry1);
            		img1.setBackgroundResource(R.drawable.sorrysmiley);
            		imgs="sorry";
                }else if (((String) txtText.getText()).contains("thank you") ||
                		((String) txtText.getText()).contains("thankyou") || ((String) txtText.getText()).contains("thanks")){
            			img.setBackgroundResource(R.drawable.thankyou);
            			img1.setBackgroundResource(R.drawable.happysmiley);
            			imgs="thankyou";
                }else if (((String) txtText.getText()).contains("weekend ventures") ||
                		((String) txtText.getText()).contains("weekendventures")){
            			img.setBackgroundResource(R.drawable.weekendventureslogo);
            			img1.setBackgroundResource(R.drawable.happysmiley);
            			imgs="weekendventureslogo";
                }else if (((String) txtText.getText()).contains("hello") ||
                		((String) txtText.getText()).contains("hi")){
                	img.setBackgroundResource(R.drawable.hello);
                	img1.setBackgroundResource(R.drawable.happysmiley);
        			imgs="hello";
                	
                }else if (((String) txtText.getText()).contains("loveyou") ||
                		((String) txtText.getText()).contains("love you")){
                	img.setBackgroundResource(R.drawable.loveyouu);
                	img1.setBackgroundResource(R.drawable.love);
        			imgs="loveyouu";
                	
                }
                else {
                	Toast.makeText(getApplicationContext(),"No Match Found!!!", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
        }
    }
}
