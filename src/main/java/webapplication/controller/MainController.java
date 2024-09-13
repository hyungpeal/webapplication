package webapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webapplication.entity.Phonebook;
import webapplication.service.PhonebookService;

@Controller
public class MainController {
	
	@Autowired
	PhonebookService service;
	
	@GetMapping(value = {"/", "/{id}"})
	public String index(Model model, @PathVariable(required = false) Integer id) {
		
		List<Phonebook> lists = service.findAll();
		model.addAttribute("lists",lists);
		
		if(id != null) {
			Phonebook pb = service.findById(id);
			model.addAttribute("pb", pb);
		}
		
		return "index";
	}
	
	@PostMapping("reg")
	public String reg(Phonebook pb) {
		
		System.out.println(pb);
		
		service.save(pb);
		
		return "redirect:/";
	}
	
	@GetMapping("modify")
	public String modify(@RequestParam int id, Model model) {
		
		Phonebook pb = service.findById(id);
		
		model.addAttribute("pb", pb);
		
		return "modifyForm";
	}
	
	@PostMapping("modifyProc")
	public String modifyProc(Phonebook pb) {
		
		System.out.println(pb);
		
		int result = service.update(pb);

		if(result == 0) System.out.println("수정 실패");
		else System.out.println("수정 성공");
		
		return "redirect:/";
	}
	
	@GetMapping("delete")
	public String delete(int id) {
		
		int result = service.delete(id);
		
		if(result == 0) System.out.println("삭제 실패");
		else System.out.println("삭제 성공");
		
		return "redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "search")
	@ResponseBody
	public List<Phonebook> search(@RequestParam("_search") String _search) throws Exception {

		
		List<Phonebook> pblist = service.search(_search);
		
		
		return pblist; 
	}
	
}
