package com.jcmc.demo.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.net.InetAddress.getLocalHost;

public class UuidUtil {

    private static final Logger LOG = Logger.getLogger(UuidUtil.class);

    public static String getUUID() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMAT_YYYYMMDDHHMMSSSS);

        String idNode = StringUtil.EMPTY_STRING;
        try {
            String addressHost = getLocalHost().getHostAddress();
            String[] splitAdress
                    = addressHost.split(StringUtil.concat(
                    StringUtil.BACKSLASH_STRING,
                    StringUtil.PERIOD_STRING));
            idNode = splitAdress[3];
        } catch (Exception ex) {
            LOG.error( "Host Not Found", ex);
        }
        String date = sdf.format(new Date());
        sb.append(idNode).append(StringUtil.DASH_STRING).append(date);
        return sb.toString();
    }
}
