//package nma.todolistapp.controller;
//
//import nma.todolistapp.dto.LoginDto;
//import nma.todolistapp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//
//    private  UserService userService;
//
//
//    @ModelAttribute("user")
//    public LoginDto userLoginDto() {
//        return new LoginDto();
//    }
//
//    @GetMapping()
//    public String showLoginForm(Model model) {
//        model.addAttribute("user", new LoginDto());
//        return "login";
//    }
//
//    @PostMapping("/index")
//    public String loginUserAccount(@ModelAttribute("user") LoginDto userLoginDto, HttpSession session, Model model) {
//        if (userService.login(userLoginDto.getEmail(), userLoginDto.getPassword())) {
//            session.setAttribute("auth", userLoginDto.getEmail());
//            return "redirect:/index";
//        }
//        model.addAttribute("error", "Invalid email or password!");
//        return "login";
//    }
//}
