package com.revature.wedding_planner.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.models.DinnerType;
import com.revature.wedding_planner.services.DinnerTypeService;

public class DinnerTypeServlet extends HttpServlet {
	
	private final DinnerTypeService dinnerTypeService;
	private final ObjectMapper mapper;
	
	public DinnerTypeServlet(DinnerTypeService dinnerTypeService, ObjectMapper mapper) {
		this.dinnerTypeService = dinnerTypeService;
		this.mapper = mapper;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			DinnerType newDinnerType = mapper.readValue(req.getInputStream(), DinnerType.class);
			boolean wasRegistered = dinnerTypeService.addDinnerType(newDinnerType);
			if(wasRegistered) {
				resp.setStatus(201);
			}
			else {
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
}
