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
    public String hello(HttpServletRequest request) {
        String html = "<form method='post' action='createMessage'>" +
                "<input type='text' name='name' />" +
                "<select name='country'>" +
                "<option value='English'>English</option>" +
                "<option value='German'>German</option>" +
                "<option value='French'>French</option>" +
                "<option value='Russian'>Russian</option>" +
                "<option value='Spanish'>Spanish</option>" +
                "</select>" +
                "<input type='submit' value='Greet Me!' />" +
                "</form>";
        return html;
    }

    @RequestMapping(value = "createMessage", method = RequestMethod.POST)
    @ResponseBody
    public static String createMessage(String name, String country) {

        switch (country) {
            case "English":
                return String.format("Hello <u>%s</u>.", name);
            case "German":
                return String.format("Hallo <u>%s</u>.", name);
            case "French":
                return String.format("Bonjour <u>%s</u>.", name);
            case "Russian":
                return String.format("Привет <u>%s</u>.", name);
            case "Spanish":
                return String.format("Hola <u>%s</u>.", name);
            default:
                return String.format("Hi <u>%s</u>.", name);
        }
    }

    public static void counter() {
        int counter = 0;
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
