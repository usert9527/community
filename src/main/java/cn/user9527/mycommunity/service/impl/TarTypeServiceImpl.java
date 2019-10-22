package cn.user9527.mycommunity.service.impl;

import cn.user9527.mycommunity.mapper.TarTypeMapper;
import cn.user9527.mycommunity.model.TarType;
import cn.user9527.mycommunity.model.TarTypeExample;
import cn.user9527.mycommunity.service.TarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date 2019/10/14 - 12:56
 */
@Service
public class TarTypeServiceImpl implements TarTypeService {

    @Autowired
    private TarTypeMapper tarTypeMapper;

    @Override
    public List<TarType> selectTarType() {

        return tarTypeMapper.selectTarType();
    }
}
