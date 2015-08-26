package com.wzt.sun.infanteducation.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

public class CheckedImageView extends ImageView implements Checkable{
	protected boolean isChecked;
	protected OnCheckedChangeListener onCheckedChangeListener;
	
	public static final int[] CHECKED_STATE_SET = { android.R.attr.state_checked };
	
	public CheckedImageView(Context context) {
		super(context);
		initialize();
	}

	public CheckedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	private void initialize() {
		isChecked = false;
	}

	@Override
	public boolean isChecked() {
		return isChecked;
	}

	@Override
	public void setChecked(boolean isChecked) {
		if (this.isChecked != isChecked) {
			this.isChecked = isChecked;
			refreshDrawableState();
			
			if (onCheckedChangeListener != null) {
				onCheckedChangeListener.onCheckedChanged(this, isChecked);
			}
		}
	}

	@Override
	public void toggle() {
		setChecked(!isChecked);
	}
	
	
	@Override
	public int[] onCreateDrawableState(int extraSpace) {
		int[] states = super.onCreateDrawableState(extraSpace + 1);
		if (isChecked()) {
			mergeDrawableStates(states, CHECKED_STATE_SET);
		}
		return states;
	}
	
	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		Drawable drawable = getDrawable();
		if (drawable != null) {
			int[] myDrawableState = getDrawableState();
			drawable.setState(myDrawableState);
			invalidate();
		}
	}
	
	public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
		this.onCheckedChangeListener = onCheckedChangeListener;
	}

	public static interface OnCheckedChangeListener {
		public void onCheckedChanged(CheckedImageView checkedImgeView, boolean isChecked);
	}
}

