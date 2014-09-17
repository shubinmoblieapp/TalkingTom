package com.example.talkingtom.helpers;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.talkingtom.R;
import com.example.talkingtom.adapters.QueueListViewAdapter;

public class LayoutControllerMediaPlayer {
	
	private List<Mp3Helper> mSongList;
	private View mView;
	private TextView mSongTitleTextView;
	private int mPosition;
	private ListView mQueueListView;
	
	public LayoutControllerMediaPlayer(View view, List<Mp3Helper> songsList, int position){
		mSongList = songsList;
		mView = view;
		mPosition = position;
		initializeVariables();
	}
	
	public void changeSongTitle(Context context){
		mSongTitleTextView.setText(mSongList.get(mPosition).getAuthor() + " - " + mSongList.get(mPosition).getTitle());
	}
	
	public void updateQueue(Context context){
		mQueueListView.setAdapter(new QueueListViewAdapter(context, android.R.layout.simple_list_item_1, mSongList));
	}
	
	private void initializeVariables(){
		mSongTitleTextView = (TextView) mView.findViewById(R.id.song_title_textview);
		mQueueListView = (ListView) mView.findViewById(R.id.queue_lsitview);
	}
}
