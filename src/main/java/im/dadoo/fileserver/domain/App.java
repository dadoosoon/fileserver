package im.dadoo.fileserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_app")
public class App {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, unique = true, length = 50)
	private String name;

	public static App create(String name) {
		App app = new App();
		app.name = name;
		return app;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(String.format("id:%d,name:%s", id, name));
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
	
}
