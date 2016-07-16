package kr.edcan.sharbat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import kr.edcan.sharbat.R;
import kr.edcan.sharbat.models.MainRecycleData;

/**
 * Created by MalangDesktop on 2016-06-04.
 */
public class CommonRecyclerAdapter extends RecyclerView.Adapter<CommonRecyclerAdapter.ViewHolder> {

    /*
    * 0 : RankingShowActivity - Sort by Rating
    * 1 : RankingShowActivity - Sort by VisitCount
    * 2 : RecommendActivity - Full View
    * 3: NewRaidActivity - SearchView
    **/
    Context context;
    ArrayList<MainRecycleData> arrayList;
    MainRecycleData data;

    public CommonRecyclerAdapter(Context context, ArrayList<MainRecycleData> items) {
        this.context = context;
        this.arrayList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_content, null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        data = arrayList.get(position);
        if (position == 0) holder.header.setVisibility(View.VISIBLE);
        else {
            holder.foreground.setVisibility(View.VISIBLE);
            holder.from.setText(data.getFrom());
            holder.address.setText(data.getAddress());
            holder.title.setText(data.getTitle());
            holder.content.setText(data.getContent());
            holder.date.setText(data.getDate().toString());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView from, address, title, content, date;
        RelativeLayout foreground, header;

        public ViewHolder(View itemView) {
            super(itemView);
            from = (TextView) itemView.findViewById(R.id.home_content_from);
            address = (TextView) itemView.findViewById(R.id.home_content_address);
            title = (TextView) itemView.findViewById(R.id.home_content_title);
            content = (TextView) itemView.findViewById(R.id.home_content_content);
            date = (TextView) itemView.findViewById(R.id.home_content_date);
            foreground = (RelativeLayout) itemView.findViewById(R.id.home_content_foreground);
            header = (RelativeLayout) itemView.findViewById(R.id.home_content_header);
            foreground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "sibalblaalall", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}