package com.example.administrator.expandablelistviewdemo;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter
{
    Activity activity;
    private List<String> groupArray;
    private List<List<String>> childArray;


    public ExpandableAdapter(Activity a)
    {
        activity = a;


        groupArray = new ArrayList<String>();
        childArray = new ArrayList<List<String>>();

        groupArray.add("group");
        groupArray.add("group2");

        List<String> tempArray = new ArrayList<String>();
        tempArray.add("child1");
        tempArray.add("child2");
        tempArray.add("child3");

        for(int index = 0; index <groupArray.size(); ++index)
        {
            childArray.add(tempArray);
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return childArray.get(groupPosition).get(childPosition);
    }
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }
    public int getChildrenCount(int groupPosition)
    {
        return childArray.get(groupPosition).size();
    }
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {
        String string = childArray.get(groupPosition).get(childPosition);
        return getGenericView(string);
    }
    // group method stub
    public Object getGroup(int groupPosition)
    {
        return groupArray.get(groupPosition);
    }
    public int getGroupCount()
    {
        return groupArray.size();
    }
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent)
    {
//        String string = groupArray.get(groupPosition);
//        parent.setClickable(false);
        View view = activity.getLayoutInflater().inflate(R.layout.group_item, parent, false);
        return view;
//        return getGenericGroupView(string);
    }
    // View stub to create Group/Children 's View
    public TextView getGenericView(String string)
    {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 64);
        TextView text = new TextView(activity);
        text.setLayoutParams(layoutParams);
        // Center the text vertically
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        text.setPadding(36, 0, 0, 0);
        text.setText(string);
        return text;
    }

    // View stub to create Group
    public TextView getGenericGroupView(String string)
    {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 64);
        TextView text = new TextView(activity);
        text.setLayoutParams(layoutParams);
        // Center the text vertically
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        text.setPadding(36, 0, 0, 0);
        text.setText(string);
        return text;
    }

    public boolean hasStableIds()
    {
        return false;
    }
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    class childholder{
        TextView tv;
    }

    class groupholder{
        TextView tv;
    }
}
