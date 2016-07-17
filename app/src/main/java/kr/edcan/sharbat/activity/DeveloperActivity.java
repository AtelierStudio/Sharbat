package kr.edcan.sharbat.activity;

import android.content.Context;
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
import android.widget.Toast;

import java.util.ArrayList;

import kr.edcan.sharbat.R;

public class DeveloperActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        setDefault();
        setAppbarLayout();
    }

    private void setDefault() {
        listView = (ListView) findViewById(R.id.developer_listview);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Re:모콘부터 시작하는 이세계 밤샘에서 제작함");
        arr.add("Android Client Developer - 20409 오준석");
        arr.add("iOS Client Developer - 20418 정준우");
        arr.add("iOS UI/UX Designer - 21111 구창림");
        arr.add("Android UI/UX Designer - 11015 김태윤");
        listView.setAdapter(new DeveloperAdapter(getApplicationContext(), arr));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                    case 2:
                        Toast.makeText(DeveloperActivity.this, "모콘 두개하지 마세요 죽어요 죽어", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(DeveloperActivity.this, "정준우 그만자", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(DeveloperActivity.this, "기획자님 멋져요!!", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(DeveloperActivity.this, "디자이너님 멋져요!", Toast.LENGTH_SHORT).show();
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
        getSupportActionBar().setTitle("개발자 정보");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

class DeveloperAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    ArrayList<String> arr;

    public DeveloperAdapter(Context c, ArrayList<String> arrayList) {
        super(c, 0, arrayList);
        this.arr = arrayList;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.settings_listview_content, null);
        String s = arr.get(position);
        TextView title = (TextView) view.findViewById(R.id.settings_listview_text);
        title.setText(s);
        return view;
    }
}
