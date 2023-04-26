package com.marketing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.entities.Lead;
import com.marketing.services.LeadService;

@Controller
public class LeadController {
	@Autowired
	private LeadService leadService;
	
	//Handler Methods
	//http:localhost:8080/create
	
	@RequestMapping("/create")
	public String viewCreateLeadForm() {
		return"create_lead";
		
	}
//	//http://localhost:8080/saveLead
	@RequestMapping("/saveLead")
	public String saveLead(@ModelAttribute("lead") Lead lead, Model model) {
		model.addAttribute("msg","record is saved!!");
		leadService.saveOneLead(lead);
		return"create_lead";
	}
	
	//Second way to read form data
	//http://localhost:8080/saveLead
//	@RequestMapping("/saveLead")
//	public String saveLead(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email,@RequestParam("mobile") long mobile) {
//		Lead lead= new Lead();
//		lead.setFirstName(firstName);
//		lead.setLastName(lastName);
//		lead.setEmail(email);
//		lead.setMobile(mobile);
//		
//		//System.out.println(firstName);
//		//System.out.println(lastName);
//		//System.out.println(email);
//		//System.out.println(mobile);
//		leadService.saveOneLead(lead);
//		return"create_lead";
//	}

//	@RequestMapping("/saveLead")
//	public String saveLead(LeadData leadData) {
//		Lead lead = new Lead();
//		lead.setFirstName(leadData.getFirstName());
//		lead.setLastName(leadData.getLastName());
//		lead.setEmail(leadData.getEmail());
//		lead.setMobile(leadData.getMobile());
//		leadService.saveOneLead(lead);
//		return"create_lead";
//	}
	//http:localhost:8080/listall
	
	@RequestMapping("/listall")
	public String listLead(Model model) {
		List<Lead> leads = leadService.getAllLeads();
		System.out.println(leads);
		model.addAttribute("leads", leads);
		return"list_leads";
	}
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id")long id,Model model) {
		leadService.deleteLead(id);
		
		List<Lead> leads = leadService.getAllLeads();
		model.addAttribute("leads", leads);
		return"list_leads";
	}
	
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id")long id,Model model) {
	Lead lead = leadService.getLeadById(id);
	model.addAttribute("lead", lead);
	return "update_lead";
	}
	
//	//http://localhost:8080/saveLead
	@RequestMapping("/updateLead")
	public String updateLead(@ModelAttribute("lead") Lead lead, Model model) {
		leadService.saveOneLead(lead);
		
		List<Lead> leads = leadService.getAllLeads();
		model.addAttribute("leads", leads);
		return"list_leads";
	}
	
	
}
