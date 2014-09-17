package com.example.talkingtom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends FragmentActivity{
	private SlidingUpPanelLayout slidingUpPane;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		slidingUpPane = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
		slidingUpPane.setEnableDragViewTouchEvents(true);
		slidingUpPane.setDragView(null);
	}
}
