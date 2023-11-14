package com.grupo2.reto1.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.grupo2.reto1.exceptions.UserNotFoundException;
import com.grupo2.reto1.security.configuration.JwtTokenUtil;
import com.grupo2.reto1.security.model.AuthRequest;
import com.grupo2.reto1.security.model.AuthResponse;
import com.grupo2.reto1.security.model.MailAuth;
import com.grupo2.reto1.security.model.PasswordAuth;
import com.grupo2.reto1.song.model.SongServiceResponse;
import com.grupo2.reto1.song.service.SongService;
import com.grupo2.reto1.user.model.UserPostRequest;
import com.grupo2.reto1.user.model.UserServiceResponse;
import com.grupo2.reto1.user.service.UserService;

import jakarta.validation.Valid;

@RequestMapping("/api/users")
@RestController
public class UserController {
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@Autowired 
	JwtTokenUtil jwtUtil;

	@Autowired
	UserService userService;
	
	@Autowired
	SongService songService;

	@GetMapping
	public ResponseEntity<List<UserServiceResponse>> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserServiceResponse> getUserById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(userService.getUserById(id), HttpStatus.ACCEPTED);
		} catch (UserNotFoundException e) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

		}
	}

	@PostMapping
	public ResponseEntity<Integer> createUser(@Valid @RequestBody UserPostRequest userPostRequest){
		UserServiceResponse userServiceResponse = new UserServiceResponse(userPostRequest.getName(), userPostRequest.getSurname(), userPostRequest.getEmail(), userPostRequest.getPassword());
		return new ResponseEntity<> (userService.createUser(userServiceResponse), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Integer> updateUser(@PathVariable("id") Integer id, @RequestBody UserPostRequest userPostRequest){
		UserServiceResponse userServiceResponse = new UserServiceResponse(userPostRequest.getName(), userPostRequest.getSurname(), userPostRequest.getEmail(), userPostRequest.getPassword());
		userServiceResponse.setId(id);
		try {
			userService.updateUser(userServiceResponse);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteUser(@PathVariable("id")Integer id){
		try {
			userService.deleteUser(id);
		} catch (UserNotFoundException e) {
			  throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	
//	//GET FAVORITE SONGS
//	@GetMapping("/{id}/favourites")
//	public ResponseEntity<UserServiceResponse> getUserWithItsFavourites(@PathVariable("id") Integer id) {
//		try {
//			return new ResponseEntity<>(userService.getUserWithItsFavourites(id), HttpStatus.ACCEPTED);
//		} catch (UserNotFoundException e) {
//			  throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
//		}
//	}
	
	//DELETE SONG FROM FAVORITE
	@DeleteMapping("/favourites/delete/{idSong}")
	public ResponseEntity<Integer> deleteFavouriteFromUser(Authentication authentication, @PathVariable("idSong")Integer idSong){
		
		UserServiceResponse userDetails = (UserServiceResponse) authentication.getPrincipal();
		int UserId = userDetails.getId();
		
		userService.deleteFavouriteFromUser(idSong, UserId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/favourites/create/{songId}")
	public ResponseEntity<Integer> createFavoriteSongForUser(Authentication authentication, @PathVariable("songId") Integer songId) {
	    try {
	        UserServiceResponse userDetails = (UserServiceResponse) authentication.getPrincipal();
	        int userId = userDetails.getId();

	        // Call the service method to create a favorite song for the user
	        int favoriteSongId = userService.createFavouriteSongFromUser(songId, userId);
	        // Check if the creation was successful
		        if (favoriteSongId > 0) {
		            return new ResponseEntity<>(favoriteSongId, HttpStatus.CREATED);
		        } else {
		            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}

//	//Login
//	@PostMapping("/login")
//	public ResponseEntity<Integer> loginUser(@Valid @RequestBody UserLoginPostRequest userPostRequest) {
//		UserLoginPostRequest user = new UserLoginPostRequest(userPostRequest.getEmail(), userPostRequest.getPassword());
//		return new ResponseEntity<Integer>(userService.logUser(user), HttpStatus.OK);
//	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			// esta es la funcion que va a intentar identificarse, dado el username y la password introducida
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
			); 
			// devolvera un objeto de tipo authenticacion de las que de momento nos interesa el "principal". El principal contiene los datos del usuario
			// por lo que lo convertimos a su modelo real de BD para tener todos sus campos
			UserServiceResponse user = (UserServiceResponse) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			ex.printStackTrace();
			// esta excepción salta y estamos devolviendo un 401. se podria cambiar pero cuidado con lo que se devuelve al fallar el login etc
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	
	@PostMapping("/auth/signup")
	public ResponseEntity<?> signIn(@RequestBody @Valid AuthRequest request) {
		// TODO solo esta creado en el caso de que funcione. Si no es posible que de 500 o 401.
		// aqui hacer lo que sea preciso

		// vamos a cifrar la contrasenia aqui, ya que no queremos andar dando vueltas con la contraseña sin encriptar si no es preciso
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(request.getPassword());
		
		// creamos el usuario en DB
		UserServiceResponse user = new UserServiceResponse(request.getName(), request.getSurname(), request.getEmail(), password);
		return new ResponseEntity<Integer>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	
	// utilizamos el /me por que vamos a coger el nuestro, el que estamos logueado...
	@GetMapping("/auth/me")
	public ResponseEntity<?> getUserInfo(Authentication authentication) {
		// aqui podemos castearlo a UserDetails o User. El UserDetails es una interfaz, 
		// si lo casteamos a la interfaz no podremos sacar campos como la ID del usuario
		UserServiceResponse userDetails = (UserServiceResponse) authentication.getPrincipal();
		
		// IMPORTANTE: por lo tanto, la ID del usuario no tiene que ir como parametro en la peticion del usuario
		
		// aqui podriamos devolver datos del usuario. quizá no sea lo que queremos devolver o no lo querramos devolver
		// es un ejemplo por que con userDetails.getId() tendríamos la ID del usuario sin que la pase por parametro
		// necesario en algunos servicios: si quiero devolver una lista o elemento privado del usuario, no voy a querer
		// que el usuario mande su ID por parametro. Ya que es trampeable.
		// de ahi que sea "/me" en el ejemplo 
		
		return ResponseEntity.ok().body(userDetails);
	}
	
	//Get the user's id when logged
	//Lista de Favoritos SOLO canciones
		@GetMapping("/favourites/me")
		public ResponseEntity<List<SongServiceResponse>> getAllFavourites(Authentication authentication){
		try {
			
			UserServiceResponse userDetails = (UserServiceResponse) authentication.getPrincipal();
			int id = userDetails.getId();
			return new ResponseEntity<>(userService.getAllFavourites(id), HttpStatus.ACCEPTED);
			
			} catch (BadCredentialsException ex) {
				// esta excepción salta y estamos devolviendo un 401. se podria cambiar pero cuidado con lo que se devuelve al fallar el login etc
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}
		
		@PutMapping("/changePassword")
		public ResponseEntity<Integer> changePassword(Authentication authentication
				, @RequestBody @Valid PasswordAuth passwords){
			try {
				UserServiceResponse userDetails = (UserServiceResponse) authentication.getPrincipal();
				int id = userDetails.getId();
				
				try {
					Integer response = userService.changePassword(passwords.getOldPassword(), passwords.getNewPassword(), id);
					if (response != null) {
						return new ResponseEntity<>(HttpStatus.ACCEPTED);
					}else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} catch (UserNotFoundException e) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
				}
			} catch (BadCredentialsException ex) {
				// esta excepción salta y estamos devolviendo un 401. se podria cambiar pero cuidado con lo que se devuelve al fallar el login etc
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}
		
		@PutMapping("/changeEmail")
		public ResponseEntity<Integer> changeMail(Authentication authentication
				, @RequestBody @Valid MailAuth mails){
			try {
				UserServiceResponse userDetails = (UserServiceResponse) authentication.getPrincipal();
				int id = userDetails.getId();
				
				try {
					Integer response;
					response = userService.changeMail(mails.getOldMail(), mails.getNewMail(), id);
					
					if (response != null) {
						return new ResponseEntity<>(HttpStatus.ACCEPTED);
					}else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
				}
			} catch (BadCredentialsException ex) {
				// esta excepción salta y estamos devolviendo un 401. se podria cambiar pero cuidado con lo que se devuelve al fallar el login etc
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}

}
