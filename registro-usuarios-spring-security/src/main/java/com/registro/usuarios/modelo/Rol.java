package com.registro.usuarios.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa un rol de usuario en el sistema.
 */
@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	/**
	 * Obtiene el ID del rol.
	 * @return ID del rol.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID del rol.
	 * @param id ID del rol.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre del rol.
	 * @return Nombre del rol.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del rol.
	 * @param nombre Nombre del rol.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Constructor con parámetros de la clase Rol.
	 * @param id ID del rol.
	 * @param nombre Nombre del rol.
	 */
	public Rol(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * Constructor vacío de la clase Rol.
	 */
	public Rol() {

	}

	/**
	 * Constructor de la clase Rol con un parámetro para el nombre del rol.
	 * @param nombre Nombre del rol.
	 */
	public Rol(String nombre) {
		super();
		this.nombre = nombre;
	}
}