package domainapp.modules.simple.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;

@Column(length = FechaNacimiento.MAX_LEN, allowsNull = "false")
@Property(maxLength = FechaNacimiento.MAX_LEN)
@Parameter(maxLength = FechaNacimiento.MAX_LEN)
@ParameterLayout(named = "FechaNacimiento")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaNacimiento {

    int MAX_LEN = 10;
}