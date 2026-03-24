package tinyspring;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// @Retention(RUNTIME) means Keep this annotation in the bytecode
// so Spring can read it at runtime using reflection

@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

}

