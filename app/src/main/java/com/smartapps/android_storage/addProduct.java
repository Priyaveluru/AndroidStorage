package com.smartapps.android_storage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by priya on 3/31/2017.
 */

public class addProduct extends Activity{
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    Button b2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        b2=(Button) findViewById(R.id.Cancel);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
            public void add(View v){
                e1 = (EditText) findViewById(R.id.editText);
                String ItemName = e1.getText().toString();
                e2 = (EditText) findViewById(R.id.editText2);
                String ItemPrice = e2.getText().toString();
                e3 = (EditText) findViewById(R.id.editText3);
                String ItemReview = e3.getText().toString();
                e4 = (EditText) findViewById(R.id.editText4);
                String ItemDesc = e4.getText().toString();
                Context context = getApplicationContext();
                Db_handler controller = new Db_handler(context);
                if(!ItemName.isEmpty()) {

                    try {
                        Toast.makeText(addProduct.this,
                                "Add Button Clicked", Toast.LENGTH_LONG).show();
                        controller.insert(ItemName, ItemPrice, ItemReview, ItemDesc);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    controller.close();
                    finish();
                }
                else {
                    controller.close();
                }

            }
            }



