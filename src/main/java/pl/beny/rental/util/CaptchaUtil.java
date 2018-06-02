package pl.beny.rental.util;

import org.springframework.web.client.RestTemplate;
import pl.beny.rental.dto.CaptchaRepsonse;

public class CaptchaUtil {

    private static RestTemplate restTemplate = new RestTemplate();

    private CaptchaUtil() {}

    public static boolean checkCaptcha(String response) {
        String URL = "";
        String SECRET = "";
        return restTemplate.postForEntity(URL, "", CaptchaRepsonse.class, SECRET, response).getBody().isSuccess();
    }

}
