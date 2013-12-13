package im.dadoo.fileserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_dadoofile")
public class Dadoofile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 100)
	private String name;
	
	@Column(nullable = false, unique = true, length = 200)
	private String uri;
	
	@Column(nullable = false, unique = true, length = 128)
	private String md5;
	
	@Column(nullable = false)
	private Long size;
	
	@Column(name = "create_datetime", nullable = false)
	private Long createDatetime;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public static Dadoofile create(String name, String uri, String md5, Long size,
			Long createDatetime, Category category) {
		Dadoofile dadoofile = new Dadoofile();
		dadoofile.name = name;
		dadoofile.uri = uri;
		dadoofile.md5 = md5;
		dadoofile.size = size;
		dadoofile.createDatetime = createDatetime;
		dadoofile.category = category;
		return dadoofile;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(String.format("id:%d,name:%s,uri:%s,md5:%s,size:%d,createDatetime:%d,category:%s",
				id, name, uri, md5, size, createDatetime, category.toString()));
		sb.append("}");
		return sb.toString();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Long createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
