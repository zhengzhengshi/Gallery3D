package com.zhengzs.Gallary3D;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<View> mList = new ArrayList<>();
    private static final int GALLARY_SIZE = 10;
    private static final int[] GALLARY_ID_ARRAY = {
            R.drawable.view1, R.drawable.view2, R.drawable.view3, R.drawable.view4, R.drawable.view5, R.drawable.view6, R.drawable.view7,
            R.drawable.view8, R.drawable.view9, R.drawable.view10};
    private static final int ROTATE_ANGLE =45;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager);
        ImageView imageView;
        for (int i = 0; i < GALLARY_SIZE; i++) {
            imageView = new ImageView(this);
            imageView.setImageResource(GALLARY_ID_ARRAY[i]);
            mList.add(imageView);
        }
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(mList.get(position));
                return mList.get(position);
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mList.get(position));
            }
            @Override
            public float getPageWidth(int position) {
                return (float) 0.2;
            }
        });
        mViewPager.setPageTransformer(true,new ScalePageTransformer());
    }
    public class ScalePageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            //TODO:recycle the view.
            float positionGap = 1.0f / 5;
            int pageWidth = page.getWidth();
            if(position<positionGap){
                page.setScaleX(1.0f);
                page.setScaleY(1.0f);
                page.setAlpha(0.5f);
                page.setTranslationX(-page.getWidth()/2);
                page.setRotationY(ROTATE_ANGLE);
            } else if(position<=2*positionGap){
                page.setScaleX(position/positionGap);
                page.setScaleY(position/positionGap);
                page.setAlpha(0.5f*position/positionGap);
                page.setTranslationX(pageWidth*position/(2*positionGap)-pageWidth);
                page.setRotationY(2*ROTATE_ANGLE-position*ROTATE_ANGLE/positionGap);
            } else if(position<=3*positionGap){
                page.setScaleX(4-position/positionGap);
                page.setScaleY(4-position/positionGap);
                page.setAlpha(2-0.5f*position/positionGap);
                page.setTranslationX(pageWidth*position/(2*positionGap)-pageWidth);
                page.setRotationY(2*ROTATE_ANGLE-position*ROTATE_ANGLE/positionGap);
            }else {
                page.setScaleX(1.0f);
                page.setScaleY(1.0f);
                page.setAlpha(0.5f);
                page.setTranslationX(page.getWidth()/2);
                page.setRotationY(-ROTATE_ANGLE);
            }
        }
    }
}
