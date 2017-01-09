import com.busnoseat.demo.facade.request.UserRequest;
import com.busnoseat.demo.facade.response.UserResponse;
import com.busnoseat.common.facade.abs.BaseResponse;
import com.busnoseat.common.util.ToStringUtil;
import com.busnoseat.runtime.rest.RestClient;
import org.junit.Test;

/**
 * The type UserTest.
 *
 * @author xubo
 * @Description:
 * @Date 2016/7/14
 */
public class UserTest {

    @Test
    public void testQueryUser() {
        RestClient client = new RestClient();
        UserRequest request = new UserRequest();
        UserResponse response = client.get("http://localhost:8081/demo/user", request, UserResponse.class);
        System.out.println(ToStringUtil.toString(response));
    }

    @Test
    public void testModifyUser() {
        RestClient client = new RestClient();
        UserRequest request = new UserRequest();
        request.setUserId(91L);
        BaseResponse response = client.put("http://localhost:8081/demo/user", request, BaseResponse.class);
        System.out.println(ToStringUtil.toString(response));
    }

    @Test
    public void testCheckParam() {
        RestClient client = new RestClient();
        UserRequest request = new UserRequest();
        request.setUserId(91L);
        request.setUserName(" ");
        BaseResponse response = client.post("http://localhost:8081/demo/user", request, BaseResponse.class);
        System.out.println(ToStringUtil.toString(response));
    }
}