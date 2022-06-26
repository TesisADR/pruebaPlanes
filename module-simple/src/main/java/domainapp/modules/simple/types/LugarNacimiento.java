package domainapp.modules.simple.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;

@Column(length = LugarNacimiento.MAX_LEN, allowsNull = "false")
@Property(maxLength = LugarNacimiento.MAX_LEN)
@Parameter(maxLength = LugarNacimiento.MAX_LEN)
@ParameterLayout(named = "LugarNacimiento")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LugarNacimiento {

    int MAX_LEN = 40;
}