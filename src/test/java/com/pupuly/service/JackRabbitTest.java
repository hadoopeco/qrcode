package com.pupuly.service;

import org.apache.log4j.helpers.NullEnumeration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/13/11
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */

@ContextConfiguration(locations = {"classpath:/applicationContext-service.xml"} )
public class JackRabbitTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DocService docService;

    @Test
    public void test() throws RepositoryException, IOException {
        String str = "test doc service implement";
//        File file = new File("test");
        InputStream fin = new FileInputStream("E:/qrcode/6000303330.png");
        byte bytes[] = new byte[2048];
        fin.read(bytes);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        String uuid = docService.saveNode("user/test2/sd/1",in);
        System.out.println(uuid);
    }

//    @Test
//    public void testResult() throws RepositoryException, IOException {
//        Node node = docService.getNode("db9e97cd-f17b-47c7-9184-d4922404a86c");
//        System.out.println(node.getPath());
//        Binary binary = node.getProperty("image").getBinary();
//        InputStream in = binary.getStream();
//        byte bytes[] = new byte[(int)binary.getSize()];
//        in.read(bytes);
//        FileOutputStream fout = new FileOutputStream("E:/qrcode/test14.png");
//        fout.write(bytes);
//        fout.flush();
//        System.out.println();
//    }

    @Test
    public void testDownloadFile() throws RepositoryException, IOException {
        docService.getdownloadFile("79cca252-c573-4006-bb0a-6a6a1b6aaccb");
    }

}
