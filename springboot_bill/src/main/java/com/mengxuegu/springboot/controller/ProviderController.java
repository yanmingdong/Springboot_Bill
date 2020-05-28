package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.dao.ProviderDao;
import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 供应商的控制层
 */
@Controller
public class ProviderController {

    @Autowired
    private ProviderMapper providerMapper;

    @GetMapping("/providers")
    public String list(Map<String, Object> map, Provider provider) {
        List<Provider> providers = providerMapper.getProviders(provider);
        map.put("providers", providers);
        //回写
        map.put("providerName", provider.getProviderName());
        return "provider/list";
    }

    @GetMapping("/provider/{pid}")
    public String view(@PathVariable("pid") Integer pid, Map<String, Object> map,
                       @RequestParam(value = "type", defaultValue = "view") String type) {
        Provider provider = providerMapper.getProviderById(pid);
        map.put("provider", provider);
        //如果type=null,进入view页面,否则进入update
        return "provider/" + type;
    }

    @PutMapping("/provider")
    public String update(Provider provider) {
        System.out.println("后台执行了");
        providerMapper.updateProvider(provider);
        return "redirect:/providers";
    }

    @GetMapping("/provider")
    public String toAddPage(){
        return "provider/add";
    }

    @PostMapping("/provider")
    public String add(Provider provider){
        providerMapper.addProvider(provider);
        return "redirect:/providers";
    }

    @DeleteMapping("/provider/{pid}")
    public String delete(@PathVariable("pid")Integer pid){
        providerMapper.deleteProvider(pid);
        return "redirect:/providers";
    }


}
