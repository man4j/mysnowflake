package ru.ussgroup.mysnowflake;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class SnowFlakeIdGeneratorTest {

	@Test
	public void getPositiveId() throws Exception {
		SnowFlakeIdGenerator generator = new SnowFlakeIdGenerator(10, 1);
		long id = generator.generateLongId();
		Assert.assertTrue(id > 0);
	}

	@Test
	public void uniqueIds() throws Exception {
		SnowFlakeIdGenerator generator = new SnowFlakeIdGenerator(100, 1);
		Set<Long> setId = new TreeSet<Long>();

		for (int i = 0; i < 100000; i++) {
			setId.add(generator.generateLongId());
		}

		Assert.assertTrue(setId.size() == 100000);
	}

	@Test(expected = IllegalStateException.class)
	public void maxClientIsNull() throws Exception {
		new SnowFlakeIdGenerator(0, 1);
	}

	@Test(expected = IllegalStateException.class)
	public void maxClientIsNegative() throws Exception {
		new SnowFlakeIdGenerator(-1, 1);
	}

	@Test(expected = IllegalStateException.class)
	public void maxClientIsTooBig() throws Exception {
		new SnowFlakeIdGenerator(5000, 1);
	}

	@Test(expected = IllegalStateException.class)
	public void clientIdNegative() throws Exception {
		new SnowFlakeIdGenerator(10, -1);
	}

	@Test(expected = IllegalStateException.class)
	public void clientIdEqualsMaxClient() throws Exception {
		new SnowFlakeIdGenerator(100, 7937428987544863821L);
	}

	@Test
	public void clientIdNull() throws Exception {
		long id = new SnowFlakeIdGenerator(10, 0).generateLongId();
		Assert.assertTrue(id >= 0);
	}

}
