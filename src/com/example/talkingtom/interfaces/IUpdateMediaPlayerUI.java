package com.example.talkingtom.interfaces;

import java.util.List;

import com.example.talkingtom.helpers.Mp3Helper;

public interface IUpdateMediaPlayerUI {
	
	void updateView(String title, int position, List<Mp3Helper> songList);
	void updateTitle(String title);
}
