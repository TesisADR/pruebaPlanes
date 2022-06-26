package domainapp.modules.simple.dom.afiliados;

import java.util.List;

import javax.inject.Inject;
import javax.jdo.JDOQLTypedQuery;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.persistence.jdo.applib.services.JdoSupportService;

import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Apellido;
import domainapp.modules.simple.types.Dni;
import domainapp.modules.simple.types.Edad;
import domainapp.modules.simple.types.FechaNacimiento;
import domainapp.modules.simple.types.LugarNacimiento;
import domainapp.modules.simple.types.Telefono;
import domainapp.modules.simple.types.FechaInicio;
import domainapp.modules.simple.types.Planes;

@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "simple.Afiliado"
)
@javax.annotation.Priority(PriorityPrecedence.EARLY)
@lombok.RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class Afiliados {

    final RepositoryService repositoryService;
    final JdoSupportService jdoSupportService;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public Afiliado create(
            @Name final String name,
            @Apellido final String apellido,
            @Dni final String dni,
            @Edad final String edad,
            @FechaNacimiento final String fechaNacimiento,
            @LugarNacimiento final String lugarNacimiento,
            @Telefono final String telefono,
            @FechaInicio final String fechaInicio,
            @Planes final String planes) {
        return repositoryService.persist(Afiliado.withName
                (name, apellido, dni, edad, fechaNacimiento,
                        lugarNacimiento, telefono, fechaInicio, planes));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public List<Afiliado> findByDni(
            @Dni final String dni
    ) {
        return repositoryService.allMatches(
                Query.named(Afiliado.class, Afiliado.NAMED_QUERY__FIND_BY_DNI_LIKE)
                        .withParameter("dni", dni));
    }


    @Programmatic
    public Afiliado findByDniExact(final String dni) {
        return repositoryService.firstMatch(
                        Query.named(Afiliado.class, Afiliado.NAMED_QUERY__FIND_BY_DNI_EXACT)
                                .withParameter("dni", dni))
                .orElse(null);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Afiliado> listAll() {
        return repositoryService.allInstances(Afiliado.class);
    }




    @Programmatic
    public void ping() {
        JDOQLTypedQuery<Afiliado> q = jdoSupportService.newTypesafeQuery(Afiliado.class);
        final QAfiliado candidate = QAfiliado.candidate();
        q.range(0,2);
        q.orderBy(candidate.dni.asc());
        q.executeList();
    }


}