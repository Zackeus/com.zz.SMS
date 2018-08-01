package com.zz.SSM.Bean;

import java.io.Serializable;

/**
 * 
 * @Title:CustomerCallData
 * @Description:TODO(催收客户通话内容)
 * @Company:
 * @author zhou.zhang
 * @date 2018年7月11日 下午2:12:19
 */
public class CustomerCallData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long taskDataId; 	// 外呼任务编号
	private Long index; 		// 排序索引
	private String nodeId; 		// 节点 ID
	private String node; 		// 节点名称
	private String robot; 		// 机器人对话
	private String hitId;
	private String hitName; 	// 客户内容解析
	private String result; 		// 客户对话

	public CustomerCallData() {
		super();
	}

	public CustomerCallData(Long taskDataId, Long index, String nodeId, String node, String robot, String hitId,
			String hitName, String result) {
		super();
		this.taskDataId = taskDataId;
		this.index = index;
		this.nodeId = nodeId;
		this.node = node;
		this.robot = robot;
		this.hitId = hitId;
		this.hitName = hitName;
		this.result = result;
	}

	public Long getTaskDataId() {
		return taskDataId;
	}

	public void setTaskDataId(Long taskDataId) {
		this.taskDataId = taskDataId;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getRobot() {
		return robot;
	}

	public void setRobot(String robot) {
		this.robot = robot;
	}

	public String getHitId() {
		return hitId;
	}

	public void setHitId(String hitId) {
		this.hitId = hitId;
	}

	public String getHitName() {
		return hitName;
	}

	public void setHitName(String hitName) {
		this.hitName = hitName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "CustomerCallData [taskDataId=" + taskDataId + ", index=" + index + ", nodeId=" + nodeId + ", node="
				+ node + ", robot=" + robot + ", hitId=" + hitId + ", hitName=" + hitName + ", result=" + result + "]";
	}
}
