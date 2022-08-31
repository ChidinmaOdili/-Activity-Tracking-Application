//package nma.todolistapp.controller;
//
//import nma.todolistapp.model.Task;
//import nma.todolistapp.repository.TaskRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@Controller
//public class ToDoFormController {
//
//
//        @Autowired
//        private TaskRepo todoItemRepository;
//
//        @GetMapping("/create-todo")
//        public String showCreateForm(Task todoItem){
//            return "add-todo-item";
//        }
//
//        @GetMapping("/edit/{id}")
//        public String showUpdateForm(@PathVariable("id") long id, Model model) {
//            Task todoItem = todoItemRepository
//                    .findById(id)
//                    .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));
//
//            model.addAttribute("todo", todoItem);
//            return "update-todo-item";
//        }
//
//        @GetMapping("/delete/{id}")
//        public String deleteTodoItem(@PathVariable("id") long id, Model model) {
//            Task todoItem = todoItemRepository
//                    .findById(id)
//                    .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));
//
//            todoItemRepository.delete(todoItem);
//            return "redirect:/";
//        }
//
//
//    }
//
//
