package webservice_live.prometheus.registrationbased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebServiceRegistrationService {

    @Autowired
    private WebserviceRepo repo;

    @RequestMapping(path = "/register", name = "/register", method = RequestMethod.POST)
    public void registerWebserviceForMonitoring(@RequestBody WebserviceRegistrationInfo info) {

        System.out.println(info.getName());
        System.out.println(info.getUrl());
        System.out.println(info.getNamespace());
        System.out.println(info.getRequestName());
        repo.save(info);
    }


    @RequestMapping(path = "/remove", name = "/remove", method = RequestMethod.POST)
    public void registerWebserviceForMonitoring(@RequestBody WebserviceUnRegistrationInfo info) {
        repo.remove(info.getName());
    }
}
