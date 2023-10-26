package com.bigcorp.booking.service;

import org.apache.openejb.testing.SingleApplicationComposerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bigcorp.app.correction.service.RestaurantCorrectionService;

import jakarta.inject.Inject;
import junit.framework.TestCase;

@RunWith(SingleApplicationComposerRunner.class)
public class RestaurantServiceTest extends TestCase {

	@Inject
	private RestaurantCorrectionService restaurantService;

    @Test
    public void test() throws Exception {
    	System.out.println(restaurantService.getRestaurantDao().getMessage());
    }
}