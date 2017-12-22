package edu.nju.dao.impl;

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
	public boolean publishQuestion(Question q) {
		try {
			baseDao.save(q);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAnswer(Answer a) {
		try {
			baseDao.save(a);
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
	public int getAnswerNum(String qid) {
		String hql = "select count(*) from Answer where qid=:qid";
		int num = (int) baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).uniqueResult();
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

	@Override
	public boolean revokeLike(String answerid, String openid) {
		String hql = "delete LikeInfo where answerid=:answerid and openid=:openid";
		int x =baseDao.getNewSession().createQuery(hql).setParameter("answerid", answerid).setParameter("openid", openid).executeUpdate();
		if(x>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Answer> sortByLikes(String pid) {
		//TODO
		return null;
	}

	@Override
	public Answer getMostLike(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
