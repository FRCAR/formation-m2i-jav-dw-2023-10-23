package com.bigcorp.booking.correction.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.openejb.testing.SingleApplicationComposerRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bigcorp.app.correction.dao.RestaurantCorrectionDao;
import com.bigcorp.app.correction.dao.RestaurantTypeCorrectionDao;
import com.bigcorp.app.correction.model.Restaurant;
import com.bigcorp.app.correction.model.RestaurantType;
import com.bigcorp.app.correction.model.TypePrix;

import jakarta.inject.Inject;
import junit.framework.TestCase;

@RunWith(SingleApplicationComposerRunner.class)
public class RestaurantDaoTest extends TestCase {

	@Inject
	private RestaurantCorrectionDao restaurantDao;

	@Inject
	private RestaurantTypeCorrectionDao restaurantTypeDao;

	@Test
	public void testSaveAndFindById() throws Exception {
		
		Assert.assertNull(this.restaurantDao.findById(0l));
		
		Restaurant restaurant = new Restaurant();
		restaurant.setName("testRestau1");
		restaurant.setTypePrix(TypePrix.MOYEN);
		restaurant.setDateCreation(LocalDate.now());
		restaurant.setActive(Boolean.TRUE);
		restaurant.setScore(-56);
		restaurant.setPhone("0601020304");
		restaurant = this.restaurantDao.merge(restaurant);

		Restaurant savedRestaurant = this.restaurantDao.findById(restaurant.getId());

		Assert.assertNotNull(savedRestaurant);
		Assert.assertEquals(restaurant.getName(), savedRestaurant.getName());
		Assert.assertEquals(restaurant.getTypePrix(), savedRestaurant.getTypePrix());
		Assert.assertEquals(restaurant.getDateCreation(), savedRestaurant.getDateCreation());
		Assert.assertEquals(restaurant.getActive(), savedRestaurant.getActive());
		Assert.assertEquals(restaurant.getScore(), savedRestaurant.getScore());
		Assert.assertEquals(restaurant.getPhone(), savedRestaurant.getPhone());
	}

	@Test
	public void testSaveRemove() throws Exception {
		Restaurant restaurant = new Restaurant();
		restaurant.setName("testRestau2");
		restaurant = this.restaurantDao.merge(restaurant);
		this.restaurantDao.delete(restaurant.getId());
		Restaurant savedRestaurant = this.restaurantDao.findById(restaurant.getId());
		Assert.assertNull(savedRestaurant);
	}

	@Test
	public void testSaveAndFindByName() throws Exception {
		Restaurant restaurant = new Restaurant();
		String name = "testRestaurFindByName";
		restaurant.setName(name);
		restaurant = this.restaurantDao.merge(restaurant);

		List<Restaurant> restaurants = this.restaurantDao.findByName("testRestaurFindByName");

		Assert.assertEquals(1, restaurants.size());

	}

	@Test
	public void testSaveAndFindByNameLikeCaseInsensitive() throws Exception {
		Assert.assertNull(this.restaurantDao.findById(0l));
		Restaurant restaurant = new Restaurant();
		String name = "testRestaurFindByNameLikeCaseInsensitive";
		restaurant.setName(name);
		restaurant = this.restaurantDao.merge(restaurant);

		List<Restaurant> restaurants = this.restaurantDao.findByNameLikeCaseInsensitive("NAMElIKeCASE");

		Assert.assertEquals(1, restaurants.size());
	}
	
	@Test
	public void testSaveWithRestaurantType() throws Exception {
		
		RestaurantType restaurantType = new RestaurantType();
		restaurantType.setName("typeRestaurant 1");
		restaurantType = this.restaurantTypeDao.merge(restaurantType);
		
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantType(restaurantType);
		restaurant = this.restaurantDao.merge(restaurant);
		
		Restaurant savedRestaurant = this.restaurantDao.findById(restaurant.getId());

		Assert.assertNotNull(savedRestaurant.getRestaurantType());
		Assert.assertEquals(restaurantType.getName(), savedRestaurant.getRestaurantType().getName());
	}

}