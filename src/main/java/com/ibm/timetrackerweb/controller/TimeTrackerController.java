package com.ibm.timetrackerweb.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.timetrackerweb.model.ComputerConfig;
import com.ibm.timetrackerweb.model.MacAndIp;
import com.ibm.timetrackerweb.repository.ComputerConfigRepository;
import com.ibm.timetrackerweb.util.DateUtil;

@RestController
@RequestMapping("/timetracker")
public class TimeTrackerController {

	@Autowired
	private ComputerConfigRepository repo;

	@RequestMapping(value="/findall.json",method = RequestMethod.GET)
	public List<ComputerConfig> findAllItems() {
		return repo.findAll();
	}
	
	@RequestMapping(value="/findbyname/{name}",method = RequestMethod.GET)
	public List<ComputerConfig> findItemsByName(@PathVariable String name) {
		return repo.findByComputerName(name);
	}
	
	@RequestMapping(value="/findbydate/{date}",method = RequestMethod.GET)
	public List<ComputerConfig> findItemsByDate(@PathVariable String date) {
		return repo.findByDateOfData(date);
	}
	
	@RequestMapping(value="/find",method = RequestMethod.GET)
	public List<ComputerConfig> findItemsByDate(@RequestParam(value="name", required=false) String name,
	        @RequestParam(value="date", required=false) String date) {
		if(name == null && date == null)
		{
			return repo.findAll();
		}
		else if(name == null && date != null)
		{
			return repo.findByDateOfData(date);
		}
		else if(name != null && date == null)
		{
			return repo.findByComputerName(name);
		}
		return repo.findByComputerNameAndDateOfData(name,date);
		
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_VALUE },consumes="application/json")
	public Map<String,String> addItem(@RequestBody ComputerConfig item) {
		String currentDate = DateUtil.getFormattedDate("dd-MMM-yyyy");
		String currentTime = DateUtil.getFormattedDate("HH:mm");
		List<ComputerConfig>list = repo.findByComputerNameAndDateOfData(item.getComputerName(), item.getDateOfData());
		//List<ComputerConfig>list = repo.findByComputerNameAndCurrentDate(item.getComputerName(), currentDate);
		ComputerConfig toBeSaved = null;
		if(list.size() > 0)
		{
			toBeSaved = list.get(0);
			for(MacAndIp mip:item.getListMacs())
			{
				MacAndIp existing = toBeSaved.findMacWithIp(mip.getMacAddress(), mip.getIpAddress());
				if(existing == null)
				{
					toBeSaved.addMacAddress(mip, false);
				}
				else
				{
					existing.setEndTime(mip.getEndTime());
				}
			}
		}
		else
		{
			toBeSaved = item;
			toBeSaved.setComputerId(null);
			for(MacAndIp mac:toBeSaved.getListMacs())
			{
				mac.setId(null);
				mac.setParent(toBeSaved);
			}
		}
		
		ComputerConfig savedData = repo.saveAndFlush(toBeSaved);
		return Collections.singletonMap("result", "success");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ComputerConfig updateItem(@RequestBody ComputerConfig updatedItem, @PathVariable Integer id) {
		updatedItem.setComputerId(id);
		return repo.saveAndFlush(updatedItem);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Integer id) {
		repo.delete(id);
	}

}
