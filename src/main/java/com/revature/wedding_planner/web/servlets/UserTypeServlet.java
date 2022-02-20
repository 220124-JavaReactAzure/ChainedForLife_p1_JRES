package com.revature.wedding_planner.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.wedding_planner.models.UserType;
import com.revature.wedding_planner.services.UserTypeService;

public class UserTypeServlet extends HttpServlet {

	private final UserTypeService userTypeService;
	private final ObjectMapper mapper;

	public UserTypeServlet(UserTypeService userTypeService, ObjectMapper mapper) {
		this.userTypeService = userTypeService;
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
				String idParam = req.getParameter("userTypeID");
				if (idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?userTypeID=# in your url");
					return;
				}

				int userTypeID = Integer.valueOf(idParam);

				UserType userType = userTypeService.getUserTypeByID(userTypeID);
				if (userType == null) {
					resp.setStatus(500);
					return;
				}

				String payload = mapper.writeValueAsString(userType);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;

		default:
			List<UserType> userTypes = userTypeService.getAllUserTypes();
			String payload = mapper.writeValueAsString(userTypes);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			UserType newUserType = mapper.readValue(req.getInputStream(), UserType.class);
			boolean wasRegistered = userTypeService.addUserType(newUserType);
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
			UserType updatedUserType = mapper.readValue(req.getInputStream(), UserType.class);
			userTypeService.updateUserTypeWithSessionMethod(updatedUserType);
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
}
