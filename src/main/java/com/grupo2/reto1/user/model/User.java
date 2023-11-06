package com.grupo2.reto1.user.model;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.grupo2.reto1.song.model.Song;

public class User implements UserDetails{
	
private Integer id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	public User () {
		
	}
	
	public User(String name, String surname, String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public User(Integer id, String name, String surname, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", password="
				+ password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, password, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// los permisos o autoridades que tiene. de momento nada. Aun sin roles
		return null;
	}

	@Override
	public String getUsername() {
		// esta funcion tiene que devolver el campo que hace de username. En este caso el email
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// si se hubiese expirado
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// si no esta bloqueada
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// si las credenciales no han sido expiradas
		return true;
	}

	@Override
	public boolean isEnabled() {
		// si la cuenta esta activada. Si se tiene en BBDD una columna enabled, usariamos dicha columna
		return true;
	}

	

}
