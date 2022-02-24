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
import com.revature.wedding_planner.models.User;
import com.revature.wedding_planner.models.UserType;
import com.revature.wedding_planner.services.UserService;
import com.revature.wedding_planner.services.UserTypeService;

public class UserServlet extends HttpServlet {
	private final UserService userService;
	private final UserTypeService userTypeService;
	private final ObjectMapper mapper;

	public UserServlet(UserService userService, UserTypeService userTypeService, ObjectMapper mapper) {
		this.userService = userService;
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
				String idParam = req.getParameter("userID");
				if (idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?userID=# in your url");
					return;
				}

				int userID = Integer.valueOf(idParam);

				User user = userService.getUserByID(userID);
				if (user == null) {
					resp.setStatus(500);
					return;
				}

				String payload = mapper.writeValueAsString(user);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;

		default:
			List<User> users = userService.getAllUsers();
			String payload = mapper.writeValueAsString(users);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		String path = req.getPathInfo();

		if (path == null)
			path = "";

		switch (path) {
		case "/register":
			try {
				String name = req.getParameter("name");
				if (name == null) {
					resp.setStatus(400);
					writer.write("Please insert a valid name");
					return;
				}

				String email = req.getParameter("email");
				if (email == null) {
					resp.setStatus(400);
					writer.write("Please insert a valid email");
					return;
				}

				String password = req.getParameter("password");
				if (password == null) {
					resp.setStatus(400);
					writer.write("Please insert a valid password");
				}

				String typeParam = req.getParameter("userTypes");
				if (typeParam == null) {
					resp.setStatus(400);
					writer.write("Please insert a valid userType");
				}
				int typeId = Integer.valueOf(typeParam);
				UserType type = userTypeService.getUserTypeByID(typeId);

				User newUser = new User(name, email, password, type);
				
				boolean wasRegistered = userService.addUser(newUser);
				
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

			break;
		default:
			try {
				User newUser = mapper.readValue(req.getInputStream(), User.class);
				boolean wasRegistered = userService.addUser(newUser);
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
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User updatedUser = mapper.readValue(req.getInputStream(), User.class);
			userService.updateUserWithSessionMethod(updatedUser);
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
