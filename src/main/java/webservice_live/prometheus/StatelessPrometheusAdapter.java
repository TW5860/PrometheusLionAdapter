package webservice_live.prometheus;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webservice_live.lion.LionWebserviceTester;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class StatelessPrometheusAdapter {

    public static final String RESPONSE_TEMPLATE = "# HELP tests_successful Was last test callWebserviceTest successful?\n" +
            "# TYPE tests_successful gauge\n" +
            "tests_successful{webservice=\"%s\",} %s\n" +
            "# HELP tests_total Total tests callWebserviceTest.\n" +
            "# TYPE tests_total counter\n" +
            "tests_total{webservice=\"%s\"";
    private HashMap<String, ObservedService> services = new HashMap<>();

    @RequestMapping(path = "/stateless", method = RequestMethod.GET)
    public String testService(@RequestParam String url, @RequestParam String name, @RequestParam String namespace, @RequestParam String requestName) {
        if (!services.containsKey(name)) {
            ObservedService value = new ObservedService();
            value.setName(name);
            services.put(name, value);
        }

        ObservedService service = services.get(name);

        int status = -1;
        try {
            System.out.println(url);
            System.out.println(name);
            System.out.println(namespace);
            status = LionWebserviceTester.callWebserviceTest(url, requestName, namespace);
        } catch (IOException e) {
            status = 0;
        }

        service.setTotal(service.getTotal() + 1);
        service.setSuccessful(status);

        return String.format(RESPONSE_TEMPLATE + ",} %s", service.getName(), service.getSuccessful(), service.getName(), service.getTotal());
    }

    public static class ObservedService {
        String name;
        int total;
        int successful;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSuccessful() {
            return successful;
        }

        public void setSuccessful(int successful) {
            this.successful = successful;
        }
    }
}
