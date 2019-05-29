package startup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import startup.controller.util.CustomHttpHeader;

@RestController
public class AppealController {
    @Autowired
    CustomHttpHeader customHttpHeader;

    private String appealResponseBase =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<com.exam.exam.LastAppeal>\n" +
            "    <date>%s</date>\n" +
            "</com.exam.exam.LastAppeal>";

    @GetMapping("/appeal")
    @ResponseBody
    public ResponseEntity<String> getAppeal(@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
        Integer paramId = Integer.parseInt(id);

        boolean result = paramId % 2 == 0;

        String body = String.format(appealResponseBase, String.valueOf(result));
        return new ResponseEntity<>(body, customHttpHeader.xmlApplication(), HttpStatus.CREATED);
    }
}
