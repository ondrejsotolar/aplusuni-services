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
public class RequirementsController {
    @Autowired
    CustomHttpHeader customHttpHeader;

    private String requirementResponseBase =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<com.exam.exam.CourseRequirement>\n" +
            "    <met>%s</met>\n" +
            "    <alarm>PT1M</alarm>\n" +
            "</com.exam.exam.CourseRequirement>";

    @GetMapping("/requirement")
    @ResponseBody
    public ResponseEntity<String> getRequirements(
            @RequestParam(name = "id", required = false, defaultValue = "0") String id,
            @RequestParam(name = "course", required = false, defaultValue = "") String course) {
        Integer paramId = Integer.parseInt(id);

        boolean result = paramId % 2 == 0 && course.length() > 0;

        String body = String.format(requirementResponseBase, String.valueOf(result));
        return new ResponseEntity<>(body, customHttpHeader.xmlApplication(), HttpStatus.CREATED);
    }
}
