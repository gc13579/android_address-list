package com.example.linkmen;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sendmessage extends Activity {
	private TextView sendmessage_et_name;
	private PendingIntent sentIntent;
	private EditText sendmessage_et_hide;
	private EditText sendmessage_sendbody;
	private Button sendmessage_sendbutton;
	private Button sendmessage_back;
	private EditText sendmessage_et_hide2;

	BroadcastReceiver r1 = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			switch (getResultCode()) {
			case Activity.RESULT_OK:
				Toast.makeText(Sendmessage.this, "消息发送成功！", Toast.LENGTH_SHORT)
						.show();
				break;
			default:
				Toast.makeText(Sendmessage.this, "消息发送失败！", Toast.LENGTH_SHORT)
						.show();
				break;
			}

		}
	};

	BroadcastReceiver f(BroadcastReceiver r2) {
		this.r1 = r2;
		return r1;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendmessage);

		sendmessage_back = (Button) findViewById(R.id.sendmessage_back);
		sendmessage_sendbody = (EditText) findViewById(R.id.sendmessage_sendbody);
		sendmessage_sendbutton = (Button) findViewById(R.id.sendmessage_sendbutton);
		sendmessage_et_hide = (EditText) findViewById(R.id.sendmessage_et_hide);
		sendmessage_et_hide2 = (EditText) findViewById(R.id.sendmessage_et_hide2);
		sendmessage_et_name = (TextView) findViewById(R.id.sendmessage_et_name);

		Bundle bundle = Sendmessage.this.getIntent().getExtras();
		final String id = bundle.getString("id");
		String name = bundle.getString("name");
		String phone = bundle.getString("phone");
		sendmessage_et_name.setText(name);
		sendmessage_et_hide.setText(phone);
		sendmessage_et_hide2.setText(id);

		String SEND_ACTION = "SEND_ACTION";
		Intent intent = new Intent(SEND_ACTION);
		sentIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

		registerReceiver(r1, new IntentFilter(SEND_ACTION));
		// registerReceiver(new BroadcastReceiver() {
		// @Override
		// public void onReceive(Context context, Intent intent) {
		// switch (getResultCode()) {
		// case Activity.RESULT_OK:
		// Toast.makeText(Sendmessage.this, "消息发送成功！",
		// Toast.LENGTH_SHORT).show();
		// break;
		// default:
		// Toast.makeText(Sendmessage.this, "消息发送失败！",
		// Toast.LENGTH_SHORT).show();
		// break;
		// }
		// }
		//
		// }, new IntentFilter(SEND_ACTION));

		sendmessage_sendbutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String phone = sendmessage_et_hide.getText().toString();
				String message = sendmessage_sendbody.getText().toString();
				if (null == phone || "".equals(phone.trim())) {
					Toast.makeText(Sendmessage.this, "电话号码不能为空！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (null == message || "".equals(message)) {
					Toast.makeText(Sendmessage.this, "短信内容不能为空！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				SmsManager manager = SmsManager.getDefault();
				manager.sendTextMessage(phone, null, message, sentIntent, null);
				sendmessage_sendbody.setText("");
			}
		});

		sendmessage_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Sendmessage.this,
						Handlelinkman.class);
				Bundle bundle = new Bundle();
				bundle.putString("id", id);
				intent.putExtras(bundle);
				startActivity(intent);
				Sendmessage.this.finish();
			}
		});
	}

	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		BroadcastReceiver r3 = f(r1);
		unregisterReceiver(r3);
	}
}
