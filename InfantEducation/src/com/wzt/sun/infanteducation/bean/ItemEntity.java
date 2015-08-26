package com.wzt.sun.infanteducation.bean;

public class ItemEntity {
	/**
	 * 主题ID
	 */
	private int th_id;
	/**
	 * 所属班级
	 */
	private int th_classes;
	/**
	 * 发起人ID
	 */
	private int th_authr;
	/**
	 * 发起人头像
	 */
	private String th_image;
	/**
	 * 发起人姓名
	 */
	private String th_name;
	/**
	 * 发起日期
	 */
	private String th_date;
	/**
	 * 主题内容
	 */
	private String th_content;
	/**
	 * 主题附件
	 */
	private String th_accessory;
	/**
	 * 查看权限
	 */
	private String th_Vp;
	/**
	 * 点赞数量
	 */
	private int th_zan;
	/**
	 * 回复数量
	 */
	private int th_num;
	public ItemEntity() {
		super();
	}
	public int getTh_id() {
		return th_id;
	}
	public void setTh_id(int th_id) {
		this.th_id = th_id;
	}
	public int getTh_classes() {
		return th_classes;
	}
	public void setTh_classes(int th_classes) {
		this.th_classes = th_classes;
	}
	public int getTh_authr() {
		return th_authr;
	}
	public void setTh_authr(int th_authr) {
		this.th_authr = th_authr;
	}
	public String getTh_date() {
		return th_date;
	}
	public void setTh_date(String th_date) {
		this.th_date = th_date;
	}
	public String getTh_content() {
		return th_content;
	}
	public void setTh_content(String th_content) {
		this.th_content = th_content;
	}
	public String getTh_accessory() {
		return th_accessory;
	}
	public void setTh_accessory(String th_accessory) {
		this.th_accessory = th_accessory;
	}
	public String getTh_Vp() {
		return th_Vp;
	}
	public void setTh_Vp(String th_Vp) {
		this.th_Vp = th_Vp;
	}
	public int getTh_zan() {
		return th_zan;
	}
	public void setTh_zan(int th_zan) {
		this.th_zan = th_zan;
	}
	public int getTh_num() {
		return th_num;
	}
	public void setTh_num(int th_num) {
		this.th_num = th_num;
	}
	public String getTh_image() {
		return th_image;
	}
	public void setTh_image(String th_image) {
		this.th_image = th_image;
	}
	public String getTh_name() {
		return th_name;
	}
	public void setTh_name(String th_name) {
		this.th_name = th_name;
	}
	@Override
	public String toString() {
		return "ItemEntity [th_id=" + th_id + ", th_classes=" + th_classes + ", th_authr=" + th_authr + ", th_image="
				+ th_image + ", th_name=" + th_name + ", th_date=" + th_date + ", th_content=" + th_content
				+ ", th_accessory=" + th_accessory + ", th_Vp=" + th_Vp + ", th_zan=" + th_zan + ", th_num=" + th_num
				+ "]";
	}
	

}
