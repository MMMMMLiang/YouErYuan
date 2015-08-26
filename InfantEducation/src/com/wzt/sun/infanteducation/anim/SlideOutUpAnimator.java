package com.wzt.sun.infanteducation.anim;

import com.nineoldandroids.animation.ObjectAnimator;

import android.view.View;
import android.view.ViewGroup;

public class SlideOutUpAnimator extends BaseViewAnimator {
    @Override
    public void prepare(View target) {
        ViewGroup parent = (ViewGroup)target.getParent();
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 1, 0),
                ObjectAnimator.ofFloat(target,"translationY",0,-parent.getHeight()/2)
        );
    }
}
