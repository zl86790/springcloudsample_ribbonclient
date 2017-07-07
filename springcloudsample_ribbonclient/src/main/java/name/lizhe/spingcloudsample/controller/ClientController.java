package name.lizhe.spingcloudsample.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired  
    private LoadBalancerClient loadBalancerClient; 
    
    @Value("${message1}")  
    private String message1; 
    
    @Value("${message2}")  
    private String message2; 
    
    @Autowired
    RestTemplate rt;

    @RequestMapping(value = "/client" ,method = RequestMethod.GET)
    public String add(@RequestParam String message) {
    	this.loadBalancerClient.choose("HELLO-SERVICE");
    	return rt.getForEntity("http://HELLO-SERVICE/hello?message="+message, String.class).getBody(); 
    }

}