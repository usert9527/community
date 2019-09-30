package cn.user9527.mycommunity.mapper;

import cn.user9527.mycommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @date 2019/9/30 - 9:45
 */
public interface QuestionMapper {

    /**
     * 发布问题
     * @param question
     */
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);


    /**
     * 一加载就查询
     * @return
     */
    @Select("SELECT `id`,`title`,`description`,`gmt_create`,`gmt_modified`,`creator`,`comment_count`,`view_count`,`like_count`,`tag` \n" +
            "FROM `myone`.`question` ")
    List<Question> all_Question();
}
