package com.neetoffice.framework.sidenavigation;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SideNavigationAdapter extends BaseAdapter {
	private Context context;
	SideNavigationAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		switch(position){
		case 0:
			convertView = SideNavigationPage.getEntity(context).getView();
			break;
		case 1:
			convertView = new View(context);
			break;
		}
		return convertView;
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if(observer != null){
			super.unregisterDataSetObserver(observer);  
	    } 
	}
	
	

}
