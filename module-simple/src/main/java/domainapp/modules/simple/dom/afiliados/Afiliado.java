package domainapp.modules.simple.dom.afiliados;

import java.util.Comparator;

import javax.inject.Inject;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;
import static org.apache.isis.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Notes;
import domainapp.modules.simple.types.Apellido;
import domainapp.modules.simple.types.Dni;
import domainapp.modules.simple.types.FechaNacimiento;
import domainapp.modules.simple.types.Edad;
import domainapp.modules.simple.types.LugarNacimiento;
import domainapp.modules.simple.types.Telefono;
import domainapp.modules.simple.types.FechaInicio;
import domainapp.modules.simple.types.Plan;

@javax.jdo.annotations.PersistenceCapable(
        schema = "afiliadoapp",
        identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Unique(
        name = "Afiliado_dni_UNQ", members = {"dni"}
)
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = Afiliado.NAMED_QUERY__FIND_BY_DNI_LIKE,
                value = "SELECT " +
                        "FROM domainapp.modules.simple.dom.afiliados.Afiliado " +
                        "WHERE name.indexOf(:dni) >= 0"
        ),
        @javax.jdo.annotations.Query(
                name = Afiliado.NAMED_QUERY__FIND_BY_DNI_EXACT,
                value = "SELECT " +
                        "FROM domainapp.modules.simple.dom.so.SimpleObject " +
                        "WHERE name == :name"
        )
})
@javax.jdo.annotations.DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY, column="id")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@DomainObject(logicalTypeName = "afiliadoapp.Afiliado", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Afiliado implements Comparable<Afiliado> {

    static final String NAMED_QUERY__FIND_BY_DNI_LIKE = "Afiliado.findByNameLike";
    static final String NAMED_QUERY__FIND_BY_DNI_EXACT = "Afiliado.findByNameExact";

    @Inject RepositoryService repositoryService;
    @Inject TitleService titleService;
    @Inject MessageService messageService;

    public static Afiliado withName(String name, String apellido, String dni, String edad,
                                    String fechaNacimiento, String lugarNacimiento, String telefono,
                                    String fechaInicio, String plan) {
        val afiliado = new Afiliado();
        afiliado.setName(name);
        afiliado.setApellido(apellido);
        afiliado.setDni(dni);
        afiliado.setEdad(edad);
        afiliado.setFechaNacimiento(fechaNacimiento);
        afiliado.setLugarNacimiento(lugarNacimiento);
        afiliado.setTelefono(telefono);
        afiliado.setFechaInicio(fechaInicio);
        afiliado.setPlan(plan);
        return afiliado;
    }

    @Inject RepositoryService repositoryService;
    @Inject TitleService titleService;
    @Inject MessageService messageService;



    @Title
    @Name
    @Getter @Setter @ToString.Include
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    private String name;

    @Notes
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "2")
    private String notes;


    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @ActionLayout(associateWith = "name", promptStyle = PromptStyle.INLINE)
    public SimpleObject updateName(
            @Name final String name) {
        setName(name);
        return this;
    }
    public String default0UpdateName() {
        return getName();
    }
    public String validate0UpdateName(String newName) {
        for (char prohibitedCharacter : "&%$!".toCharArray()) {
            if( newName.contains(""+prohibitedCharacter)) {
                return "Caracter '" + prohibitedCharacter + "' no esta permitido.";
            }
        }
        return null;
    }


    @Action(semantics = NON_IDEMPOTENT_ARE_YOU_SURE)
    @ActionLayout(
            position = ActionLayout.Position.PANEL,
            describedAs = "Deletes this object from the persistent datastore")
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.removeAndFlush(this);
    }



    private final static Comparator<SimpleObject> comparator =
            Comparator.comparing(SimpleObject::getName);

    @Override
    public int compareTo(final SimpleObject other) {
        return comparator.compare(this, other);
    }

}
