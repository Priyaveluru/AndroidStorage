package com.smartapps.android_storage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by priya on 3/31/2017.
 */

public class searchProduct extends Activity {
    EditText e1;
    EditText e2;
    Button b1;
    Button close;
    String keyword;
    String[] array;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        b1 = (Button) findViewById(R.id.button);
        close=(Button)findViewById(R.id.button2);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void searchItem(View view) {
        e1 = (EditText) findViewById(R.id.editText5);
        keyword = e1.getText().toString();
        String result = "";
        Context context = getApplicationContext();
        Db_handler controller = new Db_handler(getApplicationContext());
        if (!keyword.isEmpty()) {
            String s = controller.search(keyword);
            if (s != null && !s.isEmpty()) {
                array = s.split(Pattern.quote(","));
                e2 = (EditText) findViewById(R.id.editText6);
                result = String.valueOf("Search Results: \n\nItem name:" + String.valueOf(array[0]) + "\n" +
                        "Item Price:" + String.valueOf(array[1]) + "\n" + "ItemReview:" + String.valueOf(array[2]) + "\n" + "ItemDescription:"
                        + String.valueOf(array[3]) + "\n");
                e2.setText(result);
                controller.close();
            }
            else{
                Toast.makeText(context,
                        "No Results found", Toast.LENGTH_LONG).show();
            }

        }

    }
}
