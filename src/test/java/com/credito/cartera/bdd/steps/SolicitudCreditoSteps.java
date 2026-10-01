package com.credito.cartera.bdd.steps;

import com.credito.cartera.bdd.tasks.RegistrarSolicitud;
import com.credito.cartera.bdd.questions.EstadoSolicitud;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;

import java.math.BigDecimal;
import java.time.LocalDate;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class SolicitudCreditoSteps {

    @Dado("que el originador de créditos ingresa los datos de una solicitud de crédito")
    public void queElOriginadorIngresaLosDatos() {
        Actor actor = OnStage.theActorCalled("Originador");
        actor.wasAbleTo(
            RegistrarSolicitud.conDatos(
                "OPE-2024-001",
                "WEB",
                "DNI",
                "12345678",
                "Juan",
                "Pérez",
                new BigDecimal("15000.00"),
                12
            )
        );
    }

    @Cuando("el sistema procesa la solicitud de crédito")
    public void elSistemaProcesaLaSolicitud() {
        Actor actor = OnStage.theActorInTheSpotlight();
    }

    @Entonces("la solicitud debe ser registrada con estado {string}")
    public void laSolicitudDebeSerRegistrada(String estadoEsperado) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(
            seeThat(
                "El estado de la solicitud",
                EstadoSolicitud.es(),
                equalTo(estadoEsperado)
            )
        );
    }

    @Dado("que existe una solicitud de crédito con número de operación {string}")
    public void queExisteUnaSolicitud(String numeroOperacion) {
        Actor actor = OnStage.theActorCalled("Originador");
    }

    @Cuando("se intenta registrar una nueva solicitud con el mismo número de operación")
    public void seIntentaRegistrarConMismoNumero() {
        Actor actor = OnStage.theActorInTheSpotlight();
    }

    @Entonces("el sistema debe retornar la misma respuesta anterior")
    public void elSistemaDebeRetornarLaMismaRespuesta() {
        Actor actor = OnStage.theActorInTheSpotlight();
    }
}