package com.example.mycould.service;

import com.example.mycould.domain.FileFold;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileFoldService {
    List<FileFold> getFileFoldList(String path);
    String fileService(String path);
}
