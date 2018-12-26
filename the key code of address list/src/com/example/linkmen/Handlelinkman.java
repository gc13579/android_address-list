package com.example.linkmen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.enity.Linkman;
import com.example.service.LinkmanService;
import android.net.Uri;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class Handlelinkman extends Activity {
	private Button update;
	private Button handle_sendmessage;
	private Button delete;
	private Button handle_back;
	private ImageButton qqchat;
	private ImageButton wechat;
	private ImageButton handle_call;
	private EditText et_one_id;
	private EditText et_one_name;
	private EditText et_one_phone;
	private EditText et_one_QQ;
	private LinkmanService linkmanService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handlelinkman);
		linkmanService = new LinkmanService(Handlelinkman.this);
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);

		Bundle bundle = Handlelinkman.this.getIntent().getExtras();
		final String id = bundle.getString("id");
		handle_sendmessage = (Button) findViewById(R.id.handle_sendmessage);
		update = (Button) findViewById(R.id.update);
		delete = (Button) findViewById(R.id.delete);
		handle_back = (Button) findViewById(R.id.handle_back);
		qqchat = (ImageButton) findViewById(R.id.qqchat);
		wechat = (ImageButton) findViewById(R.id.wechat);
		handle_call = (ImageButton) findViewById(R.id.handle_call);
		et_one_id = (EditText) findViewById(R.id.et_one_id);
		et_one_name = (EditText) findViewById(R.id.et_one_name);
		et_one_phone = (EditText) findViewById(R.id.et_one_phone);
		et_one_QQ = (EditText) findViewById(R.id.et_one_QQ);
		
		Linkman linkman = linkmanService.findById(Integer.parseInt(id));
		et_one_id.setText(linkman.getId() + "");
		et_one_name.setText(linkman.getName());
		et_one_phone.setText(linkman.getPhone());
		et_one_QQ.setText(linkman.getQQ());
		
		handle_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Handlelinkman.this, Tab.class);
				startActivity(intent);
				Handlelinkman.this.finish();
			}
		});

		delete.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				builder.setMessage("真的要删除吗")
						.setNegativeButton("取消",
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										Intent intent = new Intent(
												Handlelinkman.this,
												Handlelinkman.class);
										Bundle bundle = new Bundle();
										bundle.putString("id", id + "");
										intent.putExtras(bundle);
										startActivity(intent);
										Handlelinkman.this.finish();
									}
								})
						.setPositiveButton("确定",
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										linkmanService.removeById(Integer
												.parseInt(id));
										Toast.makeText(Handlelinkman.this,
												"删除联系人成功", Toast.LENGTH_SHORT)
												.show();
										Intent intent = new Intent(
												Handlelinkman.this, Tab.class);
										startActivity(intent);
										Handlelinkman.this.finish();
									}
								});
				builder.create().show();
			}
		});

		update.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Integer id = Integer.parseInt(et_one_id.getText().toString());
				String name = et_one_name.getText().toString();
				String phone = et_one_phone.getText().toString();
				String QQ = et_one_QQ.getText().toString();
				Linkman linkman = new Linkman();
				linkman.setId(id);
				linkman.setName(name);
				linkman.setPhone(phone);
				linkman.setQQ(QQ);
				linkmanService.modify(linkman);
				Toast.makeText(Handlelinkman.this, "修改成功！", Toast.LENGTH_SHORT)
						.show();
				Intent intent = new Intent(Handlelinkman.this, Tab.class);
				// Bundle bundle = new Bundle();
				// bundle.putString("id", id + "");
				// intent.putExtras(bundle);
				startActivity(intent);
				Handlelinkman.this.finish();
			}
		});

		handle_call.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = et_one_name.getText().toString();
				String phoneNumber = et_one_phone.getText().toString().trim();

				if ("".equals(phoneNumber)) {
					Toast.makeText(Handlelinkman.this, "电话号码不能为空！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + phoneNumber));
				startActivity(intent);

				LinkmanService linkmanService = new LinkmanService(
						Handlelinkman.this);
				com.example.enity.Linkman linkman = new com.example.enity.Linkman();
				linkman.setName(name);
				linkman.setPhone(phoneNumber);

				linkmanService.insert1((com.example.enity.Linkman) linkman);

			}
		});

		handle_sendmessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String id=et_one_id.getText().toString();
				String name=et_one_name.getText().toString();
				String phone =et_one_phone.getText().toString();
				
				Intent intent = new Intent(Handlelinkman.this,
						Sendmessage.class);
				Bundle bundle = new Bundle();
				bundle.putString("id", id);
				bundle.putString("name", name);
				bundle.putString("phone", phone);
				intent.putExtras(bundle);
				startActivity(intent);
				Handlelinkman.this.finish();
//				Intent intent = new Intent(Handlelinkman.this,
//						Sendmessage.class);
//				startActivity(intent);
//				Handlelinkman.this.finish();
			}
		});

		qqchat.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// String url = "mqqwpa://im/chat?chat_type=wpa&uin="
				// + et_one_QQ.getText().toString();
				// startActivity(new Intent(Intent.ACTION_VIEW,
				// Uri.parse(url)));
				if ("".equals(et_one_QQ.getText().toString())) {
					Toast.makeText(Handlelinkman.this, "未保存QQ账号或QQ帐号格式不正确",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (isQQClientAvailable(Handlelinkman.this)) {
					final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin="
							+ et_one_QQ.getText().toString() + "&version=1";
					startActivity(new Intent(Intent.ACTION_VIEW, Uri
							.parse(qqUrl)));
				} else {
					Toast.makeText(Handlelinkman.this, "请安装QQ客户端",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		wechat.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// Intent intent=new Intent(Intent.ACTION_MAIN);
				// ComponentName cmp=new
				// ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
				// intent.addCategory(intent.CATEGORY_LAUNCHER);
				// intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
				// intent.setComponent(cmp);
				// startActivity(intent);

				// Intent intent=new Intent();
				// intent.setAction(Intent.ACTION_SEND);
				// SharedPreferences
				// sp=getPreferences(Handlelinkman.MODE_PRIVATE);
				// String tv=sp.getString("tv", "");
				// intent.putExtra(intent.EXTRA_TEXT, tv);
				// intent.setType("text/plain");
				// startActivity(intent);
				// if(isWechatClientAvailable(Handlelinkman.this)){
				// Toast.makeText(Handlelinkman.this, "已经安装微信",
				// Toast.LENGTH_SHORT)
				// .show();
				// }
			}
		});
	}

	public static boolean isQQClientAvailable(Context context) {
		final PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals("com.tencent.mobileqq")) {
					return true;
				}
			}
		}
		return false;
	}

}
