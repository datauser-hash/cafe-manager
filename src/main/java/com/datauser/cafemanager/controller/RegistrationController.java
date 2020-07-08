package com.datauser.cafemanager.controller;

import com.datauser.cafemanager.models.Table;
import com.datauser.cafemanager.models.User;
import com.datauser.cafemanager.models.enums.Role;
import com.datauser.cafemanager.service.TableService;
import com.datauser.cafemanager.service.UserService;
import com.datauser.cafemanager.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private TableService tableService;

    private final Logger logger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new CrmUser());
		
		return "create-user-form";
	}



	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
				BindingResult theBindingResult,
				Model theModel) {
		
		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "create-user-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "create-user-form";
        }
        
        // create user account        						
        userService.save(theCrmUser);
        
        logger.info("Successfully created user: " + userName);
        
        return "redirect:/manager/userList";
	}

	@GetMapping("/createTable")
	public String cratingTable(Model theModel) {

		theModel.addAttribute("table", new Table());

		return "create-user-form";
	}

	@PostMapping("/processTableCreation")
	public String processOfTableRegistrationForm(
			@Valid @ModelAttribute("table") Table newTable,
			BindingResult theBindingResult,
			Model theModel) {

		User tableAssignedUser = userService.findById(newTable.getUserId());
		String userName = tableAssignedUser.getUserName();
		logger.info("Processing registration form for: " + userName);

		String userRole = tableAssignedUser.getUserRole();

		// form validation
		if (theBindingResult.hasErrors() || userRole.equals(Role.MANAGER.name())){
			return "create-user-form";
		}

		// check the database if user already exists
		User existing = userService.findByUserName(userName);
		if (existing != null){
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
			return "create-user-form";
		}

		// create user account
		tableService.save(newTable);

		logger.info("Successfully created user: " + userName);

		return "redirect:/manager/tableList";
	}
}
