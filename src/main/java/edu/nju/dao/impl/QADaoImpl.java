package edu.nju.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.QADao;
import edu.nju.entities.Answer;
import edu.nju.entities.LikeInfo;
import edu.nju.entities.Question;

@Repository
public class QADaoImpl implements QADao{
	
	@Autowired
	BaseDao baseDao;

	@Override
	public String publishQuestion(Question q) {
		try {
			baseDao.save(q);
			return q.getId();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addAnswer(Answer a) {
		try {
			baseDao.save(a);
			System.out.println(a.getId());
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllQuestion() {
		String hql = "from Question";
		List<Question> list = baseDao.getNewSession().createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getAnswers(String qid) {
		String hql = "from Answer where qid =:qid";
		List<Answer> list = baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).getResultList();
		return list;
	}

	@Override
	public Long getAnswerNum(String qid) {
		String hql = "select count(*) from Answer where qid=:qid";
		Long num = (Long) baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).getSingleResult();
		return num;
	}

	@Override
	public boolean addlike(LikeInfo like) {
		try {
			baseDao.save(like);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean revokeLike(String qid,String answerid, String authorid,String likeid) {
		String hql = "from LikeInfo where qid=:qid and answerid=:answerid and authorid=:authorid and likeid=:likeid";
		List<LikeInfo> list=baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).setParameter("answerid", answerid).
				setParameter("authorid", authorid).setParameter("likeid", likeid).getResultList();
		if(list.size()>0) {
			baseDao.delete(list.get(0));
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Answer,Integer> sortByLikes(String qid) {
		Map<Answer,Integer> map = new HashMap<Answer,Integer>();
		String hql = "select authorid,count(*) as cnt " + 
				"from LikeInfo where qid = :qid " + 
				"group by authorid " + 
				"order by cnt desc";
		List<Object[]> list= baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).getResultList();
		for(int i=0;i<list.size();i++) {
			String authorid = list.get(i)[0].toString();//authorid
			Answer a = getAnswer(qid,authorid);
			map.put(a, Integer.parseInt(list.get(i)[1].toString()));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public Answer getAnswer(String qid,String authorid) {
		String hql = "from Answer where qid=:qid and openid=:authorid";
		List<Answer> list = baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).setParameter("authorid", authorid).getResultList();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> sortByDate(String qid) {
		String hql = "from Answer where qid = :qid " + 
				"order by createTime desc";
		List<Answer> list= baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean ifAnswer(String openid, String qid) {
		String hql = "from Answer where qid = :qid and openid = :openid";
		List<Answer> list = baseDao.getNewSession().createQuery(hql).setParameter("qid", qid)
				.setParameter("openid", openid).getResultList();
		if(list.size()>0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Answer getByAnswerId(String aid) {
		String hql = "from Answer where id = :aid";
		List<Answer> list = baseDao.getNewSession().createQuery(hql).setParameter("aid", aid)
				.getResultList();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Long getLikeNum(String answerid) {
		String hql = "select count(*) from LikeInfo where answerid=:answerid";
		Long num = (Long) baseDao.getNewSession().createQuery(hql).setParameter("answerid", answerid).getSingleResult();
		return num;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Question getByQuestionId(String qid) {
		String hql = "from Question where id = :qid";
		List<Question> list = baseDao.getNewSession().createQuery(hql).setParameter("qid", qid)
				.getResultList();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
