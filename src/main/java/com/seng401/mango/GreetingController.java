    package com.seng401.mango;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    @Controller
    public class GreetingController
    {
        //Returns view
        private static int clickCount = 0;
        @GetMapping("/")
        public String index(@RequestParam(name = "name", required = false, defaultValue =
                "World") String name, Model model)
        {
            model.addAttribute("name", name);
            model.addAttribute("clickCount", clickCount);
            return "index";
        }
        @PostMapping("/click")
        public String click()
        {
            clickCount += 1;
            return "redirect:/";
        }
    }


