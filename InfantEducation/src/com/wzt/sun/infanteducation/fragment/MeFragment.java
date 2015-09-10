package com.wzt.sun.infanteducation.fragment;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.FeedbackActivity;
import com.wzt.sun.infanteducation.activity.LeaveActivity;
import com.wzt.sun.infanteducation.activity.NotificationUpdateActivity;
import com.wzt.sun.infanteducation.activity.PersonSettingActivity;
import com.wzt.sun.infanteducation.activity.PersonalInfoActivity;
import com.wzt.sun.infanteducation.activity.PostMessageActivity;
import com.wzt.sun.infanteducation.activity.StudentInformationActivity;
import com.wzt.sun.infanteducation.activity.UpdatePasswordActivity;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.ACache;
import com.wzt.sun.infanteducation.utils.CircleTransform;
import com.wzt.sun.infanteducation.view.MyListView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

/**
 * 我的fragment
 * @author sun.ml
 *
 */
public class MeFragment extends Fragment implements OnItemClickListener{
	
	private ImageView iv;
	
	//加载菜单
	private MyListView mListView;
	private SimpleAdapter adapter;
	private Button btn;
	
	private BaseApp app;
	private ACache mACache;
	private Bitmap bm;
	private String iconUrl;
	private int id;
	
	private SharedPreferences userInfo = null;
	private SharedPreferences stuOrTea = null;
	
	private boolean isStu;
	private boolean isTea;
	private boolean isLea;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_first_me, null);
		mListView = (MyListView) view.findViewById(R.id.fragment_me_lv);
		btn = (Button) view.findViewById(R.id.fragment_me_btn);
		userInfo = getActivity().getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, 0);
		stuOrTea = getActivity().getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, 0);
		id = stuOrTea.getInt("id", 0);
		isStu = userInfo.getBoolean("isParent", false);
		isTea = userInfo.getBoolean("isTeacher", false);
		isLea = userInfo.getBoolean("isLeader", false);
		if(isTea){
			adapter = new SimpleAdapter(getActivity(), ConstantsConfig.loadMyMenuToTea(), R.layout.fragment_me_listview_item, 
					new String[] { "itemImage", "itemText" }, new int[] {R.id.me_lv_item_image, R.id.me_lv_item_text});
		}else {
			adapter = new SimpleAdapter(getActivity(), ConstantsConfig.loadMyMenu(), R.layout.fragment_me_listview_item, 
					new String[] { "itemImage", "itemText" }, new int[] {R.id.me_lv_item_image, R.id.me_lv_item_text});
		}
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
		app = (BaseApp) getActivity().getApplication();
		iv = (ImageView) view.findViewById(R.id.iv_usercenter_avatar);
		
		iconUrl = ConstansUrl.getHeadnUrl(stuOrTea.getString("photo", null));
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				if(isTea){
					intent.setClass(getActivity(), PersonalInfoActivity.class);
					intent.putExtra("ISTEA", 1);
					intent.putExtra("headUrl", iconUrl);
				}else if (isStu) {
					intent.setClass(getActivity(), StudentInformationActivity.class);
					intent.putExtra("STUID", id);
				}else {
					
				}
				startActivity(intent);
			}
		});
		Picasso.with(getActivity()).load(iconUrl).placeholder(R.drawable.avatar).error(R.drawable.avatar).transform(new CircleTransform()).into(iv);
		bm = drawable2Bitmap(iv.getDrawable());
		saveBitmap(bm);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Editor mEditor1 = userInfo.edit();
				Editor mEditor2 = stuOrTea.edit();
				mEditor1.clear();
				mEditor2.clear();
				mEditor1.commit();
				mEditor2.commit();
				dialogShow();
			}
		});
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent mIntent = new Intent();
		switch (position) {
		case 0:
			mIntent.setClass(getActivity(), PersonSettingActivity.class);
			startActivity(mIntent);
			break;
		case 1:
			if(isTea){
				mIntent.setClass(getActivity(), PostMessageActivity.class);
				mIntent.putExtra("ISPOST", 2);
				startActivity(mIntent);
			}else {
				mIntent.setClass(getActivity(), LeaveActivity.class);
				startActivity(mIntent);
			}
			break;
		case 2:
			mIntent.setClass(getActivity(), UpdatePasswordActivity.class);
			startActivity(mIntent);
			break;
		case 3:
			mIntent.setClass(getActivity(), PersonSettingActivity.class);
			startActivity(mIntent);
			break;
		case 4:
			mIntent.setClass(getActivity(), FeedbackActivity.class);
			mIntent.putExtra("BIAOSHI", 1);
			startActivity(mIntent);
			break;
		case 5:
			showUpdateDialog();
			break;

		default:
			break;
		}
		
	}
	
	private void showUpdateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("检测到新版本");
		builder.setMessage("是否下载更新?");
		builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent it = new Intent(getActivity(), NotificationUpdateActivity.class);
				startActivity(it);
				app.setDownload(true);
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		builder.show();
	}
	
	private void dialogShow() {
		DialogFragment newFragment = new MyExitDialogFragment();
		newFragment.show(getFragmentManager(), "exitDialog");
	}
	
	private void saveBitmap(Bitmap bm){
		mACache = ACache.get(getActivity());
		mACache.put("iconBitmap", bm, 2 * ACache.TIME_DAY);
	}
	
	/**
	 * Drawable 转 bitmap
	 * @param drawable
	 * @return
	 */
	private Bitmap drawable2Bitmap(Drawable drawable) {  
        if (drawable instanceof BitmapDrawable) {  
            return ((BitmapDrawable) drawable).getBitmap();  
        } else if (drawable instanceof NinePatchDrawable) {  
            Bitmap bitmap = Bitmap  
                    .createBitmap(  
                            drawable.getIntrinsicWidth(),  
                            drawable.getIntrinsicHeight(),  
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888  
                                    : Bitmap.Config.RGB_565);  
            Canvas canvas = new Canvas(bitmap);  
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),  
                    drawable.getIntrinsicHeight());  
            drawable.draw(canvas);  
            return bitmap;  
        } else {  
            return null;  
        }  
    }  
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		iconUrl = ConstansUrl.getHeadnUrl(stuOrTea.getString("photo", null));
		Picasso.with(getActivity()).load(iconUrl).placeholder(R.drawable.avatar).error(R.drawable.avatar).transform(new CircleTransform()).into(iv);
	}
	
}
