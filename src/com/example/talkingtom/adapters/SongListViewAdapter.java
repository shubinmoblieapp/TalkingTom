package com.example.talkingtom.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.talkingtom.MainActivity;
import com.example.talkingtom.MyApplication;
import com.example.talkingtom.R;
import com.example.talkingtom.dialogs.SongListDetailsDialog;
import com.example.talkingtom.helpers.MediaPlayerCustom;
import com.example.talkingtom.helpers.Mp3Helper;
import com.example.talkingtom.interfaces.IUpdateMediaPlayerUI;

public class SongListViewAdapter extends ArrayAdapter{
	
	private Context mContext;
	private int mPosition;
	private FragmentManager frManager;
	private List<Mp3Helper> mSongList;
	private MediaPlayerCustom mMediaPlayer;
	public static IUpdateMediaPlayerUI updateView = null;
	
	public SongListViewAdapter(Context context, int resource, List<Mp3Helper> mp3List, FragmentManager fm) {
		super(context, resource, mp3List);
		mContext = context;
		mSongList = new ArrayList<Mp3Helper>();
		mSongList = mp3List;
		frManager = fm;
		mMediaPlayer = ((MyApplication)((MainActivity)context).getApplicationContext()).mediaPlayer;
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
		songNameTextView.setText(mSongList.get(position).getTitle());
		
		ImageButton songDetailsButton = (ImageButton) view.findViewById(R.id.details_button);
		
		songNameTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d("CheckForNull", String.valueOf("ListView CLICK!"));
				mMediaPlayer.setNewSongList(mSongList);
				mMediaPlayer.startSong(mSongList.get(pos).getFilePath());
				updateMediaPlayerView(pos);
			}
		});
		
		songDetailsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SongListDetailsDialog dialog = new SongListDetailsDialog();
				
				Bundle bundle = new Bundle();
				bundle.putSerializable(dialog.SONG_DETAILS_KEY, mSongList.get(pos));
				dialog.setArguments(bundle);
				dialog.show(frManager,mSongList.get(pos).getTitle());
				
			}
		});
		
		return view;
	}
	
	
	private void updateMediaPlayerView(int position){
		Log.d("Test View", String.valueOf(position));
		String title = mSongList.get(position).getAuthor() + " - " + mSongList.get(position).getTitle();
		updateView.updateView(title, position, mSongList);
	}
}
