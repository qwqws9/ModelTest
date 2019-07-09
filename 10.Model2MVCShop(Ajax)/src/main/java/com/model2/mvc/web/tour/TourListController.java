package com.model2.mvc.web.tour;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.service.domain.Tour;

import crawling.TourList;

@Controller
@RequestMapping("/tour/*")
public class TourListController {
	
	public TourListController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="search", method=RequestMethod.GET )
	public String searchTour() throws Exception{
	
		System.out.println("/tour/searchTour : GET");
		
		return "redirect:/tour/tourSearch.jsp";
	}
	
	@RequestMapping( value="tourList", method=RequestMethod.POST )
	public String tourList(HttpServletRequest request,@RequestParam(value="pageNum",defaultValue="1") String pageNum
			, @RequestParam("keyword") String keyword) throws Exception{
	
		System.out.println("++++++++");
		System.out.println(keyword);
		System.out.println(pageNum);
		System.out.println("++++++++");
		System.out.println("µé¾î¿È");
		List<Tour> list = TourList.tourList(keyword, pageNum);
		System.out.println(list.size()+"::::::::::::::::::::::");
		request.setAttribute("list", list);
		
		return "forward:/tour/tourSearch.jsp";
	}

}
