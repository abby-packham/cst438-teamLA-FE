package cst438.fr.fe;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class Utils {

    public static final String COOKIE_USER_ID = "userid";
    
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            System.out.println("-----------------------" + cookies.toString());

            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(cookieName)) {
                    System.out.println("-----------------------" + cookie.toString());
                    return cookie.getValue();
                }
            }

        }
        return null;
    }
    
    public static void setUserCookie(String userID, HttpServletResponse response) {
        Cookie cookie = new Cookie(Utils.COOKIE_USER_ID, userID);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(Utils.COOKIE_USER_ID, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    
    public static void setUserIDModel(HttpServletRequest request, Model model) {
        String userID = Utils.getCookieValue(request, Utils.COOKIE_USER_ID);
        if (userID != null) {
            model.addAttribute(Utils.COOKIE_USER_ID, userID);
        }
    }
    
}
