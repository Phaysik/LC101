package cheesemvc.controllers;

import cheesemvc.models.Cheese;
import cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @RequestMapping("")
    public String index(Model model) {
        String title = "My Cheeses";

        model.addAttribute("cheeses", CheeseData.getAll());
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
    public String processAddCheese(@ModelAttribute Cheese newCheese) {

        /*
         * Cheese newCheese = new Cheese();
         * newCheese.setName(Request.getParameter("name"));
         * newCheese.setDescription(Request.getParameter("description"));
         */

        CheeseData.add(newCheese);
        // Redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removePage(Model model) {
        String title = "Remove Cheese";

        model.addAttribute("title", title);
        model.addAttribute("cheeses", CheeseData.getAll());

        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String deleteCheese(@RequestParam int[] cheeseIds) {

        for (int cheeseId: cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("cheese", CheeseData.getById(cheeseId));
        model.addAttribute("title", "Edit Cheese");

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(int cheeseId, String name, String description) {
        Cheese newCheese = CheeseData.getById(cheeseId);

        newCheese.setName(name);
        newCheese.setDescription(description);

        return "redirect:";
    }
}
