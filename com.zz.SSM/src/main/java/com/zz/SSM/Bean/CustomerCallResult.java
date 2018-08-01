package com.zz.SSM.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Title:CustomerCallResult
 * @Description:TODO(逾期客户催收结果信息)
 * @Company:
 * @author zhou.zhang
 * @date 2018年7月11日 下午2:06:14
 */
public class CustomerCallResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long taskDataId; 					// 外呼任务编号
	private Long callTime; 						// 外呼时间(时间戳格式)
	private Long callBeginTime; 				// 接通时间(时间戳格式)
	private Long callEndTime; 					// 结束时间(时间戳格式)
	private Long callTimeLength; 				// 外呼时长(单位秒)
	private Long callProcTimeLength; 			// 接通时长(单位秒)
	private Integer status; 					// 外呼状态
	private String callStatus;					// 状态说明
	private Integer result; 					// 通话结果
	private String callResult;					// 状态说明
	private String recordFile;					// 外呼电话录音文件
	private String insertTime; 					// 接收时间
	private List<CustomerCallData> callDatas; 	// 外呼交互内容

	public CustomerCallResult() {
		super();
	}

	public CustomerCallResult(Long taskDataId, Long callTime, Long callBeginTime, Long callEndTime, Long callTimeLength,
			Long callProcTimeLength, Integer status, String callStatus, Integer result, String callResult,
			String recordFile, String insertTime, List<CustomerCallData> callDatas) {
		super();
		this.taskDataId = taskDataId;
		this.callTime = callTime;
		this.callBeginTime = callBeginTime;
		this.callEndTime = callEndTime;
		this.callTimeLength = callTimeLength;
		this.callProcTimeLength = callProcTimeLength;
		this.status = status;
		this.callStatus = callStatus;
		this.result = result;
		this.callResult = callResult;
		this.recordFile = recordFile;
		this.insertTime = insertTime;
		this.callDatas = callDatas;
	}

	public Long getTaskDataId() {
		return taskDataId;
	}

	public void setTaskDataId(Long taskDataId) {
		this.taskDataId = taskDataId;
	}

	public Long getCallTime() {
		return callTime;
	}

	public void setCallTime(Long callTime) {
		this.callTime = callTime;
	}

	public Long getCallBeginTime() {
		return callBeginTime;
	}

	public void setCallBeginTime(Long callBeginTime) {
		this.callBeginTime = callBeginTime;
	}

	public Long getCallEndTime() {
		return callEndTime;
	}

	public void setCallEndTime(Long callEndTime) {
		this.callEndTime = callEndTime;
	}

	public Long getCallTimeLength() {
		return callTimeLength;
	}

	public void setCallTimeLength(Long callTimeLength) {
		this.callTimeLength = callTimeLength;
	}

	public Long getCallProcTimeLength() {
		return callProcTimeLength;
	}

	public void setCallProcTimeLength(Long callProcTimeLength) {
		this.callProcTimeLength = callProcTimeLength;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
	public String getCallResult() {
		return callResult;
	}

	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}

	public String getRecordFile() {
		return recordFile;
	}

	public void setRecordFile(String recordFile) {
		this.recordFile = recordFile;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public List<CustomerCallData> getCallDatas() {
		return callDatas;
	}

	public void setCallDatas(List<CustomerCallData> callDatas) {
		this.callDatas = callDatas;
	}

	@Override
	public String toString() {
		return "CustomerCallResult [taskDataId=" + taskDataId + ", callTime=" + callTime + ", callBeginTime="
				+ callBeginTime + ", callEndTime=" + callEndTime + ", callTimeLength=" + callTimeLength
				+ ", callProcTimeLength=" + callProcTimeLength + ", status=" + status + ", callStatus=" + callStatus
				+ ", result=" + result + ", callResult=" + callResult + ", recordFile=" + recordFile + ", insertTime="
				+ insertTime + ", callDatas=" + callDatas + "]";
	}
}
