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
import com.revature.wedding_planner.models.PlusOne;
import com.revature.wedding_planner.services.PlusOneService;

public class PlusOneServlet extends HttpServlet {
	private final PlusOneService plusOneService;
	private final ObjectMapper mapper;

	public PlusOneServlet(PlusOneService plusOneService, ObjectMapper mapper) {
		this.plusOneService = plusOneService;
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
				String idParam = req.getParameter("plusOneID");
				if (idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?plusOneID=# in your url");
					return;
				}

				int plusOneID = Integer.valueOf(idParam);

				PlusOne plusOne = plusOneService.getPlusOneByID(plusOneID);
				if (plusOne == null) {
					resp.setStatus(500);
					return;
				}

				String payload = mapper.writeValueAsString(plusOne);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;

		default:
			List<PlusOne> plusOnes = plusOneService.getAllPlusOnes();
			String payload = mapper.writeValueAsString(plusOnes);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			PlusOne newPlusOne = mapper.readValue(req.getInputStream(), PlusOne.class);
			boolean wasRegistered = plusOneService.addPlusOne(newPlusOne);
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
			PlusOne updatedPlusOne = mapper.readValue(req.getInputStream(), PlusOne.class);
			plusOneService.updatePlusOneWithSessionMethod(updatedPlusOne);
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
