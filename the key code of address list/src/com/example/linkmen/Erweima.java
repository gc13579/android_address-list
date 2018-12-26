package com.example.linkmen;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class Erweima extends Activity {
	private ImageButton next;
	private Button btn_check;
	private Button erweima_back;
	private int[] images = new int[] { R.drawable.addmywechat,
			R.drawable.addmyqq };
	private int index = 0;
	private ImageSwitcher imageswitcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_erweima);
		btn_check = (Button) findViewById(R.id.btn_check);
		erweima_back = (Button) findViewById(R.id.erweima_back);
		next = (ImageButton) findViewById(R.id.next);
		imageswitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);

		imageswitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		imageswitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		imageswitcher.setFactory(new ViewFactory() {
			public View makeView() {
				ImageView imageview = new ImageView(Erweima.this);
				imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageview.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				return imageview;
			}
		});
		imageswitcher.setImageResource(images[index]);

		next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (index < images.length - 1) {
					index++;
				} else {
					index = 0;
				}
				imageswitcher.setImageResource(images[index]);
			}
		});
		erweima_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Erweima.this, Tab.class);
				startActivity(intent);
				Erweima.this.finish();
			}
		});

		btn_check.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Erweima.this);
				builder.setMessage("本app由安卓小白制作，欢迎扫码批评指正");
				builder.setPositiveButton("确定", new AlertDialog.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
					}
					
				});
				builder.create().show();
			}
		});

	}

}
