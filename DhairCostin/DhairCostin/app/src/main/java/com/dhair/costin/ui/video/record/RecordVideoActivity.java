package com.dhair.costin.ui.video.record;

import android.content.Context;
import android.support.annotation.Nullable;

import com.dhair.costin.R;
import com.dhair.costin.ui.base.activity.BaseMvpActivity;

/**
 * Creator: dengshengjin on 16/1/21 22:46
 * Email: deng.shengjin@zuimeia.com
 */
public class RecordVideoActivity extends BaseMvpActivity<RecordVideoPresenter> implements RecordVideoMvpView {
    @Nullable
    @Override
    protected RecordVideoPresenter createPresenter(Context context) {
        return new RecordVideoPresenter(context);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_video_record;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initWidgets() {

    }

    @Override
    protected void initActions() {

    }
}
