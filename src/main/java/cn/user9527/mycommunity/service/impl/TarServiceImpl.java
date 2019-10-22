package cn.user9527.mycommunity.service.impl;

import cn.user9527.mycommunity.mapper.TarMapper;
import cn.user9527.mycommunity.model.Tar;
import cn.user9527.mycommunity.service.TarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date 2019/10/14 - 10:08
 */
@Service
public class TarServiceImpl implements TarService {

    @Autowired
    private TarMapper tarMapper;


    @Override
    public List<Tar> selectTarType(Integer id) {
        return tarMapper.selectTar(id);
    }
}
