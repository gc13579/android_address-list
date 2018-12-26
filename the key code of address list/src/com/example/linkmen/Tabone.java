package com.example.linkmen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.enity.Linkman;
import com.example.service.LinkmanService;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Tabone extends Activity {
	private TextView tv;
	private EditText et;
	private GridLayout gl;
	private ImageButton ibt;
	private Button back;
	private ImageButton laba;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Bundle bundle = Tabone.this.getIntent().getExtras();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabone);

		// 获得组件操作权
		// System.out.println(tab_lv);
		ibt = (ImageButton) findViewById(R.id.ibt);
		tv = (TextView) findViewById(R.id.tv);
		et = (EditText) findViewById(R.id.et);
		gl = (GridLayout) findViewById(R.id.gl);
		back = (Button) findViewById(R.id.back);
		laba = (ImageButton) findViewById(R.id.laba);

		// 为0-9#*添加事件监听
		for (int i = 0; i < gl.getChildCount(); i++) {
			Button bt = (Button) gl.getChildAt(i);
			bt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Button bt = (Button) v;
					// bt.setTextSize(BIND_NOT_FOREGROUND);
					et.setText(et.getText().toString() + bt.getText());
				}
			});
		}
		// 为回退添加事件监听
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = et.getText().toString();
				if (number.length() == 0) {
					return;
				}
				et.setText(number.substring(0, number.length() - 1));

			}
		});
		// 为拨打添加事件监听
		ibt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String phoneNumber = et.getText().toString().trim();

				if ("".equals(phoneNumber)) {
					Toast.makeText(Tabone.this, "电话号码不能为空！", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				// Intent intent = new Intent();
				// intent.setAction(Intent.ACTION_CALL);
				// intent.setData(Uri.parse("tel:" + phoneNumber));
				// startActivity(intent);

				LinkmanService linkmanService = new LinkmanService(Tabone.this);
				com.example.enity.Linkman linkman = new com.example.enity.Linkman();
				linkman.setPhone(phoneNumber);
				List<com.example.enity.Linkman> linkmen = linkmanService
						.selectAll();
				if (linkmen != null && linkmen.size() != 0) {
					for (com.example.enity.Linkman linkman1 : linkmen) {
						if (!(linkman.getPhone().equals(linkman1.getPhone()))) {
							linkman.setName("未知");
							linkmanService
									.insert1((com.example.enity.Linkman) linkman);
						} else {
							linkman.setName(linkman1.getName());
							linkmanService
									.insert1((com.example.enity.Linkman) linkman);
						}
					}
				} else {
					linkman.setName("未知");
					linkmanService.insert1((com.example.enity.Linkman) linkman);
				}

//				TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//				mPhoneStateListener listener = new mPhoneStateListener();
//				tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

				// 能拨打,数据已经传送，但拨打和跳转在真机的顺序不确定
				Intent[] intents = new Intent[2];
				for (int i = 0; i < intents.length; i++) {
					intents[i] = new Intent();
				}
				intents[0] = new Intent(Tabone.this, Tab.class);
				intents[1].setAction(Intent.ACTION_CALL);
				intents[1].setData(Uri.parse("tel:" + phoneNumber));
				startActivities(intents);
				// 真机拨打无法执行，但数据已经传送
				// Intent intent = new Intent();
				// intent.setAction(Intent.ACTION_CALL);
				// intent.setData(Uri.parse("tel:" + phoneNumber));
				// startActivity(intent);
				// Intent intent1 = new Intent(Tabone.this, Tab.class);
				// startActivity(intent1);
				// Tabone.this.finish();
			}
		});

		laba.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Tabone.this, Erweima.class);
				startActivity(intent);
				Tabone.this.finish();
			}
		});
	}

	// private class mPhoneStateListener extends PhoneStateListener {
	// @Override
	// public void onCallStateChanged(int state, String incomingNumber) {
	// // TODO Auto-generated method stub
	// switch (state) {
	// //
	// case TelephonyManager.DATA_DISCONNECTED:
	// Toast.makeText(Tabone.this, "........", Toast.LENGTH_SHORT)
	// .show();
	// break;
	//
	// }
	// }
	// }
	

}
