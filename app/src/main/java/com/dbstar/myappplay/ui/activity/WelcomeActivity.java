package com.dbstar.myappplay.ui.activity;

import android.content.Intent;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.di.component.AppComponent;
import com.eftimoff.androipathview.PathView;

import butterknife.BindView;

/**
 * Created by wh on 2017/8/31.
 */

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welc_pathview)
    PathView mPathView;

    @Override
    int setLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void init() {
        mPathView.getPathAnimator()
                .delay(100)
                .duration(5000)
                .interpolator(new AccelerateDecelerateInterpolator())
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        jump();
                    }
                })
                .start();
    }

    private void jump() {
        startActivity(new Intent(this,MainActivity.class));
    }

}
