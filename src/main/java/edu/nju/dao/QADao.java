package edu.nju.dao;

import java.util.List;
import java.util.Map;

import edu.nju.entities.Answer;
import edu.nju.entities.LikeInfo;
import edu.nju.entities.Question;

public interface QADao {

	boolean publishQuestion(Question q);

	boolean addAnswer(Answer a);

	List<Question> getAllQuestion();

	List<Answer> getAnswers(String qid);

	int getAnswerNum(String qid);

	boolean addlike(LikeInfo like);

	boolean revokeLike(String answerid, String openid);

	Map<Answer,Integer> sortByLikes(String pid);

	List<Answer> sortByDate(String qid);

	boolean ifAnswer(String openid, String qid);

}
