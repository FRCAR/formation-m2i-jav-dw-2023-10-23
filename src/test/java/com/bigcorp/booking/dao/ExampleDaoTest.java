package com.bigcorp.booking.dao;

import org.apache.openejb.testing.SingleApplicationComposerRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bigcorp.app.dao.ExampleDao;
import com.bigcorp.app.model.Example;

import jakarta.inject.Inject;
import junit.framework.TestCase;

@RunWith(SingleApplicationComposerRunner.class)
public class ExampleDaoTest extends TestCase {
	  
    @Inject
    private ExampleDao exampleDao;

    @Test
    public void test() throws Exception {
        Example example = new Example();
        example = this.exampleDao.merge(example);
        Assert.assertNotNull(example.getId());
        example = this.exampleDao.findById(example.getId());
        Assert.assertNotNull(example);
        this.exampleDao.remove(example.getId());
        example = this.exampleDao.findById(example.getId());
        Assert.assertNull(example);
    }
}