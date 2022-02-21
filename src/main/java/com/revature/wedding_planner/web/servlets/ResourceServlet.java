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
import com.revature.wedding_planner.models.Resource;
import com.revature.wedding_planner.services.ResourceService;

public class ResourceServlet extends HttpServlet {
	private final ResourceService resourceService;
	private final ObjectMapper mapper;

	public ResourceServlet(ResourceService resourceService, ObjectMapper mapper) {
		this.resourceService = resourceService;
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
				String idParam = req.getParameter("resourceID");
				if (idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?resourceID=# in your url");
					return;
				}

				int resourceID = Integer.valueOf(idParam);

				Resource resource = resourceService.getResourceByID(resourceID);
				if (resource == null) {
					resp.setStatus(500);
					return;
				}

				String payload = mapper.writeValueAsString(resource);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;

		default:
			List<Resource> resources = resourceService.getAllResources();
			String payload = mapper.writeValueAsString(resources);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			Resource newResource = mapper.readValue(req.getInputStream(), Resource.class);
			boolean wasRegistered = resourceService.addResource(newResource);
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
			Resource updatedResource = mapper.readValue(req.getInputStream(), Resource.class);
			resourceService.updateResourceWithSessionMethod(updatedResource);
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
