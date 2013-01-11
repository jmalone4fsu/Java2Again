package com.akapapaj.java2_week1;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.akapapaj.java2_week1.mylib.Product;

/*
 *  Author: Joseph Malone akaPapaJ
 *  Class:  Java 2 - Week 1
 *  Instructor: Josh Donlan
 *  Date: November 22, 2012
 */

public class MainActivity extends Activity {
	TextView detailsView;
	private ArrayAdapter<String> listAdapter;
	private ListView mainListView;
	String shortDesc;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.view.View truckLL = findViewById(R.id.truck);
        
        //Find the ListView resource
        mainListView = (ListView) findViewById(R.id.mainListView); 
        
        Product p1 = new Product(getString(R.string.p1_sku), getString(R.string.p1_name), getString(R.string.p1_desc), getString(R.string.p1_price), getString(R.string.p1_link));
        Product p2 = new Product(getString(R.string.p2_sku), getString(R.string.p2_name), getString(R.string.p2_desc), getString(R.string.p2_price), getString(R.string.p2_link));
        Product p3 = new Product(getString(R.string.p3_sku), getString(R.string.p3_name), getString(R.string.p3_desc), getString(R.string.p3_price), getString(R.string.p3_link));
        Product p4 = new Product(getString(R.string.p4_sku), getString(R.string.p4_name), getString(R.string.p4_desc), getString(R.string.p4_price), getString(R.string.p4_link));
        Product p5 = new Product(getString(R.string.p5_sku), getString(R.string.p5_name), getString(R.string.p5_desc), getString(R.string.p5_price), getString(R.string.p5_link));

        final Product[] allProducts = {
        		p1, p2, p3, p4, p5
        };
        String[] ProNames = {
        		p1.prod_name, p2.prod_name, p3.prod_name, p4.prod_name, p5.prod_name
        		
        };
        //Log.i("Product Sku", productSku[0]);
        for(int i=0; i<ProNames.length; i++){
        	Log.i("List Items", ProNames[i]+"\n");
        }
        
        ArrayList<String> productList = new ArrayList<String>();
        productList.addAll( Arrays.asList(ProNames) );
        
        //Create ArrayAdapter using product list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, productList);
        
        detailsView = (TextView) findViewById(R.id.detailsView);
        
        mainListView.setAdapter(listAdapter);
        //set onlick listener for the listAdapter
        mainListView.setOnItemClickListener(new OnItemClickListener() {
        	
			@Override
        	public void onItemClick(AdapterView<?> arg0,
        			View theListAdapter, int arg2, long arg3){
        		TextView slick = (TextView) theListAdapter;
        		String transName = ("LOADING..." + slick.getText().toString() + "\n");
        		
        		detailsView.setText("");
        		detailsView.append(transName);
        		
        		Log.i("Product Name: ", slick.getText().toString());
        		//Find what product was selected by user
        		String prodSelected = slick.getText().toString();
        		Log.i("Product Selected", prodSelected);
        		for(int i=0; i<allProducts.length; i++){
        			
        			//setup detailsView to display product details
        			//This information will be loaded from the BestBuy api
        			Product findProduct = allProducts[i];
        			if (prodSelected == findProduct.prod_name) {
        				String sku = ("SKU : " + findProduct.prod_sku + "\n");
        				String name = ("Name : " + findProduct.prod_name + "\n");
        				shortDesc = ("Desc : " + findProduct.prod_desc +"\n");
        				//Spanned sp= Html.fromHtml(findProduct.prod_link);
        				String mylink = findProduct.prod_link;
        				String price = ("Price : " + findProduct.prod_price +"\n");
        				String[] prod_details = {sku, name, shortDesc, price, mylink};
        				//detailsView.setMovementMethod(LinkMovementMethod.getInstance());
        				//detailsView.setText(sp);
        				
        				//Launching a new activity
        				Intent intent = new Intent(getApplicationContext(), DetailView.class);
        				
        				//send info to new activity
        				intent.putExtra("product", prod_details);
        				startActivity(intent);
						/*
        				for(int k=0; k<5; k++){
        					String mydet = prod_details[k];
        					
        					detailsView.append(mydet);
        					Log.i("Product Details", prod_details[k]+"\n");
        					
        				}*/
        				
        			}
        		}
        	}
        });
        
        // setup Linear Layout from FormStuff

        LinearLayout ll = new LinearLayout(this);
        ((LinearLayout) truckLL).addView(ll);

        
        ListView simpleList = new ListView(this);
        simpleList.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
        //setContentView(ll);
 
    }
   
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
