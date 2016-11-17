package com.redio.demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.SearchMediaWorkflowRequest;
import com.aliyuncs.mts.model.v20140618.SearchMediaWorkflowResponse;
import com.aliyuncs.profile.DefaultProfile;

/**
 * Created by XIAOYAO on 2016/10/21.
 * 媒体库SDK
 */
public class AliDemo1 {

    public static SearchMediaWorkflowResponse searchMediaWorkflow(DefaultAcsClient client) {
        SearchMediaWorkflowRequest request = new SearchMediaWorkflowRequest();
        SearchMediaWorkflowResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            throw new RuntimeException("SearchMediaWorkflowRequest Server failed");
        } catch (ClientException e) {
            throw new RuntimeException("SearchMediaWorkflowRequest Client failed");
        }

        return response;
    }

}
