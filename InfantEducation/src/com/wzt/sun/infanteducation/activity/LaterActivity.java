package com.wzt.sun.infanteducation.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wzt.sun.infanteducation.BaseApp;
import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.bean.Later;
import com.wzt.sun.infanteducation.constans.ConstansUrl;
import com.wzt.sun.infanteducation.constans.ConstantsConfig;
import com.wzt.sun.infanteducation.utils.JsonParseUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LaterActivity extends BaseActivity implements OnClickListener, OnRefreshListener2<ListView> {
	//private SharedPreferences stuOrTea = null;
	private SharedPreferences userInfo = null;
	private int sId;
	private List<Later> lists;
	private List<Later> selectid;
	private HttpUtils mHttpUtils;
	private Adapter  adapter;
	
	private boolean isMulChoice = false; //是否多选
	private Button delete; // 删除
	private Button cancle; // 撤销
	private RelativeLayout layout;
	private TextView txtcount;
	private ImageView iv;
	private PullToRefreshListView ptrListView;
	private AlertDialog myDialog = null;
	
	private Handler handle = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x0030:
				ptrListView.onRefreshComplete();
				adapter.notifyDataSetChanged();
				break;
			case 0x0031:

				break;

			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_later);
		
		initView();
		loadData();
	}
	
	public void initView(){
		//stuOrTea = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_USER, MODE_PRIVATE);
		userInfo = getSharedPreferences(ConstantsConfig.SHAREDPREFERENCES_LOGIN, MODE_PRIVATE);
		//sId = stuOrTea.getInt("s_id", 0);
		sId = userInfo.getInt("num", 0);
		mHttpUtils = new HttpUtils(10000); // 超时时间
		mHttpUtils.configCurrentHttpCacheExpiry(5000); // 设置缓存5秒,5秒内直接返回上次成功请求的结果
		lists = new ArrayList<Later>();
		selectid = new ArrayList<Later>();
		
		ptrListView = (PullToRefreshListView) findViewById(R.id.later_listview);
		ptrListView.setMode(Mode.PULL_FROM_START);
		ptrListView.setOnRefreshListener(this);
		layout = (RelativeLayout) findViewById(R.id.later_relative);
		delete = (Button) findViewById(R.id.later_delete);
		cancle = (Button) findViewById(R.id.later_cancle);
		iv = (ImageView) findViewById(R.id.titlebar_later_btn_back);
		txtcount = (TextView)findViewById(R.id.later_txtcount);
		cancle.setOnClickListener(this);
        delete.setOnClickListener(this);
        adapter = new Adapter(this,txtcount);
        ptrListView.setAdapter(adapter);
        iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LaterActivity.this.finish();
			}
		});
	}
	
	public void loadData(){
		RequestParams params = new RequestParams();
		params.addBodyParameter("S_id", sId+"");
		mHttpUtils.send(HttpMethod.POST, ConstansUrl.GETPARENTSLATER, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				BaseApp.getInstance().showToast(arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String data = arg0.result;
				List<Later> laters = JsonParseUtils.parseJsonLaters(data);
				lists.clear();
				lists.addAll(laters);
				handle.sendEmptyMessage(0x0030);
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
        case R.id.later_cancle:
            isMulChoice = false;
            selectid.clear();
            adapter = new Adapter(this,txtcount);
            ptrListView.setAdapter(adapter);
            layout.setVisibility(View.INVISIBLE);
            break;
        case R.id.later_delete:
        	List<String> ids = new ArrayList<String>();
        	String srt = null;
        	isMulChoice =false;
            for(int i=0;i<selectid.size();i++){
            	for(int j=0;j<lists.size();j++){
            		if(selectid.get(i).equals(lists.get(j))){
            			ids.add(lists.get(j).getF_id()+"");
            			lists.remove(j);
            		}
            	}
            }
            srt = ids.toString();
            srt = srt.replaceAll(" ","");
            srt = srt.subSequence(1, srt.length()-1).toString();
            //Log.i("AAAAAAAAAAAAAAAAAA", srt);
            RequestParams params = new RequestParams();
            params.addBodyParameter("F_id", srt);
            mHttpUtils.send(HttpMethod.POST, ConstansUrl.POSTLATERDELETE, params, new RequestCallBack<String>() {
            	
            	@Override
            	public void onFailure(HttpException arg0, String arg1) {
            		// TODO Auto-generated method stub
            		BaseApp.getInstance().showToast(arg1);
            		//Log.i("BBBBBBBBBBBBBBBBBB", arg1);
            	}
            	
            	@Override
            	public void onSuccess(ResponseInfo<String> arg0) {
            		// TODO Auto-generated method stub
            		BaseApp.getInstance().showToast("删除成功!");
            	}
            });
            selectid.clear();
            adapter = new Adapter(this,txtcount);
            ptrListView.setAdapter(adapter);
            layout.setVisibility(View.INVISIBLE);
            break;
        default:
            break;
        }
	}
	
	
	
	
	/**
     * @author ieasy360_1
     * 自定义Adapter
     */
    class Adapter extends BaseAdapter{
        private Context context;
        private LayoutInflater inflater=null;
        private SparseArray<View> mView ;
        public  SparseIntArray visiblecheck ;//用来记录是否显示checkBox
        public  SparseBooleanArray ischeck;
        private TextView txtcount;
        public Adapter(Context context,TextView txtcount)
        {
            this.context = context;
            this.txtcount = txtcount;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = new SparseArray<View>();
            visiblecheck = new SparseIntArray();
            ischeck      = new SparseBooleanArray();
            /*for(int i=0;i<lists.size();i++){
            	if(isMulChoice){
            		ischeck.put(i, false);
            		visiblecheck.put(i, CheckBox.VISIBLE);
            		Log.i("BBBBBBBBBBBBBBBBBB", visiblecheck.get(i)+"");
            	}else{
            		ischeck.put(i, false);
            		visiblecheck.put(i, CheckBox.INVISIBLE);
            		Log.i("AAAAAAAAAAAAAAAA", visiblecheck.get(i)+"");
            	}
            }*/
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return lists.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return lists.get(position);
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = mView.get(position);
            if(view==null)
            {
                view = inflater.inflate(R.layout.activity_later_item, null);
                TextView txt = (TextView)view.findViewById(R.id.later_item_txtName);
                TextView date = (TextView)view.findViewById(R.id.later_item_txtDate);
                final CheckBox ceb = (CheckBox)view.findViewById(R.id.later_item_check);
                
                txt.setText(lists.get(position).getF_content());
                date.setText(lists.get(position).getF_date());
                
                ceb.setChecked(ischeck.get(position));
                if(isMulChoice){
                	ceb.setVisibility(View.VISIBLE);
                }else {
                	ceb.setVisibility(View.INVISIBLE);
				}
                //ceb.setVisibility(visiblecheck.get(position));
                //Log.i("CCCCCCCCCCCCC", visiblecheck.get(position)+"");
                
                view.setOnLongClickListener(new Onlongclick());
                
                view.setOnClickListener(new OnClickListener() {
                    
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if(isMulChoice){
                            if(ceb.isChecked()){
                                ceb.setChecked(false);
                                selectid.remove(lists.get(position));
                            }else{
                                ceb.setChecked(true);
                                selectid.add(lists.get(position));
                            }
                            txtcount.setText("共选择了"+selectid.size()+"项");
                            //Log.i("CCCCCCCCCCCCC", selectid.toString());
                        }else {
                            Toast.makeText(context, "点击了"+lists.get(position).getF_id(), Toast.LENGTH_LONG).show();
                            myDialog = new AlertDialog.Builder(LaterActivity.this).create();  
                            myDialog.show();  
                            myDialog.getWindow().setContentView(R.layout.activity_later_dialog);
                            TextView tv_title = (TextView) myDialog.getWindow().findViewById(R.id.alert_later_dialog_text2);
                            tv_title.setText(lists.get(position).getF_content());  
                            myDialog.getWindow()  
                                .findViewById(R.id.later_dialog_dis)  
                                .setOnClickListener(new View.OnClickListener() {  
                                @Override  
                                public void onClick(View v) {  
                                    myDialog.dismiss();  
                                }  
                            });  
                        
                        }
                    }
                });
                
                mView.put(position, view);
            }
            return view;
        }
        
        class Onlongclick implements OnLongClickListener{

            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                
                isMulChoice = true;
                selectid.clear();
                layout.setVisibility(View.VISIBLE);
                for(int i=0;i<lists.size();i++)
                {
                    adapter.visiblecheck.put(i, CheckBox.VISIBLE);
                }
                adapter = new Adapter(context,txtcount);
                ptrListView.setAdapter(adapter);
                return true;
            }
        }
    }




	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		loadData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}




	
}
