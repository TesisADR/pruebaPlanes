package domainapp.modules.simple.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;

@Column(length = Plan.MAX_LEN, allowsNull = "false")
@Property(maxLength = Plan.MAX_LEN)
@Parameter(maxLength = Plan.MAX_LEN)
@ParameterLayout(named = "Plan")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Plan {

    int MAX_LEN = 40;

    /*
public enum Plan {
    MIL,
    TRESMIL,
    CINCOMIL,
}*/
}
