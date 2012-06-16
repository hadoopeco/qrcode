package com.pupuly.webapp.action;

import com.pupuly.Constants;
import com.pupuly.model.User;
import com.pupuly.webapp.common.ExternalAuthenticationToken;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.*;
import com.taobao.api.response.ItemsListGetResponse;
import com.taobao.api.response.ProductsGetResponse;
import com.taobao.api.response.UserGetResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * User: wbmark
 * Author: marks@126.com
 * Date: Jul 31, 2011
 * Time: 1:41:47 PM
 */
public class TaoBaoLoginAction extends BaseAction {


    private String code;
    private String top_session;
    private String top_parameters;
    private String top_appkey;
    private String top_sign;
    private UserDetailsService  userDetailsService;
    private TaobaoClient client;
    private AuthenticationDetailsSource authenticationDetailsSource;
    private ProductsGetResponse proReq;

    public ProductsGetResponse getProReq() {
        return proReq;
    }

    public void setProReq(ProductsGetResponse proReq) {
        this.proReq = proReq;
    }

    private UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();

    public TaobaoClient getClient() {
        return client;
    }

    public void setClient(TaobaoClient client) {
        this.client = client;
    }

    public void setAuthenticationDetailsSource(AuthenticationDetailsSource authenticationDetailsSource) {
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String getTop_parameters() {
        return top_parameters;
    }

    public void setTop_parameters(String top_parameters) {
        this.top_parameters = top_parameters;
    }

    public String getTop_appkey() {
        return top_appkey;
    }

    public void setTop_appkey(String top_appkey) {
        this.top_appkey = top_appkey;
    }

    public String getTop_sign() {
        return top_sign;
    }

    public void setTop_sign(String top_sign) {
        this.top_sign = top_sign;
    }

    public String getTop_session() {
        return top_session;
    }

    public void setTop_session(String top_session) {
        this.top_session = top_session;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getTaoBaoCode() throws IOException {
        log.debug("code retrieve  code=" +code +"  top_parameters="+top_parameters+" top_session="+top_session+"  top_sign"+top_sign);
     	UserGetRequest req = new UserGetRequest();//实例化具体API对应的Request类
       	req.setFields("nick,sex,buyer_credit,seller_credit ,created,last_visit");
        UserGetResponse response;
         try {
             response = client.execute(req,top_parameters); //执行API请求并打印结果
             User user ;
             try{
                user = userManager.getUserByUsername(response.getUser().getNick());
             }catch (UsernameNotFoundException e){
                com.taobao.api.domain.User tuser = response.getUser();
                user = new User();
                user.setEnabled(true);
                // Set the default user role on this new user
                user.addRole(roleManager.getRole(Constants.TAOBAO_ROLE));
                user.setUsername(tuser.getNick());
                user.setPassword(tuser.getNick());
                user.setFirstName("tet");
                user.setLastName("tet");
                user.setEmail(tuser.getNick()+"@gmail.com");
                userManager.save(user);
                getSession().setAttribute(Constants.REGISTERED, Boolean.TRUE);
             }
             // log user in automatically
             AbstractAuthenticationToken auth = new ExternalAuthenticationToken("123123", user, user.getAuthorities());
//             PreAuthenticatedAuthenticationToken pre = new PreAuthenticatedAuthenticationToken();
//             auth.setDetails(authenticationDetailsSource.buildDetails(getRequest()));
             auth.setDetails(user);
             userDetailsChecker.check(user);
             SecurityContextHolder.getContext().setAuthentication(auth);
             getRequest().getSession().setAttribute("top_session",top_session);
             System.out.println("result:"+response.getBody());
         } catch (ApiException e) {
         }
        return SUCCESS;
    }


    public  String getProducts(){
        String nickName = getUser().getUsername();
        ProductsGetRequest req = new ProductsGetRequest();
        req.setNick(nickName);
        req.setFields("product_id,tsc,cat_name,name");
        try {
            proReq = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return SUCCESS;
    }



//    public  String items(){
//        ItemsGetRequest req = new ItemsGetRequest();
//        req.s
//
//    }





}
