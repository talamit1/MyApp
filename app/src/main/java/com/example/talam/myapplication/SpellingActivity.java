package com.example.talam.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;

import java.util.Locale;

/**
 * Created by talam on 01/06/2017.
 */

public class SpellingActivity extends Activity implements AdapterView.OnItemClickListener ,View.OnClickListener,AdapterView.OnItemLongClickListener {
    ListView list;
    private static int TTS_DATA_CHECK=1;
    private EditText input;
    private Button check;

    private  Boolean firstCreat=true;

    private TextToSpeech tts = null;  //thext to speech object for itemClickEvents
    private boolean ttsInit = false;

    String[] names = {
            "LION",
            "COW",
            "BEAR",
            "PANDA",
            "TIGER",
            "ZEBRA"
     };

        Integer[] imageId ={
            R.drawable.lion,
            R.drawable.cow,
            R.drawable.bear,
            R.drawable.panda,
            R.drawable.tiger,
            R.drawable.zebra
      };

    protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
        setContentView(R.layout.spelling_activity);

        //this hide keyboard on start
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        input=(EditText)findViewById(R.id.editText);
        check=(Button)findViewById(R.id.checkButton);

        CustomList adapter = new CustomList(SpellingActivity.this,names,imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        check.setOnClickListener(this);

    /*    //make names invisible
        if (firstCreat) {
            for (int i = 0; i < names.length; i++) {
                View vv=getViewByPosition(i,list);
                TextView txtTitle = (TextView) vv.findViewById(R.id.txt);
                ((View)txtTitle).setVisibility(View.INVISIBLE);
            }
            firstCreat=false;
        }*/

        initTextToSpeech();
        list.setOnItemClickListener(this);
        list.setOnItemLongClickListener(this);



    }


      /* tts methods*********************************/

  /* public void onListItemClick(ListView parent, View v, int position,long id) {
        speak(list.[position]);
    }*/

    private void initTextToSpeech() {
        Intent checkIntent=new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent,TTS_DATA_CHECK);
    }

    protected void onActivityResult(int requesedtCode, int resultCode, Intent data) {
        if (requesedtCode == TTS_DATA_CHECK) {
            if (resultCode != TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Intent installVoice = new Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installVoice);
            }
            configTextToSpeech();
        }
    }

    private void configTextToSpeech() {
        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    ttsInit=true;
                    if(tts.isLanguageAvailable(Locale.ENGLISH)>=0)
                        tts.setLanguage(Locale.ENGLISH);
                    tts.setPitch(1);
                    tts.setSpeechRate(1);
                }
            }
        });//end of anonymous class
    }

    private void speak(String inputString) {
        if (tts != null && ttsInit) {
            tts.speak(inputString, TextToSpeech.QUEUE_ADD, null);
        }
    }

            /* tts methods*********************************/

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        speak(names[+ position]);
    }

    @Override
    public void onClick(View v) {
        if (v == check) {
            String str = input.getText().toString();
            str=str.toUpperCase();
            for (int i = 0; i < names.length; i++) {
                if (str.equals(names[i])) {
                    input.setText("");
                    View vv=getViewByPosition(i,list);
                    TextView txtTitle = (TextView) vv.findViewById(R.id.txt);
                    txtTitle.setVisibility(View.VISIBLE);
                    return;
                }
            }

        }
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        TextView txtTitle = (TextView) view.findViewById(R.id.txt);
        txtTitle.setVisibility(View.INVISIBLE);
        return true;
    }


    public View getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition ) {
            return listView.getAdapter().getView(position, null, listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

}


