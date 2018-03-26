package webservice_live.lion;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import webservice_live.prometheus.WebServiceCheck;
import webservice_live.prometheus.registrationbased.WebserviceRegistrationInfo;

import java.io.IOException;

public class LionWebserviceTester {
    public static int callWebserviceTest(String url, String name, String namespace) throws IOException {
        String myContent = String.format("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:dien=\"%s/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <dien:%s>\n" +
                "         <inputMetaData>\n" +
                "            <version>gero et</version>\n" +
                "            <metadataentry>\n" +
                "               <key>sonoras imperio</key>\n" +
                "               <value>quae divum incedo</value>\n" +
                "            </metadataentry>\n" +
                "         </inputMetaData>\n" +
                "         <depth>3</depth>\n" +
                "      </dien:%s>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>", namespace, name, name);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(WebServiceCheck.XML, myContent);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();


        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        return responseString.contains("<soapenv:Fault>") ? 0 : 1;
    }

    public static int callWebserviceTest(WebserviceRegistrationInfo serviceToTest) throws IOException {
        return callWebserviceTest(serviceToTest.getUrl(), serviceToTest.getName(), serviceToTest.getNamespace());
    }
}
