package cn.user9527.mycommunity.service.impl;

import cn.user9527.mycommunity.dto.CommentDTO;
import cn.user9527.mycommunity.dto.QuestionDTO;
import cn.user9527.mycommunity.exception.CustomizeErrorCode;
import cn.user9527.mycommunity.exception.CustomizeException;
import cn.user9527.mycommunity.mapper.QuestionMapper;
import cn.user9527.mycommunity.mapper.TarMapper;
import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.Question;
import cn.user9527.mycommunity.model.Tar;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.QuestionService;
import cn.user9527.mycommunity.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2019/9/30 - 12:59
 */
@Service
public class QuestionServiceImpl implements QuestionService {


    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TarMapper tarMapper;

    @Override
   //unless="#result == null"是指当查询为空时，不缓存，默认是空也会缓存。
    public List<QuestionDTO> list_Question(PageUtil util) {

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        //查询所有问题
        List<Question> questionList = questionMapper.all_Question(util);

        //封装 -----要重构
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
//    @Cacheable(value = "getById", key = "#id")
    public QuestionDTO getById(Integer id, Integer userid) {

        Question question = questionMapper.getById(id);

        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_EXISTS);
        }

        String[] split = question.getTag().split(",");
        int[] ints = new int[split.length];

        for(int i=0;i<split.length;i++){
            ints[i] = Integer.parseInt(split[i]);
        }


        List<Tar> tars = tarMapper.selectByIdTar(ints);

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setTags(tars);

        BeanUtils.copyProperties(question, questionDTO);

        User user = userMapper.findById(question.getCreator());


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

    @Override
    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        if(StringUtils.isBlank(questionDTO.getTag())){
            return new ArrayList<>();
        }
        String[] split = questionDTO.getTag().split(",");
        String join = String.join("|", split);

        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setTag(join);

        //相关问题 根据标签来查找  可根据评论数 点赞数 阅读数
        List<Question> questionList = questionMapper.selectRelated(question);

        List<QuestionDTO> collect = questionList.stream().map(q -> {
            QuestionDTO questionDTO1 = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO1);
            return questionDTO1;
        }).collect(Collectors.toList());
        return collect;
    }
}
