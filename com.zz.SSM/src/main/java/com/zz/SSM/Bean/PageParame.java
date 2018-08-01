package com.zz.SSM.Bean;

import java.io.Serializable;

/**
 * 
 * @Title:PageParame
 * @Description:TODO(分页参数)
 * @Company:
 * @author zhou.zhang
 * @date 2018年7月10日 下午6:03:03
 */
public class PageParame implements Serializable {

	private static final long serialVersionUID = 1L;

	private String contractNum; 	// 合同号
	private String name; 			// 姓名
	private String telNum; 			// 手机号
	private String beginDate; 		// 起始时间
	private String endDate; 		// 结束时间

	public PageParame() {
		super();
	}

	public PageParame(String contractNum, String name, String telNum, String beginDate, String endDate) {
		super();
		this.contractNum = contractNum;
		this.name = name;
		this.telNum = telNum;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "PageParame [contractNum=" + contractNum + ", name=" + name + ", telNum=" + telNum + ", beginDate="
				+ beginDate + ", endDate=" + endDate + "]";
	}

}
