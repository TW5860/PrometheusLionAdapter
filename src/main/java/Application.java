import okhttp3.*;

import java.io.IOException;

public class Application {

    public static final MediaType XML
            = MediaType.parse("application/xml; charset=utf-8");

    public static void main(String args[]) throws IOException {
        run("http://kims-macbook-pro.local:8088/mockDienstleisterAuftragBESv3Binding/testService","DienstleisterAuftragBESv3testServiceRequest1","http://BeauftragungGruppe/DienstleisterAuftragBES/specification/ServiceView/DienstleisterAuftragBESProvider/DienstleisterAuftragBESv3");
    }

    static public String run(String url,String name, String namespace) throws IOException {
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
                "      </dien:DienstleisterAuftragBESv3testServiceRequest1>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>",namespace,name);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(XML, myContent);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();

        System.out.println(responseString);
        return responseString;
    }
}
