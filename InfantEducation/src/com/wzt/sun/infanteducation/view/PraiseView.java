package com.wzt.sun.infanteducation.view;

import com.wzt.sun.infanteducation.R;
import com.wzt.sun.infanteducation.anim.AnimHelper;
import com.wzt.sun.infanteducation.anim.PulseAnimator;
import com.wzt.sun.infanteducation.anim.SlideOutUpAnimator;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PraiseView extends FrameLayout implements Checkable{
	protected OnPraisCheckedListener praiseCheckedListener;
	protected CheckedImageView imageView;
	protected TextView textView;
	protected int padding;

	public PraiseView(Context context) {
		super(context);
		initalize();
	}

	public PraiseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initalize();
	}

	protected void initalize() {
		setClickable(true);
		imageView = new CheckedImageView(getContext());
		imageView.setImageResource(R.drawable.blog_praise_selector);
		FrameLayout.LayoutParams flp = new LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT,Gravity.CENTER);
		addView(imageView, flp);

		textView = new TextView(getContext());
		textView.setTextSize(10);
		textView.setText("+1");
		textView.setTextColor(Color.parseColor("#A24040"));
		textView.setGravity(Gravity.CENTER);
		addView(textView, flp);
		textView.setVisibility(View.GONE);
	}
	
	@Override
	public boolean performClick() {
		checkChange();
		return super.performClick();
	}

	@Override
	public void toggle() {
		checkChange();
	}

	public void setChecked(boolean isCheacked) {
		imageView.setChecked(isCheacked);
	}

	public void checkChange() {
		if (imageView.isChecked) {
			imageView.setChecked(false);
		} else {
			imageView.setChecked(true);
			textView.setVisibility(View.VISIBLE);
			AnimHelper.with(new PulseAnimator()).duration(1000).playOn(imageView);
			AnimHelper.with(new SlideOutUpAnimator()).duration(1000).playOn(textView);
		}
		if (praiseCheckedListener != null) {
			praiseCheckedListener.onPraisChecked(imageView.isChecked);
		}
	}

	public boolean isChecked() {
		return imageView.isChecked;
	}

	public void setOnPraisCheckedListener(OnPraisCheckedListener praiseCheckedListener) {
		this.praiseCheckedListener = praiseCheckedListener;
	}
	
	public interface OnPraisCheckedListener{
		void onPraisChecked(boolean isChecked);
	}
}

