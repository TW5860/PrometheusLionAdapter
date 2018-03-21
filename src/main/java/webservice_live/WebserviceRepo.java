package webservice_live;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sun.awt.util.IdentityArrayList;

import java.util.List;

@Service
public class WebserviceRepo {

    private IdentityArrayList<WebserviceRegistrationInfo> list = new IdentityArrayList<>();

    public void save(WebserviceRegistrationInfo info){
        list.add(info);
    }

    public List<WebserviceRegistrationInfo> getAll(){
        return list;
    }
}
