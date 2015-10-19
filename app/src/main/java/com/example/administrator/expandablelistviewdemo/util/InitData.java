package com.example.administrator.expandablelistviewdemo.util;

import com.example.administrator.expandablelistviewdemo.CharperBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Administrator
 * 
 */
public class InitData {

	/**
	 * @param object
	 * @return list
	 */
	public static ArrayList<CharperBean> GetCharperList(JSONObject object) {
		ArrayList<CharperBean> list = new ArrayList<CharperBean>();
		if (null != object) {
			if (object != null) {
				ArrayList<CharperBean> listcharper = GetSectionsList(object);// �����½��б�
				// ��ȡ���½�
				for (int i = 0; i < listcharper.size(); i++) {
					CharperBean item = listcharper.get(i);
					if ("00".equals(item.getParent_code())) {// ���½�
						List<CharperBean> child = new ArrayList<CharperBean>();
						for (int j = 0; j < listcharper.size(); j++) {
							CharperBean charper = listcharper.get(j);
							if (charper.getParent_code().equals(
									item.section_code)) {
								List<CharperBean> child1 = new ArrayList<CharperBean>();
								for (int k = 0; k< listcharper.size(); k++) {
									CharperBean charper1 = listcharper.get(k);
									if (charper1.getParent_code().equals(
											charper.section_code)) {
										List<CharperBean> child2 = new ArrayList<CharperBean>();
										for (int m = 0; m< listcharper.size(); m++) {
											CharperBean charper2 = listcharper.get(m);
											if (charper2.getParent_code().equals(
													charper1.section_code)) {
													child2.add(charper2);
											}
										}
										if (null!=child2&&0!=child2.size()) {
											charper1.setChildren(child2);
										}else charper1.setChildren(null);
										child1.add(charper1);
									}
								}
								if (null!=child1&&0!=child1.size()) {
								charper.setChildren(child1);
								}else charper.setChildren(null);
								child.add(charper);
							}
						}
						if (null!=child&&0!=child.size()) {
							item.setChildren(child);
						}else item.setChildren(null);
						list.add(item);
					}
				}
			}
		}
		return list;
	}
private static boolean hasIt(List<CharperBean> child,CharperBean item){
	for (int i = 0; i < child.size(); i++) {
		if (item.getId()==child.get(i).getId()) {
			return true;
		}
	}
	return false;
}
	private static ArrayList<CharperBean> GetSectionsList(JSONObject object) {
		ArrayList<CharperBean> list = new ArrayList<CharperBean>();
		CharperBean sectionsBean;
		JSONArray array;
		try {
			array = object.getJSONArray("sections");
			// LogWrapper.e("JSONArray", "JSONArray ����=" + array.length());
			for (int i = 0; i < array.length(); i++) {
				JSONObject array2Object = array.getJSONObject(i);
				sectionsBean = new CharperBean();
				sectionsBean.setId(array2Object.optInt("id"));// ID
				sectionsBean.setSection_code(array2Object
						.optString("section_code"));// �½ڴ���
				sectionsBean.setSection_name(array2Object
						.optString("section_name"));// �½�����
				sectionsBean.setParent_code(array2Object
						.optString("parent_code"));// �½ڸ�����
				list.add(sectionsBean);
			}
			// LogWrapper.e(TAG, list.toString());
			// LogWrapper.e(TAG, object.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
