package org.collegelabs.bitmaploader.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Pair;

public abstract class DemoUtils {

	public static ArrayList<Pair<String, String>> getSampleData() throws JSONException, IOException{
		
		//Pull images from imgur. Use the thumbnails
		
		final String mUrl = "http://imgur.com/gallery/hot.json";
		URL url = new URL(mUrl);
		JSONObject object = new JSONObject(convertStreamToString(url.openStream()));
		JSONArray locations = object.getJSONArray("gallery");
		
		ArrayList<Pair<String, String>> objects = new ArrayList<Pair<String, String>>();
    	for(int i=0; i < locations.length(); i++){
    		JSONObject item = locations.getJSONObject(i);
    		objects.add(new Pair<String,String>(
    				"http://i.imgur.com/"+item.get("hash")+"b"+item.get("ext") ,
    				(String) item.get("title")));        	
        }
    	
    	return objects;
	}
	
	//Stupid Scanner Tricks http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
	public static String convertStreamToString(InputStream is) { 
	    return new Scanner(is).useDelimiter("\\A").next();
	}
}
