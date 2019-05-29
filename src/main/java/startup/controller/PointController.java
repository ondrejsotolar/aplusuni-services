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
public class PointController {
    @Autowired
    CustomHttpHeader customHttpHeader;

    private String requirementResponseBase =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<com.exam.exam.StudentPoints>\n" +
                    "    <points>%s</points>\n" +
                    "</com.exam.exam.StudentPoints>";

    @GetMapping("/points")
    @ResponseBody
    public ResponseEntity<String> getRequirements(
            @RequestParam(name = "id", required = false, defaultValue = "0") String id,
            @RequestParam(name = "course", required = false, defaultValue = "") String course) {
        Integer paramId = Integer.parseInt(id);

        int result = paramId % 2 == 0 && course.length() > 0
                ? 33 : 0;

        String body = String.format(requirementResponseBase, String.valueOf(result));
        return new ResponseEntity<>(body, customHttpHeader.xmlApplication(), HttpStatus.CREATED);
    }
}
