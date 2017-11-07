package com.example.mycould.controller;

import com.example.mycould.domain.FileFold;
import com.example.mycould.service.FileFoldService;
import com.example.mycould.utils.IsNull;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class FileController {

    @Autowired
    private FileFoldService fileFoldService;

    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "/filePath/**",method = RequestMethod.GET)
    public String getFilePath( Map<String,Object> map, Model model, ModelMap modelMap, ModelAndView modelAndView) {
        String path = request.getServletPath();
        if (path.length() < 10) {
            path += "/";
        }
        path = path.substring(10, path.length());
        if (IsNull.isNull(path)) {
            path = "H:\\";
        } else {
            path = "H:\\" + path;
        }
        List<FileFold> fileFolds = fileFoldService.getFileFoldList(path);
        model.addAttribute("files", fileFolds);
        return "file";
    }

}
