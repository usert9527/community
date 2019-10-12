package cn.user9527.mycommunity.mapper;

import cn.user9527.mycommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @date 2019/9/29 - 18:45
 */
public interface UserMapper {

    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,avatar_url) value(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select id,account_id,name,token,gmt_create,gmt_modified,avatar_url from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select id,account_id,name,token,gmt_create,gmt_modified,avatar_url from user where id = #{id}")
    User findById(@Param("id") Integer id);
    @Select("select id,account_id,name,token,gmt_create,gmt_modified,avatar_url from user where account_id = #{id}")
    User findByIdAccountId(String accountId);

    @Update("update user set name = #{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id = #{id}")
    void update(User userAccount);

    List<User> selectListByUserId(List<Integer> id);
}
