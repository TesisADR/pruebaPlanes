package domainapp.modules.simple.fixture;

import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithBuilderScript;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithFinder;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import domainapp.modules.simple.dom.afiliados.Afiliado;
import domainapp.modules.simple.dom.afiliados.Afiliados;
import domainapp.modules.simple.types.Dni;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SimpleObject_persona
        implements PersonaWithBuilderScript<SimpleObjectBuilder>, PersonaWithFinder<Afiliado> {

    FOO("39355100"),
    BAR("63516516"),
    BAZ("35168468"),
    FRODO("8468688"),
    FROYO("86435135"),
    FIZZ("98168168"),
    BIP("6816846"),
    BOP("351651685"),
    BANG("65165165"),
    BOO("6510515");


    private final String dni;

    @Override
    public SimpleObjectBuilder builder() {
        return new SimpleObjectBuilder().setDni(dni);
    }

    @Override
    public Afiliado findUsing(final ServiceRegistry serviceRegistry) {
        Afiliados afiliados = serviceRegistry.lookupService(Afiliados.class).orElse(null);
        return afiliados.findByDniExact(dni);
    }

    public static class PersistAll
            extends PersonaEnumPersistAll<SimpleObject_persona, Afiliado> {

        public PersistAll() {
            super(SimpleObject_persona.class);
        }
    }
}