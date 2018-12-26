package com.example.linkmen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.service.LinkmanService;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Tabtwo extends Activity {
	private ListView lv;
	private Button addlinkman;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_tabtwo);
		lv = (ListView) findViewById(R.id.lv);
		addlinkman=(Button) findViewById(R.id.addlinkman);
		LinkmanService linkmanService = new LinkmanService(Tabtwo.this);

		// List<Linkman> linkmen = linkmanService.selectAll();
		List<com.example.enity.Linkman> linkmen = linkmanService.selectAll();

		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Map<String, String> title = new HashMap<String, String>();
		title.put("id", "编号");
		title.put("name", "姓名");
		title.put("phone", "电话");
		title.put("QQ", "QQ号码");
		data.add(title);
		if (linkmen != null && linkmen.size() != 0) {
			for (com.example.enity.Linkman linkman : linkmen) {
				Map<String, String> rowData = new HashMap<String, String>();
				rowData.put("id", linkman.getId() + "");
				rowData.put("name", linkman.getName());
				rowData.put("phone", linkman.getPhone());
				rowData.put("QQ", linkman.getQQ());
				data.add(rowData);
			}
		}
		//tab_lv适配器添加，第二个参数，可获取data数组内容
		lv.setAdapter(new SimpleAdapter(Tabtwo.this, data,
				R.layout.listviewformat, new String[] { "name" },
				new int[] { R.id.listviewformat_name }));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long lineNumber) {				
				if(position==0){
					return;
				}			
				ListView lv=(ListView) parent;
				Map<String,String> rowData=(Map<String, String>) lv.getItemAtPosition(position);			
				String id=rowData.get("id");
				
				Intent intent=new Intent(Tabtwo.this,Handlelinkman.class);
				Bundle bundle=new Bundle();
				bundle.putString("id", id);				
				intent.putExtras(bundle);
				startActivity(intent);
				Tabtwo.this.finish();
			}
		});
		addlinkman.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				Intent intent=new Intent(Tabtwo.this,Addlinkman.class);
				startActivity(intent);
				Tabtwo.this.finish();
			}
		});
	}
}
