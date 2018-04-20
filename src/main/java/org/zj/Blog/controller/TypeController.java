package org.zj.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zj.Blog.bean.Type;
import org.zj.Blog.service.ITypeService;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    ITypeService typeService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public List<Type> addType(@RequestParam(value = "info",required = true)String desc,@RequestParam(value = "title")String title){
        Type type=new Type();
        type.setType_id(typeService.getLastIndex()+1);
        type.setTitle(title);
        type.setInfo(desc);
        typeService.insertType(type);
        System.out.println("+1 type"+type);
        return typeService.getAllType();
    }
}
