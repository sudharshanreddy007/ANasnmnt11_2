package com.sudharshan.user.anasnmnt11_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    //An editable text view that shows completion suggestions automatically while the user is typing
    TextView textView;
    //A user interface element that displays text to the user.
    ArrayList<String> productname;
    //ArrayList is a part of collection framework and It provides us dynamic arrays in Java.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
// call the super class onCreate to complete the creation of activity like the view hierarchy
        setContentView(R.layout.activity_main);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        textView=(TextView)findViewById(R.id.textView);
        //created an object and instantiated it
        productname=new ArrayList<>();
        //creating new array list
        productname.add("Hp Inkjet Printer");
        //adding the values to the array list
        productname.add("Android");
        productname.add("IOS");
        DBhelper dbhelper=new  DBhelper(this);
        //create the database object
        //this database helper will help us to maintain the database and helper will take the values from the main activity
        for(int i=0;i<productname.size();i++)
        {
            dbhelper.Addproducts(productname.get(i));//here we will get the products and add to the database
        }
        ArrayList arrayList=dbhelper.Databasetoarray();
        //creating an arraylist object and converting the database to array using myhelper

        //created an objects
        //Finds a view that was identified by the android:id XML attribute that was processed in onCreate(Bundle).
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        autoCompleteTextView.setThreshold(1);
        //Specifies the minimum number of characters the user has to type in the edit box before the drop down list is shown.
        //When threshold is less than or equals 0, a threshold of 1 is applied.
        autoCompleteTextView.setAdapter(arrayAdapter);
        //Sets the adapter that provides the data and the views to represent the data in this widget
    }

}