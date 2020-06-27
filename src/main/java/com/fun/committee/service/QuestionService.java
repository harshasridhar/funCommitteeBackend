package com.fun.committee.service;

import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.dao.DescriptiveRepository;
import com.fun.committee.dao.McqRepository;
import com.fun.committee.dao.OptionRepository;
import com.fun.committee.model.QuestionType;
import com.fun.committee.model.json.Question;
import com.fun.committee.model.json.QuestionList;
import com.fun.committee.model.sql.DescriptiveEntity;
import com.fun.committee.model.sql.McqEntity;
import com.fun.committee.model.sql.OptionEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshams on 25/06/2020
 */
@Service
public class QuestionService {

    @Autowired
    McqRepository mcqRepository;
    @Autowired
    OptionRepository optionRepository;
    @Autowired
    DescriptiveRepository descriptiveRepository;


    public void addQuestion(Question question)throws Exception{
        try{
            if(QuestionType.MCQ == question.getQuestionType()){
                McqEntity mcqEntity = new McqEntity();
                mcqEntity.setQuestion(question.getQuestion());
                mcqEntity.setHasOtherOption(question.getHasOtherOption());
                mcqEntity.setTag(question.getTag());
                List<OptionEntity> options = mcqEntity.getOptions();
                for(String option: question.getOptions()){
                    OptionEntity optionEntity = new OptionEntity();
                    optionEntity.setValue(option);
                    options.add(optionEntity);
                }
                optionRepository.saveAll(options);
                mcqEntity.setOptions(options);
                mcqRepository.save(mcqEntity);
            }else if(QuestionType.DESCRIPTIVE == question.getQuestionType()){
                DescriptiveEntity descriptiveEntity = new DescriptiveEntity();
//                BeanUtils.copyProperties(question,descriptiveEntity);
                descriptiveEntity.setQuestion(question.getQuestion());
                descriptiveEntity.setTag(question.getTag());
                descriptiveRepository.save(descriptiveEntity);
            }else {
                throw new FunCommitteeException(ErrorCode.INVALID_ARGUMENTS,"Invalid question type");
            }
        }catch (Exception e){
            throw e;
        }
    }

    public QuestionList getQuestions(){
        QuestionList questionList = new QuestionList();
        List<Question> questions = new ArrayList<>();
        for(McqEntity mcqEntity: mcqRepository.findAll()){
            Question question = new Question();
            question.setQuestionType(QuestionType.MCQ);
            BeanUtils.copyProperties(mcqEntity,question);
            question.setOptions(new ArrayList<>());
            for(OptionEntity optionEntity: mcqEntity.getOptions()){
                question.getOptions().add(optionEntity.getValue());
            }
            questions.add(question);
        }
        for(DescriptiveEntity descriptiveEntity: descriptiveRepository.findAll()){
            Question question = new Question();
            question.setQuestionType(QuestionType.DESCRIPTIVE);
            BeanUtils.copyProperties(descriptiveEntity,question);
            questions.add(question);
        }
        questionList.setList(questions);
        return questionList;
    }

}
