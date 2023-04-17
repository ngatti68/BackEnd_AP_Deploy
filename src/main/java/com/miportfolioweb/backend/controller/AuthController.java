package com.miportfolioweb.backend.controller;

import com.miportfolioweb.backend.DTO.JwtDTO;
import com.miportfolioweb.backend.DTO.LoginUsuario;
import com.miportfolioweb.backend.DTO.Mensaje;
import com.miportfolioweb.backend.DTO.NuevoUsuario;
import com.miportfolioweb.backend.entity.Rol;
import com.miportfolioweb.backend.entity.Usuario;
import com.miportfolioweb.backend.enums.RolNombre;
import com.miportfolioweb.backend.security.JWT.JwtProvider;
import com.miportfolioweb.backend.service.RolService;
import com.miportfolioweb.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://ng-portfolioweb-ap.web.app")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos incorrectos."),HttpStatus.BAD_REQUEST);

        if(usuarioService.existePorNombre(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity<>(new Mensaje("El nombre de usuario ingresado ya existe."), HttpStatus.BAD_REQUEST);

        if(usuarioService.existePorEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity<>(new Mensaje("El email ingresado ya existe."), HttpStatus.BAD_REQUEST);

        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).orElse(null));

        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).orElse(null));
        usuario.setRoles(roles);
        usuarioService.guardar(usuario);

        return new ResponseEntity<>(new Mensaje("Usuario guardado satisfactoriamente."),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos incorrectos."), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

}