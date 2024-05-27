package com.registro.usuarios.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.UsuarioRepositorio;

/**
 * Implementación del servicio de usuarios que maneja operaciones como guardar, listar,
 * actualizar y eliminar usuarios, además de manejar la autenticación de usuarios.
 */
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * Constructor de la clase UsuarioServicioImpl.
	 * @param usuarioRepositorio Repositorio de usuarios utilizado para operaciones de base de datos.
	 */
	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	/**
	 * Guarda un nuevo usuario en la base de datos.
	 * @param registroDTO Datos del usuario a ser registrados.
	 * @return El usuario guardado.
	 */
	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getNombre(),
				registroDTO.getApellido(), registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_USER")));
		return usuarioRepositorio.save(usuario);
	}

	/**
	 * Carga un usuario por su nombre de usuario (en este caso, el email).
	 * @param username Nombre de usuario (email) del usuario a cargar.
	 * @return Detalles del usuario cargado.
	 * @throws UsernameNotFoundException Si el usuario no es encontrado.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	/**
	 * Obtiene la lista de todos los usuarios registrados.
	 * @return Lista de usuarios.
	 */
	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	/**
	 * Obtiene un usuario por su ID.
	 * @param id ID del usuario a buscar.
	 * @return El usuario encontrado.
	 */
	@Override
	public Usuario obtenerusuarioporid(long id) {
		return usuarioRepositorio.findById(id).get();
	}

	/**
	 * Actualiza la información de un usuario.
	 * @param usuario Usuario con la información actualizada.
	 * @return El usuario actualizado.
	 */
	@Override
	public Usuario actualizarusario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}

	/**
	 * Elimina un usuario por su ID.
	 * @param id ID del usuario a eliminar.
	 */
	@Override
	public void eliminarusuario(long id) {
		usuarioRepositorio.deleteById(id);
	}

	/**
	 * Convierte roles del usuario en autoridades para Spring Security.
	 * @param roles Colección de roles del usuario.
	 * @return Autoridades del usuario.
	 */
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
}
