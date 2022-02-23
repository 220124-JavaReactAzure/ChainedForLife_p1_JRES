package com.revature.wedding_planner.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.models.Attendee;
import com.revature.wedding_planner.models.UserType;
import com.revature.wedding_planner.services.AttendeeService;


public class AttendeeServlet extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		System.out.println("Init has been called for Attendee Servlet");
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
		PrintWriter writer = resp.getWriter();

		String path = req.getPathInfo();
		if (path == null)
			path = "";
		switch (path) {

		case "/ID":
			try {
				String idParam = req.getParameter("attendeeID");
				if (idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?attendeeID=# in your url");
					return;
				}

				int attendeeID = Integer.valueOf(idParam);

				Attendee attendee = attendeeService.getAttendeeByID(attendeeID);
				if (attendee == null) {
					resp.setStatus(500);
					return;
				}

				String payload = mapper.writeValueAsString(attendee);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;

		default:
			List<Attendee> attendees = attendeeService.getAllAttendees();
			String payload = mapper.writeValueAsString(attendees);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		
		try {
			Attendee newAttendee = mapper.readValue(req.getInputStream(), Attendee.class);
			boolean wasRegistered = attendeeService.addAttendee(newAttendee) != null;
			
			
			if (wasRegistered) {
				resp.setStatus(201);
			} else {
				resp.setStatus(500);
				resp.getWriter().write("Database did not persist");
			}
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some random exception, data did not persist");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Attendee updatedAttendee = mapper.readValue(req.getInputStream(), Attendee.class);
			attendeeService.updateAttendeeWithSessionMethod(updatedAttendee);
			resp.setStatus(204);
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception did not persist");
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}



	@Override
	public void destroy() {
		System.out.println("Attendee Servlet Destroyer");
	}
}
