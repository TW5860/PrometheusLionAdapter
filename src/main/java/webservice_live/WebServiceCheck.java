package webservice_live;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebServiceCheck {

    public static final MediaType XML
            = MediaType.parse("application/xml; charset=utf-8");
    private static final Logger LOG = LoggerFactory.getLogger(WebServiceCheck.class);
    private static final Counter tests = Counter.build()
            .name("tests_total")
            .help("Total tests callWebserviceTest.")
            .labelNames("webservice")
            .register();
    private static final Gauge success = Gauge.build()
            .name("tests_successful")
            .help("Was last test callWebserviceTest successful?")
            .labelNames("webservice")
            .register();
    @Autowired
    private WebserviceRepo repo;

    @Autowired
    public WebServiceCheck() {
    }

    @Scheduled(fixedDelay = 15000)
    public void sheduledCheck() {
        for (WebserviceRegistrationInfo info : repo.getAll()) {
            runTest(info.getName(), info.getUrl(), info.getRequestName(), info.getNamespace());
        }
    }

    private void runTest(String name, String url, String requestName, String namespace) {
        int result;
        tests.labels(name).inc();
        try {
            LOG.info("Run against " + name + ".");
            result = callWebserviceTest(url, requestName, namespace);
            LOG.info("Test with " + name + " was performed successfully.");
        } catch (Exception e) {
            LOG.info("Test with " + name + " failed, caught exception.", e);
            result = 0;
        }
        LOG.info("Result was : " + result);
        success.labels(name).set(result);
    }

    private int callWebserviceTest(String url, String name, String namespace) throws IOException {
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
        RequestBody body = RequestBody.create(XML, myContent);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();


        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        return responseString.contains("err") ? 2 : 1;
    }

}