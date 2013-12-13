package im.dadoo.fileserver.service;

import java.io.File;

import im.dadoo.fileserver.dao.AppDao;
import im.dadoo.fileserver.dao.CategoryDao;
import im.dadoo.fileserver.dao.DadoofileDao;
import im.dadoo.fileserver.domain.Category;
import im.dadoo.fileserver.domain.Dadoofile;
import im.dadoo.fileserver.exception.ExceptionCode;
import im.dadoo.fileserver.exception.FSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DadoofileService {
	
	@Autowired
	private String rootPath;

	@Autowired
	private AppDao appDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private DadoofileDao dfDao;
	
	public Dadoofile save(MultipartFile file, Integer categoryId) {
		Dadoofile df = null;
		try {
			Category category = this.categoryDao.fetchById(categoryId);
			if (file != null && !file.isEmpty() && file.getSize() > 0) {
				//获取文件内容
				byte[] data = file.getBytes();
				//获取文件大小
				Long size = file.getSize();
				//获取原始文件名
				String name = file.getOriginalFilename();
				//获取md5字符串
				String md5 = DigestUtils.md5DigestAsHex(data);
				//获取文件后缀
				String suffix = "";
				String[] ts = name.split("\\.");
				if (ts.length > 0) {
					suffix = ts[ts.length - 1];
				}
				//组合新文件名
				String newName = md5 + "." + suffix;
				//组合存放uri
				//拼接分类路径
				String cpath = category.getName();
				Category sup = category.getSup();
				while (sup != null) {
					cpath = sup.getName() + "\\" + cpath;
					sup = sup.getSup();
				}
				String relativeDirPath = String.format("%s\\%s", category.getApp().getName(), cpath);
				String uri = relativeDirPath + "\\" + newName;
				String absoluteDirPath = String.format("%s\\%s", this.rootPath, relativeDirPath);

				File dir = new File(absoluteDirPath);
				dir.mkdirs();
				
				File localFile = new File(dir, newName);
				if (localFile.createNewFile()) {
					file.transferTo(localFile);
					df = Dadoofile.create(name, uri, md5, size, System.currentTimeMillis(), category);
					this.dfDao.save(df);
					System.out.println(localFile.length());
					return df;
				}
			}
			return df;
		} catch(Exception e) {
			e.printStackTrace();
			throw new FSException(500, ExceptionCode.INNER_ERROR, "保存文件时发生错误");
		}
	}

}
