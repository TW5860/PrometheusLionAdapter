package webservice_live.prometheus.registrationbased;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebserviceRepo {

    private ArrayList<WebserviceRegistrationInfo> list = new ArrayList<>();

    public void save(WebserviceRegistrationInfo info){
        list.add(info);
    }

    public List<WebserviceRegistrationInfo> getAll(){
        return list;
    }

    public void remove(String name) {
        for (WebserviceRegistrationInfo info : list) {
            if (info.getName().contentEquals(name)){
                list.remove(info);
            }
        }
    }
}
