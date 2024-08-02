package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.modelclass;
import com.example.demo.repository.repository;
import jakarta.servlet.http.HttpSession;
@Controller
public class controllerclass {
	@Autowired
	repository repo;
    @RequestMapping("/Welcome")
    public String welcome() {
    	return "Welcome";
    }
	@GetMapping("/getall")
	public String findall (Model m) {
		List<modelclass> li = (List<modelclass>) repo.findAll();
		m.addAttribute("add-products",li);
		return "home";
	}
	    @GetMapping("/getbyid/{id}")
	    public String getby(@PathVariable(value = "id" )int Id, Model m) {
	    	Optional<modelclass> c = repo.findById(Id);
	    	modelclass v = c.get();
	    	m.addAttribute("products",v);
	    	return "update";
	    }
	    @PostMapping("/save_products")
	    public String insert(@ModelAttribute modelclass m , HttpSession session) {
	    	repo.save(m);
	    	session.setAttribute("message", "successfullyadded");
	    	return "redirect:/loadform";
	    }
	    @PutMapping("/update")
	    public String edit(@ModelAttribute modelclass m , HttpSession session) {
	    	repo.save(m);
	    	session.setAttribute("message", "successfullyadded");
	    	return "redirect:/";
	    }
	    @DeleteMapping("/delete/{id}")
	    public String delete(@PathVariable(value="id") int id,HttpSession session) {
	    	repo.deleteById(id);
	    	session.setAttribute("message", "successfullydeleted");
	    	return "redirect:/";
	    }
	    @GetMapping("/loadform")
	    public String loadform() {
	    	return "insert";
	    }
}
