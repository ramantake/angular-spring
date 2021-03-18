package com.WebProject.movieCruizer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.WebProject.movieCruizer.model.Favorite;
import com.WebProject.movieCruizer.model.Movie;
import com.WebProject.movieCruizer.service.FavoriteService;
import com.WebProject.movieCruizer.service.MovieService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FrontController {

	
	@Autowired
	MovieService movieService;
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	UserValidationServise userValidadtionService;

	 @RequestMapping(value = "/admin")
    public String showadminmovielist(ModelMap model) {

			List<Movie> list = movieService.getAllMovies();
			model.put("list", list);
			
		
		return "movie-list-admin";
	}
	
	@RequestMapping("/customer")
	public String viewCustomer(ModelMap model) {
		List<Movie> list = movieService.getAllMovies();
		model.put("list", list);
		return "movie-list-customer";
	}
	
//	@RequestMapping("/customer-ang")
//	public List<Movie> viewCustomerAngular() {
//		List<Movie> list = movieService.getAllMovies();
//		return list;
//	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSuccess(ModelMap model, @RequestParam String username, @RequestParam String password) {

		if (userValidadtionService.isUserAdmin(username, password)) {
			return "redirect:/admin";
		} else if (username.equalsIgnoreCase("user") && password.equalsIgnoreCase("user")) {
			return "redirect:/customer";
		} else {
			model.put("message", "Invalid credentials");
			return "login";
		}

	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(ModelMap model, @RequestParam int id) {
		model.put("id", id);
		return "edit-movie";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editSuccess(ModelMap model, @RequestParam int id,@RequestParam String title,@RequestParam String active,@RequestParam String genre,@RequestParam String string,@RequestParam String boxOffice) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setActive(active);
		movie.setGenre(genre);
		movie.setHasTeaser(string);
		movie.setBoxOffice(boxOffice);;
		movieService.editMovie(movie);
		return "edit-movie-status.jsp";
	
	}

//	@RequestMapping("/add")
//	public String addToFavorite(ModelMap model, @RequestParam int id) {
//		
//		Favorite favourite = new Favorite();
//		favourite.setUser_id(1);
//		favourite.setProduct_id(id);
//		favoriteService.addToFavorite(favourite);
//		
//		List<Movie> list = movieService.getAllMovies();
//		Movie movie = new Movie();
//		for (Movie moviei : list) {
//			if (moviei.getId() == id) {
//				movie = moviei;
//			}
//		}
//		
//		String str = movie.getTitle() + " added successfully!";
//		model.put("list", list);
//		model.put("message", str);
//		return "movie-list-customer-notification.jsp";
//	}
	
	
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(value="/delete")
	public Movie removeFavoriteMovie(@PathVariable int id) {
	favoriteService.deleteFromFavorite(id);
//	List<Movie> cartItem = new ArrayList<>();
//	List<Favorite> list = favoriteService.getAllFavorite(1);
//	for (Favorite ele : list) {
//		List<Movie> listTemp = movieService.getAllMovies();
//		for (Movie m : listTemp) {
//			if (m.getId() == ele.getProduct_id()) {
//				cartItem.add(m);
//				}
//		}
//	}
//	return cartItem;
		List<Movie> movie = movieService.getAllMovies().stream().filter(m->m.getId()==id).collect(Collectors.toList());
		return movie.get(0);
	}
	

//	@RequestMapping("/favorite")
//	public String viewFavorite(ModelMap model) {
//		List<Favorite> list = favoriteService.getAllFavorite(1);
//		List<Movie> cartItem = new ArrayList<>();
//		int total = 0;
//		for (Favorite ele : list) {
//			List<Movie> listTemp = movieService.getAllMovies();
//			for (Movie m : listTemp) {
//				if (m.getId() == ele.getProduct_id()) {
//					cartItem.add(m);
//					total=total+1;
//				}
//			}
//		}
//		model.put("list", cartItem);
//		model.put("total", total);
//		return "favorites.jsp";
//	}
	
	@GetMapping("/favorite")
	@ResponseBody
	public List<Movie> viewFavorite1(ModelMap model) {
		List<Favorite> list = favoriteService.getAllFavorite(1);
		List<Movie> cartItem = new ArrayList<>();
		int total = 0;
		for (Favorite ele : list) {
			List<Movie> listTemp = movieService.getAllMovies();
			for (Movie m : listTemp) {
				if (m.getId() == ele.getProduct_id()) {
					cartItem.add(m);
					total=total+1;
				}
			}
		}
		model.put("list", cartItem);
		model.put("total", total);
		return cartItem;
	}
	
	@RequestMapping(value="/signup")
	public String signup() {
	return "Signup2";
	}
	
	
	

}