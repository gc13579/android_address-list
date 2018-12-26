package com.example.linkmen;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.TabHost;

public class Tab extends TabActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost th = getTabHost();
		LayoutInflater.from(Tab.this).inflate(R.layout.activity_tab,
				th.getTabContentView(), true);
		th.addTab(th.newTabSpec("1").setIndicator("拨号")
				.setContent(new Intent(this, Tabone.class)));
		th.addTab(th.newTabSpec("2").setIndicator("联系人")
				.setContent(new Intent(this, Tabtwo.class)));
		th.addTab(th.newTabSpec("3").setIndicator("通话记录")
				.setContent(new Intent(this, Tabthree.class)));
	}

}
