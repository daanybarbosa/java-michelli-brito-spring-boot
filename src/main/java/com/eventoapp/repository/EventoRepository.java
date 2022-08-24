package com.eventoapp.repository;

import com.eventoapp.models.Evento;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface será instanciada e usaremos metodos já prontos como salvar, deletar e atualizar no banco de dados.
 */

public interface EventoRepository extends CrudRepository<Evento, String> {
    //busca do codigo do evento
    Evento findByCodigo(Long codigo);


}
