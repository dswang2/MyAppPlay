package com.dbstar.myappplay.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dbstar.myappplay.R;
import com.dbstar.myappplay.bean.Category;
import com.dbstar.myappplay.common.imageloader.ImageLoader;

/**
 * Created by wh on 2017/10/18.
 */

public class CategoryAdapter extends BaseQuickAdapter <Category,BaseViewHolder>{

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    public CategoryAdapter() {
        super(R.layout.template_category);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        helper.setText(R.id.category_tv_name,item.getName());
        // 利用glide填充图片
        ImageLoader.load(baseImgUrl+item.getIcon(), (ImageView) helper.getView(R.id.category_iv_icon));

    }
}
