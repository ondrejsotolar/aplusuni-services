package startup.controller.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomHttpHeader{

    public HttpHeaders xmlApplication() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "xml"));
        List<Charset> charsetList = new ArrayList<>();
        charsetList.add(StandardCharsets.UTF_8);
        responseHeaders.setAcceptCharset(charsetList);

        return responseHeaders;
    }
}
