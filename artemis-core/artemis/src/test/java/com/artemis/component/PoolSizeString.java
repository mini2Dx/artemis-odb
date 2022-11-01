/**
 * Copyright 2022 Viridian Software Ltd.
 */
package com.artemis.component;

import com.artemis.ComponentPoolTest;
import com.artemis.PooledComponent;
import com.artemis.annotations.InitialPoolSize;

@InitialPoolSize(size = ComponentPoolTest.POOL_SIZE)
public class PoolSizeString extends PooledComponent {
	public String s;

	@Override
	protected void reset() {

	}
}
