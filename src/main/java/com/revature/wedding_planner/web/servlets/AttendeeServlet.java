package com.revature.wedding_planner.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.models.Attendee;
import com.revature.wedding_planner.services.AttendeeService;

@WebServlet("/attendee")
public class AttendeeServlet extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		System.out.println("Init has been called for AttendeeServlet");
	}
	
	
	private final AttendeeService attendeeService;
	private final ObjectMapper mapper;

	public AttendeeServlet(AttendeeService attendeeService, ObjectMapper mapper) {
		// TODO Auto-generated constructor stub
		this.attendeeService = attendeeService;
		this.mapper = mapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getPathInfo();
		
		PrintWriter writer = resp.getWriter();
		
		if(path == null) {
			List<Attendee> attendees = attendeeService.getAllAttendees();
			
			String payload = mapper.writeValueAsString(attendees);
			
			writer.write(payload);
			
			resp.setStatus(200);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}



	@Override
	public void destroy() {
		System.out.println("TestServlet Destroyer");
	}
}
