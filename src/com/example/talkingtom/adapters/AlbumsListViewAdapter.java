package com.example.talkingtom.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.talkingtom.R;
import com.example.talkingtom.dialogs.SongListDetailsDialog;
import com.example.talkingtom.helpers.Mp3Helper;

public class AlbumsListViewAdapter extends ArrayAdapter{
	private int mResource;
	private Context mContext;
	private int mPosition;
	private FragmentManager frManager;
	private ImageButton mDetailsButton;
	private List<String> mSongList;
	
	public AlbumsListViewAdapter(Context context, int resource, List<String> objects, FragmentManager fm) {
		super(context, resource, objects);
		mContext = context;
		mSongList = new ArrayList<String>();
		mSongList = objects;
		frManager = fm;
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
		View view = convertView;
		
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.listview_adapter_layout, null);
		
		final int pos = position;
		
		TextView songNameTextView = (TextView) view.findViewById(R.id.song_name_TextView);
		songNameTextView.setText(mSongList.get(position));
		
		ImageButton songDetailsButton = (ImageButton) view.findViewById(R.id.details_button);
		
		songDetailsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SongListDetailsDialog dialog = new SongListDetailsDialog();
				
				Bundle bundle = new Bundle();
				bundle.putSerializable(dialog.SONG_DETAILS_KEY, mSongList.get(pos));
				dialog.setArguments(bundle);
				dialog.show(frManager,mSongList.get(pos));
				
			}
		});
		
		Log.d("CheckForNull", mSongList.get(position));
		
		return view;
	}

}
