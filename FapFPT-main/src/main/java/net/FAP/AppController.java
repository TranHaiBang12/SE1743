package net.FAP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private FunctionFAPService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<FunctionFAP> listFunctionFAPs = service.listAll();
		model.addAttribute("listFunctionFAPs", listFunctionFAPs);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewFunctionFAPForm(Model model) {
		FunctionFAP FunctionFAP = new FunctionFAP();
		model.addAttribute("FunctionFAP", FunctionFAP);
		
		return "new_FunctionFAP";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveFunctionFAP(@ModelAttribute("FunctionFAP") FunctionFAP FunctionFAP) {
		service.save(FunctionFAP);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditFunctionFAPForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_FunctionFAP");
		
		FunctionFAP FunctionFAP = service.get(id);
		mav.addObject("FunctionFAP", FunctionFAP);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteFunctionFAP(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
}
