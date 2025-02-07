package com.jcmc.demo.core.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class UuidUtil {

    @Qualifier("hostName")
    private static String hostName;

    public UuidUtil(String hostName) {
        this.hostName = hostName;
    }

    public static String getUUID() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT_YYYYMMDDHHMMSSSS);
        String uuid = UUID.randomUUID().toString();
        if (hostName != null) {
            return sb.append(hostName)
                    .append(StringUtil.DASH_STRING)
                    .append(uuid)
                    .append(StringUtil.DASH_STRING)
                    .append(sdf.format(new Date()))
                    .toString();
        }

        return sb.append(sdf.format(new Date()))
                .append(StringUtil.DASH_STRING)
                .append(uuid)
                .append(StringUtil.DASH_STRING)
                .append(sdf.format(new Date()))
                .toString();
    }
}
