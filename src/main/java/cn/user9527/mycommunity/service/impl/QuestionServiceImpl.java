package cn.user9527.mycommunity.service.impl;

import cn.user9527.mycommunity.dto.QuestionDTO;
import cn.user9527.mycommunity.mapper.QuestionMapper;
import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.Question;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.QuestionService;
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
    public List<QuestionDTO> list_Question() {

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionMapper.all_Question();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();

            BeanUtils.copyProperties(question, questionDTO);

            User user = userMapper.findById(questionDTO.getCreator());

            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
