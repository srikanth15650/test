
//import io.github.resilience4j.bulkhead.Bulkhead;
//import io.github.resilience4j.bulkhead.BulkheadConfig;
//import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.*;

@SpringBootApplication
@EnableEurekaServer
public class DemoApplication {
	@Component
	@KafkaListener(id = "multiGroup", topics = "multitype")
	public class MultiTypeKafkaListener {

		@KafkaHandler
		public void handleGreeting(Greeting greeting) {
			System.out.println("Greeting received: " + greeting);
		}

		@KafkaHandler
		public void handleF(Farewell farewell) {
			System.out.println("Farewell received: " + farewell);
		}

		@KafkaHandler(isDefault = true)
		public void unknown(Object object) {
			System.out.println("Unkown type received: " + object);
		}
	}
	@Autowired
	private static CircuitBreakerFactory circuitBreakerFactory;
	@Autowired
	public static RemoteService service;
@Autowired
static KafkaTemplate<String, Greeting> greetingKafkaTemplate;

@Autowired
static KafkaTemplate<String,Object> multiTypeKafkaTemplate;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
		String topicName = null;
		kafkaTemplate.send(topicName, msg);
	}

	public class Product{
		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		Long productId;
		Integer quantity;
		Integer stock;

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Integer getStock() {
			return stock;
		}

		public void setStock(Integer stock) {
			this.stock = stock;
		}
	}
	enum OrderStatus {
		SUCCESS,
		FAILURE;
	}

	//@Data
	class Order{
		public OrderStatus getOrderStatus() {
			return orderStatus;
		}

		public Order setOrderStatus(OrderStatus orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}

		OrderStatus orderStatus;
		List<Product> getLineItems(){
			return null;
		}

		public String getShippingAddress() {
			return shippingAddress;
		}

		public Order setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
			return this;
		}

		public LocalDate getShippingDate() {
			return shippingDate;
		}

		public Order setShippingDate(LocalDate shippingDate) {
			this.shippingDate = shippingDate;
			return this;
		}

		String shippingAddress;
		LocalDate shippingDate;
	}


	interface ProductRepository extends org.springframework.data.repository.CrudRepository<Product, Long> {}
	ProductRepository productRepository;
	interface ProductService{
		List<Product> getProducts();
		Order handleOrder(Order order);
		Order revertOrder(Order order);
	}

	ProductService productService = new ProductService() {
		@Override
		public List<Product> getProducts() {
			return List.of();
		}

		@Override
		public Order handleOrder(Order order) {
			return null;
		}

		@Override
		public Order revertOrder(Order order) {
			return null;
		}
	};
	interface ProductService2{
		Flux<Product> getAllProducts2();
		Mono<Order> handleOrder2(Order order);
		Mono<Order> revertOrder2(Order order);
	}


	ProductService2 productService2 = new ProductService2() {
		@Override
		public Flux<Product> getAllProducts2() {
			return Flux.just(new Product(), new Product());
		}

		@Override
		public Mono<Order> handleOrder2(Order order) {
			return null;
		}

		@Override
		public Mono<Order> revertOrder2(Order order) {
			return null;
		}
	};
//	@Transactional
//	public Mono<Order> handleOrder2(Order order) {
//		return Flux.fromIterable(order.getLineItems())
//				.flatMap((com.example.demo.DemoApplication.Product l) -> {
//                    return productRepository.findById(l.getProductId());
//                })
//				.flatMap(p -> {
//					int q = order.getLineItems().stream()
//							.filter(l -> l.getProductId().equals(p.getId()))
//							.findAny().get()
//							.getQuantity();
//					if (p.getStock() >= q) {
//						p.setStock(p.getStock() - q);
//						return productRepository.save(p);
//					} else {
//						return Mono.error(new RuntimeException("Product is out of stock: " + p.getId()));
//					}
//				})
//				.then(Mono.just(order.setOrderStatus("SUCCESS")));
//	}

