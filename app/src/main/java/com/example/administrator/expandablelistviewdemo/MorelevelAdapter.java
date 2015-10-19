/**
 * 
 */
package com.example.administrator.expandablelistviewdemo;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.expandablelistviewdemo.util.LogWrapper;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Administrator
 *
 */
public class MorelevelAdapter extends BaseExpandableListAdapter {
	private List<CharperBean> list;
	private Context context;
	public MorelevelAdapter(Context context,List<CharperBean> list2) {
		this.context=context;
		this.list=list2;
	}
	@Override
	public int getGroupCount() {
		return null==this.list?0:this.list.size();
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		return null==this.list.get(groupPosition).getChildren()?0:this.list.get(groupPosition).getChildren().size();
//		return null==this.list.get(groupPosition).getChildren()?0:this.list.get(groupPosition).getChildren().size();
	}
	@Override
	public Object getGroup(int groupPosition) {
		return this.list.get(groupPosition);
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.list.get(groupPosition).getChildren().get(childPosition);
	}
	@Override
	public long getGroupId(int groupPosition) {
		return this.list.get(groupPosition).hashCode();
	}
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return this.list.get(groupPosition).getChildren().get(childPosition).hashCode();
	}
	@Override
	public boolean hasStableIds() {
		return false;
	}
	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
//		return getGenericView( this.list.get(groupPosition).getSection_name());

		View view = convertView;
		ViewHolderGroup holderGroup;
		if (view == null) {
			holderGroup = new ViewHolderGroup();
			if ("00".equals(this.list.get(groupPosition).getParent_code())) {//�׼�
				view = ((Activity) context).getLayoutInflater().inflate(
						R.layout.item_practice_chapters_group, null);
				holderGroup.imgExpand = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_expand);
				holderGroup.viewTop = view
						.findViewById(R.id.img_item_practice_chapters_group_top);
				holderGroup.viewBottom = view
						.findViewById(R.id.img_item_practice_chapters_group_bottom);
				holderGroup.txtCharptersLabel = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_label);
				holderGroup.txtPracticeTimes = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_times);
				holderGroup.imgEdit = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_edit);
			}else{//�μ��Լ����ͼ���û���ϱ߾ࣩ
				view = ((Activity) context).getLayoutInflater().inflate(
						R.layout.item_practice_chapters_group2, null);
				holderGroup.imgExpand = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_expand2);
				holderGroup.viewTop = view
						.findViewById(R.id.img_item_practice_chapters_group_top2);
				holderGroup.viewBottom = view
						.findViewById(R.id.img_item_practice_chapters_group_bottom2);
				holderGroup.txtCharptersLabel = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_label2);
				holderGroup.txtPracticeTimes = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_times2);
				holderGroup.imgEdit = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_edit2);
			}
			view.setTag(holderGroup);
		} else {
			holderGroup = (ViewHolderGroup) view.getTag();
		}
		if ("00".equals(this.list.get(groupPosition).getParent_code())) {//�׼�
			holderGroup.viewTop.setVisibility(View.INVISIBLE);
		}else
			holderGroup.viewTop.setVisibility(View.VISIBLE);

		if (isExpanded) {
			holderGroup.imgExpand.setImageResource(R.drawable.ic_close_chapter);
		}else{
			holderGroup.imgExpand.setImageResource(R.drawable.ic_open_chapter);
		}
			if (isExpanded||(!"00".equals(this.list.get(groupPosition).getParent_code())&&groupPosition!=this.list.size()-1)) {//ĩβ
		 holderGroup.viewBottom.setVisibility(View.VISIBLE);
		}else
			holderGroup.viewBottom.setVisibility(View.INVISIBLE);
		//û�����½ڣ����½�ǰ���չ����ͷ����
		if (null==this.list.get(groupPosition).getChildren()||
				0==this.list.get(groupPosition).getChildren().size()) {
			holderGroup.imgExpand.setImageResource(R.drawable.ic_circle_gray);
		}
		 
		holderGroup.txtCharptersLabel.setText(this.list.get(groupPosition).getSection_name());
		//  ��ѯ��ϰ����
//		DBWrapperHistoryListPractice practice = DBWrapperHistoryListPractice.getInstance(context);
//		 Map<Integer, Integer>timesCount = practice.checkCountItem(this.list.get(groupPosition).getId(),
//				 GetBaseData.data.mCurrentSubject.getId());
//		 Iterator<Entry<Integer, Integer>>   it = timesCount.entrySet().iterator();
//		  while (it.hasNext()) {
//		   Map.Entry entry = it.next();
//		   holderGroup.txtPracticeTimes.setText(
//				   context.getResources().getString(R.string.item_practice_times, entry.getKey(),entry.getValue()));
//		  }
		  holderGroup.imgEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
//				PracticeAcvtivity.practice.mDialogWaiting.show();
				//  ���Ƶ���¼�����ȡ��Ŀ��ǰ������
				LogWrapper.e("���½�", list.get(groupPosition).getSection_name());
