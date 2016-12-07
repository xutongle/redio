package cn.redio.util.AlibabaCloud;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.SearchMediaWorkflowRequest;
import com.aliyuncs.mts.model.v20140618.SearchMediaWorkflowResponse;
import com.aliyuncs.profile.DefaultProfile;

/**
 * Created by XIAOYAO on 2016/12/7 11:12.
 * 阿里云视频点播api
 */
public class VodUtils {

    private static final String ACCESSKEYID = "";

    private static final String ACCESSKEYSECRET = "";

    private static final String DQ = "cn-beijing"; //地区

    private static SearchMediaWorkflowResponse searchMediaWorkflow(DefaultAcsClient client) {
       SearchMediaWorkflowRequest request = new SearchMediaWorkflowRequest();

        SearchMediaWorkflowResponse response = null;

        try {
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            throw new RuntimeException("SearchMediaWorkflowRequest Server faild");
        } catch (ClientException e) {
            throw new RuntimeException("SearchMediaWorkflowRequest client faild");
        }
        return response;
    }

    public static SearchMediaWorkflowResponse searchMediaWorkflow() {
        DefaultProfile profile = DefaultProfile.getProfile(DQ, ACCESSKEYID, ACCESSKEYSECRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return searchMediaWorkflow(client);
    }
}
