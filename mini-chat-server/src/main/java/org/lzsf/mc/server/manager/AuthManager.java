package org.lzsf.mc.server.manager;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.lzsf.mc.server.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class AuthManager {

    private static Map<String, String> userInfoTestMap = new HashMap<>();

    private static Map<String, Long> userNameIdMap = new HashMap<>();

    static {
        userInfoTestMap.put("zhangsan", "123456");
        userNameIdMap.put("zhangsan", 24532122451234L);

        userInfoTestMap.put("lisi", "123456");
        userNameIdMap.put("lisi", 24532126811234L);
    }

    public static Long getUserId(String userName, String password) {
        if (!Objects.equals(userInfoTestMap.get(userName), password)) {
            return null;
        }
        return userNameIdMap.get(userName);
    }

    public static Long getUserIdByUserName(String userName) {
        return userNameIdMap.get(userName);
    }

    public static String genUserToken(Long userId) {
//        if (!Objects.equals(userInfoTestMap.get(userName), password)) {
//            return null;
//        }
        return JwtUtil.genJwtToken(userId);
    }

    public static boolean validateUser(String token) {
        try {
            if (token == null) {
                return false;
            }
            Claims claims = JwtUtil.parseToken(token);
            Long userId = Long.parseLong(claims.getSubject());
            if (!userNameIdMap.containsValue(userId)) {
                return false;
            }
            return !JwtUtil.isTokenExpired(claims.getExpiration());
        } catch (Exception e) {
            log.error("鉴权失败", e);
            return false;
        }
    }
}
