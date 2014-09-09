package com.example.talkingtom.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.talkingtom.AvailablePlaylistActivity;
import com.example.talkingtom.R;
import com.example.talkingtom.helpers.Mp3Helper;
import com.example.talkingtom.utils.Utils;

public class SongListDetailsDialog extends DialogFragment {

	public static final String SONG_DETAILS_KEY = "songDetails";
	private Bundle mBundle;
	private Intent mIntent;
	
	static SongListDetailsDialog newInstance(Mp3Helper mp3) {
		
		SongListDetailsDialog songsDialog = new SongListDetailsDialog();
		Bundle args = new Bundle();
		
		args.putSerializable(SONG_DETAILS_KEY, mp3);
		
		songsDialog.setArguments(args);
		
		return songsDialog;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		mBundle = new Bundle();
		mBundle.putSerializable(Utils.PASS_SONG_TO_PLAYLIST_KET, getArguments().getSerializable(SONG_DETAILS_KEY));
		
		
		Mp3Helper mp3 = new Mp3Helper();
		mp3 = (Mp3Helper) getArguments().getSerializable(SONG_DETAILS_KEY);
		
		builder.setTitle(mp3.getTitle()).setItems(R.array.song_details_dialog, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				userChoice(which);
			}
		});
		
		return builder.create();
	}
	
	private void userChoice(int choice){
		switch (choice) {
		case 0:
			break;
		case 1:
			mIntent = new Intent(getActivity(), AvailablePlaylistActivity.class);
			mIntent.putExtras(mBundle);
			startActivity(mIntent);
			break;
		case 2:
			break;
		case 3:
			break;
			
		default:
			break;
		}
	}
	
	
}
