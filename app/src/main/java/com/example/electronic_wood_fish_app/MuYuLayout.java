package com.example.electronic_wood_fish_app;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Random;

public class MuYuLayout extends BaseLinearLayout implements View.OnClickListener {
    private static final String TAG = MuYuLayout.class.getSimpleName();
    private static final int[] ANIMATION_STATES = {0, 50, 100, 200, 600, 650, 400, 450, 550, 700};
    private static final int ANIMATION_DURATION = 3800; // ms
    private static final float START_TRANSLATION_X = 620f;
    private static final float START_TRANSLATION_Y = 880f;

    private ImageButton mMuYu;
    private RelativeLayout mRelativeLayout;
    private TextView mTextNumber;
    private int number = 0;

    public MuYuLayout(Context context) {
        super(context);
    }

    public MuYuLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        mMuYu = findViewById(R.id.muyu);
        mMuYu.setOnClickListener(this);
        mRelativeLayout = findViewById(R.id.muyu_layout);
        mTextNumber = findViewById(R.id.Number);
        mTextNumber.setVisibility(VISIBLE);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.muyu_vie_layout;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.muyu) {
            performRandomAnimation();
            number++;
            HandlerUtils.run(() -> {
                mTextNumber.setText("已积累功德：" + number);
                mTextNumber.setVisibility(VISIBLE);
            });
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        int[] location = new int[2];
        mMuYu.getLocationOnScreen(location);
        Log.e("LOCATION", location[0] + "->" + location[1]);
    }

    private void performRandomAnimation() {
        Log.d(TAG, "performRandomAnimation");
        Random random = new Random();
        int state = ANIMATION_STATES[random.nextInt(ANIMATION_STATES.length)];
        playAnimation(state);
    }

    private void playAnimation(float state) {
        Log.d(TAG, "playAnimation");
        TextView textView = new TextView(getContext());
        mRelativeLayout.addView(textView);
        textView.setText("功德+1");
        textView.setTextSize(20);
        textView.setTextColor(Color.WHITE);

        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator animX = ObjectAnimator.ofFloat(textView, "translationX", START_TRANSLATION_X, state);
        ObjectAnimator animY = ObjectAnimator.ofFloat(textView, "translationY", START_TRANSLATION_Y, 0f);
        ObjectAnimator animAlpha = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0f);

        animSet.playTogether(animX, animY, animAlpha);
        animSet.setDuration(ANIMATION_DURATION);
        animSet.start();
    }
}
