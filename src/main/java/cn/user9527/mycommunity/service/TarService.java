package cn.user9527.mycommunity.service;


import cn.user9527.mycommunity.model.Tar;
import cn.user9527.mycommunity.model.TarType;

import java.util.List;

/**
 * @date 2019/10/14 - 10:07
 */
public interface TarService {

    List<Tar> selectTarType(Integer id);

}
