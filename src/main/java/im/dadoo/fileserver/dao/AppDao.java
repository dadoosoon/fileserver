package im.dadoo.fileserver.dao;

import im.dadoo.fileserver.domain.App;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class AppDao extends BaseDao<App> {

	public AppDao() {
		super(App.class);
	}
	
	public App fetchByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		return (App) session.createCriteria(App.class)
				.add(Restrictions.like("name", name))
				.uniqueResult();
	}
}