//	@Transactional
//	public Mono<Order> revertOrder(Order order) {
//		return Flux.fromIterable(order.getLineItems())
//				.flatMap(l -> productRepository.findById(l.getProductId()))
//				.flatMap(p -> {
//					int q = order.getLineItems().stream()
//							.filter(l -> l.getProductId().equals(p.getId()))
//							.findAny().get()
//							.getQuantity();
//					p.setStock(p.getStock() + q);
//					return productRepository.save(p);
//				})
//				.then(Mono.just(order.setOrderStatus("SUCCESS")));
//	}

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getProducts();
	}

	@PostMapping
	public Order processOrder(@RequestBody Order order) {
		return productService.handleOrder(order);
	}

	@DeleteMapping
	public Order revertOrder3(@RequestBody Order order) {
		return productService.revertOrder(order);
	}

	@Transactional
	public Order handleOrder(Order order) {
		order.getLineItems()
				.forEach(l -> {
					Product p = productRepository.findById(l.getProductId())
							.orElseThrow(() -> new RuntimeException("Could not find the product: " + l.getProductId()));
					if (p.getStock() >= l.getQuantity()) {
						p.setStock(p.getStock() - l.getQuantity());
						productRepository.save(p);
					} else {
						throw new RuntimeException("Product is out of stock: " + l.getProductId());
					}
				});
		return order.setOrderStatus(OrderStatus.SUCCESS);
	}

	@Transactional
	public Order revertOrder(Order order) {
		order.getLineItems()
				.forEach(l -> {
					Product p = productRepository.findById(l.getProductId())
							.orElseThrow(() -> new RuntimeException("Could not find the product: " + l.getProductId()));
					p.setStock(p.getStock() + l.getQuantity());
					productRepository.save(p);
				});
		return order.setOrderStatus(OrderStatus.SUCCESS);
	}

	  interface ShippingService{
		Order handleOrder(Order order);
	}

	interface  ShipmentRepository extends org.springframework.data.repository.CrudRepository{}

	ShipmentRepository shipmentRepository;

	@Data
	class Shipment{
		public String getAddress() {
			return address;
		}

		public Shipment setAddress(String address) {
			this.address = address;
			return this;
		}

		public LocalDate getShippingDate() {
			return shippingDate;
		}

		public Shipment setShippingDate(LocalDate shippingDate) {
			this.shippingDate = shippingDate;
			return this;
		}

		String address;
		LocalDate shippingDate;
	}
	ShippingService shippingService  = new ShippingService() {
		@Override
		public Order handleOrder(Order order) {
			LocalDate shippingDate = null;
			if (LocalTime.now().isAfter(LocalTime.parse("10:00"))
					&& LocalTime.now().isBefore(LocalTime.parse("18:00"))) {
				shippingDate = LocalDate.now().plusDays(1);
			} else {
				throw new RuntimeException("The current time is off the limits to place order.");
			}
			shipmentRepository.save(new Shipment()
					.setAddress(order.getShippingAddress())
					.setShippingDate(shippingDate));
			return order.setShippingDate(shippingDate)
					.setOrderStatus(OrderStatus.SUCCESS);
		}
	};


	interface OrderService{
		public Order createOrder(Order order);
		List<Order> getOrders();
	}
	interface OrderRepository extends CrudRepository<Order, Long> {}
	OrderRepository orderRepository = null;
	RestTemplate restTemplate= null;
	String inventoryServiceUrl = null;
	String shippingServiceUrl;
	OrderService orderService = new OrderService() {
		@Override
		public Order createOrder(Order order) {
			boolean success = true;
			Order savedOrder = orderRepository.save(order);
			Order inventoryResponse = null;
			try {
				inventoryResponse = restTemplate.postForObject(
						inventoryServiceUrl, order, Order.class);
			} catch (Exception ex) {
				success = false;
			}
			Order shippingResponse = null;
			try {
 				shippingResponse = restTemplate.postForObject(
						shippingServiceUrl, order, Order.class);
			} catch (Exception ex) {
				success = false;
				HttpEntity<Order> deleteRequest = new HttpEntity<>(order);
				ResponseEntity<Order> deleteResponse = restTemplate.exchange(
						inventoryServiceUrl, HttpMethod.DELETE, deleteRequest, Order.class);
			}
			if (success) {
				savedOrder.setOrderStatus(OrderStatus.SUCCESS);
				savedOrder.setShippingDate(shippingResponse.getShippingDate());
			} else {
				savedOrder.setOrderStatus(OrderStatus.FAILURE);
			}
			return orderRepository.save(savedOrder);
		}

		@Override
		public List<Order> getOrders() {
			return List.of();
		}
	};

	@GetMapping
	public Flux<Product> getAllProducts2() {
		return productService2.getAllProducts2();
	}

	@PostMapping
	public Mono<Order> processOrder2(@RequestBody Order order) {
		return productService2.handleOrder2(order);
	}

	@DeleteMapping
	public Mono<Order> revertOrder2(@RequestBody Order order) {
		return productService2.revertOrder2(order);
	}
	@PostMapping
	public Order create(@RequestBody Order order) {
		Order processedOrder = orderService.createOrder(order);
		if (OrderStatus.FAILURE.equals(processedOrder.getOrderStatus())) {
			throw new RuntimeException("Order processing failed, please try again later.");
		}
		return processedOrder;
	}
	@GetMapping
	public List<Order> getAll() {
		return orderService.getOrders();
	}
	@PostMapping
	public Order process(@RequestBody Order order) {
		return shippingService.handleOrder(order);
	}


	public void sendMessage2(String message) {
		String topicName = null;
		CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println("Sent message=[" + message +
						"] with offset=[" + result.getRecordMetadata().offset() + "]");
			} else {
				System.out.println("Unable to send message=[" +
						message + "] due to : " + ex.getMessage());
			}
		});
	}

	@KafkaListener(topicPartitions
			= @TopicPartition(topic = "topicName", partitions = { "0", "1" }))
	public void listenGroupFoo1(String message) {
		System.out.println("Received Message in group foo: " + message);
	}


	@KafkaListener(topics = "topicName", groupId = "foo")
	public void listenGroupFoo(String message) {
		System.out.println("Received Message in group foo: " + message);
	}

	@KafkaListener(topics = "topic1, topic2", groupId = "foo")
	public void listenGroupFoo2(String message) {
		System.out.println("Received Message in group foo: " + message);
	}

	@KafkaListener(topics = "topicName")
	public void listenWithHeaders(
			@org.springframework.messaging.handler.annotation.Payload String message,
			//@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
			@Header(KafkaHeaders.PARTITION) int partition) {
		System.out.println(
				"Received Message: " + message
						+ "from partition: " + partition);
	}

	@KafkaListener(
			topicPartitions = @TopicPartition(topic = "topicName",
					partitionOffsets = {
							@PartitionOffset(partition = "0", initialOffset = "0"),
							@PartitionOffset(partition = "3", initialOffset = "0")}),
			containerFactory = "partitionsKafkaListenerContainerFactory")
	public void listenToPartition(
			@Payload String message,
			@Header(KafkaHeaders.PARTITION) int partition) {
		System.out.println(
				"Received Message: " + message
						+ "from partition: " + partition);
	}

	@KafkaListener(
			topics = "topicName",
			containerFactory = "filterKafkaListenerContainerFactory")
	public void listenWithFilter(String message) {
		System.out.println("Received Message in filtered listener: " + message);
	}
	@KafkaListener(
			topics = "topicName",
			containerFactory = "greetingKafkaListenerContainerFactory")
	public void greetingListener(Greeting greeting) {
		// process greeting message
	}
	public static void main(String[] args) throws Exception {



		CircuitBreakerRegistry circuitBreakerRegistry
				= CircuitBreakerRegistry.ofDefaults();
		Builder circuitBreakerConfigBuilder = custom();
		CircuitBreakerConfig config = custom()
				.failureRateThreshold(20)
				 .slidingWindow(5,20,SlidingWindowType.COUNT_BASED)//
				.build();
		SpringApplication.run(DemoApplication.class, args);

		CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
		CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
		Function<Integer, Integer> decorated = CircuitBreaker
				.decorateFunction(circuitBreaker, service::process);



		RateLimiterConfig config2 = RateLimiterConfig.custom().limitForPeriod(2).build();
		RateLimiterRegistry registry2 = RateLimiterRegistry.of(config2);
		RateLimiter rateLimiter = registry2.rateLimiter("my");
		Function<Integer, Integer> decorated2
				= RateLimiter.decorateFunction(rateLimiter, service::process);

/*		BulkheadConfig config3 = BulkheadConfig.custom().maxConcurrentCalls(1).build();
		BulkheadRegistry registry3 = BulkheadRegistry.of(config3);
		Bulkhead bulkhead = registry3.bulkhead("my");
		Function<Integer, Integer> decorated3
				= Bulkhead.decorateFunction(bulkhead, service::process);
				*/


		CountDownLatch latch = new CountDownLatch(1);
		latch.countDown();
		Thread.currentThread().join();
		/*decorated3.apply(1);*/
		latch.await();


		RetryConfig config4 = RetryConfig.custom().maxAttempts(2).build();
		RetryRegistry registry4 = RetryRegistry.of(config4);
		Retry retry4 = registry4.retry("my");
		Function<Integer, Void> decorated4
				= Retry.decorateFunction(retry4, (Integer s) -> {
			service.process(s);
			return null;
		});
		decorated.apply(1);
//		javax.cache.Cache cache = ...; // Use appropriate cache here
//		Cache<Integer, Integer> cacheContext = Cache.of(cache);
//		Function<Integer, Integer> decorated
//				= Cache.decorateSupplier(cacheContext, () -> service.process(1));


		long ttl = 1;
		TimeLimiterConfig config5
				= TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(ttl)).build();
		TimeLimiter timeLimiter = TimeLimiter.of(config5);
		Future futureMock = null;
		Callable restrictedCall
				= TimeLimiter.decorateFutureSupplier(timeLimiter, () -> futureMock);
		restrictedCall.call();
		Callable chainedCallable
				= CircuitBreaker.decorateCallable(circuitBreaker, restrictedCall);


		org.springframework.cloud.client.circuitbreaker.CircuitBreaker circuitbreaker2 = circuitBreakerFactory.create("circuitbreaker");
		String url = "http://localhost:1234/not-real";
		circuitbreaker2.run(() -> new RestTemplate().getForObject(url, String.class),
				throwable -> "getDefaultAlbumList()");



		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
				.failureRateThreshold(50)
				.waitDurationInOpenState(Duration.ofMillis(1000))
				.slidingWindowSize(2)
				.build();
		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
				.timeoutDuration(Duration.ofSeconds(4))
				.build();




		IntervalFunction intervalFn = IntervalFunction.ofExponentialBackoff(1000, 2);
		RetryConfig retryConfig = RetryConfig.custom()
				.maxAttempts(5)
				.intervalFunction(intervalFn)
				.build();

		CircuitBreakerConfig config6 = CircuitBreakerConfig.custom()
				.failureRateThreshold(50) // Trip if 50% of requests fail
				.waitDurationInOpenState(Duration.ofSeconds(30)) // Interval = 30s
				.permittedNumberOfCallsInHalfOpenState(3) // Allow 3 test calls
				.build();

