package com.apis.fakestore.services;

import com.apis.fakestore.dtos.FakeStoreProductDto;
import com.apis.fakestore.exceptions.ProductNotFoundException;
import com.apis.fakestore.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service // either compononet or service to create an object of this servoce
public class FakeStoreProductService  implements  ProductService{
    private  RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //call fakestore api to get product with given id
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        // covert fakestore dto to product object
        if(fakeStoreProductDto == null){
           // return null;
            throw new ProductNotFoundException("");
        }

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
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

}
