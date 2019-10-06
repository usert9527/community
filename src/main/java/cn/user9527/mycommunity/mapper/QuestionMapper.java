package cn.user9527.mycommunity.mapper;

import cn.user9527.mycommunity.model.Question;
import cn.user9527.mycommunity.util.PageUtil;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
            "FROM `myone`.`question` LIMIT  #{rowStart},#{pageSize} ")
    List<Question> all_Question(PageUtil util);

    @Select("select count(1) from question")
    Integer num_Question();

    @Select("SELECT `id`,`title`,`description`,`gmt_create`,`gmt_modified`,`creator`,`comment_count`,`view_count`,`like_count`,`tag` \n" +
            "FROM `myone`.`question` where creator = #{id} LIMIT  #{page.rowStart},#{page.pageSize} ")
    List<Question> select_Question(@Param("id") Integer id,@Param("page") PageUtil util);

    @Select("select count(1) from question where creator = #{id}")
    Integer this_Question(Integer id);

    /**
     * 问题详情
     * @param id
     * @return
     */
    @Select("SELECT `id`,`title`,`description`,`gmt_create`,`gmt_modified`,`creator`,`comment_count`,`view_count`,`like_count`,`tag` \n" +
            "FROM `myone`.`question` where id = #{id} ")
    Question getById(@Param("id") Integer id);

    @Update("update question set title = #{title},description = #{description},gmt_modified = #{gmtModified},tag = #{tag} where id = #{id}")
    Integer update(Question question);

    @Update("UPDATE question SET view_count =view_count + 1 WHERE id = #{id}")
    Integer update_ById_Question(Integer id);
}
