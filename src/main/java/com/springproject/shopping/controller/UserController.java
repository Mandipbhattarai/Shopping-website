package com.springproject.shopping.controller;

import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.springproject.shopping.model.User;
import com.springproject.shopping.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	// Starting the use of BCrypt password encoder
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@GetMapping({ "/", "/user-login" })
	public String getUserLogin(HttpSession session) {
		if (session.getAttribute("activeUser") != null) {
			return "adminDashboard";
		}
		return "userLogin";
	}

	@PostMapping("/user-login")
	public String postLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model,jakarta.servlet.http.HttpSession session, @RequestParam(name = "rememberMe", required = false, defaultValue = "false") boolean rememberMe, @RequestParam("role") String role) {
		
		if(role.equals("user")) {
		User user = userService.findUserByEmail(email);
		if (userService.verifyPassword(password, user, bCryptPasswordEncoder) != true) {
			model.addAttribute("error", "Invalid email or password");
			return "userlogin";
		} else {
			handleSuccessfullLogin(user, session, rememberMe, model);
			if (user.getRole() == "admin") {
				return "adminDashboard";
			} 
			return "redirect:/user-login";
		}
		}
		model.addAttribute("error", "Invalid role. Please allow JavaScript.");
		return "userLogin";
	}

	public void handleSuccessfullLogin(User user, jakarta.servlet.http.HttpSession session, boolean rememberMe,
			Model model) {

		if (user.getProfilePicture() != null) {
			// Converting the byte array of profile picture to base64
			String base64Image = Base64.getEncoder().encodeToString(user.getProfilePicture());
			user.setProfilePictureBase64(base64Image);
			session.setAttribute("activeUser", user);
			if (rememberMe == false) {
				session.setMaxInactiveInterval(20);
			}
		}
	}

	@GetMapping("/user-signup")
	public String getUserSignup(HttpSession session) {
		if (session.getAttribute("activeUser") != null) {
			session.invalidate();
		}
		return "userSignup";
	}

	@PostMapping("/user-signup")
	public String postSignup(@ModelAttribute User user, Model model, @RequestParam String email, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String password, @RequestParam String phone) {
		if (user.getPassword().equals(user.getPassword2())) {
			try {
				String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
				user.setPassword(hashedPassword);
				// this sends the data to signup service while receiving if and which data
				// already exist in database
				String dupError = userService.userSignup(user);
				if (dupError == null) {
					return "UserLogin";
				}
				// Add specific error messege this way cause i was not able to extract that
				// information from exception
				model.addAttribute("dupError", dupError + " already exists");
				return "UserSignup";
			} catch (DataIntegrityViolationException e) {
				// Add error message to the model
				model.addAttribute("dupError", "Some info you entered already exists, try new one");
				return "redirect:/userSignup";
			}
		}
		model.addAttribute("error", "Passwords do not match");
		return "userSignup";
	}

	@GetMapping("/home")
	public String home(HttpSession session) {
		if (session.getAttribute("activeUser") != null) {
			return "userPanel";
		}
		return "userLogin";
	}

	@GetMapping("/employees")
	public String employees() {

		return "employees";
	}

	@GetMapping("sellers")
	public String sellers() {

		return "sellers";
	}

	@GetMapping("/users")
	public String users() {

		return "users";
	}

	@GetMapping("/requests")
	public String requests() {
		return "requests";
	}

	@GetMapping("/user")
	public String user(Model model, HttpSession session) {
		// Verifying activeUser
		if (session.getAttribute("activeUser") != null) {
			List<User> userList = userService.getAllUser();

			// Defining for_each loop to convert longblob image to normal one
//		userList.forEach(user -> {
//			if (user.getProfilePicture() != null) {
//				// Converting the byte array of profile picture to base64
//				String base64Image = Base64Utils.encodeToString(user.getProfilePicture());
//				user.setProfilePictureBase64(base64Image);
//			}
//		});
			model.addAttribute("userList", userList);
			return "users";
		}
		return "userLogin";

	}

	@PostMapping("/user")
	public String userList(HttpSession session) {
		if (session.getAttribute("activeUser") != null) {
			return "users";
		}

		return "userLogin";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// Destroying the session
		session.invalidate();
		return "userLogin";
	}

	@GetMapping("/user/delete")
	public String delete(@RequestParam int id) {
		userService.deleteUser(id);
		return "redirect:/user";
	}

	@GetMapping("/user/edit")
	public String edit() {

		return "userEdit";
	}
}
