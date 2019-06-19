package org.openpaas.paasta.marketplace.web.user.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 파일 조회 Utility
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-06-19
 */
@Slf4j
public class FileUtils {

    public static ResponseEntity<byte[]> displayImageFile(String filePath, String fileName) throws Exception {
        InputStream in = null;
        ResponseEntity<byte[]> entity;
        log.info("file path : " + filePath);
        log.info("file name : " + fileName);

        try{
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            MediaType mediaType = MediaUtils.getMediaType(formatName);
            HttpHeaders headers = new HttpHeaders();
            in = new FileInputStream(filePath + fileName);

            if(mediaType != null) {
                headers.setContentType(mediaType);
            }else {
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
            }

            entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

        }catch (Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }finally {
            in.close();
        }
        return entity;
    }
}
