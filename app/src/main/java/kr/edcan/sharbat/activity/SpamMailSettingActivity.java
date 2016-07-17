package kr.edcan.sharbat.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rey.material.widget.Switch;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import kr.edcan.sharbat.R;
import kr.edcan.sharbat.utils.DataManager;

public class SpamMailSettingActivity extends AppCompatActivity {

    ListView listView;
    Toolbar toolbar;
    DataManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spam_mail_setting);
        manager = new DataManager();
        manager.initializeManager(this);
        setDefault();
        setAppbarLayout();
    }

    private void setDefault() {
        listView = (ListView) findViewById(R.id.spammail_listview);
        ArrayList<String> arr = new ArrayList<>();
        String[] arrString = {"자동으로 스팸 메일 필터링 하기", "스팸 메일 자동으로 삭제", "사용자 지정 스팸으로 관리"};
        for (String s : arrString) {
            arr.add(s);
        }
        listView.setAdapter(new SpamListAdapter(getApplicationContext(), arr));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Switch filterSwitch = (Switch) view.findViewById(R.id.spamsetting_switch);
                filterSwitch.setChecked(!filterSwitch.isChecked());
                switch (i) {
                    case 0:
                        manager.save(DataManager.AUTO_FILTER, filterSwitch.isChecked());
                        break;
                    case 1:
                        manager.save(DataManager.AUTO_DELETE, filterSwitch.isChecked());
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), CustomSpamSetActivity.class));
                        break;
                }
            }
        });
    }

    private void setAppbarLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        getSupportActionBar().setTitle("스팸 메일에 대한 정책 설정");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

class SpamListAdapter extends ArrayAdapter<String> {
    Context c;
    LayoutInflater inflater;
    ArrayList<String> arr;
    DataManager manager;

    public SpamListAdapter(Context context, ArrayList<String> arr) {
        super(context, 0, arr);
        this.c = context;
        this.arr = arr;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        manager = new DataManager();
        manager.initializeManager(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.spam_settings_listview_content, null);
        TextView content = (TextView) view.findViewById(R.id.spamsetting_text);
        Switch filterSwitch = (Switch) view.findViewById(R.id.spamsetting_switch);
        String s = arr.get(position);
        content.setText(s);
        switch (position) {
            case 0:
                if (manager.getBoolean(DataManager.AUTO_FILTER)) {
                    filterSwitch.setChecked(true);
                } else filterSwitch.setChecked(false);
                break;
            case 1:
                if (manager.getBoolean(DataManager.AUTO_DELETE)) {
                    filterSwitch.setChecked(true);
                } else filterSwitch.setChecked(false);
                break;
            case 2:
                filterSwitch.setVisibility(View.GONE);
                break;
        }
        return view;
    }
}