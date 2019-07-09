package com.model2.mvc.web.tour;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.domain.Tour;

import crawling.TourList;

@RestController
@RequestMapping("/tour/*")
public class TourListRestController {
	
	public TourListRestController(){
		System.out.println(this.getClass());
	}
	
	
	@RequestMapping( value="json/tourList", method=RequestMethod.POST )
	public List<Tour> tourList(@RequestBody Tour tour) throws Exception{
	
		System.out.println("++++++++++++++++++");
		System.out.println(tour.getKeyword());
		System.out.println(tour.getPageNum());
		System.out.println("++++++++++++++++++");
		List<Tour> list = TourList.tourList(tour.getKeyword(), tour.getPageNum());
		
//		List<Tour> list = new ArrayList<Tour>();
//		Tour tour = new Tour();
//		tour.setTourName("관광지명");
//		tour.setTourId("키키키");
//		list.add(tour);
//		tour = new Tour();
//		tour.setTourName("ddddd");
//		tour.setTourId("2222");
//		list.add(tour);
		System.out.println(list.size()+"zzzzzzzzzzzzzzzzzzzz");
		
		return list;
	}

}
