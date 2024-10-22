package com.webmasterperu.backend.Services;

import com.webmasterperu.backend.Entities.TipoSexo;
import com.webmasterperu.backend.Entities.User;
import com.webmasterperu.backend.Repositories.ITipoDocumentoRepository;
import com.webmasterperu.backend.Repositories.ITipoRoleRepository;
import com.webmasterperu.backend.Repositories.ITipoSexoRepository;
import com.webmasterperu.backend.Repositories.IUserRepository;
import com.webmasterperu.backend.Request.LoginRequest;
import com.webmasterperu.backend.Request.RegisterRequest;
import com.webmasterperu.backend.Response.AuthResponse;
import com.webmasterperu.backend.jwt.JwtService;
import com.webmasterperu.backend.utils.enums.ESexo;
import com.webmasterperu.backend.utils.list.TipoDocumento;
import com.webmasterperu.backend.utils.list.TipoRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ITipoDocumentoRepository tipoDocumentoRepository;
    private final ITipoRoleRepository tipoRoleRepository;
    private final ITipoSexoRepository tipoSexoRepository;
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

        Optional<TipoRole> optionalTipoRole = tipoRoleRepository.findByRole(request.getRole());
        if (optionalTipoRole.isEmpty()) {
            throw new IllegalArgumentException("Rol proporcionado no encontrado");
        }
        TipoRole tipoRole = optionalTipoRole.get();

        Optional<TipoDocumento> optionalTipoDocumento = tipoDocumentoRepository.findByDocumento(request.getTipoDocumento());
        if (optionalTipoDocumento.isEmpty()) {
            throw new IllegalArgumentException("Tipo de documento proporcionado no encontrado");
        }
        TipoDocumento tipoDocumento = optionalTipoDocumento.get();

        Optional<TipoSexo> optionalTipoSexo = tipoSexoRepository.findByDescripcion(ESexo.valueOf(request.getTipoSexo()));
        if (optionalTipoSexo.isEmpty()) {
            throw new IllegalArgumentException("Tipo de sexo proporcionado no encontrado");
        }
        TipoSexo tipoSexo = optionalTipoSexo.get();



        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .tipoDocumento(tipoDocumento)
                .role(tipoRole)
                .tiposexo(tipoSexo)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public List<TipoRole> getAllRolesExceptAdmin() {
        // Obtener todos los roles y filtrar para excluir el rol "ADMIN"
        return tipoRoleRepository.findAll()
                .stream()
                .filter(role -> !role.getRole().equalsIgnoreCase("ADMIN"))
                .collect(Collectors.toList());
    }
}
