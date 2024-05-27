package com.registro.usuarios.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.Usuario;

/**
 * Interfaz que define los métodos para el servicio de usuarios.
 * Extiende de UserDetailsService para soportar la carga de usuarios por nombre de usuario.
 */
public interface UsuarioServicio extends UserDetailsService {

	/**
	 * Guarda un nuevo usuario.
	 * @param registroDTO Datos del usuario a ser registrados.
	 * @return El usuario guardado.
	 */
	public Usuario guardar(UsuarioRegistroDTO registroDTO);

	/**
	 * Obtiene la lista de todos los usuarios registrados.
	 * @return Lista de usuarios.
	 */
	public List<Usuario> listarUsuarios();

	/**
	 * Obtiene un usuario por su ID.
	 * @param id ID del usuario a buscar.
	 * @return El usuario encontrado.
	 */
	public Usuario obtenerusuarioporid(long id);

	/**
	 * Actualiza la información de un usuario.
	 * @param usuario Usuario con la información actualizada.
	 * @return El usuario actualizado.
	 */
	public Usuario actualizarusario(Usuario usuario);

	/**
	 * Elimina un usuario por su ID.
	 * @param id ID del usuario a eliminar.
	 */
	public void eliminarusuario(long id);
}