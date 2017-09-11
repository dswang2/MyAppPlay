package com.dbstar.myappplay.ui.fragment;

import android.Manifest;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dbstar.myappplay.R;
import com.dbstar.myappplay.di.component.AppComponent;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by wh on 2017/6/6.
 */
public class CategoryFragment extends ProgressFragment {


    @BindView(R.id.category_tv_title)
    Button categoryTvTitle;

    @Override
    protected void init() {

        rxpermissionTest();

    }

    private void rxpermissionTest() {
        categoryTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions rxPermission = new RxPermissions(getActivity());
                rxPermission
                        .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_CALENDAR,
                                Manifest.permission.READ_CALL_LOG,
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.READ_SMS,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.CAMERA,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.SEND_SMS)
                        .subscribe(new Action1<Permission>() {
                            @Override
                            public void call(Permission permission) {
                                if (permission.granted) {
                                    // 用户已经同意该权限
                                    Log.e("CategoryFragment", "accept(CategoryFragment.java:52) " + permission.name + " is granted.");
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                    Log.e("CategoryFragment", "accept(CategoryFragment.java:56) " + permission.name + " is denied. More info should be provided.");
                                } else {
                                    // 用户拒绝了该权限，并且选中『不再询问』
                                    Log.e("CategoryFragment", "accept(CategoryFragment.java:56) " + permission.name + " is denied.");
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onEmptyClick() {

    }

    @Override
    public String getTitle() {
        return "分类";
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.frag_category;
    }




}
