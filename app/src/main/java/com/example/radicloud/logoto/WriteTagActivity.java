package com.example.radicloud.logoto;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radicloud.logoto.Models.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

import static com.example.radicloud.logoto.R.drawable.spinner;


/**
 * Created by ahmad on 9/20/2016.
 */

public class WriteTagActivity extends AppCompatActivity {

    private View v;
    private NFCManager nfcMger;
    private NdefMessage message = null;
    private ProgressDialog dialog;
    Tag currentTag;



    AnimationDrawable rocketAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String content = randomString(5);
        nfcMger = new NFCManager(this);
        message =  nfcMger.createUriMessage(content, "http://radicloud.ir?");

        ImageView rocketImage = (ImageView) findViewById(R.id.splashSpinner);
        rocketImage.setBackgroundResource(R.drawable.spinner);
        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
        rocketAnimation.setOneShot(true);
        rocketAnimation.start();

        if (message != null) {

            //dialog = new ProgressDialog(WriteTagActivity.this);
           // dialog.setMessage("Tag NFC Tag please");
           // dialog.show();;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                rocketAnimation.stop();
                return true;
            }
            return super.onTouchEvent(event);
        }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            nfcMger.verifyNFC();
           // nfcMger.enableDispatch();

            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, nfcIntent, 0);
            IntentFilter[] intentFiltersArray = new IntentFilter[] {};
            String[][] techList = new String[][] { { android.nfc.tech.Ndef.class.getName() },
                    { android.nfc.tech.NdefFormatable.class.getName() } };
            NfcAdapter nfcAdpt = NfcAdapter.getDefaultAdapter(this);
            nfcAdpt.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techList);
        }
        catch(NFCManager.NFCNotSupported nfcnsup) {
            Snackbar.make(v, "تگ پشتیبانی نشد", Snackbar.LENGTH_LONG).show();
        }
        catch(NFCManager.NFCNotEnabled nfcnEn) {
            Snackbar.make(v, "NFC موبایل شما روشن نیست", Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.d("Nfc", "New intent");
        // It is the time to write the tag
        currentTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (message != null) {
            nfcMger.writeTag(currentTag, message);
            //dialog.dismiss();
            Toast.makeText(WriteTagActivity.this,"Tag Written",Toast.LENGTH_LONG).show();
            //show proper image for successful
            //un show the progress bar
            ProgressBar b=(ProgressBar) findViewById(R.id.pbHeaderProgress);
            b.setVisibility(View.INVISIBLE);
            TextView t=(TextView) findViewById(R.id.messageText);
            t.setText("نوشتن روی تگ با موفقیت انجام شد، می توانید از برنامه خارج شوید و تگ را امتحان کنید");


        } else {
            // Handle intent

        }
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }



    }
