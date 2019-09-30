package cn.user9527.mycommunity.mapper;

import cn.user9527.mycommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @date 2019/9/29 - 18:45
 */
public interface UserMapper {

    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,avatar_url) value(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select id,account_id,name,token,gmt_create,gmt_modified,avatar_url from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select account_id,name,token,gmt_create,gmt_modified,avatar_url from user where id = #{id}")
    User findById(@Param("id") Integer id);
}
