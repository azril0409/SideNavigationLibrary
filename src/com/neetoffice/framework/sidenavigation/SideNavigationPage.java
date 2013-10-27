package com.neetoffice.framework.sidenavigation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

public  class SideNavigationPage {
	static String Translate = "Translate";
	private static SideNavigationPage sideNavigationPage;
	public static final int Top = 0;
	public static final int Left = 1;
	public static final int Bottom = 2;
	public static final int Right = 3;
	private int translate = Left;
	private Context context;
	private View view;
	private SideNavigationActivity activity;
	private SideNavigationDismissCallback callback;
	private boolean b = true;
	
	public static SideNavigationPage getEntity(Context context){
		if(sideNavigationPage == null){
			sideNavigationPage = new SideNavigationPage(context);
		}
		return sideNavigationPage;
	}

	private SideNavigationPage(Context context) {
		this.context = context;
	}
	
	void onCreate(SideNavigationActivity activity){
		this.activity = activity;
	}
	
	public void setTranslate(int translate){
		this.translate = translate;
	}
	
	public View createView(int layoutRid){
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		return layoutInflater.inflate(layoutRid, null);
	}

	public void show(View view) {
		if(b || (activity !=null && activity.isFinishing())){
			b= false;
			this.view = view;	
			if(view == null){
				return;
			}
			Intent intent = new Intent();
			intent.putExtra(Translate, translate);
			intent.setClass(context, SideNavigationActivity.class);
			context.startActivity(intent);			
		}
	}
	
	public void dismiss(SideNavigationDismissCallback callback){
		this.callback = callback;
		if (activity != null){
			activity.onBackPressed();			
		}
		b=true;
	}
	
	public void dismiss(){
		dismiss(null);
	}
	
	void finish(){
		if(callback != null){
		callback.onDismiss();
		}
		callback = null;
	}
	
	View getView(){
		return view;
	}

}
