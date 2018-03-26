package webservice_live.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import okhttp3.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import webservice_live.prometheus.registrationbased.WebserviceRegistrationInfo;
import webservice_live.prometheus.registrationbased.WebserviceRepo;
import webservice_live.lion.LionWebserviceTester;

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
            result = LionWebserviceTester.callWebserviceTest(url, requestName, namespace);
            LOG.info("Test with " + name + " was performed successfully.");
        } catch (Exception e) {
            LOG.info("Test with " + name + " failed, caught exception.", e);
            result = 0;
        }
        LOG.info("Result was : " + result);
        success.labels(name).set(result);
    }

}