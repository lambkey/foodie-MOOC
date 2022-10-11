package lamb.key.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author JoinYang
 * @date 2022/6/9 15:26
 * @Version 1.0
 */
public class UploadUtils {
    public static JSONResult<String> doUpload(
                        String accesskey,
                        String secretKey,
                        String bucket,
                        String bucketName,
                        String path,
                        MultipartFile file
    ){
        // 获取原始上传的文件名
        String orgFileName = file.getOriginalFilename();

        // 获取文件类型【后缀】
        String suffix = orgFileName.substring(orgFileName.lastIndexOf("."));

        // 使用UUID生成新的文件名
        String newFileName = UUID.randomUUID() + suffix;

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);

        // 1.初始化用户身份信息（secretId，secretKey）
        COSCredentials credentials =new BasicCOSCredentials(accesskey,secretKey);

        // 2.设置bucket的区域
        ClientConfig clientConfig =new ClientConfig(new Region(bucket));

        // 3.生成cos客户端
        COSClient cosClient = new COSClient(credentials,clientConfig);

        File localFile = null;

        try {
            // 创建一个文件夹
            localFile = File.createTempFile("temp",null);

            file.transferTo(localFile);

            // 指定要上传COS的路径
            String key = "/"+year+"/"+month+"/"+day+"/"+newFileName;

            // bucket桶名，key上传路径，localFile本地文件
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,key,localFile);
            // 真正上传
            cosClient.putObject(putObjectRequest);
            return JSONResult.successNeedData(new UploadMsg(1,"上传成功",path+putObjectRequest.getKey()));
        } catch (IOException e) {
            return JSONResult.successNeedData(new UploadMsg(-1,e.getMessage(),null));
        } finally {
            cosClient.shutdown();
        }

    }
}
