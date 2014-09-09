package com.example.talkingtom;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.talkingtom.adapters.PlaylistListviewAdapter;
import com.example.talkingtom.helpers.Mp3Helper;
import com.example.talkingtom.helpers.PlaylistOptions;
import com.example.talkingtom.utils.Utils;

public class AvailablePlaylistActivity extends Activity{
	
	private ListView mListView;
	private List<Mp3Helper> mMp3List;
	private PlaylistListviewAdapter mAdapter;
	private PlaylistOptions mPlaylistOptions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.available_playlists_activity);
		initializeVariables();
		
		mAdapter = new PlaylistListviewAdapter(this, android.R.layout.simple_list_item_1, mPlaylistOptions.getListOfPlaylists());
		
		mListView.setAdapter(mAdapter);
		
		
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		
		mMp3List.add((Mp3Helper) bundle.getSerializable(Utils.PASS_SONG_TO_PLAYLIST_KET));

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				
					mPlaylistOptions.addSongToPlaylist(mPlaylistOptions.getListOfPlaylists().get(position), mMp3List);
					finish();
			}
		});
		
	}
	
	private void initializeVariables(){
		mListView = (ListView) findViewById(R.id.playlist_listview);
		mPlaylistOptions = new PlaylistOptions(getContentResolver());
		mMp3List = new ArrayList<Mp3Helper>();
	}
	
}
