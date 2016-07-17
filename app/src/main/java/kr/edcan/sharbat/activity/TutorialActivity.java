package kr.edcan.sharbat.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import kr.edcan.sharbat.R;

public class TutorialActivity extends AppCompatActivity {

    ViewPager pager;
    int page2CurPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        setDefault();
    }

    private void setDefault() {
        pager = (ViewPager) findViewById(R.id.tutorial_viewPager);
        pager.setAdapter(new PagerAdapterClass(getApplicationContext()));
        pager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
    }

    class PagerAdapterClass extends PagerAdapter implements View.OnClickListener {
        private LayoutInflater mInflater;

        public PagerAdapterClass(Context c) {
            super();
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object instantiateItem(final View pager, int position) {
            View v = null;
            // TODO: Set each page's view
            switch (position) {
                case 0:
                    v = mInflater.inflate(R.layout.tuto_1, null);
                    break;
                case 1:
                    v = mInflater.inflate(R.layout.tuto_2, null);
                    break;
                case 2:
                    v = mInflater.inflate(R.layout.tuto_3, null);
                    break;
                case 3:
                    v = mInflater.inflate(R.layout.tuto_4, null);
                    break;
                case 4:
                    v = mInflater.inflate(R.layout.tuto_5, null);
                    break;
                case 5:
                    v = mInflater.inflate(R.layout.tuto_6, null);
                    break;
            }
            setPage(v, position, pager);
            ((ViewPager) pager).addView(v, 0);
            return v;
        }

        public void setPage(View v, int position, final View pager) {
            switch (position) {
                case 0:
                    TextView next = (TextView) v.findViewById(R.id.tuto_1_next);
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((ViewPager) pager).setCurrentItem(((ViewPager) pager).getCurrentItem() + 1, true);
                        }
                    });
                    break;
                case 1:
                    page2CurPos = -1;
                    TextView google = (TextView) v.findViewById(R.id.tuto_2_google);
                    TextView naver = (TextView) v.findViewById(R.id.tuto_2_naver);
                    TextView worksmail = (TextView) v.findViewById(R.id.tuto_2_worksmail);
                    google.setOnClickListener(this);
                    naver.setOnClickListener(this);
                    worksmail.setOnClickListener(this);
            }
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

        @Override
        public void startUpdate(View arg0) {
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tuto_2_google:
                    page2CurPos = 0;
                    break;
                case R.id.tuto_2_naver:
                    page2CurPos = 1;
                    break;
                case R.id.tuto_2_worksmail:
                    page2CurPos = 2;
                    break;
            }
        }
    }
}
