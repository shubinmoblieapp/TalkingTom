package com.example.talkingtom.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.talkingtom.MainActivity;
import com.example.talkingtom.MyApplication;
import com.example.talkingtom.R;
import com.example.talkingtom.helpers.MediaPlayerCustom;
import com.example.talkingtom.helpers.Mp3Helper;
import com.example.talkingtom.interfaces.IUpdateMediaPlayerUI;

public class QueueListViewAdapter extends ArrayAdapter{
	private Context mContext;
	private int mPosition;
	private List<Mp3Helper> mSongList;
	private MediaPlayerCustom mMediaPlayer;
	private TextView titleTextView;
	public static IUpdateMediaPlayerUI updateView = null;
	
	public QueueListViewAdapter(Context context, int resource, List<Mp3Helper> mp3List) {
		super(context, resource, mp3List);
		mContext = context;
		mSongList = new ArrayList<Mp3Helper>();
		mSongList = mp3List;
		mMediaPlayer = ((MyApplication)((MainActivity)context).getApplicationContext()).mediaPlayer;
		mMediaPlayer.setNewSongList(mSongList);
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
		view = inflater.inflate(R.layout.queue_listview_layout, null);
		
		final int pos = position;
		
		TextView songNameTextView = (TextView) view.findViewById(R.id.song_name_textview);
		
		songNameTextView.setText(mMediaPlayer.getCurrentPlaylist().get(pos).getTitle());
		
		songNameTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d("CheckForNull", String.valueOf("ListView CLICK!"));
				mMediaPlayer.startSong(mSongList.get(pos).getFilePath());
				updateView(pos);
			}
		});
		
		return view;
	}
	
	private void updateView(int position){
		String title = mSongList.get(position).getAuthor() + " - " + mSongList.get(position).getTitle();
		updateView.updateTitle(title);
	}
}
