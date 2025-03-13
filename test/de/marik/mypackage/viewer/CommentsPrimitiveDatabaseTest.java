package de.marik.mypackage.viewer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommentsPrimitiveDatabaseTest {

	@Test
	void testGetList() {
		assertTrue((new CommentsPrimitiveDatabase()).getList().size() > 0);
	}
}
