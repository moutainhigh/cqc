package com.cqc.portal.controller;

import com.cqc.common.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadImgController {

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${upload.urlPrefix}")
    private String urlPrefix;

    @PostMapping("/uploadImg")
    public Result<String> upload(@NotBlank(message = "文件不能为空") MultipartFile file) throws IOException {

        log.info("保存文件地址: {}, 访问地址:{}", uploadDir, urlPrefix);
        String filename = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        FileUtils.copyInputStreamToFile(inputStream, new File(uploadDir+"/receive", filename));
        // 返回
        String url = urlPrefix +"/receive/" + filename;

        return Result.success(url);


    }

}
