package kr.edcan.sharbat.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.net.IDN;
import java.util.concurrent.ExecutionException;

import kr.edcan.sharbat.R;
import kr.edcan.sharbat.utils.DataManager;
import kr.edcan.sharbat.utils.MailParseHelper;

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

    class PagerAdapterClass extends PagerAdapter {
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
                    v = mInflater.inflate(R.layout.tutorial_2, null);
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
                    ImageView google = (ImageView) v.findViewById(R.id.tuto2_google);
                    ImageView naver = (ImageView) v.findViewById(R.id.tuto2_naver);
                    ImageView worksmail = (ImageView) v.findViewById(R.id.tuto2_worksmail);
                    google.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onPage2Click(0);
                        }
                    });
                    naver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onPage2Click(1);

                        }
                    });
                    worksmail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onPage2Click(2);
                        }
                    });
                    break;
                case 2:
                    TextView nextPage = (TextView) v.findViewById(R.id.go_right);
                    nextPage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((ViewPager) pager).setCurrentItem(((ViewPager) pager).getCurrentItem() + 1, true);
                        }
                    });
                    break;
                case 3:
                case 4:
                    TextView prev = (TextView) v.findViewById(R.id.go_left);
                    nextPage = (TextView) v.findViewById(R.id.go_right);
                    prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((ViewPager) pager).setCurrentItem(((ViewPager) pager).getCurrentItem() - 1, true);
                        }
                    });
                    nextPage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((ViewPager) pager).setCurrentItem(((ViewPager) pager).getCurrentItem() + 1, true);
                        }
                    });
                    break;
                case 5:
                    prev = (TextView) v.findViewById(R.id.go_left);
                    nextPage = (TextView) v.findViewById(R.id.go_right);
                    prev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((ViewPager) pager).setCurrentItem(((ViewPager) pager).getCurrentItem() - 1, true);
                        }
                    });
                    nextPage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    });

                    break;
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

        public void onPage2Click(int position) {
            page2CurPos = position;
            View custom = getLayoutInflater().inflate(R.layout.login_custom_view, null);
            final EditText id = (EditText) custom.findViewById(R.id.login_info_id);
            final EditText pw = (EditText) custom.findViewById(R.id.login_info_password);
            new MaterialDialog.Builder(TutorialActivity.this)
                    .title("로그인 정보 입력")
                    .customView(custom, false)
                    .positiveText("로그인")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            String idStr = id.getText().toString().trim();
                            String pwStr = pw.getText().toString().trim();
                            if (idStr.equals("")) id.setError("빈칸 없이 입력해주세요");
                            else if (pwStr.equals("")) pw.setError("빈칸 없이 입력해주세요");
                            else {
                                try {
                                    if (new MailParseHelper.LoginCheckClass(page2CurPos, idStr, pwStr).execute().get()) {
                                        Toast.makeText(TutorialActivity.this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show();
                                        DataManager manager = new DataManager();
                                        manager.initializeManager(getApplicationContext());
                                        manager.save(DataManager.ID, idStr);
                                        manager.save(DataManager.PW, pwStr);
                                        manager.save(DataManager.LOGIN_TYPE, page2CurPos);
                                        pager.setCurrentItem(pager.getCurrentItem() + 1, true);
                                        manager.save(DataManager.IS_FIRST, false);
                                    } else
                                        Toast.makeText(TutorialActivity.this, "인증에 실패했습니다.\n아이디나 비밀번호를 확인해 주시고, 사용중인 메일 사이트에서 POP/IMAP 외부메일을 허용하셨는지 확인해주세요.", Toast.LENGTH_SHORT).show();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {

                                }
                            }
                        }
                    }).show();
        }
    }
}
