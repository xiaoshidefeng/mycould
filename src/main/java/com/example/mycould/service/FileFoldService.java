package com.example.mycould.service;

import com.example.mycould.domain.FileFold;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public interface FileFoldService {
    List<FileFold> getFileFoldList(String path);
    String fileService(String path);
    String downFile(Model model, HttpServletRequest request, HttpServletResponse response);
}
