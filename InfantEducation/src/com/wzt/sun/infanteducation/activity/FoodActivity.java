package com.wzt.sun.infanteducation.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.view.CustomProgressDialog;

/**
 * 这是今日食谱页面
 * @author sun
 *
 */
public class FoodActivity extends BaseActivity {
	private ImageView titlebar_back;
	private CustomProgressDialog progressDialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food);
		
		titlebar_back = (ImageView) findViewById(R.id.titlebar_btn_back);
		startProgressDialog();
		titlebar_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				FoodActivity.this.finish();
				
			}
		});
	}
	
	private void startProgressDialog(){
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("正在加载中...");
        }
         
        progressDialog.show();
    }
	
	private void stopProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
