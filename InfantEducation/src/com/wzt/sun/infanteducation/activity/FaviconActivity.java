package com.wzt.sun.infanteducation.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.BitmapUtils;
import com.wzt.sun.infanteducation.utils.Utils;
import com.wzt.sun.infanteducation.view.SelectPicPopupWindow;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 变换头像主界面
 * @author sun.ml
 *
 */
public class FaviconActivity extends Activity {
	
	private ImageView headIcon;
	public static final int CAMERA  = 0x01;
	public static final int PICTURE  = 1;
	public static final int TAKECAMERA  = 2;
	public boolean isChange = false;
	private String picturePath;
	private HttpUtils mHttpUtils;
	private SharedPreferences loginSp = null;
	private SharedPreferences stuOrTea = null;
	private int id;
	private String state;
	
	private Uri mPhotoUri;           //拍照后的uri
    public static final int REQUEST_CODE_TAKE_PICTURE = 3;         //拍照显示图片
    private Bitmap bitmap;
	
	private SelectPicPopupWindow menuWindow;
	
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
		mHttpUtils = new HttpUtils();
		loginSp = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		id = loginSp.getInt("num", 0);
		state = loginSp.getString("state", null);
		String url = stuOrTea.getString("photo", null);
		Picasso.with(this).load(ConstansUrl.getHeadnUrl(url)).placeholder(R.drawable.avatar).error(R.drawable.avatar).into(headIcon);
	}
	
	public void btnClick(View view){
		switch (view.getId()) {
		case R.id.favicon_btn:
			 
			/*Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(picture, PICTURE);*/
			menuWindow = new SelectPicPopupWindow(this, itemsOnClick);
			menuWindow.showAtLocation(view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
			
		case R.id.favicon_btn_save:
			if(!isChange){
				BaseApp.getInstance().showToast("请先更改头像");
			}else {
				upLoad();
				//BaseApp.getInstance().showToast("保存中");
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
		switch (requestCode) {
		case CAMERA:
			if (resultCode == Activity.RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Log.i("GETALLURL", filePathColumn.toString());
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				picturePath = cursor.getString(columnIndex);
				cursor.close();
				Picasso.with(this).load(new File(picturePath)).into(headIcon);
				mHandle.sendEmptyMessage(0x0001);
			}
			break;
		case TAKECAMERA:
			if (resultCode == Activity.RESULT_OK) {
				final String state = Environment.getExternalStorageState();
				if (Environment.MEDIA_MOUNTED.equals(state)) {
					final Uri uri = mPhotoUri;
					Log.e("MainActivity",uri+"");
					if (uri != null) {
						processPicture(uri);
					}
					String resetPhotoName = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA))+"";
					Utils.savePhotoToSDCard(bitmap,Utils.genProjectPath(),resetPhotoName);
					picturePath = Utils.genProjectPath()+resetPhotoName+".jpg";
					Picasso.with(this).load(new File(picturePath)).into(headIcon);
					mHandle.sendEmptyMessage(0x0001);
				}
			}
			break;

		default:
			break;
		}
		
	}
	
	private void upLoad(){
		String url = ConstansUrl.POSTHEAD;
		RequestParams params=new RequestParams();
		File mFile = new File(picturePath);
		params.addBodyParameter("str", state);
		params.addBodyParameter("id", id+"");
		params.addBodyParameter("file", mFile);
		mHttpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast("上传失败！");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast("上传成功！");
				String str = arg0.result;
				Editor editor=stuOrTea.edit();
				editor.putString("photo", str);
				editor.commit();
				Intent intent = getIntent();
				Bundle bundle = new Bundle();
				bundle.putString("imgUrl", str);
				intent.putExtras(bundle);
				setResult(1, intent);
				FaviconActivity.this.finish();
			}
		});
	}
	
	//为弹出窗口实现监听类  
    private OnClickListener  itemsOnClick = new OnClickListener(){  
  
        public void onClick(View v) {  
            menuWindow.dismiss();  
            switch (v.getId()) {  
            case R.id.btn_take_photo: 
            	Intent takePictureImIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ContentValues values = new ContentValues();
                mPhotoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                takePictureImIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mPhotoUri);
                startActivityForResult(takePictureImIntent,TAKECAMERA);
                break;  
            case R.id.btn_pick_photo:   
            	Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    			startActivityForResult(picture, PICTURE);
                break;
            case R.id.btn_cancel:
            	menuWindow.dismiss();
            	break;
            default:  
                break;  
            }  
              
                  
        }  
          
    };  
    
    /**
     * 图片显示
     * @param uri
     */
    private void processPicture(Uri uri) {
        final String[] projection = {MediaStore.Images.Media.DATA};
        final Cursor cursor = managedQuery(uri, projection, null, null, null);
        cursor.moveToFirst();
        final int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        String imagePath = cursor.getString(columnIndex);
        Log.e("MainActivity",imagePath);

        final String targetPath = BitmapUtils.toRegularHashCode(imagePath) + ".jpg";
        BitmapUtils.compressBitmap(imagePath, targetPath, 640);  //压缩
        bitmap = BitmapUtils.decodeBitmap(imagePath, 150);       //分解
        /*headIcon.setImageBitmap(bitmap);
        isChange = true;*/
    }
    
}

