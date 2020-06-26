package com.fun.committee.dao;

import com.fun.committee.model.json.QuestionIdAnswer;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by harshams on 26/06/2020
 */
@Repository
public class CompositeDao extends AbstractDao{

    public List<QuestionIdAnswer> getAnswersForUser(Long userId){
        Session session = openSession();
        try{
         List<QuestionIdAnswer> questionIdAnswers = session.createQuery("select q.id as questionId,q.tag as tag,ha.answer as answer  from HasAnsweredEntity ha," +
                 " QuestionEntity q where  ha.questionId = q.id and userId = :userId")
                 .setParameter("userId",userId)
                 .setResultTransformer(Transformers.aliasToBean(QuestionIdAnswer.class))
                 .list();
         return questionIdAnswers;

        }catch (Exception e){
            rollbackTransaction(session);
            throw e;
        }finally {
            closeSession(session);
        }
    }
}
