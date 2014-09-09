package com.example.talkingtom.adapters;

import java.util.ArrayList;
import java.util.List;

import com.example.talkingtom.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class PlaylistListviewAdapter extends ArrayAdapter{
	
	private Context mContext;
	private List<String> mPlaylistList;
	
	public PlaylistListviewAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		mContext = context;
		mPlaylistList = new ArrayList<String>();
		mPlaylistList = objects;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public int getPosition(Object item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return super.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.listview_adapter_playlists, null);
		
		TextView textView = (TextView) view.findViewById(R.id.playlist_name_textview);
		
		textView.setText(mPlaylistList.get(position));
		
		
		return view;
	}
	

}
