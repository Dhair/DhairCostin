package com.dhair.costin.service;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.os.Build;

import com.dhair.costin.ui.home.HomeActivity;

import java.util.LinkedList;

/**
 * Creator: dengshengjin on 16/2/1 20:21
 * Email: deng.shengjin@zuimeia.com
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {
    private HomeActivity mActivity;

    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private final LinkedList<JobParameters> jobParamsMap = new LinkedList<JobParameters>();

    public void setUiCallback(HomeActivity activity) {
        mActivity = activity;
    }

    public void scheduleJob(JobInfo t) {
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(t);
    }
}
