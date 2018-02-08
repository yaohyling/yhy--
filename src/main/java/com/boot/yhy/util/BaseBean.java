package com.boot.yhy.util;

/**
 * 
 * ********************************************************** copyright by
 * 四川复兴科技有限公司 ========================================================== 模块介绍
 * version: v0.01. function: 基类
 * ========================================================== created by cao.zl
 * on 2017年3月8日上午11:10:28
 * 
 * @author cao.zl ==========================================================
 *         modified by [name] on [time] description:
 * 
 *
 ************************************************************
 *
 */
public class BaseBean {

	/** 创建时间 */
	public static Long created;
	/** 创建人 */
	public static Integer createdby;

	/** 修改时间 */
	public static Long updated;
	/** 修改人 */
	public static Integer updatedby;

	
	/** 是否有效 */
	public static String isactive;

	public static Long getCreated() {
		return created;
	}

	public static void setCreated(Long created) {
		BaseBean.created = created;
	}
	public static Integer getCreatedby() {
		return createdby;
	}

	public static void setCreatedby(Integer createdby) {
		BaseBean.createdby = createdby;
	}

	public static Integer getUpdatedby() {
		return updatedby;
	}

	public static void setUpdatedby(Integer updatedby) {
		BaseBean.updatedby = updatedby;
	}

	public static Long getUpdated() {
		return updated;
	}

	public static void setUpdated(Long updated) {
		BaseBean.updated = updated;
	}

	public static String getIsactive() {
		return isactive;
	}

	public static void setIsactive(String isactive) {
		BaseBean.isactive = isactive;
	}

}