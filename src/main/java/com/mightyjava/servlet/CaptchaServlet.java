package com.mightyjava.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/captcha-servlet")
public class CaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = -5840561187921381583L;
	
	public static final String FILE_TYPE = "jpeg";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Progma", "no-cache");
		response.setDateHeader("Max-Age", 0);
		
		String captcha = generateCaptcha(5);
		
		int width = 160, height = 35;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.OPAQUE);
		Graphics graphics = bufferedImage.createGraphics();
		graphics.setFont(new Font("Arial", Font.BOLD, 20));
		graphics.setColor(new Color(169, 169, 169));
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(new Color(255, 255, 255));
		graphics.drawString(captcha, 20, 25);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("captcha", captcha);
		
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(bufferedImage, FILE_TYPE, outputStream);
		outputStream.close();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	private String generateCaptcha(int captchaLength) {
		String captcha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuffer captchaBuffer = new StringBuffer();
		Random random = new Random();
		
		while(captchaBuffer.length() < captchaLength) {
			int index = (int) (random.nextFloat() * captcha.length());
			captchaBuffer.append(captcha.substring(index, index+1));
		}
		return captchaBuffer.toString();
	}

}
