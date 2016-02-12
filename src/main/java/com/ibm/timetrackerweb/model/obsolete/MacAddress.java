package com.ibm.timetrackerweb.model.obsolete;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
public class MacAddress  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/*@ManyToOne(targetEntity = ComputerConfig.class)
	@JoinColumn(name="computerId",insertable=true, updatable=false)
	private Integer parentId;*/
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private ComputerConfig parent;
	
	private String computerName;
	
	@Column
	private String macAddress;
	
	@OneToMany(targetEntity=IpAddress.class, mappedBy="parent", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	private List<IpAddress> allIpAddress;

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

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}


	public List<IpAddress> getAllIpAddress() {
		if(allIpAddress == null)
		{
			allIpAddress = new ArrayList<>();
		}
		return allIpAddress;
	}
	
	public void addIpAddress(IpAddress ipAddress , boolean writeToJson)
	{
		getAllIpAddress().add(ipAddress);
		if(writeToJson)
		{
			System.out.println("Added IP");
		}
	}

	public IpAddress getIpAddressByIp(String ipStr) {
		for(IpAddress ip: getAllIpAddress())
		{
			if(ip.getIpAddress().equals(ipStr))
			{
				return ip;
			}
		}
		return null;
	}

	public ComputerConfig getParent() {
		return parent;
	}

	public void setParent(ComputerConfig parent) {
		this.parent = parent;
	}

}
