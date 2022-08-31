//package nma.todolistapp.controller;
//
//import nma.todolistapp.dto.UserDTO;
//import nma.todolistapp.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//
//    @Controller
//    public class UserRegController {
//        private UserService userService;
////        @GetMapping("/")
////        public String indexPage(){
////            return "index";
////        }
//
//
//        @ModelAttribute("user")
//        public UserDTO userRegistrationDto() {
//            return new UserDTO();
//        }
//
////        @GetMapping({"/register"})
////        public String showRegistrationForm() {
////            return "registration";
////        }
//@GetMapping(value = "/register")
//public  String showRegistrationForm(Model model){
//    model.addAttribute("userRegistrationDetails" , new UserDTO());
//    return  "registration";
//}
//
//
//        @PostMapping("/registration")
//        public String registerUserAccount(@ModelAttribute("user") UserDTO userRegistration) {
//            //userService.save(userRegistration);
//            return "redirect:/register?success";
//        }
//
//
//    }
//
//
//
//
//
//
