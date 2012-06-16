package com.pupuly.webapp.action;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.value.StringValue;
import org.apache.poi.util.StringUtil;

import javax.jcr.*;

/**
 * User: wbmark
 * Author: marks@126.com
 * Date: Jul 31, 2011
 * Time: 12:45:48 PM
 */
public class TaoBaoClient {
    protected static String url = "http://gw.api.tbsandbox.com/router/rest";//沙箱环境调用地址
//    protected static String url = "http://gw.api.taobao.com/router/rest";//正式环境需要设置为:
    	protected static String appkey = "12007234";
    	protected static String appSecret = "sandbox37f9acf1bd98c87ca326eacd8";
//    	protected static String appSecret = "85abd2f37f9acf1bd98c87ca326eacd8";
    	public static void testUserGet() {
        	 TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);//实例化TopClient类

//        	 UserGetRequest req = new UserGetRequest();//实例化具体API对应的Request类
             ProductsGetRequest req = new ProductsGetRequest();
             req.setFields("product_id,tsc,cat_name,name");
//        	 req.setFields("nick,sex,buyer_credit,seller_credit ,created,last_visit");
              req.setNick("sandbox_c_1");
        	 ProductsGetResponse response;
//        	 UserGetResponse response;
        	 try {
            	 response = client.execute(req); //执行API请求并打印结果
            	 System.out.println("result:"+response.getBody());
        	 } catch (ApiException e) {
        	 // deal error
        	 }
    	}

        public static void getItem() throws ApiException {
             TaobaoClient client=new DefaultTaobaoClient(url, appkey, appSecret);
            ItemGetRequest req=new ItemGetRequest();
            req.setNumIid(6000306192l);
            req.setFields("num_iid,detail_url,title,nick,pic_url,cid,price,type,delist_time,post_fee,score,volume");
            ItemGetResponse response = client.execute(req);
            System.out.println(response.getBody());
            System.out.println("error code ="+response.getErrorCode());
            System.out.println("error msg ="+response.getMsg());
            System.out.println("error sub msg ="+response.getSubMsg());
            System.out.println("error sub code ="+response.getSubCode());
            System.out.println("error reponse body ="+response.getBody());
        }

        public static void getItems() throws ApiException {
            TaobaoClient client=new DefaultTaobaoClient(url, appkey, appSecret);
            ItemsListGetRequest req=new ItemsListGetRequest();
            req.setNumIids("6000303346,6000304700");
            req.setFields("num_iid,detail_url,title,nick,pic_url,cid,price,type,delist_time,post_fee,score,volume");
            ItemsListGetResponse response = client.execute(req);
            System.out.println("error code ="+response.getErrorCode());
            System.out.println("error msg ="+response.getMsg());
            System.out.println("error sub msg ="+response.getSubMsg());
            System.out.println("error sub code ="+response.getSubCode());
            System.out.println("error respons body ="+response.getBody());
//            System
        }

        public static void getOnsaleItem(){
             TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);//实例化TopClient类
             ItemsOnsaleGetRequest req = new ItemsOnsaleGetRequest();
             req.setFields("num_iid,title,nick,pic_url,detail_url,cid");
//             req.setFields("sku_id,iid,properties,quantity,price,outer_id,created,modified,status");
             ItemsOnsaleGetResponse  res;
//
        	 try {
            	 res = client.execute(req,"alipublic21"); //执行API请求并打印结果
            	 System.out.println("result:"+res.getBody());
        	 } catch (ApiException e) {
        	 // deal error
        	 }
        }

    public static void testRepo() throws RepositoryException {
            Repository r =new  TransientRepository();
            Session session = r.login(new SimpleCredentials("userid", "".toCharArray()));
            try{
            Workspace ws = session.getWorkspace();

            String s = ws.getNamespaceRegistry().getURI("wiki");
            if(StringUtils.isEmpty(s)){
                ws.getNamespaceRegistry().registerNamespace("wiki", "http://www.barik.net/wiki/1.0");
            }
            Node rn = session.getRootNode();
            Node encyclopedia = rn.addNode("wiki:encyclopedia");

            Node p = encyclopedia.addNode("wiki:entry");
            p.setProperty("wiki:title", new StringValue("rose"));
            p.setProperty("wiki:content", new
            StringValue("A rose is a flowering shrub."));
            p.setProperty("wiki:category",
            new Value[]{
            new StringValue("flower"),
            new StringValue("plant"),
            new StringValue("rose")});

             Node n = encyclopedia.addNode("wiki:entry");
            n.setProperty("wiki:title", new StringValue("Shakespeare"));
            n.setProperty("wiki:content", new
            StringValue("A famous poet who likes roses."));
            n.setProperty("wiki:category", new StringValue("poet"));

            session.save();
            }finally {
                session.logout();

            }

    }
    	public static void main(String[] args) throws ApiException, RepositoryException {
            TaoBaoClient.getOnsaleItem();
//        	 TaoBaoClient.getItem();

//            testRepo();
    	}
}