//
//		HystrixCommandProperties.Setter()
//				.withCircuitBreakerSleepWindowInMilliseconds(10000) // Interval = 10s
//				.withCircuitBreakerRequestVolumeThreshold(20);

		IntervalFunction intervalF2n = IntervalFunction.ofExponentialBackoff(1000, 2);
		RetryConfig retryConfig2 = RetryConfig.custom()
				.maxAttempts(5)
				.intervalFunction(intervalF2n)
				.build();

		Retry retry = Retry.of("paymentRetry", retryConfig2);
//
//		when(paymentService.process(1)).thenThrow(new RuntimeException("First Failure"))
//				.thenThrow(new RuntimeException("Second Failure"))
//				.thenReturn("Success");


		Callable<String> decoratedCallable = Retry.decorateCallable(
				retry, () -> paymentService.processPayment(1)
		);

		try {
			String result = decoratedCallable.call();
		} catch (Exception ignored) {
		}

		CircuitBreakerConfig circuitBreakerConfig2 = CircuitBreakerConfig.custom()
				.failureRateThreshold(50)
				.slidingWindowSize(5)
				.permittedNumberOfCallsInHalfOpenState(3)
				.build();

		CircuitBreaker circuitBreaker2 = CircuitBreaker.of("paymentCircuitBreaker", circuitBreakerConfig2);

		Callable<String> decoratedCallable2 = CircuitBreaker.decorateCallable(
				circuitBreaker2, () -> paymentService.processPayment(1)
		);

		for (int i = 0; i < 10; i++) {
			try {
				decoratedCallable.call();
			} catch (Exception ignored) {
			}
		}
		AtomicInteger callCount = new AtomicInteger(0);
		callCount.incrementAndGet();

		callCount.get();
		boolean b = circuitBreaker2.getState() == CircuitBreaker.State.OPEN;

		callCount.set(0);
		circuitBreaker.transitionToHalfOpenState();
		boolean b1 = CircuitBreaker.State.HALF_OPEN == circuitBreaker.getState();


		for (int i = 0; i < 3; i++) {
			try {
				decoratedCallable.call();
			} catch (Exception ignored) {
			}
		}
		boolean b2 = callCount.get() == 3;

		boolean b3 = CircuitBreaker.State.CLOSED == circuitBreaker2.getState();


		AtomicInteger counter = new AtomicInteger(0);

		boolean success = counter.compareAndSet(0, 1);

		boolean success2 = counter.compareAndSet(0, 2);


		ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
		valueOps.set("key", "value");

