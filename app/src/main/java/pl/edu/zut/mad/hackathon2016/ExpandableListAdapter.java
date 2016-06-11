package pl.edu.zut.mad.hackathon2016;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import pl.edu.zut.mad.hackathon2016.model.Entry;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> days;
    private HashMap<String, List<Entry>> hours;

    public ExpandableListAdapter(Context context, List<String> days, HashMap<String, List<Entry>> hours) {
        this.context = context;
        this.days = days;
        this.hours = hours;
    }

    @Override
    public int getGroupCount() {
        return days.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<Entry> entries = hours.get(days.get(groupPosition));
        return entries == null ? 0 : entries.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return days.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hours.get(days.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.reservation_day_item, parent, false);
        }

        String day = (String) getGroup(groupPosition);
        TextView daysView = (TextView) convertView.findViewById(R.id.day_of_week);
        daysView.setText(day);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.reservation_hour_item, parent, false);
        }

        Entry entry = (Entry) getChild(groupPosition, childPosition);
        String hour = entry.getTime();
        TextView hourView = (TextView) convertView.findViewById(R.id.hour_of_day);
        TextView weatherView = (TextView) convertView.findViewById(R.id.weather_icon);
        hourView.setText(hour);
        weatherView.setTypeface(Typeface.createFromAsset(context.getAssets(), "weather.ttf"));
        if (!entry.isReserved()) {
            ImageView reservedIcon = (ImageView) convertView.findViewById(R.id.reserved_icon);
            reservedIcon.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
