package cn.user9527.mycommunity.service;

import cn.user9527.mycommunity.dto.CommentDTO;
import cn.user9527.mycommunity.dto.QuestionDTO;
import cn.user9527.mycommunity.exception.CustomizeException;
import cn.user9527.mycommunity.model.Question;
import cn.user9527.mycommunity.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @date 2019/9/30 - 12:57
 */
public interface QuestionService {

    List<QuestionDTO> list_Question(PageUtil util);

    Integer num_Question();

    List<QuestionDTO> select_Question(Integer id,PageUtil util);

    Integer this_Question(Integer id);

    QuestionDTO getById(Integer id,Integer userid);

    void createOrUpdate(Question question);

    void addView(Integer id);

    List<QuestionDTO> selectRelated(QuestionDTO questionDTO);
}
