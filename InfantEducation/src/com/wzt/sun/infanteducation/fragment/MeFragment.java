package com.wzt.sun.infanteducation.fragment;

import com.squareup.picasso.Picasso;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.MainActivity;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.activity.FeedbackActivity;
import com.wzt.sun.infanteducation.activity.NotificationUpdateActivity;
import com.wzt.sun.infanteducation.activity.PersonSettingActivity;
import com.wzt.sun.infanteducation.activity.PersonalInfoActivity;
import com.wzt.sun.infanteducation.activity.UpdatePasswordActivity;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.CircleTransform;
import com.wzt.sun.infanteducation.view.MyListView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class MeFragment extends Fragment implements OnItemClickListener{
	
	private ImageView iv;
	
	//加载菜单
	private MyListView mListView;
	private SimpleAdapter adapter;
	private Button btn;
	
	private BaseApp app;
	
	private SharedPreferences userInfo = null;
	private SharedPreferences stuOrTea = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_first_me, null);
		mListView = (MyListView) view.findViewById(R.id.fragment_me_lv);
		btn = (Button) view.findViewById(R.id.fragment_me_btn);
		adapter = new SimpleAdapter(getActivity(), ConstantsConfig.loadMyMenu(), R.layout.fragment_me_listview_item, 
				new String[] { "itemImage", "itemText" }, new int[] {R.id.me_lv_item_image, R.id.me_lv_item_text});
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
		userInfo = getActivity().getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, 0);
		stuOrTea = getActivity().getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, 0);
		app = (BaseApp) getActivity().getApplication();
		iv = (ImageView) view.findViewById(R.id.iv_usercenter_avatar);
		
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), PersonalInfoActivity.class);
				startActivity(intent);
			}
		});
		Picasso.with(getActivity()).load(R.drawable.head_icon).transform(new CircleTransform()).into(iv);
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
			mIntent.setClass(getActivity(), UpdatePasswordActivity.class);
			startActivity(mIntent);
			break;
		case 2:
			mIntent.setClass(getActivity(), PersonSettingActivity.class);
			startActivity(mIntent);
			break;
		case 3:
			mIntent.setClass(getActivity(), FeedbackActivity.class);
			startActivity(mIntent);
			break;
		case 4:
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

}
