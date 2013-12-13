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
@Table(name = "t_category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, unique = true, length = 50)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "app_id")
	private App app;
	
	@ManyToOne
	@JoinColumn(name = "sup_id")
	private Category sup;

	public static Category create(String name, App app, Category sup) {
		Category category = new Category();
		category.name = name;
		category.app = app;
		category.sup = sup;
		return category;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(String.format("id:%d,name:%s,app:%s,sup:%s", id, name, app.toString(), sup != null ? sup.toString() : null));
		sb.append("}");
		return sb.toString();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public Category getSup() {
		return sup;
	}

	public void setSup(Category sup) {
		this.sup = sup;
	}
	
}
