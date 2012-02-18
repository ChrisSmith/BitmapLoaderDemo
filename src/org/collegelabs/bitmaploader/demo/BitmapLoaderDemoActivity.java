package org.collegelabs.bitmaploader.demo;

import java.util.ArrayList;
import java.util.List;

import org.collegelabs.library.bitmaploader.BitmapLoader;
import org.collegelabs.library.bitmaploader.R;
import org.collegelabs.library.bitmaploader.views.AsyncImageView;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BitmapLoaderDemoActivity extends ListActivity {
    
	private BitmapLoader mBitmapLoader;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	mBitmapLoader  = new BitmapLoader(this);
        final MyAdapter adapter = new MyAdapter(this, 0, new ArrayList<Pair<String, String>>(), mBitmapLoader);
        setListAdapter(adapter);

        new AsyncTask< Void, Void, ArrayList<Pair<String, String>>>() {
			@Override
			protected ArrayList<Pair<String, String>> doInBackground(Void... params) {
				try{
					return DemoUtils.getSampleData();
				}catch(Exception e){
					e.printStackTrace();
					return null;
				}
			}
			
			@Override
			protected void onPostExecute(ArrayList<Pair<String, String>> list){
				if(list != null){
					for(Pair<String,String> pair : list){
						adapter.add(pair);
					}
					adapter.notifyDataSetChanged();
				}
			}
			
		}.execute();
    }
    
	@Override 
	public void onDestroy(){
		super.onDestroy();
		mBitmapLoader.shutdownNow();
	}
	
    private static class MyAdapter extends ArrayAdapter<Pair<String, String>>{

    	private BitmapLoader bitmapLoader;
    	
		public MyAdapter(Context context, int textViewResourceId, List<Pair<String, String>> objects, BitmapLoader bitmapLoader) {
			super(context, textViewResourceId, objects);
			this.bitmapLoader = bitmapLoader;
		}

		@Override
		public View getView (int position, View convertView, ViewGroup parent){
			if(convertView == null)
				convertView = newView();
			
			MyHolder holder = (MyHolder) convertView.getTag();
			Pair<String,String> pair = getItem(position);
			holder.mImageView.setImageUrl(pair.first, bitmapLoader);
			holder.mTextView.setText(pair.second);
			
			return convertView;
		}
		
		private View newView(){
			View v = LayoutInflater.from(getContext()).inflate(R.layout.list_row, null);
			MyHolder holder = new MyHolder();
			holder.mImageView = (AsyncImageView) v.findViewById(R.id.imageView1);
			holder.mTextView = (TextView) v.findViewById(R.id.textView1);
			v.setTag(holder);
			return v;
		}
		
    }
    
    
    private static class MyHolder{
    	AsyncImageView mImageView;
    	TextView mTextView;
    }
    
}