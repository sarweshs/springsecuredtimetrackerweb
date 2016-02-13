package com.ibm.timetrackerweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.timetrackerweb.model.ComputerConfig;
import com.ibm.timetrackerweb.repository.ComputerConfigRepository;





@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private ComputerConfigRepository repo;

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(@ModelAttribute("computerConfig")ComputerConfig config, ModelMap model) {

		List<ComputerConfig> users = null;
		if(config == null )
		{
			users = repo.findAll();
		}
		else if((StringUtils.isEmpty(config.getComputerName()) && StringUtils.isEmpty(config.getDateOfData())))
		{
			users = repo.findAll();
		}
		else if(!StringUtils.isEmpty(config.getComputerName()) && !StringUtils.isEmpty(config.getDateOfData()))
		{
			users = repo.findByComputerNameAndDateOfData(config.getComputerName().trim(), config.getDateOfData().trim());
		}
		else if(!StringUtils.isEmpty(config.getComputerName()))
		{
			users = repo.findByComputerName(config.getComputerName().trim());
		}
		else if(!StringUtils.isEmpty(config.getDateOfData()))
		{
			users = repo.findByDateOfData(config.getDateOfData().trim());
		}
		model.addAttribute("users", users);
		return "userslist";
	}

	/**
	 * This method will provide the medium to add a new user.
	 *//*
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}

	*//**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 *//*
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		//return "success";
		return "registrationsuccess";
	}


	*//**
	 * This method will provide the medium to update an existing user.
	 *//*
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	*//**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 *//*
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}


		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		return "registrationsuccess";
	}

	
	*//**
	 * This method will delete an user by it's SSOID value.
	 *//*
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}
	

	*//**
	 * This method will provide UserProfile list to views
	 *//*
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
*/
}
