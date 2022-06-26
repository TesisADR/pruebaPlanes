package domainapp.modules.simple.fixture;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.personas.BuilderScriptWithResult;

import domainapp.modules.simple.dom.afiliados.Afiliado;
import domainapp.modules.simple.dom.afiliados.Afiliados;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class SimpleObjectBuilder extends BuilderScriptWithResult<Afiliado> {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String apellido;

    @Getter @Setter
    private String dni;

    @Getter @Setter
    private String edad;

    @Getter @Setter
    private String fechaNacimiento;

    @Getter @Setter
    private String lugarNacimiento;

    @Getter @Setter
    private String telefono;

    @Getter @Setter
    private String fechaInicio;

    @Getter @Setter
    private String plan;



    @Override
    protected Afiliado buildResult(final ExecutionContext ec) {

        checkParam("dni", ec, String.class);
        checkParam("apellido", ec, String.class);
        checkParam("name", ec, String.class);
        checkParam("edad", ec, String.class);
        checkParam("fechaNacimiento", ec, String.class);
        checkParam("lugarNacimiento", ec, String.class);
        checkParam("telefono", ec, String.class);
        checkParam("fechaInicio", ec, String.class);
        checkParam("plan", ec, String.class);

        return wrap(afiliados).create
                (dni, apellido, name, edad,
                        fechaNacimiento, lugarNacimiento, telefono,
                        fechaInicio, plan);
    }

    // -- DEPENDENCIES

    @Inject Afiliados afiliados;

}