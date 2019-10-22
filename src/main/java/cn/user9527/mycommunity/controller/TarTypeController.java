package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.model.Tar;
import cn.user9527.mycommunity.model.TarType;
import cn.user9527.mycommunity.service.TarService;
import cn.user9527.mycommunity.service.TarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @date 2019/10/14 - 10:25
 */
@Controller
public class TarTypeController {

    @Autowired
    private TarTypeService tarTypeService;

    @Autowired
    private TarService tarService;

    @RequestMapping(value = "/allTarType",method = RequestMethod.POST)
    @ResponseBody
    public List<TarType> allTarType(){
        return tarTypeService.selectTarType();
    }

    @RequestMapping(value = "/allTar/{id}",method = RequestMethod.POST)
    @ResponseBody
    public List<Tar> allTar(@PathVariable(name="id")Integer id){
        return tarService.selectTarType(id);
    }

}
