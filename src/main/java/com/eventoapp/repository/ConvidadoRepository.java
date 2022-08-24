package com.eventoapp.repository;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {

    // ira buscar no BD os convidados de determinado evento
    Iterable<Convidado> findByEvento(Evento evento);
}
