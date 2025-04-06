package EcommercePage.producingwebservice.model.Controller;
 

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class HomeController {

    @GetMapping("/homePage")
    public String home(Model model, HttpSession session) {
        Object user = session.getAttribute("user");

        if (user instanceof UserDetails) {
            model.addAttribute("nome", ((UserDetails) user).getUsername());
        }

        return "homePage"; // Retorna para o template home.html
    }
}
