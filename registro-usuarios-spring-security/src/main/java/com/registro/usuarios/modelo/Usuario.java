package com.registro.usuarios.modelo;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Clase que representa a un usuario en el sistema.
 */
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	private String email;

	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
	)
	private Collection<Rol> roles;

	/**
	 * Obtiene el ID del usuario.
	 * @return ID del usuario.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID del usuario.
	 * @param id ID del usuario.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre del usuario.
	 * @return Nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 * @param nombre Nombre del usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el apellido del usuario.
	 * @return Apellido del usuario.
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido del usuario.
	 * @param apellido Apellido del usuario.
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Obtiene el email del usuario.
	 * @return Email del usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el email del usuario.
	 * @param email Email del usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene la contraseña del usuario.
	 * @return Contraseña del usuario.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establece la contraseña del usuario.
	 * @param password Contraseña del usuario.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtiene los roles del usuario.
	 * @return Roles del usuario.
	 */
	public Collection<Rol> getRoles() {
		return roles;
	}

	/**
	 * Establece los roles del usuario.
	 * @param roles Roles del usuario.
	 */
	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	/**
	 * Constructor con parámetros de la clase Usuario.
	 * @param id ID del usuario.
	 * @param nombre Nombre del usuario.
	 * @param apellido Apellido del usuario.
	 * @param email Email del usuario.
	 * @param password Contraseña del usuario.
	 * @param roles Roles asignados al usuario.
	 */
	public Usuario(Long id, String nombre, String apellido, String email, String password, Collection<Rol> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	/**
	 * Constructor con parámetros de la clase Usuario.
	 * @param nombre Nombre del usuario.
	 * @param apellido Apellido del usuario.
	 * @param email Email del usuario.
	 * @param password Contraseña del usuario.
	 * @param roles Roles asignados al usuario.
	 */
	public Usuario(String nombre, String apellido, String email, String password, Collection<Rol> roles) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	/**
	 * Constructor vacío de la clase Usuario.
	 */
	public Usuario() {

	}
}