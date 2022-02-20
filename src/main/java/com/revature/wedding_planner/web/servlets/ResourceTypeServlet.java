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
import com.revature.wedding_planner.models.ResourceType;
import com.revature.wedding_planner.services.ResourceTypeService;

public class ResourceTypeServlet extends HttpServlet {
	private final ResourceTypeService resourceTypeService;
	private final ObjectMapper mapper;

	public ResourceTypeServlet(ResourceTypeService resourceTypeService, ObjectMapper mapper) {
		this.resourceTypeService = resourceTypeService;
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
				String idParam = req.getParameter("resourceTypeID");
				if (idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?resourceTypeID=# in your url");
					return;
				}

				int resourceTypeID = Integer.valueOf(idParam);

				ResourceType resourceType = resourceTypeService.getResourceTypeByID(resourceTypeID);
				if (resourceType == null) {
					resp.setStatus(500);
					return;
				}

				String payload = mapper.writeValueAsString(resourceType);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;

		default:
			List<ResourceType> resourceTypes = resourceTypeService.getAllResourceTypes();
			String payload = mapper.writeValueAsString(resourceTypes);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			ResourceType newResourceType = mapper.readValue(req.getInputStream(), ResourceType.class);
			boolean wasRegistered = resourceTypeService.addResourceType(newResourceType);
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
			ResourceType updatedResourceType = mapper.readValue(req.getInputStream(), ResourceType.class);
			resourceTypeService.updateResourceTypeWithSessionMethod(updatedResourceType);
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
