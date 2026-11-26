package restfulApiDev;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class APIDevTest {


    Playwright playwright;
    APIRequestContext requestContext;


    @Test
    public void testRestFulAPIPost() throws IOException {
        playwright = Playwright.create();
        requestContext = playwright.request().newContext();

        String addProductPayload = Files.readString(Path.of("src/test/resources/addProduct.json"));
        APIResponse addProductResponseObject = requestContext.post("https://api.restful-api.dev/objects",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(addProductPayload));

        String addProductResponse = addProductResponseObject.text();
        System.out.println("Add Product Response: " + addProductResponse);


        JsonObject jsonObject = JsonParser.parseString(addProductResponse).getAsJsonObject();
        JsonObject dataObject = jsonObject.getAsJsonObject("data");


        String id = jsonObject.get("id").getAsString();
        String created = jsonObject.get("createdAt").getAsString();
        String name = jsonObject.get("name").getAsString();
        String year = dataObject.get("year").getAsString();
        String model = dataObject.get("CPU model").getAsString();

        System.out.println("id: " + id);
        System.out.println("Year: " + year);
        System.out.println("created: " + created);
        System.out.println("name: " + name);
        System.out.println("model: " + model);


        APIResponse getResponse = requestContext.get("https://api.restful-api.dev/objects/" + id,
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json"));

        System.out.println("Get Response: " + getResponse.text());

        APIResponse getAllResponse = requestContext.get("https://api.restful-api.dev/objects/",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json"));

        System.out.println("Get All Response: " + getAllResponse.text());


        APIResponse changeProductObject = requestContext.put("https://api.restful-api.dev/objects/" + id,
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(Files.readString(Path.of("src/test/resources/changeProduct.json"))));

        String changeProduct = changeProductObject.text();

        JsonObject changeProductJsonObject = JsonParser.parseString(changeProduct).getAsJsonObject();
        String updated = changeProductJsonObject.get("updatedAt").getAsString();
        System.out.println("Updated: " + updated);
        Assert.assertEquals(changeProductJsonObject.get("name").getAsString(), "Test New Product");
    }


}
