package kr.edcan.sharbat.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import kr.edcan.sharbat.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int tab_on[] = {R.drawable.btn_tab_mailbox_on, R.drawable.btn_tab_home_on, R.drawable.btn_tab_settings_on};
    int tab_off[] = {R.drawable.btn_tab_mailbox_off, R.drawable.btn_tab_home_off, R.drawable.btn_tab_settings_off};
    ImageView tab1, tab2, tab3;
    ImageView[] tabs;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefault();
    }

    private void setDefault() {
        tab1 = (ImageView) findViewById(R.id.main_tab_mailbox);
        tab2 = (ImageView) findViewById(R.id.main_tab_home);
        tab3 = (ImageView) findViewById(R.id.main_tab_settings);
        pager = (ViewPager) findViewById(R.id.main_viewpager);
        pager.setAdapter(new PagerAdapterClass(this));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabs = new ImageView[]{tab1, tab2, tab3};
        for (ImageView view : tabs) view.setOnClickListener(this);
        setTab(1);
        pager.setCurrentItem(1);
    }

    private void setTab(int position) {
        if (position > tabs.length) return;
        for (int i = 0; i < tabs.length; i++) {
            if (i == position) {
                tabs[i].setImageResource(tab_on[i]);
            } else tabs[i].setImageResource(tab_off[i]);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_mailbox:
                setTab(0);
                pager.setCurrentItem(0);
                break;
            case R.id.main_tab_home:
                setTab(1);
                pager.setCurrentItem(1);
                break;
            case R.id.main_tab_settings:
                setTab(2);
                pager.setCurrentItem(2);
                break;

        }
    }
}

class PagerAdapterClass extends PagerAdapter {
    Context context;
    private LayoutInflater mInflater;

    public PagerAdapterClass(Context c) {
        super();
        mInflater = LayoutInflater.from(c);
        this.context = c;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(View pager, int position) {
        View v = null;
        switch (position) {
            case 0:
                v = mInflater.inflate(R.layout.main_first_page, null);
                setFirstPage(v);
                break;
            case 1:
                v = mInflater.inflate(R.layout.main_second_page, null);
                setSecondPage(v);
                break;
            case 2:
                v = mInflater.inflate(R.layout.main_third_page, null);
                setThirdPage(v);
                break;
        }
        ((ViewPager) pager).addView(v, 0);
        return v;
    }

    @Override
    public void destroyItem(View pager, int position, Object view) {
        ((ViewPager) pager).removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View pager, Object obj) {
        return pager == obj;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    void setFirstPage(View v) {
    }

    void setSecondPage(View v) {
        v = mInflater.inflate(R.layout.main_second_page, null);
        RecyclerView view = (RecyclerView) v.findViewById(R.id.main_recyclerView);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(context));
    }

    void setThirdPage(View v) {
        v = mInflater.inflate(R.layout.main_third_page, null);

    }
}

