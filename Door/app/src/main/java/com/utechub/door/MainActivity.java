package com.utechub.door;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt1;
    EditText edt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=(EditText) findViewById(R.id.editText2);
        edt3=(EditText)findViewById(R.id.editText3);
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s=edt1.getText().toString();

                String a[]=new String[4];
                a[0]=""+s.charAt(0)+s.charAt(1);
                a[1]=""+s.charAt(2)+s.charAt(3);
                a[2]=""+s.charAt(4)+s.charAt(5);
                a[3]=""+s.charAt(6)+s.charAt(7);


                int arr[]=new int[4];
                for(int i=0;i<4;i++){
                    arr[i]=Integer.parseInt(a[i]);

                }
                char[] key = {'K', 'C', 'Q'}; //Can be any chars, and any length array
                StringBuilder output = new StringBuilder();

                for(int i = 0; i < 4; i++) {
                    output.append( (arr[i] ^ key[i % key.length]));
                }
                String res=output.toString();
                edt3.setText(res);

            }
        });
    }
}



