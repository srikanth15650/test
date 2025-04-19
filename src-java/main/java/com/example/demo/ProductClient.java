//package com.example.demo;
//
//import ch.qos.logback.core.joran.spi.HttpUtil;
//
//@FeignClient(name = "product-client", url="http://localhost:8081/product/", configuration = FeignConfig.class)
//public interface ProductClient {
//    @RequestMapping(value = "{id}", method = HttpUtil.RequestMethod.GET")
//    Product getProduct(@PathVariable(value = "id") String id);
//}
