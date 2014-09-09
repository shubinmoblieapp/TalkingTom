package com.example.talkingtom;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v4.widget.SlidingPaneLayout.PanelSlideListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.talkingtom.adapters.CollectionPagerAddapter;

public class MainActivity extends FragmentActivity{

	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private SlidingPaneLayout mDLayout;
	private ImageButton mOpendDrawerButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		initializeVariables();
		
	}
	
	private void initializeVariables(){
//		mPager = (ViewPager) findViewById(R.id.viewpager);
//		mPagerAdapter = new CollectionPagerAddapter(getSupportFragmentManager());
//		mDLayout = (SlidingPaneLayout) findViewById(R.id.drawer_layout);
//		mOpendDrawerButton = (ImageButton) findViewById(R.id.drawer_layout_button);
	}
}
