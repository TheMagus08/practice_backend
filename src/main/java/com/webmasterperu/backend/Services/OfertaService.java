package com.webmasterperu.backend.Services;

import com.webmasterperu.backend.Entities.Ofertas;
import com.webmasterperu.backend.Entities.TipoSector;
import com.webmasterperu.backend.Repositories.IOfertasRepository;
import com.webmasterperu.backend.Entities.TipoEstado;
import com.webmasterperu.backend.utils.enums.EEstadoOferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OfertaService {

    @Autowired
    private IOfertasRepository ofertasRepository;

    public List<Ofertas> findAll() {
        return ofertasRepository.findAll();
    }

    public List<Ofertas> obtenerOfertasPublicadas() {
        return ofertasRepository.findByEstadoDescripcion(EEstadoOferta.PUBLICADO);
    }

    public List<Ofertas> obtenerOfertasPorSector(TipoSector tipoSector) {
        return ofertasRepository.findByEstadoDescripcionAndTipoSector(EEstadoOferta.PUBLICADO, tipoSector);
    }

    public Optional<Ofertas> findById(Integer id) {
        return ofertasRepository.findById(id);
    }

    public Ofertas save(Ofertas oferta) {
        return ofertasRepository.save(oferta);
    }

    public Ofertas update(Integer id, Ofertas ofertaDetails) {
        Ofertas oferta = ofertasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada " + id));
            oferta.setFechaInicio(ofertaDetails.getFechaInicio());
            oferta.setFechaFin(ofertaDetails.getFechaFin());
            oferta.setTipoSector(ofertaDetails.getTipoSector());
            oferta.setIndustria(ofertaDetails.getIndustria());
            oferta.setTitulo(ofertaDetails.getTitulo());
            oferta.setEmpresa(ofertaDetails.getEmpresa());
            oferta.setModalidad(ofertaDetails.getModalidad());
            oferta.setEnumTiempo(ofertaDetails.getEnumTiempo());
            oferta.setSalario(ofertaDetails.getSalario());
            oferta.setEstado(ofertaDetails.getEstado());
            return ofertasRepository.save(oferta);
        }


    public void delete(Integer id) {
        ofertasRepository.deleteById(id);
    }


}
