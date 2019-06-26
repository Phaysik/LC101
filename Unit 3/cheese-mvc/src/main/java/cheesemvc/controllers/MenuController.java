package cheesemvc.controllers;

import cheesemvc.models.Cheese;
import cheesemvc.models.Menu;
import cheesemvc.models.data.CheeseDao;
import cheesemvc.models.data.MenuDao;
import cheesemvc.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenu(Model model, @ModelAttribute @Valid Menu newMenu, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(newMenu);

        return "redirect:view/" + newMenu.getId();
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {
        Optional<Menu> newMenu = menuDao.findById(menuId);

        model.addAttribute("menu", newMenu.get());
        model.addAttribute("title", newMenu.get().getName());

        return "menu/view";
    }


    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId) {
        Optional<Menu> newMenu = menuDao.findById(menuId);

        AddMenuItemForm form = new AddMenuItemForm(cheeseDao.findAll(), newMenu.get());

        model.addAttribute("title", "Add item to menu: " + newMenu.get().getName());
        model.addAttribute("form", form);

        return "menu/add-item";
    }

    @RequestMapping(value="add-item", method=RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }
        Optional<Cheese> newCheese = cheeseDao.findById(form.getCheeseId());
        Optional<Menu> newMenu = menuDao.findById(form.getMenuId());

        newMenu.get().addItem(newCheese.get());
        menuDao.save(newMenu.get());

        return "redirect:/menu/view/" + newMenu.get().getId();
    }
}
