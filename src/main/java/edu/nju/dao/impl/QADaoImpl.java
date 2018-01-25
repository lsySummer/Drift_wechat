package edu.nju.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
	public String addAnswer(Answer a) {
		try {
			baseDao.save(a);
			return a.getId();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllQuestion(int start,int page) {
		String hql = "from Question order by createTime desc";
//		query0.setFirstResult(start); // 开始记录
//		query0.setMaxResults(10); // 查询多少条
		List<Question> list = baseDao.getNewSession().createQuery(hql).setFirstResult(start).setMaxResults(page).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getAnswers(String qid,int start,int num) {
		String hql = "from Answer where qid =:qid";
		List<Answer> list = baseDao.getNewSession().createQuery(hql).setFirstResult(start).setMaxResults(num).setParameter("qid", qid).getResultList();
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
	public List<Answer> sortByLikes(String qid) {
		List<Answer> result = new ArrayList<Answer>();
		List<String> sortIds = new ArrayList<String>();//有点赞的答案id集合
		String hql = "select answerid,count(*) as cnt " + 
				"from LikeInfo where qid = :qid " + 
				"group by answerid " + 
				"order by cnt desc";
		List<Object[]> list= baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).getResultList();
		for(int i=0;i<list.size();i++) {
			String answerid = list.get(i)[0].toString();//authorid
			Answer a = getByAnswerId(answerid);
			result.add(a);
			sortIds.add(a.getId());
		}
		String hql1 = "select id from Answer where qid =:qid";
		List<String> answers = baseDao.getNewSession().createQuery(hql1).setParameter("qid", qid).getResultList();//全部答案id
		for(int i=0;i<answers.size();i++) {
			if(!sortIds.contains(answers.get(i))) {
				result.add(getByAnswerId(answers.get(i)));
			}
		}
		return result;
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkIfLike(String qid, String answerid, String authorid, String likeid) {
		String hql = "from LikeInfo where qid=:qid and answerid=:answerid and authorid=:authorid and likeid=:likeid";
		List<LikeInfo> list=baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).setParameter("answerid", answerid).
				setParameter("authorid", authorid).setParameter("likeid", likeid).getResultList();
		if(list.size()>0) {
			return true;
		}
		return false;
	}

	@Override
	public Long getQuestionNum() {
		String hql = "select count(*) from Question";
		Long num = (Long) baseDao.getNewSession().createQuery(hql).getSingleResult();
		return num;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionByNum(int start, int num) {
		List<Question> result = new ArrayList<Question>();
		List<String> sortIds = new ArrayList<String>();//有点赞的答案id集合
		String hql = "select qid,count(*) as cnt " + 
				"from Answer " + 
				"group by qid " + 
				"order by cnt desc";
		List<Object[]> list= baseDao.getNewSession().createQuery(hql).getResultList();
		for(int i=0;i<list.size();i++) {
			String qid = list.get(i)[0].toString();
			Question q = getByQuestionId(qid);
			if(q!=null) {
				result.add(q);
				sortIds.add(q.getId());
			}
		}
		String hql1 = "select id from Question";
		List<String> questions = baseDao.getNewSession().createQuery(hql1).getResultList();//全部答案id
		for(int i=0;i<questions.size();i++) {
			String tmpId = questions.get(i);
			if(!sortIds.contains(tmpId)) {
				result.add(getByQuestionId(tmpId));
			}
		}
		result = result.subList(start, start+num);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LikeInfo> getLike(String answerid) {
		String hql = "from LikeInfo where answerid = :answerid";
		List<LikeInfo> list = baseDao.getNewSession().createQuery(hql).setParameter("answerid", answerid)
				.getResultList();
		return list;
	}
}
