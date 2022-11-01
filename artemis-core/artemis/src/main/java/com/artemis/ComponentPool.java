package com.artemis;

import com.artemis.annotations.InitialPoolSize;
import com.artemis.utils.Bag;
import com.artemis.utils.reflect.ClassReflection;
import com.artemis.utils.reflect.ReflectionException;

public class ComponentPool<T extends PooledComponent> {
	private final Bag<T> cache;
	private Class<T> type;

	ComponentPool(Class<T> type) {
		this.type = type;
		cache = new Bag<T>(type);

		for(int i = initialPoolSize(); i > 0; i--) {
			try {
				cache.add(ClassReflection.newInstance(type));
			} catch (ReflectionException e) {
				throw new InvalidComponentException(type, e.getMessage(), e);
			}
		}
	}

	private int initialPoolSize() {
		if(type.isAnnotationPresent(InitialPoolSize.class)) {
			final InitialPoolSize initialPoolSize = type.getAnnotation(InitialPoolSize.class);
			return initialPoolSize.size();
		}
		//Default behavior
		return 0;
	}

	@SuppressWarnings("unchecked")
	<T extends PooledComponent> T obtain() {
		try {
			return (T) ((cache.size() > 0)
				? cache.removeLast()
				: ClassReflection.newInstance(type));
		} catch (ReflectionException e) {
			throw new InvalidComponentException(type, e.getMessage(), e);
		}
	}

	void free(T component) {
		component.reset();
		cache.add(component);
	}

	int cacheSize() {
		return cache.size();
	}
}
