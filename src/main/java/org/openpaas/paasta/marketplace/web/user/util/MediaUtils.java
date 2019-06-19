package org.openpaas.paasta.marketplace.web.user.util;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

/**
 * 이미지 파일 분기 Utility
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-06-19
 */
public class MediaUtils {
    private static Map<String, MediaType> mediaMap;

    static {
        mediaMap = new HashMap<String, MediaType>();
        mediaMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaMap.put("JPEG", MediaType.IMAGE_JPEG);
        mediaMap.put("GIF", MediaType.IMAGE_GIF);
        mediaMap.put("PNG", MediaType.IMAGE_PNG);
    }

    public static MediaType getMediaType(String type) {
        return mediaMap.get(type.toUpperCase());
    }
}
