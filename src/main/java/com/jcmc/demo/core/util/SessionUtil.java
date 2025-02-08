package com.jcmc.demo.core.util;

import com.jcmc.demo.auth.model.User;
import org.slf4j.MDC;

public class SessionUtil {

    public static User getUser(){
        User user = new User();
        user.setIdUsuario(Integer.valueOf(MDC.get("id_user")));
        user.setName(MDC.get("usuario"));
        return user;
    }
}
