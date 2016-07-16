package kr.edcan.sharbat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import kr.edcan.sharbat.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int tab_on[] = {R.drawable.btn_tab_mailbox_on, R.drawable.btn_tab_home_on, R.drawable.btn_tab_settings_on};
    int tab_off[] = {R.drawable.btn_tab_mailbox_off, R.drawable.btn_tab_home_off, R.drawable.btn_tab_settings_off};
    ImageView tab1, tab2, tab3;
    ImageView[] tabs;

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
        tabs = new ImageView[]{tab1, tab2, tab3};
        for (ImageView view : tabs) view.setOnClickListener(this);
        setTab(1);
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
        switch (view.getId()){
            case R.id.main_tab_mailbox:
                setTab(0);
                break;
            case R.id.main_tab_home:
                setTab(1);
                break;
            case R.id.main_tab_settings:
                setTab(2);
                break;

        }
    }
}
