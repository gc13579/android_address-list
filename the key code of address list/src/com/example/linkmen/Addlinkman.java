package com.example.linkmen;

import com.example.service.LinkmanService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addlinkman extends Activity {
	private Button savelinkman;
	private Button addlinkman_back;
	private EditText etQQ;
	private EditText etName;
	private EditText etPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addlinkman);
		addlinkman_back=(Button) findViewById(R.id.addlinkman_back);
		etQQ=(EditText) findViewById(R.id.et_add_QQ);
		etName=(EditText) findViewById(R.id.et_add_name);
		etPhone=(EditText) findViewById(R.id.et_add_phone);
		savelinkman = (Button) findViewById(R.id.savelinkman);
		savelinkman.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = etName.getText().toString();
				String phone = etPhone.getText().toString();
				String QQ = etQQ.getText().toString();
				LinkmanService linkmanService = new LinkmanService(
						Addlinkman.this);
				com.example.enity.Linkman linkman = new com.example.enity.Linkman();
				linkman.setName(name);
				linkman.setPhone(phone);
				linkman.setQQ(QQ);
				linkmanService.insert((com.example.enity.Linkman) linkman);
				if("".equals(name)){
					Toast.makeText(Addlinkman.this,"姓名不能为空", Toast.LENGTH_SHORT)
					.show();
					return;
				}
				Toast.makeText(Addlinkman.this, "添加联系人成功！",
						Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(Addlinkman.this,Tab.class);
				startActivity(intent);
				Addlinkman.this.finish();	
			}		
		});
		addlinkman_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(Addlinkman.this,Tab.class);
				startActivity(intent);
				Addlinkman.this.finish();
				
			}
		});
	}

}
