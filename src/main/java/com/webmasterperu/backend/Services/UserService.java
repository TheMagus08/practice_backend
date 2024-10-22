package com.webmasterperu.backend.Services;

import com.webmasterperu.backend.Entities.TipoSexo;
import com.webmasterperu.backend.Entities.User;
import com.webmasterperu.backend.Repositories.ITipoDocumentoRepository;
import com.webmasterperu.backend.Repositories.ITipoSexoRepository;
import com.webmasterperu.backend.Repositories.IUserRepository;
import com.webmasterperu.backend.Request.UserRequest;
import com.webmasterperu.backend.Response.UserResponse;
import com.webmasterperu.backend.utils.dto.UserDto;
import com.webmasterperu.backend.utils.dto.UserListDTO;
import com.webmasterperu.backend.utils.enums.ESexo;
import com.webmasterperu.backend.utils.enums.Role;
import com.webmasterperu.backend.utils.list.TipoDocumento;
import com.webmasterperu.backend.utils.list.TipoRole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    public final IUserRepository userRepository;

    @Autowired
    private ITipoSexoRepository tipoSexoRepository;

    @Autowired
    private ITipoDocumentoRepository tipoDocumentoRepository;

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {


        // Buscar el usuario existente por ID
        User user = User.builder()
                .id(userRequest.getId())
                .build();

        // Recuperar el usuario existente desde la base de datos
        User existingUser = userRepository.findById(userRequest.getId())
                .orElseThrow(() -> new NoSuchElementException("usuario no encontrado"));

        ESexo sexoEnum;
        sexoEnum = ESexo.valueOf(userRequest.getSexo().toUpperCase());

        TipoSexo tipoSexo = tipoSexoRepository.findByDescripcion(sexoEnum)
                .orElseThrow(() -> new NoSuchElementException("TipoSexo no encontrado"));

        TipoDocumento tipoDocumento = tipoDocumentoRepository.findByDocumento(userRequest.getTipoDocumento())
                .orElseThrow(() -> new NoSuchElementException("Tipo de documento no encontrado"));


        // Actualizar los campos básicos del usuario
        existingUser.setTiposexo(tipoSexo);
        existingUser.setPrimerNombre(userRequest.getPrimerNombre());
        existingUser.setSegundoNombre(userRequest.getSegundoNombre());
        existingUser.setCiudadNac(userRequest.getCiudadNac());
        existingUser.setEstadoCivil(userRequest.getEstadoCivil());
        existingUser.setFechaNac(userRequest.getFechaNac());
        existingUser.setApellidoM(userRequest.getApellidoM());
        existingUser.setApellidoP(userRequest.getApellidoP());
        existingUser.setCodigo(userRequest.getCodigo());
        existingUser.setTipoDocumento(tipoDocumento);
        existingUser.setNumeroDocumento(userRequest.getNumeroDocumento());
        existingUser.setTelefono1(userRequest.getTelefono1());
        existingUser.setTelefono2(userRequest.getTelefono2());


        // Guardar el usuario actualizado (esto también guardará los datos personales actualizados)
        userRepository.save(existingUser);

        return new UserResponse("El usuario se actualizó satisfactoriamente");
    }


    /*User user = User.builder()
                .id(userRequest.id)
                .firstname(userRequest.firstname)
                .lastname(userRequest.lastname)
                .role(Role.USER)
                .build();
        userRepository.updateUser(user.getId(), user.getFirstname(), user.getLastname());
        return new UserResponse("El usuario se registró satisfactoriamente");*/


    public UserDto getUser(Integer id) {
        User user= userRepository.findById(id).orElse(null);

        if (user!=null)
        {
            /*DatosPersonalesUser datosPersonales = user.getDatosPersonalesUser();
            DatosPersonalesUserDTO datosPersonalesDTO = DatosPersonalesUserDTO.builder()
                    .id(datosPersonales.getId())
                    .direccion(datosPersonales.getDireccion())
                    .celular(datosPersonales.getCelular())
                    .build();*/


            UserDto userDTO = UserDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .primerNombre(user.getPrimerNombre())
                    .sexo(user.getTiposexo().getDescripcion())
                    .segundoNombre(user.getSegundoNombre())
                    .role(user.getRole().getRole())
                    .apellidoP(user.getApellidoP())
                    .apellidoM(user.getApellidoM())
                    .fechaNac(user.getFechaNac())
                    .estadoCivil(user.getEstadoCivil())
                    .ciudadNac(user.getCiudadNac())
                    .codigo(user.getCodigo())
                    .tipoDocumento(user.getTipoDocumento().getDocumento())
                    .numeroDocumento(user.getNumeroDocumento())
                    .telefono1(user.getTelefono1())
                    .telefono2(user.getTelefono2())
                    .direccion(user.getDireccion())
                    .urbanizacion(user.getUrbanizacion())
                    .linkedin(user.getLinkedin())
                    .facebook(user.getFacebook())
                    .instagram(user.getInstagram())
                    .build();
            return userDTO;
        }
        return null;
    }

    public List<UserListDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .filter(user -> !user.getRole().getRole().equalsIgnoreCase("ADMIN"))
                .map(user -> new UserListDTO(
                        user.getId(),
                        user.getPrimerNombre(),
                        user.getSegundoNombre(),
                        user.getApellidoP(),
                        user.getApellidoM(),
                        user.getCodigo(),
                        user.getUsername(),
                        user.getNumeroDocumento(),
                        user.getRole() != null ? user.getRole().getRole() : "No Role"

                ))
                .collect(Collectors.toList());
    }
}
