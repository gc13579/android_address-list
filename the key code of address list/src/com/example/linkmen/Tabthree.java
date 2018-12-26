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
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Tabthree extends Activity {
	private ListView tab_lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabthree);
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		tab_lv = (ListView) findViewById(R.id.tab_lv);
		final LinkmanService linkmanService = new LinkmanService(Tabthree.this);
		List<com.example.enity.Linkman> linkmen = linkmanService.selectAll1();
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Map<String, String> title = new HashMap<String, String>();
		title.put("id", "编号");
		title.put("name", "姓名");
		title.put("phone", "电话");
		data.add(title);
		if (linkmen != null && linkmen.size() != 0) {
			for (com.example.enity.Linkman linkman : linkmen) {
				Map<String, String> rowData = new HashMap<String, String>();
				rowData.put("id", linkman.getId() + "");
				rowData.put("name", linkman.getName());
				rowData.put("phone", linkman.getPhone());
				data.add(rowData);
			}
		}
		
		tab_lv.setAdapter(new SimpleAdapter(Tabthree.this, data,
				R.layout.listviewformat2, new String[] { "name", "phone" },
				new int[] { R.id.listviewformat2_name,
						R.id.listviewformat2_phone }));
		tab_lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(final AdapterView<?> parent, View view,
					final int position, long lineNumber) {
				ListView lv = (ListView) parent;
				final Map<String, String> rowData = (Map<String, String>) lv
						.getItemAtPosition(position);
				if (position == 0) {
					return;
				}
				builder.setNegativeButton("删除此记录",
						new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								String id = rowData.get("id");
								Toast.makeText(Tabthree.this, "通话记录删除成功",
										Toast.LENGTH_LONG).show();
								linkmanService.removeById1(Integer.parseInt(id));
								Intent intent = new Intent(Tabthree.this,
										Tab.class);
								// Bundle bundle = new Bundle();
								// intent.putExtras(bundle);
								startActivity(intent);
								Tabthree.this.finish();

							}
						})
						.setNeutralButton("发短信",
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										String id = rowData.get("id");
										String name=rowData.get("name");
										String phone=rowData.get("phone");
										Intent intent = new Intent(Tabthree.this,
												Sendmessage2.class);
										Bundle bundle = new Bundle();
										
										Linkman linkman=new Linkman();
										linkman.setId(Integer.parseInt(id));
										linkman.setName(name);
										linkman.setPhone(phone);
										
										LinkmanService linkmanService = new LinkmanService(Tabthree.this);
										List<com.example.enity.Linkman> linkmen = linkmanService.selectAll();
										if (linkmen != null && linkmen.size() != 0) {
											for (Linkman linkman1 : linkmen) {
												if(!(linkman.getPhone().equals(linkman1.getPhone()))){
													bundle.putString("id", id);
													bundle.putString("name", phone);
													bundle.putString("phone", phone);
													
												}
												else{
													bundle.putString("id", id);
													bundle.putString("name", name);
													bundle.putString("phone", phone);
												}
											}
										}
										
										
										intent.putExtras(bundle);
										startActivity(intent);
										Tabthree.this.finish();
									}
								})
						.setPositiveButton("拨打",
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// rowData为某一行拨打记录
										String id = rowData.get("id");
										Linkman linkman = linkmanService
												.findById1(Integer.parseInt(id));
										String phone = linkman.getPhone()
												.toString();
										Intent intent = new Intent();
										intent.setAction(Intent.ACTION_CALL);
										intent.setData(Uri
												.parse("tel:" + phone));
										startActivity(intent);
										// 为本次拨打增加拨打记录
										LinkmanService linkmanService = new LinkmanService(
												Tabthree.this);
										com.example.enity.Linkman linkman1 = new com.example.enity.Linkman();
										linkman1.setName(linkman.getName());
										linkman1.setPhone(linkman.getPhone());

										linkmanService
												.insert1((com.example.enity.Linkman) linkman1);
										List<com.example.enity.Linkman> linkmen = linkmanService
												.selectAll1();
										List<Map<String, String>> data = new ArrayList<Map<String, String>>();
										Map<String, String> title = new HashMap<String, String>();
										title.put("id", "编号");
										title.put("name", "姓名");
										title.put("phone", "电话");
										data.add(title);
										if (linkmen != null
												&& linkmen.size() != 0) {
											for (com.example.enity.Linkman linkman2 : linkmen) {
												Map<String, String> rowData = new HashMap<String, String>();
												rowData.put("id",
														linkman2.getId() + "");
												rowData.put("name",
														linkman2.getName());
												rowData.put("phone",
														linkman2.getPhone());
												data.add(rowData);
											}
										}
										// tab_lv适配器添加，第二个参数，可获取data数组内容
										tab_lv.setAdapter(new SimpleAdapter(
												Tabthree.this,
												data,
												R.layout.listviewformat2,
												new String[] { "name", "phone" },
												new int[] {
														R.id.listviewformat2_name,
														R.id.listviewformat2_phone }));
									}
								});
				builder.create().show();
			}
		});
	}

}
