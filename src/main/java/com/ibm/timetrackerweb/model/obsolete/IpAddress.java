package com.ibm.timetrackerweb.model.obsolete;

import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
public class IpAddress  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/*@ManyToOne(targetEntity = MacAddress.class)
	@JoinColumn(name="id",insertable=false, updatable=false)
	private Integer parentId;*/
	@JsonIgnore
	@ManyToOne
	private MacAddress parent;
	
	@Column
	private String ipAddress;
	
	@Column
	private String startTime;
	
	@Column
	private String endTime;
	
	@Column
	private String parentMac;
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getParentMac() {
		return parentMac;
	}

	public void setParentMac(String parentMac) {
		this.parentMac = parentMac;
	}
	
	protected double getHours()
	{
		if(getEndTime() == null || getStartTime() == null)
		{
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		String[] endTime = getEndTime().split(":");
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endTime[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(endTime[1]));
		long end = cal.getTimeInMillis();
		
		String[] startTime = getStartTime().split(":");
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(startTime[1]));
		long start = cal.getTimeInMillis();
		
		long diff = end - start;
		long hours = TimeUnit.MILLISECONDS.toHours(diff);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff - (hours * 60 * 60 * 1000));
		double minuteD = (double)minutes/60;
		return hours + minuteD;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}*/

	public MacAddress getParent() {
		return parent;
	}

	public void setParent(MacAddress parent) {
		this.parent = parent;
	}

}
