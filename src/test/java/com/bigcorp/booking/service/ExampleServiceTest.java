package com.bigcorp.booking.service;

import org.apache.openejb.testing.SingleApplicationComposerRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bigcorp.app.model.Example;
import com.bigcorp.app.service.ExampleService;

import jakarta.inject.Inject;
import junit.framework.TestCase;

@RunWith(SingleApplicationComposerRunner.class)
public class ExampleServiceTest extends TestCase {
	  
    @Inject
    private ExampleService exampleService;

    @Test
    public void test() throws Exception {
        Example example = new Example();
        example = this.exampleService.save(example);
        Assert.assertNotNull(example.getId());
        example = this.exampleService.findById(example.getId());
        Assert.assertNotNull(example);
        this.exampleService.removeById(example.getId());
        example = this.exampleService.findById(example.getId());
        Assert.assertNull(example);
    }
}