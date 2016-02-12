package com.ibm.timetrackerweb.model.obsolete;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ibm.timetrackerweb.util.DecimalFormatUtil;

//@Entity(name = "com.dummy.myclass")
public class ComputerConfig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="computer_id")
	private Integer computerId;
	
	@Column
	private String computerName;
	
	@Column
	private String currentDate;
	
	@OneToMany(targetEntity=MacAddress.class, mappedBy="parent", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	private List<MacAddress>listMacs;
	
	public Integer getComputerId() {
		return computerId;
	}

	public void setComputerId(Integer computerId) {
		this.computerId = computerId;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public List<MacAddress> getListMacs() {
		if(listMacs == null)
		{
			listMacs = new ArrayList<>();
		}
		return listMacs;
	}
	
	public void addMacAddress(MacAddress mac, boolean writeToJson)
	{
		getListMacs().add(mac);
		if(writeToJson)
		{
			System.out.println("Added Mac");
		}
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public void clearListMacAddress() {
		// TODO Auto-generated method stub
		getListMacs().clear();
	}

	public MacAddress findMacWithIp(String macStr, String ipStr) {
		// TODO Auto-generated method stub
		for(MacAddress mac:getListMacs())
		{
			if(mac.getMacAddress().equals(macStr))
			{
				for(IpAddress ip: mac.getAllIpAddress())
				{
					if(ip.getIpAddress().equals(ipStr))
					{
						return mac;
					}
				}
			}
		}
		return null;
	}

	public String getTotalTime() {
		double totalTime = 0;
		String startTimeForDay = getStartTime();
		String endTimeForDay = getLastEndTimeForDay();
		
		if(endTimeForDay == null || startTimeForDay == null)
		{
			return "0";
		}
		Calendar cal = Calendar.getInstance();
		String[] endTime = endTimeForDay.split(":");
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endTime[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(endTime[1]));
		long end = cal.getTimeInMillis();
		
		String[] startTime = startTimeForDay.split(":");
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(startTime[1]));
		long start = cal.getTimeInMillis();
		
		long diff = end - start;
		long hours = TimeUnit.MILLISECONDS.toHours(diff);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff - (hours * 60 * 60 * 1000));
		double minuteD = (double)minutes/60;
		
		//return DecimalFormatUtil.getFormattedData(totalTime);
		return hours + " hours" + minutes + " minutes";
	}

	private String getLastEndTimeForDay() {
		// TODO Auto-generated method stub
		if(getListMacs().size()>0)
		{
			String endTime = "00:00";
			for(MacAddress mac:getListMacs())
			{
				for(IpAddress ip:mac.getAllIpAddress())
				{
					String ipEndTime = ip.getEndTime();
					if(ipEndTime.compareTo(endTime)>0)
					{
						endTime = ipEndTime;
					}
				}//end for
			}//end for
			return endTime;
		}
		return null;
	}

	private String getStartTime() {
		// TODO Auto-generated method stub
		if(getListMacs().size()>0)
		{
			return getListMacs().get(0).getAllIpAddress().get(0).getStartTime();
		}
		return null;
	}
	
}
