package edu.nju.dao;

import java.util.List;
import java.util.Map;

import edu.nju.entities.Answer;
import edu.nju.entities.LikeInfo;
import edu.nju.entities.Question;

public interface QADao {

	String publishQuestion(Question q);

	String addAnswer(Answer a);

	List<Question> getAllQuestion();

	List<Answer> getAnswers(String qid);

	Long getAnswerNum(String qid);

	boolean addlike(LikeInfo like);

	boolean revokeLike(String qid,String answerid, String authorid,String likeid);

	Map<Answer,Integer> sortByLikes(String pid);

	List<Answer> sortByDate(String qid);

	boolean ifAnswer(String openid, String qid);

	Answer getByAnswerId(String aid);

	Long getLikeNum(String answerid);

	Question getByQuestionId(String qid);

	boolean checkIfLike(String qid, String answerid, String authorid, String likeid);

}
