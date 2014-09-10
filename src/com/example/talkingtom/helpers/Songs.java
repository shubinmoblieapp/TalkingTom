package com.example.talkingtom.helpers;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;

public class Songs {

	private FragmentActivity mFragActivity;
	
	public Songs(FragmentActivity activity){
		mFragActivity = activity;
	}
	
	public List<Mp3Helper> getAllSongs(){
		
		Cursor cursor;
		List<Mp3Helper> listOfSongs = new ArrayList<Mp3Helper>();
		
		String[] mEverything = {"*"};
		Uri allSongsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";
		
		ContentResolver resolver = mFragActivity.getContentResolver();
		
		cursor = resolver.query(allSongsUri, mEverything, selection, null, null);
		
		if (cursor != null){
			if(cursor.moveToFirst()){
				do{
					listOfSongs.add(setMp3(cursor));
				}while (cursor.moveToNext());
			}
		}
		
		return listOfSongs;
	}
	
	public List<String> getAllAlbums(){
		
		Cursor cursor;
		List<String> listOfSongs = new ArrayList<String>();
		
		String[] selectAlbumNames = {"DISTINCT " + MediaStore.Audio.Albums.ALBUM};
		Uri allSongsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";
		
		ContentResolver resolver = mFragActivity.getContentResolver();
		
		cursor = resolver.query(allSongsUri, selectAlbumNames, selection, null, null);
		
		if (cursor != null){
			if(cursor.moveToFirst()){
				do{
					listOfSongs.add(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM)));
				}while (cursor.moveToNext());
			}
		}
		
		return listOfSongs;
	}
	
	public List<String> getAllArtists(){
		
		Cursor cursor;
		List<String> listOfArtists = new ArrayList<String>();
		
		String[] selectAlbumNames = {"DISTINCT " + MediaStore.Audio.Artists.ARTIST};
		Uri allSongsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";
		
		ContentResolver resolver = mFragActivity.getContentResolver();
		
		cursor = resolver.query(allSongsUri, selectAlbumNames, selection, null, null);
		
		if (cursor != null){
			if(cursor.moveToFirst()){
				do{
					listOfArtists.add(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST)));
				}while (cursor.moveToNext());
			}
		}
		
		return listOfArtists;
	}
	

	public List<String> getAllGenres(){
		
		Cursor cursor;
		List<String> listOfGenres = new ArrayList<String>();
		
		String[] selectGenres = {MediaStore.Audio.Genres.NAME};
		Uri allSongsUri = MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI;
		String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";
		
		ContentResolver resolver = mFragActivity.getContentResolver();
		
		cursor = resolver.query(allSongsUri, selectGenres, null, null, null);
		
		if (cursor != null){
			if(cursor.moveToFirst()){
				do{
					listOfGenres.add(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Genres.NAME)));
				}while (cursor.moveToNext());
			}
		}
		
		return listOfGenres;
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
	
}
