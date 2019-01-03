/*
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    private TextToSpeech mTTS;
    private Handler handler;
    int i=0;
    String[] data=new String[10];
    private Button btn;
    ArrayList<Integer> ids=new ArrayList<Integer>();
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

        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                mTTS.speak("Text which you want to read", TextToSpeech.QUEUE_FLUSH, null);
                int j=0;
                String text=null;
                while (true) {
                    try {
                        text=data[j];
                    }
                catch (ArrayIndexOutOfBoundsException e){
                        text="No bus yet! PLease wait!";
                }

                if(text==null){
                    text="Processing";
                }
                boolean play=false;
                try {
                   play=ids.get(j)!=null;
                }
                catch (IndexOutOfBoundsException e){

                }

                if(play){

                    mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    ids.set(j,null);

                }
                try {
                    j=(j+1)%data.length;
                }
                catch (ArithmeticException e){

                }

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
                    con.setRequestProperty("Cookie", "__test=f97add793073836837f7e68f367ddf47; expires=Fri, 01-Jan-38 5:55:55 GMT; path=/");
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
            try {
                ids.set(i,i);
            }
            catch (IndexOutOfBoundsException e){
                ids.add(i);
            }
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

*/
package net.simplifiedlearning.androidjsonparsing;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {
    static int mtsStatus = 0;
    String connected = null;
    String[] data;
    private Handler handler;
    /* renamed from: i */
    int i = 0;
    ArrayList<Integer> ids = new ArrayList();
    ListView listView;
    private TextToSpeech mTTS;
    MemorizingTrustManager mtm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////////////////
        JULHandler.initialize();
//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        //////////////////////////

        this.data = new String[10];
        this.listView = (ListView) findViewById(R.id.listView);

        //////////////////////////////////////////////////////////////////////////////////////
        try {
            // set location of the keystore
            MemorizingTrustManager.setKeyStoreFile("private", "sslkeys.bks");

            // register MemorizingTrustManager for HTTPS
            SSLContext sc = SSLContext.getInstance("TLS");
            mtm = new MemorizingTrustManager(this);
            sc.init(null, new X509TrustManager[] { mtm },
                    new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(
                    mtm.wrapHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier()));

            // disable redirects to reduce possible confusion
            HttpsURLConnection.setFollowRedirects(false);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ///////////////////////////////////////////////////////////////////////////////////////









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

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    MainActivity.this.getJSON("https://notify247.000webhostapp.com/json_test.php");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.mTTS.speak("application is starting please wait !", 0, null);
                int j = 0;
                String text = null;
                while (true) {
                    try {
                        text = MainActivity.this.data[j];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (MainActivity.mtsStatus == 0) {
                            MainActivity.mtsStatus = 1;
                        }
                    }
                    if (MainActivity.this.connected != null) {
                        text = MainActivity.this.connected;
                    } else if (text == null) {
                        text = "No bus yet , Please wait ";
                    }
                    try {
                        if (MainActivity.this.ids.size() == 0) {
                            MainActivity.this.mTTS.speak(text, 0, null);
                        } else if (MainActivity.this.ids.get(j) != null) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(MainActivity.this.ids.get(j));
                            stringBuilder.append(" ======================>>>>>>");
                            Log.i("tts", stringBuilder.toString());
                            synchronized (MainActivity.this.mTTS) {
                                MainActivity.this.mTTS.speak(text, 0, null);
                                MainActivity.this.ids.set(j, null);
                                j++;
                            }
                        } else if (MainActivity.this.ids.get(MainActivity.this.ids.size() - 1) == null) {
                            MainActivity.this.mTTS.speak(text, 0, null);
                        }
                    } catch (IndexOutOfBoundsException e2) {
                        text = "No bus yet , Please wait ";
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                }

            }
        }).start();
    }


    private void getJSON(final String urlWebService) {
        new AsyncTask<Void, Void, String>() {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    MainActivity.this.loadIntoListView(s);
                    MainActivity.this.connected = null;
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                    MainActivity.this.connected = "Please enable internet connection";
                }
            }


            protected String doInBackground(Void... voids) {
                try {
                    HttpsURLConnection urlConnection;


                        URL u = new URL(urlWebService);
                         urlConnection = (HttpsURLConnection)u.openConnection();
                        urlConnection.connect();
                    InputStream in = urlConnection.getInputStream();
                    //copyInputStreamToOutputStream(in, System.out);
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));



                    //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        String json = readLine;
                        if (readLine == null) {
                            return sb.toString().trim();
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(json);
                        stringBuilder.append("\n");
                        sb.append(stringBuilder.toString());
                    }
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute(new Void[0]);
    }

    public void onManage(View view) {
        final ArrayList<String> aliases = Collections.list(mtm.getCertificates());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, aliases);
        new AlertDialog.Builder(this).setTitle("Tap Certificate to Delete")
                .setNegativeButton(android.R.string.cancel, null)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            String alias = aliases.get(which);
                            mtm.deleteCertificate(alias);
                           // setText("Deleted " + alias, false);
                        } catch (KeyStoreException e) {
                            e.printStackTrace();
                           // setText("Error: " + e.getLocalizedMessage(), false);
                        }
                    }
                })
                .create().show();
    }
    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] line = new String[jsonArray.length()];
        i = 0;
        while (i < jsonArray.length()) {
            JSONObject obj = jsonArray.getJSONObject(i);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Welcome station ");
            stringBuilder.append(obj.getString("station_name"));
            stringBuilder.append(" Bus name ");
            stringBuilder.append(obj.getString("name"));
            stringBuilder.append(" has reached the station ");
            stringBuilder.append(" from ");
            stringBuilder.append(obj.getString("vfrom"));
            stringBuilder.append(" will depart for ");
            stringBuilder.append(obj.getString("vto"));
            line[i] = stringBuilder.toString();
            synchronized (this.ids) {
                try {
                    if (this.ids.size() == 0) {
                        this.ids.add(Integer.valueOf(0));
                    }
                    if (this.ids.get(i) != null) {
                        this.ids.set(i, Integer.valueOf(i));
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(this.ids.get(i));
                        stringBuilder2.append(" ======================>>>>>>");
                        Log.i("i", stringBuilder2.toString());
                    }
                } catch (IndexOutOfBoundsException e) {
                    this.ids.add(Integer.valueOf(i));
                }
            }
            i++;
        }
        this.data = new String[line.length];
        System.arraycopy(line, 0, this.data, 0, line.length);
        this.listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, line));
    }

    protected void onDestroy() {
        if (this.mTTS != null) {
            this.mTTS.stop();
            this.mTTS.shutdown();
        }
        super.onDestroy();
    }
}
