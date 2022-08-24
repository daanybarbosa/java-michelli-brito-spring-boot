package com.eventoapp.controllers;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;

    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET) //ira retornar um formulario
    public String form(){
        return "/evento/formEvento";  //colocar o caminho até a pagina html
    }

    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.POST) //ira salvar os dados do formulario
    public String form(Evento evento){
        //persistir no banco de dados
        er.save(evento);
        return "redirect:/cadastrarEvento";  //redirecionar para o /cadastrarEvento do metodo GET
    }

    //metodo para retornar a lista de eventos
    @RequestMapping("/eventos")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView("index"); //ira rederizar a pagina index
        Iterable<Evento> eventos = er.findAll(); //buscar uma lista de eventos
        mv.addObject("eventos", eventos); //parametros: nomeDaView (html) + lista de eventos
        return mv;
    }

    // Quando clicar em cima do nome do evento ira redirecionar para o respectivo codigo do evento e exibir os detalhes do mesmo.
    @RequestMapping("/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo") Long codigo) {
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento"); //caminho do html detalhesEvento
        mv.addObject("evento", evento);
        return mv;
    }
}
