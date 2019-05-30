package cheesemvc.controllers;

import cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    @RequestMapping("")
    public String index(Model model) {
        String title = "My Cheeses";

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", title);
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        String title = "Add Cheese";

        model.addAttribute("title", title);
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheese(@RequestParam String cheeseName, @RequestParam String cheeseDesc) {
        Cheese cheeseItem = new Cheese();

        cheeseItem.setName(cheeseName);
        cheeseItem.setDescription(cheeseDesc);

        cheeses.add(cheeseItem);

        // Redirect to /cheese
        return "redirect:";
    }
}
