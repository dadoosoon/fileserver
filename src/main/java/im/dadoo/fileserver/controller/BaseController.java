package im.dadoo.fileserver.controller;

import im.dadoo.fileserver.dto.DTException;
import im.dadoo.fileserver.exception.FSException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {
	
	@ExceptionHandler(FSException.class)
	@ResponseBody
	public DTException process(FSException ex, 
			HttpServletRequest req, HttpServletResponse res) {
		res.setStatus(ex.getStatus());
		return ex.toDTO();
	}
}
