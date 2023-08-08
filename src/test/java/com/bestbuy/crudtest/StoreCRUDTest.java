package com.bestbuy.crudtest;

import com.bestbuy.bestbuysteps.StoreSteps;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)

public class StoreCRUDTest extends StoreSteps {
    static String name = "Prime Store" + TestUtils.getRandomValue();
    static String type = "power" + TestUtils.getRandomValue();
    static String address = "Lisbon Rua" + TestUtils.getRandomValue();
    static String address2 = "Rua James" + TestUtils.getRandomValue();
    static String city = "Lisbon" + TestUtils.getRandomValue();
    static String state = "Lisboa" + TestUtils.getRandomValue();
    static String zip = "XY1 1YX" + TestUtils.getRandomValue();
    static int lat = 55;
    static int lng = 89;
    static String hours = "Mon : 10-9; Tue : 10-9; Wed : 10-9; Thu : 10-9; Fri : 10-9;Sat : 10-9; Sun: 10-6";
    static int storeId;

    StoreSteps storesteps;

    @Title("This will create new store")
    @Test
    public void test001() {
        storesteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(201);
    }
    @Title("Verify if the store was added successfully")
    @Test
    public void test002() {

        HashMap<String, Object> storeMap = storesteps.getStoreInformationByName(name);
        Assert.assertThat(storeMap, hasValue(name));
        storeId = (int) storeMap.get("id");
    }
    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {

        name = name + "_updated";

        storesteps.updateStore(storeId,name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);

        HashMap<String, Object> storeMap = storesteps.getStoreInformationByName(name);
        Assert.assertThat(storeMap, hasValue(name));
    }
    @Title("Delete the store and verify if the store is deleted")
    @Test
    public void test004() {

        storesteps.deleteStore(storeId).statusCode(204);

        storesteps.getStoreById(storeId).statusCode(404);

    }

}
