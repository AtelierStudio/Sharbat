package kr.edcan.sharbat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.edcan.sharbat.R;

/**
 * Created by MalangDesktop on 2016-05-08.
 */
public class SettingsAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    ArrayList<String> arr;

    public SettingsAdapter(Context c, ArrayList<String> arrayList) {
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