// List operations
		ListOperations<String, String> listOps = redisTemplate.opsForList();
		listOps.leftPush("mylist", "item1");

// Set operations
		SetOperations<String, String> setOps = redisTemplate.opsForSet();
		setOps.add("myset", "a", "b", "c");

// Hash operations
		HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
		hashOps.put("myhash", "field", "value");

// ZSet (sorted set) operations
		ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
		zSetOps.add("myzset", "item", 1.0);

		AtomicInteger version = new AtomicInteger(0);


			int currentVersion = version.get();
			// Prepare new state...
			if (!version.compareAndSet(currentVersion, currentVersion + 1)) {

			}

		int oldValue;
		do {
			oldValue = counter.get();
		} while (!counter.compareAndSet(oldValue, oldValue + 1));


		RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration()
				.clusterNode("redis1.example.com", 6379)
				.clusterNode("redis2.example.com", 6379)
				.clusterNode("redis3.example.com", 6379);

		JedisConnectionFactory factory = new JedisConnectionFactory(clusterConfig);

		RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
				.master("mymaster")
				.sentinel("sentinel1.example.com", 26379)
				.sentinel("sentinel2.example.com", 26379);

		JedisConnectionFactory factory2 = new JedisConnectionFactory(sentinelConfig);
		greetingKafkaTemplate.send("topicName", new Greeting("Hello", "World"));
		multiTypeKafkaTemplate.send("multiTypeTopicName", new Greeting("Greetings", "World!"));
		multiTypeKafkaTemplate.send("multiTypeTopicName", new Farewell("Farewell", 25));
		multiTypeKafkaTemplate.send("multiTypeTopicName", "Simple string message");
	}

	@Autowired
	static RedisTemplate<String,String> redisTemplate;

	static PaymentService paymentService;
	static interface PaymentService {
		String processPayment(int amount);
	}

}
