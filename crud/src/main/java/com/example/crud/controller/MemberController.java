package com.example.crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crud.entity.Member;
import com.example.crud.service.MemberService;


@Controller
@RequestMapping("/members")
public class MemberController {
	
	private MemberService memberService;

	@Autowired
	public MemberController(MemberService theMemberService) {
		memberService = theMemberService;
	}
		

	@GetMapping("/list")
	public String listMembers(Model theModel) {

		List<Member> theMembers = memberService.findAll();
		theModel.addAttribute("members", theMembers);
		return "list-members";
	}
		
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) { 
		
		Member theMember = new Member();
		theModel.addAttribute("member", theMember);		
		return "member-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("memberId") int theId, Model theModel) {

		Member theMember = memberService.findById(theId);
		//fill form
		theModel.addAttribute("member", theMember);
		return "member-form";
	}
	
	@PostMapping("/save")
	public String saveMember(@Valid @ModelAttribute("member") Member theMember, BindingResult result) {

		if (result.hasErrors()) {
		    return "member-form";
		}
		//else, save the memb
		memberService.save(theMember);
		//and redirect to list
		return "redirect:/members/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("memberId") int theId) {

		memberService.deleteById(theId);
		return "redirect:/members/list";
	}
	
	

}
