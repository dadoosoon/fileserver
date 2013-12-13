package im.dadoo.fileserver.aspect;

import im.dadoo.fileserver.exception.ExceptionCode;
import im.dadoo.fileserver.exception.FSException;
import im.dadoo.fileserver.service.DadoofileService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
@Aspect
public class FileAspect {

	@Autowired
	private DadoofileService dfService;
	
	//检验文件是否存在
	@Around("execution(public * im.dadoo.fileserver.service.DadoofileService.save(..)) ")
	public Object check(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		MultipartFile file = (MultipartFile)args[0];
		String md5 = DigestUtils.md5DigestAsHex(file.getBytes());
		if (this.dfService.fetchByMd5(md5) == null) {
			return pjp.proceed();
		}
		else {
			throw new FSException(400, ExceptionCode.FILE_EXIST, "文件已存在");
		}
	}
}
