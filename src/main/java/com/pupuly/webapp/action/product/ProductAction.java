package com.pupuly.webapp.action.product;

import com.google.zxing.WriterException;
import com.pupuly.model.Product;
import com.pupuly.service.ProductService;
import com.pupuly.service.QrImgService;
import com.pupuly.webapp.action.BaseAction;
import com.pupuly.webapp.action.bean.Item;
import org.apache.commons.lang.StringUtils;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/22/11
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductAction extends BaseAction {

    private Product product;
    private Long id;
    private List<Product> products;
    private Integer total;
    private List<Item>  items=new ArrayList<Item>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private ProductService productService;

    private QrImgService qrService;

    public void setQrService(QrImgService qrService) {
        this.qrService = qrService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public String add() {
        String method = getMethod();
        if(REQUEST_POST.equalsIgnoreCase(method)){
            if("".equals(product.getId()) || product.getId() ==null){
                product.setUser(getUser());
                product.setAddDt(new Date());
                productService.save(product);
            }else{
                Product dbproduct = productService.get(product.getId());
                dbproduct.setProductName(product.getProductName());
                dbproduct.setProductUrl(product.getProductUrl());
                productService.save(dbproduct);
            }
            return "add";
        }
        return SUCCESS;
    }

    public String edit(){
        String method = getMethod();
        if(REQUEST_GET.equalsIgnoreCase(method)){
           product = productService.get(id);
           return "edit";
        }
        return SUCCESS;
    }

    public  String delete(){
        product = productService.get(id);
        List<Object> args = new ArrayList<Object>();
        args.add(product.getProductName());
        if(!product.isExisted()){
            productService.remove(id);
            saveMessage(getText("product.delete.success",args));
        }else{
            saveMessage(getText("product.delete.failed",args));
        }

        return SUCCESS;
    }


    /*
    * show the product liste
    * */
    public String list() {
        Long userId =  getUser().getId();
        HttpServletRequest request = getRequest();
        total = productService.getUserProductsTotal(userId).intValue();
        String pageIndexName  =   new  org.displaytag.util.ParamEncoder("products").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        int  pageIndex  =  StringUtils.isBlank(request.getParameter(pageIndexName)) ? 0 :(Integer.valueOf(request.getParameter(pageIndexName))-1);
        products = productService.getUserProductsPagging(userId,pageIndex*10,10);
        return SUCCESS;
    }


    public  String generate() throws RepositoryException, IOException, WriterException {
        if(checkGen(items)){
            qrService.generateInternalCode(items,getUser());
            saveMessage(getText("qrimg.generate.success"));
        } else{
            saveMessage(getText("qrimg.generate.notsuccess"));
        }
        return SUCCESS;
    }

    private boolean checkGen(List<Item> items){
        for(Item item:items){
            if(item != null && Boolean.FALSE.equals(item.getGen())){
                return true;
            }
        }
        return false;
    }
}
