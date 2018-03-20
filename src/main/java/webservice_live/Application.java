package webservice_live;

import okhttp3.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    public static final MediaType XML
            = MediaType.parse("application/xml; charset=utf-8");

    public static void main(String[] args) {
//        run("http://kims-macbook-pro.local:8088/mockDienstleisterAuftragBESv3Binding/testService","DienstleisterAuftragBESv3testServiceRequest1","http://BeauftragungGruppe/DienstleisterAuftragBES/specification/ServiceView/DienstleisterAuftragBESProvider/DienstleisterAuftragBESv3");
        SpringApplication.run(Application.class, args);
    }


}
