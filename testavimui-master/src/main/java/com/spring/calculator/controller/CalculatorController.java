package com.spring.calculator.controller;

import com.spring.calculator.model.Number;
import com.spring.calculator.model.User;
import com.spring.calculator.service.NumberService;
import com.spring.calculator.service.SecurityService;
import com.spring.calculator.service.UserService;
import com.spring.calculator.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import java.util.HashMap;

// @RestController negrąžina view.
// Kadangi mums reikia grąžinti view pagal Spring MVC, naudojame @Controller
@Controller
//  @EnableAutoConfiguration - žymi konfigūracijos komponentą. Viduje leidžia kurti bean per metodus su @Bean
//  Ši klasės lygio anotacija nurodo Spring karkasui “atspėti” konfigūraciją,
//  remiantis priklausomybėmis (jar bibliotekos), kurias programuotojas įtraukė į projektą.
//  Šiuo atveju ji veikia kartu su main metodu.
@EnableAutoConfiguration
public class CalculatorController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    // autowire- naudojamas automatinei priklausomybių injekcijai
    // Kad panaudoti @Autowired anotaciją, reikia pirmiausiai turėti apsirašius @Bean @Configuration klasėje
    @Autowired
    // @Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkrečiai bean susieti priklausomybę.
    // Jeigu @Configuration klasėje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma,
    // kitu atveju metama klaida:
    // 'Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans,
    // or using @Qualifier to identify the bean that should be consumed'
    @Qualifier("NumberService")
    public NumberService numberService;

    // Maršrutizavimo informacija. Šiuo atveju, ji nurodo Spring karkasui,
    // jog visas HTTP užklausas, kurių kelias yra “/” apdoros metodas “home”.
    @GetMapping({"/", "/skaiciuotuvas"})
    String home(Model model) {
        // Jeigu Model 'number' nepraeina validacijos - per jį grąžinamos validacijos klaidos į View
        model.addAttribute("number", new Number());
        // grąžiname JSP failą, turi būti talpinami 'webapp -> WEB-INF -> jsp' aplanke
        return "skaiciuotuvas";
    }

    // Kadangi skaičiuotuvo forma naudoja POST metodą, čia irgi nurodome POST
    // SVARBU: parametras BindingResult turi eiti iškarto po anotacijos @Valid
    // kitu atveju bus 'Validation failed for object'
    @RequestMapping(method = RequestMethod.POST, value = "/skaiciuoti")
    String skaiciuoti(@Valid @ModelAttribute("number") Number e, BindingResult br,
                      @RequestParam HashMap<String, String> skaiciai, ModelMap modelMap) {

        if(br.hasErrors()){ // jeigu validacijos klaidos (žr. Number klasėje aprašytą validaciją prie kiekvieno skaičiaus)
            return "skaiciuotuvas"; // vartotojas lieka skaičiuotuvo lange tol, kol neištaiso validacijos klaidų
        } else { // praėjo validaciją- skaičiuojamas rezultatas
            int sk1 = Integer.parseInt(skaiciai.get("sk1"));
            int sk2 = Integer.parseInt(skaiciai.get("sk2"));
            String zenklas = skaiciai.get("zenklas");

            int rezultatas = 0;
            if (zenklas.equals("+")) {
                rezultatas = sk1 + sk2;
            } else if (zenklas.equals("-")) {
                rezultatas = sk1 - sk2;
            } else if (zenklas.equals("*")) {
                rezultatas = sk1 * sk2;
            } else if (zenklas.equals("/")) {
                rezultatas = sk1 / sk2;
            }

            // ModelMap objektas naudojamas siųsti reikšmes iš Spring MVC controller į JSP
            modelMap.put("sk1", sk1);
            modelMap.put("sk2", sk2);
            modelMap.put("zenklas", zenklas);
            modelMap.put("rezultatas", rezultatas);

            // Kreipiamės į service, kuris savo ruožtu kreipiasi į DAO ir išsaugo irašą DB
            numberService.save(new Number(sk1, sk2, zenklas, rezultatas));

            return "skaiciuoti";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/skaiciai")
    public String getAllNumbers(Model model) {
        model.addAttribute("skaiciai", numberService.getAll());
        return "skaiciai";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rodyti{id}")
    public String getById(@RequestParam("id") int id, Model model) {
        model.addAttribute("skaicius", numberService.getById(id));
        return "skaicius";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trinti{id}")
    public String delete(@RequestParam("id") int id, Model model) {
        numberService.delete(id);
        model.addAttribute("skaiciai", numberService.getAll());
        return "skaiciai";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/atnaujinti{id}")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("skaicius", numberService.getById(id));
        return "atnaujinti";
    }

    // Kadangi atnaujinti skaičių forma naudoja POST metodą, čia irgi nurodome POST
    @RequestMapping(method = RequestMethod.POST, value = "/atnaujintiSkaiciu")
    public String updateNumber(@ModelAttribute("skaicius") Number number) {
        numberService.update(number);
        return "redirect:/rodyti?id=" + number.getId();
    }

    @GetMapping("/registruoti")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registruoti";
    }

    @PostMapping("/registruoti")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registruoti";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/skaiciuotuvas";
    }

    @GetMapping("/prisijungti")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi");

        if (logout != null)
            model.addAttribute("message", "Sėkmingai atsijungėte");

        return "prisijungti";
    }

}
