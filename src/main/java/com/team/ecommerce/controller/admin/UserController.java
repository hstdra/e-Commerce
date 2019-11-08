package com.team.ecommerce.controller.admin;

import com.team.ecommerce.entity.User;
import com.team.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/customer")
public class UserController {
	@Autowired
	PasswordEncoder passEncode;
	@Autowired
    UserService service;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@RequestMapping("add")
	ModelAndView add()
	{
		ModelAndView mav = new ModelAndView("admin/customer/add");
		mav.addObject(new User());
		return mav;
	}
	
    @RequestMapping("")
	ModelAndView management() 
	{
		ModelAndView mav = new ModelAndView("admin/customer/management");
		mav.addObject("user",service.showAll());
		return mav;
	}
    
    @RequestMapping("edit/{id}")
    ModelAndView edit(@PathVariable int id) 
	{
		ModelAndView mav = new ModelAndView("admin/customer/edit");
		mav.addObject("user",service.get(id));
		return mav;
	}
	
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        try {
            service.delete(id);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/customer";
    }
    
    @PostMapping(value = "save")
    public String save(@ModelAttribute User user) {
        try {

			user.setPassword(passEncode.encode(user.getPassword()));
            service.save(user);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/customer";
    }

    @PostMapping(value = "savePass")
    public String savePass(@ModelAttribute User user) {
    	
    	try {
			service.get(user.getId()).setPassword(passEncode.encode(user.getPassword()));
            service.save(user);
        } catch (Exception ignored) {
        	}
        return "redirect:/admin/customer";
    }
    
    @RequestMapping("editPass/{id}")
    public ModelAndView editPass(@PathVariable("id") int id) {
    	ModelAndView mav = new ModelAndView("admin/customer/editPassword");
    	mav.addObject("user", service.get(id));
    	return mav;
    }

}
