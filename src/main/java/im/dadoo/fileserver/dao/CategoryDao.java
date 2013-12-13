package im.dadoo.fileserver.dao;

import java.util.List;

import im.dadoo.fileserver.domain.Category;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao extends BaseDao<Category> {

	public CategoryDao() {
		super(Category.class);
	}
	
	public Category fetchByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Category) session.createCriteria(Category.class)
			.add(Restrictions.like("name", name))
			.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> listBySup(Category sup) {
		Session session = this.sessionFactory.getCurrentSession();
		return (List<Category>) session.createCriteria(Category.class)
				.add(Restrictions.eq("sup", sup)).list();
	}
}
