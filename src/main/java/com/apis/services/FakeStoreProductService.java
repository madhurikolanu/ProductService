package com.apis.services;

import com.apis.configs.RedisTemplateConfig;
import com.apis.dtos.FakeStoreProductDto;
import com.apis.exceptions.ProductNotFoundException;
import com.apis.models.Product;
import com.apis.projections.ProductWithTitleDescription;
import com.apis.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("fakeStoreService") // either compononet or service to create an object of this servoce
public class FakeStoreProductService  implements  ProductService{
    private  RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate){
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
       // product.setCategory(fakeStoreProductDto.getCategory());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
    @Override
    public Optional<Product> getProductById(Long id) throws ProductNotFoundException {

        // get the product data from redis cache if available

        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_" + id);

        if(product != null){
            return Optional.of(product);
        }
        //call fakestore api to get product with given id
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        // covert fakestore dto to product object
        if(fakeStoreProductDto == null){
           // return null;
            throw new ProductNotFoundException("");
        }

        product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

        // store data into redis cache
        // here redis cache is volatile in nature once this application server is restarted then data will be wiped off
        //key
        //hash key
        //hash value
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_" + id, product);
        return Optional.of(product);
    }

    @Override
    public List<Product> getAllProducts() {
        //List.class - at rumtime it will convert to map because runtime every type converts into object
        // generics have only scope till compile time
        // arrays do not use generics
        //List<FakeStoreProductDto> fakeStoreProductDtoList = restTemplate.getForObject("https://fakestoreapi.com/products", List.class);
        FakeStoreProductDto[] fakeStoreProductDtoList = restTemplate.
                getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoList){
            productList.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImage());

        // instead of thie we can use put and get methods but to reduce calling 2 apis we use httpentitycallback
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDtoResponse = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponse);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public ProductWithTitleDescription someRandomQuery(Long id) {
        return null;
    }

    @Override
    public ProductWithTitleDescription sqlQuery(Long id) {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        return null;
    }

}
