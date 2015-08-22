package com.wzt.sun.infanteducation.activity;

import java.io.File;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

/**
 * 变换头像主界面
 * @author sun.ml
 *
 */
public class FaviconActivity extends BaseActivity {
	
	private ImageView headIcon;
	public static final int CAMERA  = 0x01;
	public static final int PICTURE  = 1;
	public boolean isChange = false;
	
	private Handler mHandle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x0001) {
				headIcon.invalidate();
				isChange = true;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favicon);
		headIcon = (ImageView) findViewById(R.id.favicon_image);
		Picasso.with(this).load(R.drawable.head_icon).into(headIcon);
	}
	
	public void btnClick(View view){
		switch (view.getId()) {
		case R.id.favicon_btn:
			 
			Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(picture, PICTURE);
			
			break;
			
		case R.id.favicon_btn_save:
			if(!isChange){
				BaseApp.getInstance().showToast("请先更改头像");
			}else {
				BaseApp.getInstance().showToast("保存中");
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == CAMERA && resultCode == Activity.RESULT_OK && null != data){

			Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
  
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
  
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Picasso.with(this).load(new File(picturePath)).into(headIcon);
			mHandle.sendEmptyMessage(0x0001);
		}
	}
}
