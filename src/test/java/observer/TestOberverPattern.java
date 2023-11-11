package observer;

import org.francescobasile.tisano.prove.observer.*;
import org.francescobasile.tisano.utils.PropertyUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestOberverPattern {

    @Test
    @DisplayName("Test pattern publish/subscriber")
    public void PublishSubscriberPatternTest() {
        EventManager eventManager = new EventManager();
        PublishManager publishManager = new PublishManager();

        Publishable publisher1 = new Publisher(eventManager);
        Publishable publisher2 = new Publisher(eventManager);

        Subscribable subscriber1 = new Subscriber();
        Subscribable subscriber2 = new Subscriber();

        EventSchema eventSchema1 = new EventSchema(EventSchema.EventType.OBSERVATION);
        eventSchema1.setDateTime(LocalDateTime.now());
        eventSchema1.setMessageVersion(102L);

        EventSchema eventSchema2 = new EventSchema(EventSchema.EventType.TIMESCHEDULE);
        eventSchema2.setDateTime(LocalDateTime.now().minusDays(10));
        eventSchema2.setMessageVersion(105L);

        publishManager.addPublisher(publisher1);
        publishManager.addPublisher(publisher2);

        eventManager.subscribe(subscriber1, EventSchema.EventType.OBSERVATION);
        eventManager.subscribe(subscriber2, EventSchema.EventType.TIMESCHEDULE);

        publisher1.publish(eventSchema1);
        publisher2.publish(eventSchema1);

    }

    @Test
    @DisplayName("Test di reflection method passaggio nomi attributi")
    public void reflectionVerifyFieldNameTest() {
        MedicoStub medicoStub = new MedicoStub("Ciccio", "Panza");

        assertEquals("nome", PropertyUtils.verifyFieldName(medicoStub, "nome"));
        assertEquals("cognome", PropertyUtils.verifyFieldName(medicoStub, "cognome"));
        assertThrows(IllegalArgumentException.class, () -> PropertyUtils.verifyFieldName(medicoStub, "id"));
    }

    @Test
    @DisplayName("Test entità publish/subscribe")
    public void entityReaderServiceTest() throws InterruptedException {

        EventManager eventManager = new EventManager();

        MedicoStub medicoPublisher = new MedicoStub("Ciccio_1", "Panza_1");
        medicoPublisher.setEventManager(eventManager);
        medicoPublisher.setPublishable(true);

        MedicoStub medicoSubscriber = new MedicoStub();
        eventManager.subscribe(medicoSubscriber, EventSchema.EventType.OBSERVATION);

        medicoPublisher.setNome("CICCIO_1_mod");
        medicoPublisher.setCognome("PANZA_1_mod");

        assertEquals("CICCIO_1_mod", medicoSubscriber.getNome());
        assertEquals("PANZA_1_mod", medicoSubscriber.getCognome());

    }
}
