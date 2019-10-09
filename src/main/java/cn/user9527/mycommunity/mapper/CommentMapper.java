package cn.user9527.mycommunity.mapper;

import cn.user9527.mycommunity.model.Comment;
import cn.user9527.mycommunity.model.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Wed Oct 09 15:39:24 CST 2019
     */
    long countByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Wed Oct 09 15:39:24 CST 2019
     */
    int deleteByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Wed Oct 09 15:39:24 CST 2019
     */
    int insert(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Wed Oct 09 15:39:24 CST 2019
     */
    int insertSelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Wed Oct 09 15:39:24 CST 2019
     */
    List<Comment> selectByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Wed Oct 09 15:39:24 CST 2019
     */
    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Wed Oct 09 15:39:24 CST 2019
     */
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);


    @Select("SELECT `id`,`parent_id`,`type`,`commentator`,`gmt_create`,`gmt_modified`,`like_count`,`comment` \n" +
            "FROM `myone`.`comment` WHERE parent_id = ${id}")
    Comment selectByParentKey(Integer parentId);
}
