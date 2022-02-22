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
import com.revature.wedding_planner.models.RentedResource;
import com.revature.wedding_planner.services.RentedResourceService;

public class RentedResourceServlet extends HttpServlet {
	private final RentedResourceService rentedResourceService;
	private final ObjectMapper mapper;

	public RentedResourceServlet(RentedResourceService rentedResourceService, ObjectMapper mapper) {
		this.rentedResourceService = rentedResourceService;
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
				String idParam = req.getParameter("rentedResourceID");
				if (idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?rentedResourceID=# in your url");
					return;
				}

				int rentedResourceID = Integer.valueOf(idParam);

				RentedResource rentedResource = rentedResourceService.getRentedResourceByID(rentedResourceID);
				if (rentedResource == null) {
					resp.setStatus(500);
					return;
				}

				String payload = mapper.writeValueAsString(rentedResource);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;

		default:
			List<RentedResource> rentedResources = rentedResourceService.getAllRentedResources();
			String payload = mapper.writeValueAsString(rentedResources);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			RentedResource newRentedResource = mapper.readValue(req.getInputStream(), RentedResource.class);
			boolean wasRegistered = rentedResourceService.addRentedResource(newRentedResource);
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
			RentedResource updatedRentedResource = mapper.readValue(req.getInputStream(), RentedResource.class);
			rentedResourceService.updateRentedResourceWithSessionMethod(updatedRentedResource);
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
