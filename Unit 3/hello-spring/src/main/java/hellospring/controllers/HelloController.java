package hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @RequestMapping("")
    @ResponseBody
    public String index(HttpServletRequest request) {
        String name = request.getParameter("name");

        return name != null ? String.format("Hello %s", name) : "Invalid name.";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm() {
        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<input type='submit' value='Greet Me!' />" +
                "</form>";
        return html;
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request) {
        String name = request.getParameter("name");

        return name != "" ? String.format("Hello %s.", name) : "Invalid name.";
    }

    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name) {
        return String.format("Hello %s.", name);
    }

    @RequestMapping("goodbye")
    public String goodBye() {
        return "redirect:/";
    }
}
