package com.uploader.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uploader.spring.models.dto.ResponseWrapper;
import com.uploader.spring.models.dto.UploaderResponsedto;
import com.uploader.spring.service.UploaderService;
import com.uploader.spring.utils.constant.ApiPathConstant;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(ApiPathConstant.API +
        ApiPathConstant.VERSION +
        "/uploader")
public class UploaderController {

    @Autowired
    private UploaderService uploaderService;

    @PostMapping(value = "s3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseWrapper<UploaderResponsedto>> uploaderS3Handler(
            @RequestParam("file") MultipartFile file) throws IOException {

        UploaderResponsedto response = this.uploaderService.uploadData(file);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ResponseWrapper<UploaderResponsedto>(
                                HttpStatus.OK.value(),
                                Boolean.TRUE,
                                "Success Upload data",
                                response));
    }

}
