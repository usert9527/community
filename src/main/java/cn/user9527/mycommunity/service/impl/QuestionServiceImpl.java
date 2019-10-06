package cn.user9527.mycommunity.service.impl;

import cn.user9527.mycommunity.dto.QuestionDTO;
import cn.user9527.mycommunity.exception.CustomizeErrorCode;
import cn.user9527.mycommunity.exception.CustomizeException;
import cn.user9527.mycommunity.mapper.QuestionMapper;
import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.Question;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.QuestionService;
import cn.user9527.mycommunity.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2019/9/30 - 12:59
 */
@Service
public class QuestionServiceImpl implements QuestionService {


    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<QuestionDTO> list_Question(PageUtil util) {

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        List<Question> questionList = questionMapper.all_Question(util);

        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();

            BeanUtils.copyProperties(question, questionDTO);

            User user = userMapper.findById(questionDTO.getCreator());

            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    @Override
    public Integer num_Question() {
        return questionMapper.num_Question();
    }


    @Override
    public List<QuestionDTO> select_Question(Integer id, PageUtil util) {

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        List<Question> questionList = questionMapper.select_Question(id, util);

        for (Question question : questionList) {

            QuestionDTO questionDTO = new QuestionDTO();

            BeanUtils.copyProperties(question, questionDTO);

            User user = userMapper.findById(questionDTO.getCreator());

            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    @Override
    public Integer this_Question(Integer id) {
        return questionMapper.this_Question(id);
    }

    @Override
    public QuestionDTO getById(Integer id, Integer userid) {
        Question question = questionMapper.getById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_EXISTS);
        }
        QuestionDTO questionDTO = new QuestionDTO();

        BeanUtils.copyProperties(question, questionDTO);

        User user = userMapper.findById(question.getCreator());

        System.out.println("当前"+userid+"发布人"+user.getId());

        if (userid != null && userid != user.getId()) {
            questionMapper.update_ById_Question(id);
        }
        questionDTO.setUser(user);

        return questionDTO;
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.create(question);
        } else {
            //更新
            question.setGmtModified(System.currentTimeMillis());
            Integer update = questionMapper.update(question);
            if (update == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_UPDATE_EXISTS);
            }
        }

    }

    @Override
    public void addView(Integer id) {

    }
}
