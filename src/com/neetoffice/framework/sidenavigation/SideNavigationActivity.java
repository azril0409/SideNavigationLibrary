package com.neetoffice.framework.sidenavigation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

import com.neetoffice.framework.sidenavigation.FlowView.ViewSwitchListener;

public class SideNavigationActivity extends Activity implements AnimationListener,ViewSwitchListener{
	private FlowView view;
	private int translate;
	private SideNavigationPage sideNavigationPage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(android.R.style.Theme_Translucent_NoTitleBar);
		super.onCreate(savedInstanceState);
		translate = getIntent().getIntExtra(SideNavigationPage.Translate, SideNavigationPage.Left);

		sideNavigationPage = SideNavigationPage.getEntity(this);
		sideNavigationPage.onCreate(this);
		
		SideNavigationAdapter adapter = new SideNavigationAdapter(this);
		view = new FlowView(this,translate);
		view.setAdapter(adapter);
		view.setOnViewSwitchListener(this);
		
		setContentView(view);
		TranslateAnimation animShow = null;
		switch(translate){
		case SideNavigationPage.Top:
			animShow = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, -1,
					Animation.RELATIVE_TO_SELF, 0);
			break;
		case SideNavigationPage.Bottom:
			animShow = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 1,
					Animation.RELATIVE_TO_SELF, 0);
			break;
		case SideNavigationPage.Right:
			animShow = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 1,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0);
			break;
		case SideNavigationPage.Left:
		default:
			animShow = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, -1,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0);
			break;
		}
		animShow.setDuration(300);
		animShow.setFillAfter(true);
		view.setAnimation(animShow);
	}

	@Override
	public void onBackPressed() {
		Log.d("SideNavigation", "onBackPressed");
		TranslateAnimation animShow = null;
		switch(translate){
		case SideNavigationPage.Top:
			animShow = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, -1,
					Animation.RELATIVE_TO_SELF, 0);
			break;
		case SideNavigationPage.Bottom:
			animShow = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 1,
					Animation.RELATIVE_TO_SELF, 0);
			break;
		case SideNavigationPage.Right:
			animShow = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 1,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0);
			break;
		case SideNavigationPage.Left:
		default:
			animShow = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, -1,
					Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0);
			break;
		}
		animShow.setFillAfter(true);
		animShow.setDuration(300);
		animShow.setAnimationListener(this);
		view.startAnimation(animShow);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Log.d("SideNavigation", "onAnimationEnd");
		sideNavigationPage.finish();
		finish();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		Log.d("SideNavigation", "onAnimationStart");
	}

	@Override
	public void onSwitched(View view, int position) {
		if(position == 1){
			finish();
		}
		
	}	
}
