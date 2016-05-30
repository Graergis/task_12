package client;

import generate.GetClientResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(ClientConfig.class, args);
        ClientWeb clientWeb = ctx.getBean(ClientWeb.class);

        int i = 1;
        GetClientResponse response = clientWeb.getClientResponse(i);
        clientWeb.printResponse(response);
    }
}
