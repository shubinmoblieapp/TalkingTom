package com.example.talkingtom;

import java.util.ArrayList;
import java.util.List;

import com.example.talkingtom.adapters.SongListViewAdapter;
import com.example.talkingtom.helpers.Mp3Helper;
import com.example.talkingtom.helpers.PlaylistOptions;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;

public class AllSongsFragment extends Fragment{

	private ListView songListView;
	private List<Mp3Helper> mListOfMp3;
	private Mp3Helper mMp3;
	private List<Mp3Helper> mListOfCheckedMp3;
	private EditText mPlaylistNameEditText;
	private Context mContext;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.create_playlist_activity, container, false);
		view = rootView;
		
		initializeVariables();
		listAllSongs();
		
		SongListViewAdapter arrayAdapter = new SongListViewAdapter(getActivity(), android.R.layout.simple_list_item_1, mListOfMp3, getActivity().getFragmentManager());
		
		if(arrayAdapter != null && songListView != null){
			songListView.setAdapter(arrayAdapter);
		}
		
		
		return view;
	}
	
	
	private void listAllSongs(){
		Cursor cursor;
		
		String[] mEverything = {"*"};
		Uri allSongsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";
		
		ContentResolver resolver = getActivity().getContentResolver();
		
		cursor = resolver.query(allSongsUri, mEverything, selection, null, null);
		
		if (cursor != null){
			if(cursor.moveToFirst()){
				do{
					mListOfMp3.add(setMp3(cursor));
				}while (cursor.moveToNext());
			}
		}
	}

	private void initializeVariables(){
		songListView = (ListView) view.findViewById(R.id.song_container_listView);
		
		mContext = getActivity();
		
		mListOfMp3 = new ArrayList<Mp3Helper>();
		mListOfCheckedMp3 = new ArrayList<Mp3Helper>();
		
	}
	
	private Mp3Helper setMp3(Cursor cursor){
		Mp3Helper mp3 = new Mp3Helper();
		
		mp3.setId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
		mp3.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
		mp3.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
		mp3.setAlbum(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
		mp3.setDuration(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
		mp3.setFilePath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
		
		return mp3;
		
	}
	
	private String getPlaylistName(){
		String playlistName = "";
		
		if(!mPlaylistNameEditText.getText().toString().isEmpty() && mPlaylistNameEditText != null){
		    playlistName = mPlaylistNameEditText.getText().toString();
		}
		
		return playlistName;
	}
	
	
	private void addSongToPlaylist(){
		
		String testPlaylistName = getPlaylistName();
		
		PlaylistOptions mediaPlayer = new PlaylistOptions(getActivity().getContentResolver());
		
		mediaPlayer.createPlaylist(testPlaylistName);
		
		mediaPlayer.addSongToPlaylist(testPlaylistName, mListOfCheckedMp3); 
	}

}
