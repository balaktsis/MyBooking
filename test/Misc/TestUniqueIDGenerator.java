package Misc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUniqueIDGenerator {

    @Before
    public void before(){
        UniqueIDGenerator.setSequentialId(0);
    }

    @Test
    public void UniqueIDGeneratorTest(){
        assertEquals("0", UniqueIDGenerator.getUniqueId());

        UniqueIDGenerator.setSequentialId(15L);
        assertEquals(15L, UniqueIDGenerator.getSequentialId());

    }


}
