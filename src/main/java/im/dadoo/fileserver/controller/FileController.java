package im.dadoo.fileserver.controller;

import im.dadoo.fileserver.domain.Dadoofile;
import im.dadoo.fileserver.exception.FSException;
import im.dadoo.fileserver.service.DadoofileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	@Autowired
	private DadoofileService dfService;
	
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	@ResponseBody
	public Dadoofile upload(@RequestParam MultipartFile file) {
		try {
			return this.dfService.save(file, 1);
		} catch(FSException ex) {
			ex.setUrl("/file");
			ex.setMethod("POST");
			throw ex;
		}
	}
}
