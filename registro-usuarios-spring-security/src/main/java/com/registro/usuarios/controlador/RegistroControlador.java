package com.registro.usuarios.controlador;

import com.registro.usuarios.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.registro.usuarios.servicio.UsuarioServicio;

/**
 * Controlador que maneja las operaciones relacionadas con el registro y gestión de usuarios.
 */
@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio servicio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * Maneja la solicitud GET para la página de inicio de sesión.
	 * @return Nombre de la vista "login".
	 */
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	/**
	 * Maneja la solicitud GET para la página de inicio.
	 * @param modelo Modelo para agregar atributos a la vista.
	 * @return Nombre de la vista "Inicio".
	 */
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		return "Inicio";
	}

	/**
	 * Maneja la solicitud GET para la página "Qué somos".
	 * @param modelo Modelo para agregar atributos a la vista.
	 * @return Nombre de la vista "Que somos".
	 */
	@GetMapping("/Que somos")
	public String verPaginaDequesomos(Model modelo) {
		return "Que somos";
	}

	/**
	 * Maneja la solicitud GET para la página de visualización de la tabla de usuarios.
	 * @param modelo Modelo para agregar atributos a la vista.
	 * @return Nombre de la vista "index".
	 */
	@GetMapping("/tabla")
	public String vertabla(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "index";
	}

	/**
	 * Maneja la solicitud GET para mostrar el formulario de edición de un usuario específico.
	 * @param id ID del usuario a editar.
	 * @param modelo Modelo para agregar atributos a la vista.
	 * @return Nombre de la vista "editar_usuario".
	 */
	@GetMapping("tabla/editar/{id}")
	public String mostrarformulariodeEditar(@PathVariable Long id, Model modelo){
		modelo.addAttribute("usuario", servicio.obtenerusuarioporid(id));
		return "editar_usuario";
	}

	/**
	 * Maneja la solicitud POST para actualizar la información de un usuario.
	 * @param id ID del usuario a actualizar.
	 * @param usuario Objeto Usuario con los datos actualizados.
	 * @param modelo Modelo para agregar atributos a la vista.
	 * @return Redirección a la página de visualización de la tabla de usuarios.
	 */
	@PostMapping("tabla/{id}")
	public String actualizarusuario(@PathVariable Long id, @ModelAttribute("usuario") Usuario usuario, Model modelo) {
		Usuario usuarioexistente = servicio.obtenerusuarioporid(id);
		usuarioexistente.setId(id);
		usuarioexistente.setNombre(usuario.getNombre());
		usuarioexistente.setApellido(usuario.getApellido());
		usuarioexistente.setEmail(usuario.getEmail());
		usuarioexistente.setPassword(passwordEncoder.encode(usuario.getPassword()));

		servicio.actualizarusario(usuarioexistente);
		return "redirect:/tabla";
	}

	/**
	 * Maneja la solicitud GET para eliminar un usuario.
	 * @param id ID del usuario a eliminar.
	 * @return Redirección a la página de visualización de la tabla de usuarios.
	 */
	@GetMapping("/tabla/{id}")
	public String eliminarusuario(@PathVariable Long id) {
		servicio.eliminarusuario(id);
		return "redirect:/tabla";
	}
}