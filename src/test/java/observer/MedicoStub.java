package observer;

import org.francescobasile.tisano.prove.observer.*;

public class MedicoStub implements Subscribable, Publishable {
    private String nome;
    private String cognome;

    private EventManager eventManager = null;
    private Boolean publishable = false;

    public MedicoStub() {
    }

    public MedicoStub(EventManager eventManager) {
        this();
        this.eventManager = eventManager;
        this.publishable = true;
    }

    public MedicoStub(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        EventSchema eventSchema = EventSchema.buildSchemaForPublisher(
                EventSchema.EventType.OBSERVATION,
                this,
                "nome",
                this.nome,
                nome
        );
//     TODO: test   this.nome = nome;
        this.publish(eventSchema);
    }

    public void setNome(String nome, int i) {
        //TODO: generalizzare il filtro in una classe SchemaRepository passando param di classe ed attributo
        //TODO: studiare la persistenza e molteplicità
        //TODO: modificare EventSchema per sottomettere getValue()... sotto attributeName
        // per formare coppia chiave valore, inoltre trasformare da singolo a multi attributo la struttura dati:
        // {
        //  attributes:
        //      {
        //          attributeName: "someattributename",
        //          attributeValues:
        //          {
        //              "previousValue" : "someoldvalue",
        //              "actualValue" : "somenewvalue",
        //          }
        //      }
        // }
        // TODO: fare una collection associata alla mappa di Subscribers per stabilire se un reader
        //  ha gia letto ed utilizzato un attributo, settando un parametro di timestamp + outdated
        // TODO: spostare il filtro sugli attributi in un metodo del reader invece che nel setter del consumer finale
//        String nomeClasse = this.getClass().getSimpleName();
//        String nomeAttributo = "nome";
//        TaskManger taskManger = new TaskManger();
//
//        SchemaQueryService.findByType();
//        reader.getSubscribers();
//        EventSchema schema = reader.getSchemas().stream()
//                .filter(eventSchema -> eventSchema
//                        .getPayload()
//                        .getSoggetto()
//                        .getEntityClass()
//                        .contains(nomeClasse)
//                        && eventSchema
//                        .getPayload()
//                        .getSoggetto()
//                        .getAttributeName()
//                        .contains(nomeAttributo)
//                )
//                .findFirst().orElseThrow();
//        this.nome = (String) schema.getPayload().getNewValue();
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        EventSchema eventSchema = EventSchema.buildSchemaForPublisher(
                EventSchema.EventType.OBSERVATION,
                this,
                "cognome",
                this.cognome,
                cognome
        );
//      TODO: test  this.cognome = cognome;
        this.publish(eventSchema);
    }

    @Override
    public void onEvent(EventSchema schema) {
        //TODO: analizzare lo schema di attributi, decidere se utilizzarlo o scartarlo e spuntare come letto.
        if (!SchemaQueryService.containsSubjectClass(schema, this.getClass().getSimpleName())) return;
        if (SchemaQueryService.containsAttributeName(schema, "nome")) {
            this.nome = (String) SchemaQueryService.getAttributeByName(schema, "nome").getActualValue();
        }
        if (SchemaQueryService.containsAttributeName(schema, "cognome")) {
            this.cognome = (String) SchemaQueryService.getAttributeByName(schema, "cognome").getActualValue();
        }
    }

    @Override
    public void publish(EventSchema eventSchema) {
        if (this.eventManager == null) {
            throw new IllegalCallerException("No event manager found");
        }
        if (!this.isPublishable()) return;
        this.eventManager.publish(eventSchema);
    }

    @Override
    public boolean isPublishable() {
        return this.publishable;
    }

    public void setPublishable(Boolean publishable) {
        this.publishable = publishable;
    }

    @Override
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }
}

