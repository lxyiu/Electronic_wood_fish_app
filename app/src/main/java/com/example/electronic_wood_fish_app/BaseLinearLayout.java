package com.example.electronic_wood_fish_app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


public abstract class BaseLinearLayout extends LinearLayout {
    protected Context mContext;

    public BaseLinearLayout(Context context) {
        super(context);
    }

    public BaseLinearLayout(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = getContext();
        View.inflate(mContext, getLayoutId(), this);
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();
}
