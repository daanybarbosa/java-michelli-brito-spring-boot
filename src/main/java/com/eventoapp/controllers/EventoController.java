package com.eventoapp.controllers;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET) //ira retornar um formulario
    public String form(){
        return "/evento/formEvento";  //colocar o caminho até a pagina html
    }

    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.POST) //ira salvar os dados do formulario
    public String form(Evento evento){

        //persistir no banco de dados
        eventoRepository.save(evento);

        return "redirect:/cadastrarEvento";  //redirecionar para o /cadastrarEvento do metodo GET
    }
}
