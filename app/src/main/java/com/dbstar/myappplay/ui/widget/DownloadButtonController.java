package com.dbstar.myappplay.ui.widget;

import android.content.Context;
import android.os.Environment;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.AppInfo;
import com.dbstar.myappplay.common.rx.RxSchedulers;
import com.dbstar.myappplay.common.util.AppUtils;
import com.dbstar.myappplay.common.util.LogUtils;
import com.dbstar.myappplay.common.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

/**
 * Created by dswang on 2017/11/25.
 */

public class DownloadButtonController {
    private RxDownload mRxDownload;

    public DownloadButtonController(RxDownload rxDownload) {
        mRxDownload = rxDownload;
    }

    public void handClick(final DownloadProgressButton button, final AppInfo appInfo) {
        isAppInstalled(button.getContext(), appInfo).flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {

            @Override
            public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent downloadEvent) throws Exception {
                if(downloadEvent.getFlag() == DownloadFlag.UN_INSTALL){
                    return isApkFileExsit(button.getContext(),appInfo);
                }
                return Observable.just(downloadEvent);
            }
        }).compose(RxSchedulers.<DownloadEvent>io_main()).subscribe(new DownloadConsumer(button, appInfo));
    }

    private void bindClick(final DownloadProgressButton button, AppInfo appInfo) {
        RxView.clicks(button).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                int flag = (int) button.getTag(R.id.tag_apk_flag);
                switch (flag) {
                    case DownloadFlag.INSTALLED:
                        LogUtils.e("dswang", "DownloadButtonController.accept.INSTALLED = " + "已安装-点击");
                        ToastUtils.showSafeToast(button.getContext(), "已安装");
                        break;
                    case DownloadFlag.UN_INSTALL:
                        LogUtils.e("dswang", "DownloadButtonController.accept.UN_INSTALL = " + "未安装-点击");
                        ToastUtils.showSafeToast(button.getContext(), "未安装");
                        break;
                    case DownloadFlag.FILE_EXIST:
                        LogUtils.e("dswang", "DownloadButtonController.accept.UN_INSTALL = " + "已下载-文件存在");
                        ToastUtils.showSafeToast(button.getContext(), "文件存在，需要安装");
                        break;
                    case DownloadFlag.NORMAL:
                        LogUtils.e("dswang", "DownloadButtonController.accept.NORMAL = " + "未下载-文件不存在");
                        ToastUtils.showSafeToast(button.getContext(), "文件不存在，需要点击下载");
                        break;
                }
            }
        });
    }

    public Observable<DownloadEvent> isApkFileExsit(Context context, AppInfo appInfo) {
        //        String path = ACache.get(context).getAsString(Constant.APK_DOWNLOAD_DIR) +  File.separator + appInfo.getReleaseKeyHash();
        String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS) + File.separator + appInfo.getReleaseKeyHash()+".apk";
        File file = new File(path);
        LogUtils.e("dswang", "DownloadButtonController.isApkFileExsit.path = " + path);
        LogUtils.e("dswang", "DownloadButtonController.isApkFileExsit.path = " + file.exists());
        DownloadEvent event = new DownloadEvent();
        event.setFlag(file.exists() ? DownloadFlag.FILE_EXIST : DownloadFlag.NORMAL);
        return Observable.just(event);
    }

    public Observable<DownloadEvent> isAppInstalled(Context context, AppInfo appInfo) {
        DownloadEvent event = new DownloadEvent();
        event.setFlag(AppUtils.isInstalled(context, appInfo.getPackageName()) ? DownloadFlag.INSTALLED : DownloadFlag.UN_INSTALL);
        return Observable.just(event);
    }

    class DownloadConsumer implements Consumer<DownloadEvent> {

        DownloadProgressButton mButton;
        AppInfo mAppInfo;

        public DownloadConsumer(DownloadProgressButton button, AppInfo appInfo) {
            mButton = button;
            mAppInfo = appInfo;
        }

        @Override
        public void accept(@NonNull DownloadEvent downloadEvent) throws Exception {
            int flag = downloadEvent.getFlag();
            mButton.setTag(R.id.tag_apk_flag, flag);
            bindClick(mButton, mAppInfo);
            switch (flag) {
                case DownloadFlag.INSTALLED:
                    mButton.setText("运行");
                    break;
                case DownloadFlag.UN_INSTALL:
                    mButton.setText("下载");
                    break;
                case DownloadFlag.FILE_EXIST:
                    mButton.setText("安装");
                    break;
                case DownloadFlag.NORMAL:
                    mButton.setText("下载");
                    break;
            }

        }


    }

}
