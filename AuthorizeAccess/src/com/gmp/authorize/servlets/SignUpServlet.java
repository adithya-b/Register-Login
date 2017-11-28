package com.gmp.authorize.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmp.authorize.handler.RequestHandler;
import com.gmp.authorize.handler.RequestHandlerFactory;
import com.gmp.authorize.handler.HandlerResponse;
import com.gmp.authorize.handler.HandlerResponse.Status;

/**
 * Servlet implementation class ServeOnFlyServlet
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SignUpServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			try {
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line).append('\n');
				}
			} finally {
				reader.close();
			}
			String content = sb.toString();
			System.out.println("starting the signup servlet");
			RequestHandler handler = RequestHandlerFactory.getInstance().getHandler("signup");
			System.out.println("got the handler");
			HandlerResponse handlerResponse = handler.handlePost(content, request.getParameterMap());
			response.setStatus(handlerResponse.getStatusCode());
			response.getWriter().append(handlerResponse.getMessage());
			return;
		} catch (Exception e) {
			response.sendError(400, "Bad request");
			return;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(405, "Get request is not supported");

	}

	private void setCORSHeaders(HttpServletRequest request, HttpServletResponse response) {
		if (request.getHeader("Origin") != null)
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		else
			response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
	}

}