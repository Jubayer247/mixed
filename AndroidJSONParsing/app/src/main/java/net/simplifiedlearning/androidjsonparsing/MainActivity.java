package net.simplifiedlearning.androidjsonparsing;

import android.os.AsyncTask;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private TextToSpeech mTTS;
    private Handler handler;
    int i=0;
    String[] data=new String[10];
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ////////////////////////////

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        //mTTS.setPitch(0.7f);
                        mTTS.setSpeechRate(0.7f);
                       // mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });


        /////////////////////////

        listView = (ListView) findViewById(R.id.listView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getJSON("http://notify247.epizy.com/json_test.php");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        /*
        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String text=listView.getItemAtPosition(1).toString();
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    });

*/

       /* mTTS=new TextToSpeech(

                getApplicationContext(), new TextToSpeech.OnInitListener() {

            @Override

            public void onInit(int status) {

                if(status != TextToSpeech.ERROR) {

                    mTTS.setLanguage(Locale.UK);

                }

            }

        });*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                mTTS.speak("Text which you want to read", TextToSpeech.QUEUE_FLUSH, null);
                int j=0;
                while (true) {
                String text=data[j];

                if(text==null){
                    text="Processing";
                }
                mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    j=(j+1)%data.length;
                    try {
                       Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();



    }


    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();


                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ");
                    con.setRequestProperty("Content-Language", "en-US");
                    con.setRequestProperty("Cookie", "__test=b7e810df64c013c6a3f271236a040413; expires=Fri, 01-Jan-38 5:55:55 GMT; path=/");
                    con.setUseCaches(false);
                    con.setDoInput(true);
                    con.setDoOutput(true);


                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        final String[] line = new String[jsonArray.length()];
        for ( i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            line[i] = "Bus name "+obj.getString("name")+" has reached the station "+obj.getString("station_id" )+ " from "+obj.getString("vfrom" ) +" will depart for "+obj.getString("vto" );

        }
        data=new String[line.length];
        System.arraycopy(line,0,data,0,line.length);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, line);
        listView.setAdapter(arrayAdapter);

    }







    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }





    class ThreadDemo extends Thread {
        private Thread t;
        private String threadName;

        ThreadDemo( String name) {
            threadName = name;
            System.out.println("Creating " +  threadName );
        }

        public void run() {
            System.out.println("Running " +  threadName );
            try {
                    for(String s:data) {
                        mTTS.speak(s, TextToSpeech.QUEUE_FLUSH, null);


                        Thread.sleep(10000);
                    }



            } catch (InterruptedException e) {
                System.out.println("Thread " +  threadName + " interrupted.");
            }
            System.out.println("Thread " +  threadName + " exiting.");
        }

        public void start () {
            System.out.println("Starting " +  threadName );
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
    }
}

