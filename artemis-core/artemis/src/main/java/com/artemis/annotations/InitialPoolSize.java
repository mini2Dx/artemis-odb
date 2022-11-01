package com.artemis.annotations;

import java.lang.annotation.*;

/**
 * Specifies the initial pool size for component pools
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface InitialPoolSize {

	/**
	 * The initial amount of instances created. Defaults to <code>1</code>.
	 */
	int size() default 1;
}
