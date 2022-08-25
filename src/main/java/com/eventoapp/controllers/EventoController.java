package com.eventoapp.controllers;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;

    @Autowired
    private ConvidadoRepository cr;

    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET) //ira retornar um formulario
    public String form(){
        return "/evento/formEvento";  //colocar o caminho até a pagina html
    }

    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.POST) //ira salvar os dados do formulario
    public String form(@Valid Evento evento){
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
    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") Long codigo) {
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento"); //caminho do html detalhesEvento
        mv.addObject("evento", evento);

        Iterable<Convidado> convidados = cr.findByEvento(evento); // lista de convidados de acordo com cada evento
        mv.addObject("convidados", convidados);
        return mv;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo") Long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
        //ira verificar se tem erros nos campos e ira exibir na view do browser
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/{codigo}";
        }
        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
        return "redirect:/{codigo}";
    }
}
