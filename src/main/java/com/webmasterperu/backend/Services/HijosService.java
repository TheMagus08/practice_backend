package com.webmasterperu.backend.Services;

import com.webmasterperu.backend.Entities.Hijos;
import com.webmasterperu.backend.Entities.TipoSexo;
import com.webmasterperu.backend.Entities.User;
import com.webmasterperu.backend.Repositories.IHijosRepository;
import com.webmasterperu.backend.Repositories.ITipoSexoRepository;
import com.webmasterperu.backend.Repositories.IUserRepository;
import com.webmasterperu.backend.utils.dto.HijosDTO;
import com.webmasterperu.backend.utils.enums.ESexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HijosService {

    @Autowired
    private IHijosRepository hijosRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ITipoSexoRepository tipoSexoRepository;

    @Transactional
    public HijosDTO addHijo(HijosDTO hijosDTO) {
        User user = verificarUsuarioExistente(hijosDTO.getId());

        // Convertir String a ESexo
        ESexo sexoEnum;
        try {
            sexoEnum = ESexo.valueOf(hijosDTO.getSexo().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo de sexo no válido: " + hijosDTO.getSexo());
        }

        TipoSexo tipoSexo = tipoSexoRepository.findByDescripcion(sexoEnum)
                .orElseThrow(() -> new RuntimeException("Tipo de sexo no encontrado"));

        Hijos hijo = Hijos.builder()
                .user(user)
                .nombres(hijosDTO.getNombres())
                .tiposexo(tipoSexo)
                .fechaNacimiento(hijosDTO.getFechaNacimiento())
                .dni(hijosDTO.getDni())
                .build();

        Hijos hijoGuardado = hijosRepository.save(hijo);

        return mapToDTO(hijoGuardado);
    }

    private HijosDTO mapToDTO(Hijos hijo) {
        return HijosDTO.builder()
                .id(hijo.getId())
                .nombres(hijo.getNombres())
                .sexo(hijo.getTiposexo().getDescripcion())
                .fechaNacimiento(hijo.getFechaNacimiento())
                .dni(hijo.getDni())
                .build();
    }

    public User verificarUsuarioExistente(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
    }

    public List<Hijos> getHijosByUserId(Integer userId) {
        return hijosRepository.findByUserId(userId);
    }

    public Optional<Hijos> getHijoById(Integer id) {
        return hijosRepository.findById(id);
    }

    @Transactional
    public void deleteHijo(Integer id) {
        hijosRepository.deleteById(id);
    }

    @Transactional
    public Hijos updateHijo(Integer id, Hijos hijoDetails) {

        Hijos existingHijo = hijosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hijo no encontrado con id " + id));



        // Asignar los detalles actualizados
        existingHijo.setNombres(hijoDetails.getNombres());
        existingHijo.setFechaNacimiento(hijoDetails.getFechaNacimiento());
        existingHijo.setDni(hijoDetails.getDni());

        // Aquí es donde solucionamos el problema del sexo
        if (hijoDetails.getTiposexo() != null) {
            // Buscar el TipoSexo por su id y asignarlo al hijo
            TipoSexo tipoSexo = tipoSexoRepository.findById(hijoDetails.getTiposexo().getId())
                    .orElseThrow(() -> new RuntimeException("TipoSexo no encontrado con id " + hijoDetails.getTiposexo().getId()));
            existingHijo.setTiposexo(tipoSexo);
        }

        // Guardar los cambios
        return hijosRepository.save(existingHijo);
    }

}