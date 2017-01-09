import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * The type UrlConnectionTest.
 *
 * @author xubo
 * @Description:
 * @Date 2016/9/19
 */
public class UrlConnectionTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public String sendPostByHttp(String url, String param) {
        logger.info("开始进入sendPostByHttp...");
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            logger.info("创建url...");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            logger.info("建立连接...");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("content-Type", "application/json");
            //			// 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(1000*60*5);
            conn.setReadTimeout(1000*60*6);
            logger.info("头信息设置完毕...");
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            logger.info("数据post完成...");
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            logger.info("请求结束..,请求结果" + result);
            return result;
        } catch (Exception e) {
            logger.error("发送Http的POST 请求出现异常！" + e);
            return null;
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void test2() {
        String s = sendPostByHttp("http://localhost:8086/model/user", "{\"userId\":91,\"userName\":\" \",\"pageSize\":10,\"currPage\":1,\"ip\":\"192.168.16.151\"}");
        System.out.println(s);
    }

    @Test
    public void test3() {
        String s = sendPostByHttp("http://localhost:8086/model/user", "{\"userId\":91,\"userName\":\" \",\"pageSize\":10,\"currPage\":1,\"ip\":\"192.168.16.151\"}");
        System.out.println(s);
    }
}
