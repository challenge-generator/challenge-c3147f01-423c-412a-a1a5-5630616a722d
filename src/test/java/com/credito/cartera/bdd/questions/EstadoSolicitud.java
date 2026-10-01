package com.credito.cartera.bdd.questions;

import com.credito.cartera.domain.model.SolicitudCredito;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

public class EstadoSolicitud implements Question<String> {

    private static final String CONSULTA_ESTADO = 
        "SELECT estado FROM solicitud_credito WHERE numero_operacion = ?";

    private EstadoSolicitud() {
    }

    public static EstadoSolicitud es() {
        return new EstadoSolicitud();
    }

    @Override
    public String answeredBy(Actor actor) {
        List<SolicitudCredito> solicitudes = actor.recall("solicitudes_registradas");
        
        if (solicitudes != null && !solicitudes.isEmpty()) {
            SolicitudCredito ultima = solicitudes.get(solicitudes.size() - 1);
            return ultima.getEstado().name();
        }
        
        return "SIN_REGISTRO";
    }

    public static Question<String> conNumeroOperacion(String numeroOperacion) {
        return new Question<String>() {
            @Override
            public String answeredBy(Actor actor) {
                List<SolicitudCredito> solicitudes = actor.recall("solicitudes_registradas");
                
                if (solicitudes != null) {
                    return solicitudes.stream()
                        .filter(s -> s.getNumeroOperacion().equals(numeroOperacion))
                        .findFirst()
                        .map(s -> s.getEstado().name())
                        .orElse("NO_ENCONTRADO");
                }
                
                return "NO_ENCONTRADO";
            }

            @Override
            public String getSubject() {
                return "El estado de la solicitud con número de operación " + numeroOperacion;
            }
        };
    }
}