package com.webboard.front;

import com.webboard.models.UserVO;
import com.webboard.models.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static java.lang.Character.MIN_VALUE;
import static javafx.scene.input.KeyCode.L;

/**
 * Created by Sorravit on 2/11/2559.
 *
 */
@Controller
public class WebboardController {
    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/")
    public String main(ModelMap modelMap){
        UserVO user = userDAO.findOne(12);
        modelMap.addAttribute("user",user);
        System.out.print(user);
        return "main";
    }
    @ResponseBody
    @RequestMapping(value = "/saveMember")
    public String saveMember(HttpServletRequest request){
        try {
//            byte[] password =hash(request.getParameter("password").toCharArray(),"Salt".getBytes());
//            UserVO userVO = new UserVO(request.getParameter("name"),password.toString(),request.getParameter("email"));
            UserVO userVO = new UserVO(request.getParameter("name"),request.getParameter("password"),request.getParameter("email"));
            userDAO.save(userVO);
        }catch (Exception e){
            return "Something wrong";
        }
        return "Success";
    }
    @ResponseBody
    @RequestMapping(value = "/checkMember")
    public String checkMember(HttpServletRequest request) {
        try {
            UserVO user = userDAO.findOne(Integer.parseInt(request.getParameter("id")));
            return String.valueOf(user.getPassword().equals(request.getParameter("password")));
//        return String.valueOf(isExpectedPassword(request.getParameter("password").toCharArray(),"Salt".getBytes(),request.getParameter("expectedHash").getBytes()));
        } catch (Exception e) {
            return "false";
        }
    }
    public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;

        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    public static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
        byte[] pwdHash = hash(password, salt);
        Arrays.fill(password, MIN_VALUE);
        if (pwdHash.length != expectedHash.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) return false;
        }
        return true;
    }
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }
}
