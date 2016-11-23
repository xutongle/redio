package cn.redio.demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

import java.util.Date;

/**
 * Created by XIAOYAO on 2016/10/20.
 *
 */
public class AliDemo {

    public static void main(String[] args) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou", "", "");
        DefaultAcsClient client = new DefaultAcsClient(profile);
        AssumeRoleResponse response = assumeRole(client, "");
        AssumeRoleResponse.Credentials credentials = response.getCredentials();
        System.out.println(credentials.getAccessKeyId() + "\n" +
                credentials.getAccessKeySecret() + "\n" +
                credentials.getSecurityToken() + "\n" +
                credentials.getExpiration());
    }

    private static AssumeRoleResponse assumeRole(
            DefaultAcsClient client,
            String roleArn)
            throws ClientException {
        final AssumeRoleRequest request = new AssumeRoleRequest();
        request.setVersion("2015-04-01");
        request.setMethod(MethodType.POST);
        request.setProtocol(ProtocolType.HTTPS);
        request.setDurationSeconds(900L);
        request.setRoleArn(roleArn);
        request.setRoleSessionName("test-token");
        return client.getAcsResponse(request);
    }

    private static boolean isTimeExpire(String expiration) {
        Date nowDate = new Date();
        Date expireDate = javax.xml.bind.DatatypeConverter.parseDateTime(expiration).getTime();
        if (expireDate.getTime() <= nowDate.getTime()) {
            return true;
        } else {
            return false;
        }
    }
}