//				queryQuest( list.get(groupPosition).getId(),list.get(groupPosition).getSection_name(),null);
				Toast.makeText(context, list.get(groupPosition).getId()+"==>"+list.get(groupPosition).getSection_name(), Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
//		if (null!=list.get(groupPosition).getChildren() && 0!=list.get(groupPosition).getChildren().size()) {
//			ExpandableListView expandableListView = getchild();			
//			expandableListView.setAdapter(new MorelevelAdapter(context, list.get(groupPosition).getChildren()));
//			return expandableListView;
//		}else return getGenericView( this.list.get(groupPosition).getChildren().get(childPosition).getSection_name());
		
		if (null!=list.get(groupPosition).getChildren().get(childPosition) &&
				null!=list.get(groupPosition).getChildren().get(childPosition).getChildren()&&
			0!=list.get(groupPosition).getChildren().get(childPosition).getChildren().size()) {
			ExpandableListView expandableListView = getchild();	
			List<CharperBean> dataList = new ArrayList<CharperBean>();
			if (0==childPosition) {//�Ӽ����һ�飬����ظ�
				dataList=list.get(groupPosition).getChildren();
			}else dataList=null;
			expandableListView.setAdapter(new MorelevelAdapter(context,dataList ));
			return expandableListView;
		} else {
			View view = convertView;
			ViewHolderChild holderChild;
			if (view == null) {
				holderChild = new ViewHolderChild();
				view = ((Activity) context).getLayoutInflater().inflate(
						R.layout.item_practice_chapters_group2, null);
				holderChild.imgExpand = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_expand2);
				holderChild.viewTop = view
						.findViewById(R.id.img_item_practice_chapters_group_top2);
				holderChild.viewBottom = view
						.findViewById(R.id.img_item_practice_chapters_group_bottom2);
				holderChild.txtCharptersLabel = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_label2);
				holderChild.txtPracticeTimes = (TextView) view
						.findViewById(R.id.txt_item_practice_chapters_group_times2);
				holderChild.imgEdit = (ImageView) view
						.findViewById(R.id.img_item_practice_chapters_group_edit2);
				view.setTag(holderChild);
			} else {
				holderChild = (ViewHolderChild) view.getTag();
			}
			if (isLastChild
					&& childPosition == this.list.get(groupPosition)
							.getChildren().size() - 1//���һ��child
							&& (groupPosition == this.list.size() - 1//����Ҳ�����һ��
					|| "00".equals(this.list.get(groupPosition)
							.getParent_code()))
							) {
				holderChild.viewBottom.setVisibility(View.INVISIBLE);
			} else {
				holderChild.viewBottom.setVisibility(View.VISIBLE);
			}
			holderChild.viewTop.setVisibility(View.VISIBLE);
//			//û�����½ڣ����½�ǰ���չ����ͷ����
				holderChild.imgExpand.setVisibility(View.VISIBLE);//��ʾ
			holderChild.imgExpand.setImageResource(R.drawable.ic_circle_gray);
			holderChild.txtCharptersLabel.setText(this.list.get(groupPosition).getChildren().get(childPosition).getSection_name());
			//  ��ѯ��ϰ����
//			DBWrapperHistoryListPractice practice = DBWrapperHistoryListPractice.getInstance(context);
//			 Map<Integer, Integer>timesCount = practice.checkCountItem(this.list.get(groupPosition).getChildren().get(childPosition).getId(),
//					 GetBaseData.data.mCurrentSubject.getId());
//			 Iterator<Entry<Integer, Integer>>   it = timesCount.entrySet().iterator();
//			  while (it.hasNext()) {
//			   Map.Entry entry = it.next();
//			   holderChild.txtPracticeTimes.setText(
//					   context.getResources().getString(R.string.item_practice_times, entry.getKey(),entry.getValue()));
//			  }
			  holderChild.imgEdit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
//					PracticeAcvtivity.practice.mDialogWaiting.show();
					//  ���Ƶ���¼�����ȡ��Ŀ��ǰ������
					LogWrapper.e("���½�",list.get(groupPosition).getSection_name());
//					queryQuest( list.get(groupPosition).getChildren().get(childPosition).getId(),
//							list.get(groupPosition).getChildren().get(childPosition).getSection_name(),null);
					Toast.makeText(context, list.get(groupPosition).getChildren().get(childPosition).getId()+"==>"
+list.get(groupPosition).getChildren().get(childPosition).getSection_name(), Toast.LENGTH_SHORT).show();
				}
			});
			return view;
		}
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
	}
	
	public TextView getGenericView(String string) {
		// Layout parameters for the ExpandableListView

		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, 64);
		TextView text = new TextView(context);
		text.setLayoutParams(layoutParams);
		// Center the text vertically

		text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		// Set the text starting position

		 text.setPadding(36, 0, 0, 0);
		text.setText(string);
		return text;
	}
	
	public ExpandableListView getchild() {
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		MyExpandableListView text = new MyExpandableListView(
				context);
		text.setDivider(null);
//		text.setCacheColorHint(context.getResources().getColor(R.color.transparent));
		text.setGroupIndicator(null);
		text.setLayoutParams(layoutParams);
		text.setPadding(0, 0, 0, 0);
		return text;
	}

//	void queryQuest(int sectionIds, String grpName, String chdName) {
//		LogWrapper.e("EXAM", "�½���ϰ");
//		Intent intentChapter = new Intent();
//		intentChapter.putExtra("dis", Constants.RECEIVER_GET_CHAPTER_QUSET_LIST);
//		intentChapter.putExtra("sectionIds", sectionIds);
//		intentChapter.putExtra("grpName", grpName);
//		intentChapter.putExtra("chdName", chdName);
//		intentChapter.setAction(Constants.RECEIVER_EXAM_PAPER_INFO);// action���������ͬ
//		context.sendBroadcast(intentChapter);
//		LogWrapper.d("sendBroadcast", "intentChapter");
//	}
	class ViewHolderGroup {
		ImageView imgExpand,imgEdit;
		TextView txtCharptersLabel, txtPracticeTimes;
		View viewTop ,viewBottom;
	}

	class ViewHolderChild {
		ImageView imgExpand,imgEdit;
		TextView txtCharptersLabel, txtPracticeTimes;
		View viewTop,viewBottom;
	}
}
