package ru.ussgroup.mysnowflake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class SnowFlakeIdGeneratorTest {
    @Test
    public void shouldGenerateOrderedLongs() throws InterruptedException {
        SnowFlakeIdGenerator g = new SnowFlakeIdGenerator(1000, 1);
        
        System.out.println("Sequence max: " + g.getSequenceMax());
        
        List<Long> list = new ArrayList<>();
        
        Random rnd = new Random();
        
        for (int i = 0; i < 100; i++) {
            int randomInt = rnd.nextInt(g.getSequenceMax() * 2);
            
            for (int j = 0; j < randomInt; j++) {
                list.add(g.generateLongId());
            }
            
            Thread.sleep(rnd.nextInt(100));
        }
        
        List<Long> expectedList = list.stream().sorted().collect(Collectors.toList());
        
        Assert.assertEquals(expectedList, list);
        Assert.assertTrue(g.getSequenceOverflowCount() > 0);
        
        System.out.println("Generated count: " + list.size());
        System.out.println("Sequence overflow count: " + g.getSequenceOverflowCount());
        System.out.println("Time shifts: " + g.getTimeShiftCount());
    }
}
