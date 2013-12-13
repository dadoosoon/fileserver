package im.dadoo.fileserver.dao;

import java.util.Map;

import im.dadoo.fileserver.domain.App;
import im.dadoo.fileserver.domain.Category;
import im.dadoo.fileserver.domain.Dadoofile;
import im.dadoo.fileserver.util.Interval;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DadoofileDao extends BaseDao<Dadoofile> {

	public DadoofileDao() {
		super(Dadoofile.class);
	}
	
	public Dadoofile fetchByMd5(String md5) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Dadoofile) session.createCriteria(Dadoofile.class)
			.add(Restrictions.like("md5", md5))
			.uniqueResult();
	}
	
	protected Criteria makeQuery(Criteria criteria, Map<String, Object> params) {
		if (params.containsKey("name")) {
			String title = (String)params.get("name");
			criteria.add(Restrictions.like("name", title, MatchMode.ANYWHERE));
		}
		if (params.containsKey("md5")) {
			String md5 = (String)params.get("md5");
			criteria.add(Restrictions.like("md5", md5, MatchMode.EXACT));
		}
		if (params.containsKey("category")) {
			Category category = (Category)params.get("category");
			criteria.add(Restrictions.eq("category", category));
		}
		if (params.containsKey("createDatetime")) {
			Interval<Long> createDatetime = (Interval<Long>)params.get("createDatetime");
			criteria.add(Restrictions.between("createDatetime", createDatetime.v1, createDatetime.v2));
		}
		return criteria;
	}
}
