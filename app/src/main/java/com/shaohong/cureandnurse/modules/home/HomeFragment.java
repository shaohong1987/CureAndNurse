package com.shaohong.cureandnurse.modules.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.shaohong.cureandnurse.BaseFragment;
import com.shaohong.cureandnurse.R;
import com.shaohong.cureandnurse.lib.MyGridView;
import com.shaohong.cureandnurse.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shaohong on 2017/4/25.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.common_grid_view)
    MyGridView myGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home,container,false);
        ButterKnife.bind(this,view);
        List<String> images=new ArrayList<>();
        List<String> titles=new ArrayList<>();
        images.add("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg");
        images.add("http://pic18.nipic.com/20111215/577405_080531548148_2.jpg");
        images.add("http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg");
        images.add("http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg");
        titles.add("图片1");
        titles.add("图片2");
        titles.add("图片3");
        titles.add("图片4");
        int[] imageRes = {
                R.drawable.i6,
                R.drawable.i7,
                R.drawable.i8
        };

        //定义图标下方的名称数组
         String[] name = {
                "通知",
                "新闻",
                "更多"
        };
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        int length = imageRes.length;
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("ItemImage", imageRes[i]);//添加图像资源的ID
            map.put("ItemText", name[i]);//按序号做ItemText
            list.add(map);
        }

        initBanner(images,titles);
        initGridView(list);
        return view;
    }

    //初始化轮播图片
    private void initBanner(List<String> images,List<String> titles){
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置轮播图片点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    //初始化九宫格
    private void initGridView(ArrayList<HashMap<String, Object>> list){
        SimpleAdapter adapter=new SimpleAdapter(
                getActivity(),
                list,
                R.layout.item_grid_view,
                new String[]{"ItemImage", "ItemText"},
                new int[]{R.id.img_image_view, R.id.title_text_view});
        myGridView.setAdapter(adapter);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
