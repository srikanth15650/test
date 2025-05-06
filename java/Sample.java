package com.example.restservice;

import javafx.util.Pair;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.regex.MatchResult;
import java.util.stream.*;

import static java.lang.Thread.sleep;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
@RestController
public class Sample {


//    @Entity
//    public class User {
//        @Id
//        private Long id;
//        private String name;
//    }

    Comparator<Integer> cc = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    };

    Integer i2;

    Comparable<Integer> c = new Comparable<Integer>(){

        @Override
        public int compareTo(Integer o) {
            return i2-o;
        }
    };

    class AB implements  Comparable<Integer>{

        @Override
        public int compareTo(Integer o) {
            return 0;
        }
    }

    BigDecimal bc  =new BigDecimal(9);

    Comparator<Integer> nameComparator = Comparator.naturalOrder();

    Comparator<Integer> nameComparator2 = (p1, p2) -> p1-p2;

    Comparator<Integer> nameComparator3 = nameComparator.thenComparing(nameComparator2);

    void implementionOfComparing(){
        Comparator.comparing(BlogPost::getAuthor, String.CASE_INSENSITIVE_ORDER);
    }

    void sorting(){
        Collections.sort(new ArrayList<Integer>(),nameComparator3);
    }



    interface SampleFunction<Integer,String>  extends Function<Integer,String>{

    }

    interface SamplePredicate<Integer> extends  Predicate<Integer>{

    }
    interface SampleConsumer11<Integer> extends  Consumer<Integer>{

    }

    interface SampleBiConsumer<Integer,String> extends  BiConsumer<Integer,String>{

    }

    interface GenericSampleFunction<T,U> extends Function<T,U>{

    }

    interface GenericSamplePredicate<T> extends  Predicate<T>{

    }

    @FunctionalInterface
    interface GenericSampleBiPredicate<T,U,V> extends  BiPredicate<T,U>{

    }

    @FunctionalInterface
    interface SampleFunction2<Integer,String>  extends Function<Integer,String>{

    }

      class CustomPredicate implements Predicate<Integer>{
        @Override
        public boolean test(Integer integer) {
            return false;
        }
    }

      class CustomConsumer implements Consumer<Integer>{

        @Override
        public void accept(Integer integer) {

        }
    }

      class CustomSupplier implements Supplier<Integer>{

        @Override
        public Integer get() {
            return null;
        }
    }




    @FunctionalInterface
      interface Function2<Integer,String> extends Function<Integer,String>{

    }

    @FunctionalInterface
      interface Predicate2<Integer> extends Predicate<Integer>{

    }

    @FunctionalInterface
      interface Consumer2<Integer> extends  Consumer<Integer>{

    }

    @FunctionalInterface
      interface Supplier2<Integer> extends Supplier<Integer>{

    }

    @FunctionalInterface
      interface BiPredicate2<Integer,String> extends BiPredicate<Integer,String>{

    }

    @FunctionalInterface
    public interface BiConsumer2<Integer,String> extends  BiConsumer<Integer, String>{
     default void somemethod(){
         System.out.println();
     }
    }


    public class FunctionImplementation implements Function<Integer,String>{
        @Override
        public String apply(Integer integer) {
            return null;
        }
    }


    @GetMapping("/hello")
    public @ResponseBody Flux<String> idempotentMethod11(@RequestParam(defaultValue = "Guest") String name) {
        return Flux.just("Hello", "from", "Spring", "WebFlux!");
    }


    @PostMapping("/hello2")
    public @ResponseBody Flux<String> nonIdempotentMethod(@RequestParam(required = true) String name) {
        //create a resource in database
        return Flux.just("Hello", "from", "Spring", "WebFlux!");
    }

    @PutMapping("/hello4")
    public @ResponseBody Flux<String> idempotentMethod() {
        //update a resource in database
        return Flux.just("Hello", "from", "Spring", "WebFlux!");
    }


    @DeleteMapping("/hello3/{id}")
    public @ResponseBody Flux<String> idempotentMethod2(@PathVariable String id) {
        //delete a resource in database
        return Flux.just("Hello", "from", "Spring", "WebFlux!");
    }

    @PatchMapping("/user/{id:[0-9]+}")
    public @ResponseBody Flux<String> nonIdempotentMethod4(@PathVariable("id") String id) {
        //patch a resource in databasea
        return Flux.just("Hello", "from", "Spring", "WebFlux!");
    }




//    public static interface ProductClient {
//        @RequestMapping(value = "{id}", method = RequestMethod.GET")
//                Product getProduct(@PathVariable(value = "id") String id);
//    }
    @FunctionalInterface // Step 2: Optional annotation to enforce the rule
    interface MathOperation2 {
        // Single abstract method
        int operate(int a, int b);

        // Default methods are allowed
        default void printResult(int result) {
            System.out.println("Result: " + result);
        }

        // Static methods are allowed
        static MathOperation getAddition() {
            return (a, b) -> a + b;
        }
    }
    static String toUpperCase(String str) {
        return str.toUpperCase();
    }
    @FunctionalInterface
    static interface MathOperation {
        int operate(int a, int b);
    }
    static CompletableFuture<Integer> computeAnother(Integer i){
        return CompletableFuture.supplyAsync(() -> 10 + i);
    }
    public record CityRecord(String city, String country) {
    }
    static CityRecord createCityRecord(ResultSet resultSet) throws SQLException {
        return new CityRecord(resultSet.getString(1), resultSet.getString(2));
    }
    static class Tuple {
        public BlogPostType getType() {
            return type;
        }

        public void setType(BlogPostType type) {
            this.type = type;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Tuple(BlogPostType type, String author) {
            this.type = type;
            this.author = author;
        }

        BlogPostType type;
        String author;
    }
    static class BlogPost {
        record PostCountTitlesLikesStats(long postCount, String titles, IntSummaryStatistics likesStats){};
        record TitlesBoundedSumOfLikes(String titles, int boundedSumOfLikes) {};

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public BlogPostType getType() {
            return type;
        }

        public void setType(BlogPostType type) {
            this.type = type;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        String title;
        String author;

        public BlogPost(String title, String author, BlogPostType type, int likes) {
            this.title = title;
            this.author = author;
            this.type = type;
            this.likes = likes;
        }
        public BlogPost(){
        }

        BlogPostType type;
        int likes;
    }

    static  enum BlogPostType {
        NEWS,
        REVIEW,
        GUIDE
    }

    @FunctionalInterface
    interface  CustomPredicate2<Integer> extends Predicate<Integer>{

    }
    public static void main(String[] ra ) throws IOException, SQLException, ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        /*
        * not increasing or decreasing code
        * array consisting of distinct integers
        * print minimum number
        * of elements to be removed
        * such that no three
        * consecutive elemnts in the
        * array are either
        * increasing or decreasing
        *
        * first line of input
        * contains an integer n
        * representing the size
        * of the array
        *
        *array of distinct integers , minimum number
        * of elements to be removed
        * such that no three consecutive elements in the array
        * are either increasing or decresing
        *
        *
        *
        * */

        int rem = 0 ;
        java.util.List<Integer> ar =
        new java.util.ArrayList<>();
        ar.add(1);ar.add(2);ar.add(3);ar.add(5);

        for (int i=1;i<ar.size()-1;i++){
          if ((ar.get(i-1) < ar.get(i)
          && (ar.get(i)< ar.get(i+1)) ||
                  (ar.get(i-1) > ar.get(i) &&
                        ar.get(i)>ar.get(i+1)))){
                ar.set(i,ar.get(i-1));
                rem++;
            }
        }
        //System.out.println(rem);
        for(int i=1;i<ar.size()-1;i++){
            if(ar.get(i-1)<ar.get(i) && ar.get(i)<ar.get(i+1) ||
                    (ar.get(i-1)>ar.get(i) && ar.get(i)> ar.get(i+1))){
                ar.set(i,ar.get(i-1));
                rem++;
            }
        }



        java.util.List<Integer> ar3 =
                new java.util.ArrayList<>();
        ar3.add(1);ar3.add(3);ar3.add(3);ar3.add(2);
        int ans = 0;
        int b = 2;

        for( int j=1 ; j < ar3.size(); j++ ){

            int x1 = ar3.get(j-1);
            int y1 = ar3.get(j);

            while(y1<=x1){
                y1 = y1 + b;
                ans++;
            }
            ar3.set(j,y1);
        }
        System.out.println(ar3);





        int[] nums2 = new int[]{1,2,0,0,4,5,6,3,3};

        int firstpointer=0;
        int secondpointer=0;

        while(secondpointer< nums2.length){
            if(nums2[secondpointer]!=0){
                swaptheelements(firstpointer, secondpointer, nums2);
                firstpointer++;
            }
            secondpointer++;
        }
        for(int i=0;i<nums2.length;i++){
            System.out.println(nums2[i]);
        }



        int[] nums3= new int[]{1,2,3,4,1};
        boolean[] doubles = new boolean[nums3.length];
        for(int i=0;i<nums3.length;i++){
            if(doubles[nums3[i]]==true){
                System.out.println(nums3[i]);
                break;
            }else{
                doubles[nums3[i]]=true;
            }
        }

        java.util.List<Integer> test = new java.util.ArrayList<>();
        System.out.println(test.size());
        test.add(0);
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        Iterator it =test.iterator();it.next();
        it.remove();
        System.out.println(test);
        test.add(0);
        System.out.println(test);

        LinkedHashMap<Integer,Integer> lLashMap = new LinkedHashMap<>();
        lLashMap.put(3,3);
        lLashMap.put(3,4);
        lLashMap.put(4,4);
        lLashMap.put(0,4);
        lLashMap.put(2,4);
        System.out.println(lLashMap
        );

        LinkedHashSet<Integer> lHashSet = new LinkedHashSet<>();
        lHashSet.add(3);
        lHashSet.add(4);
        lHashSet.add(3);
        lHashSet.add(5
        );
        lHashSet.add(6
        );
        System.out.println(lHashSet);

        List<BlogPost> posts = Arrays.asList( new BlogPost[]{new BlogPost()} );

    java.util.LinkedList<Integer> linkedList = new java.util.LinkedList<>();
    linkedList.addFirst(3);
        linkedList.addFirst(33);
        linkedList.addLast(343);




        java.util.Deque<Integer> deque = new java.util.ArrayDeque<>();
        deque.addFirst(1);
        deque.addLast(33);
        deque.add(333);
        System.out.println(deque);


    java.util.Map<String,Integer> sample = new java.util.HashMap<>();
    java.util.List<String> sampleList = new java.util.ArrayList<>();

    sampleList.stream()
              .max(java.util.Comparator.comparing(String::length));
    sampleList.stream()
              .min(java.util.Comparator.comparing(String::length));
    sampleList.stream()
              .count();
    sampleList.stream()
              .collect(toCollection(TreeSet::new));
        sampleList.stream()
                  .collect(toCollection(HashSet::new));
        sampleList.stream()
                  .collect(toCollection(ArrayDeque::new));
        sampleList.stream()
                  .collect(toCollection(LinkedHashSet::new));
        sampleList.stream()
                  .collect(toCollection(LinkedList::new));
        sampleList.stream()
                  .collect(toCollection(PriorityQueue::new));
        sampleList.stream()
                  .collect(toCollection(PriorityBlockingQueue::new));
        sampleList.stream()
                  .collect(toCollection(Stack::new));
        sampleList.stream()
                  .collect(toCollection(ConcurrentLinkedDeque::new));
        sampleList.stream()
                  .collect(toCollection(ConcurrentSkipListSet::new));
        sampleList.stream()
                  .collect(toCollection(CopyOnWriteArrayList::new));
        sampleList.stream()
                .collect(toCollection(CopyOnWriteArraySet::new));
        sampleList.stream()
                  .collect(toCollection(LinkedTransferQueue::new));
        sampleList.stream()
                  .collect(toCollection(LinkedBlockingDeque::new));






        sampleList.stream()
                  .distinct()
                  .count();
    sampleList.stream()
              .sorted()
              .collect(toUnmodifiableList());
    sampleList.stream()
              .limit(4)
              .sorted()
              .collect(toUnmodifiableList());
    sampleList.stream()
              .sorted(Comparator.reverseOrder())
              .limit(2)
              .collect(toUnmodifiableList())
              .get(1);
    sampleList.stream()
              .sorted()
              .limit(1)
              .collect(toUnmodifiableList());
    sampleList.stream()
              .skip(5)
              .collect(toUnmodifiableList());
    sampleList.stream()
              .skip(sampleList.size())
              .collect(toUnmodifiableList())  ;
    sampleList.stream()
              .sorted()
              .skip(1)
              .collect(toUnmodifiableList());
        Map<Boolean, List<String>> collect1 = sampleList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(groupingBy(a -> a.length() % 2 == 0));
        Map<Boolean, List<String>> collect = sampleList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(groupingBy(a -> a.length() == 2));
        Map<Integer, List<String>> collect2 = sampleList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(groupingBy(String::length));
        Map<Boolean, List<String>> a1 = sampleList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(groupingBy(o -> o.equalsIgnoreCase("A")));
        Map<BlogPostType, List<BlogPost>> collect4 = posts.stream()
                .sorted()
                .collect(groupingBy(blogPost -> blogPost.type));
        Map<BlogPostType, List<BlogPost>> collect3 = posts.stream()
                .sorted()
                .collect(groupingBy(BlogPost::getType));
        Map<String, List<BlogPost>> collect5 = posts.stream()
                .sorted()
                .collect(groupingBy(a -> a.getAuthor()));
        Map<String, List<BlogPost>> collect6 = posts.stream()
                .sorted()
                .collect(groupingBy(a -> a.getTitle()));

        //posts.stream()
          //      .collect(Collectors.groupingBy(post -> new javafx.util.Pair<>(post.getType(), post.getAuthor())));
        Map<BlogPostType, Set<BlogPost>> collect7 = posts.stream()
                .collect(groupingBy(BlogPost::getType, toSet()));

        Map<String, Map<BlogPostType, List<BlogPost>>> collect8 = posts.stream()
                .collect(groupingBy(BlogPost::getAuthor, groupingBy(BlogPost::getType)));
        Map<BlogPostType, Double> collect9 = posts.stream()
                .collect(groupingBy(BlogPost::getType, averagingInt(BlogPost::getLikes)));
        posts.stream().collect(groupingBy(a->a.getType(),averagingInt(a->a.getLikes())));
        posts.stream().collect(groupingBy(a->a.getType(),summingInt(a->a.getLikes())));
        posts.stream().collect(groupingBy(a->a.getType(),summarizingInt(a->a.getLikes()))).values().forEach(a->a.getMax());
        posts.stream().collect(groupingBy(a -> a.getType(), groupingBy(a -> a.getTitle())));
        posts.stream().collect(groupingBy(a -> a.getType(), groupingBy(a -> a.getTitle(), groupingBy(a -> a.getAuthor()))));



        Map<BlogPostType, Integer> collect10 = posts.stream()
                .collect(groupingBy(BlogPost::getType, summingInt(BlogPost::getLikes)));
        Map<BlogPostType, Optional<BlogPost>> collect11 = posts.stream()
                .collect(groupingBy(BlogPost::getType,
                        maxBy(comparingInt(BlogPost::getLikes))));
        Map<BlogPostType, IntSummaryStatistics> collect14 = posts.stream()
                .collect(groupingBy(BlogPost::getType,
                        summarizingInt(BlogPost::getLikes)));


        posts.stream().collect(groupingBy(a->a.getType(),maxBy(comparingInt(a->a.getLikes()))));
        posts.stream().collect(groupingBy(a->a.getType(),minBy(comparingInt(a -> a.getLikes()))));

        List<Integer> list1 = null,  list2 = null;
        List<int[]> collect21 = list1.stream()
                .flatMap(num1 -> list2.stream().map(num2 -> new int[]{num1, num2}))
                .collect(toList());
        list1.stream()
                .flatMap(num1 -> list2.stream().map(num2 -> new int[] { num1, num2 }))
                .filter(pair -> pair[0] + pair[1] > 7)
                .findFirst();

        collect14.values()
                 .stream()
                 .forEach(a->a.getAverage());
        collect14.values()
                .stream()
                .forEach(a->a.getMin());
        collect14.values()
                .stream()
                .forEach(a->a.getMax());
        collect14.values()
                .stream()
                .forEach(a->a.getCount());
        collect14.values()
                .stream()
                .forEach(a->a.getSum());

        Map<String, BlogPost.PostCountTitlesLikesStats> collect15 = posts.stream()
                .collect(groupingBy(BlogPost::getAuthor, collectingAndThen(toList(), list -> {
                    long count = list.stream()
                            .map(BlogPost::getTitle)
                            .collect(counting());
                    String titles = list.stream()
                            .map(BlogPost::getTitle)
                            .collect(joining(" : "));
                    IntSummaryStatistics summary = list.stream()
                            .collect(summarizingInt(BlogPost::getLikes));
                    return new BlogPost.PostCountTitlesLikesStats(count, titles, summary);
                })));

        posts.stream().collect(groupingBy(a->a.getAuthor(),collectingAndThen(toList(),a->{
            long c =a.stream().map(p->p.getTitle()).collect(counting());
            String collect13 = a.stream().map(p -> p.getTitle()).collect(joining(":"));
            IntSummaryStatistics collect12 = a.stream().collect(summarizingInt(p -> p.getLikes()));
            return new BlogPost.PostCountTitlesLikesStats(c,collect13,collect12);
        })));

        int maxValLikes = 17;
        Map<String, BlogPost.TitlesBoundedSumOfLikes> postsPerAuthor = posts.stream()
                .collect(toMap(BlogPost::getAuthor, post -> {
                    int likes = (post.getLikes() > maxValLikes) ? maxValLikes : post.getLikes();
                    return new BlogPost.TitlesBoundedSumOfLikes(post.getTitle(), likes);
                }, (u1, u2) -> {
                    int likes = (u2.boundedSumOfLikes() > maxValLikes) ? maxValLikes : u2.boundedSumOfLikes();
                    return new BlogPost.TitlesBoundedSumOfLikes(u1.titles().toUpperCase() + " : " + u2.titles().toUpperCase(), u1.boundedSumOfLikes() + likes);
                }));
        posts.stream()
             .collect(toMap(a->a.getAuthor(),p->
                             new BlogPost.TitlesBoundedSumOfLikes(p.getTitle(),p.getLikes()>maxValLikes?maxValLikes:p.getLikes())
        ,(p1,p2)->{
                         int i = p2.boundedSumOfLikes() > maxValLikes ? maxValLikes : p2.boundedSumOfLikes();
                         return new BlogPost.TitlesBoundedSumOfLikes(p1.titles().toUpperCase()+":"+p2.titles().toUpperCase(),p1.boundedSumOfLikes()+i);
                     }));

        Map<BlogPostType, String> collect18 = posts.stream()
                .collect(groupingBy(BlogPost::getType,
                        mapping(BlogPost::getTitle, joining(", ", "Post titles: [", "]"))));

        EnumMap<BlogPostType, List<BlogPost>> collect17 = posts.stream()
                .collect(groupingBy(BlogPost::getType,
                        () -> new EnumMap<>(BlogPostType.class), toList()));

        ConcurrentMap<BlogPostType, List<BlogPost>> collect16 = posts.parallelStream()
                .collect(groupingByConcurrent(BlogPost::getType));

        Map<Tuple, List<BlogPost>> collect19 = posts.stream()
                .collect(groupingBy(post -> new Tuple(post.getType(), post.getAuthor())));
        Map<Pair<BlogPostType, String>, List<BlogPost>> collect20 = posts.stream()
                .collect(groupingBy(post -> new Pair<>(post.getType(), post.getAuthor())));
        List<AbstractMap.SimpleEntry<Integer, Integer>> collect22 = IntStream.range(0, list1.size() - 1)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(list1.get(i), list1.get(i + 1))).collect(toList());

        List<List<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < list1.size() - 1; i++) {
            pairs.add(Arrays.asList(list1.get(i), list1.get(i + 1)));
        }

        byte[] bytes = "Hello\nWorld\nThis\nis\na\ntest".getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        try(InputStreamReader inputStreamReader =
                    new InputStreamReader(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)){

        }
        try (InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            Stream<String> stringStream = reader.lines();
            String result = stringStream.reduce("", (s1, s2) -> s1 + s2);

        }

        Stream<String> lines = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)).lines();
        lines.reduce("", (a,c)->a+c);

        String reduce = new ArrayList<String>().stream()
                .reduce("", (s1, s2) -> s1 + s2);

        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            Stream<String> stringStream = scanner.findAll(".+")
                    .map(a->a.group());
            String result = stringStream.collect(joining());//

        }
        Scanner scanner = new Scanner(new InputStreamReader(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
        Stream<MatchResult> all = scanner.findAll(".+");
        Stream<String> stringStream = all.map(a -> a.group());
        stringStream.collect(joining());

        CompletableFuture<Integer> ci = new CompletableFuture<>();



//        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
//        Flux<Integer> fluxPipeline = Flux.fromIterable(numbers)
//                .filter(n -> n % 2 == 0)
//                .map(n -> n * 2);

        Stream<Integer>
                s = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        s.collect(Collectors.partitioningBy(a->a>3));
        s.collect(Collectors.partitioningBy(a->a>3,Collectors.counting()));
        Predicate<Integer> predicate =   integer -> integer.equals(2);
        Predicate<Integer> predicate2 =   new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer.equals(4);
            }
        };

        Predicate<Integer> predicate3 = predicate.and(predicate2);
        Consumer<Integer> printConsumer= city-> System.out.println(city);
         Consumer<String> printConsumer2= city-> System.out.println(city);
        Consumer<String> printConsumer3= city-> System.out.println(city+"");
        Predicate<String> stringPredicate = a->a.equalsIgnoreCase("a");
        Predicate<String> stringPredicate2 = a->a.equalsIgnoreCase("b");
        Predicate<String> stringPredicate3= stringPredicate.and(stringPredicate2);
        Predicate<String>  stringPredicate4 = stringPredicate.negate().and(stringPredicate2);


        List<String> cities = new ArrayList<>();
        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Goa");
        cities.add("Pune");
        cities.forEach(printConsumer2.andThen(printConsumer3));

        sampleList.stream()
                  .filter(stringPredicate4)
                  .collect(toUnmodifiableList());
        String QUERY = "SELECT name, country FROM cities";
        Connection connection = null;
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        connection.setAutoCommit(false);
        preparedStatement.setFetchSize(5000);
        ResultSet resultSet = preparedStatement.executeQuery();
        StreamSupport.stream(new Spliterators.AbstractSpliterator<CityRecord>(Long.MAX_VALUE,Spliterator.ORDERED) {
            @Override
            public boolean tryAdvance(Consumer<? super CityRecord> action) {
                try {
                    if(!resultSet.next()) return false;
                    action.accept(createCityRecord(resultSet));
                    return true;
                } catch(SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }, false);

        List<Integer> ls = new ArrayList<>();
        Comparator<? super Integer> comparator = ls.spliterator().getComparator();
        ls.spliterator()
          .trySplit()
          .forEachRemaining(a-> System.out.println(a));
        ls.spliterator()
          .trySplit()
          .characteristics();
        ls.spliterator()
          .trySplit()
          .estimateSize();
        ls.spliterator()
          .trySplit()
          .getExactSizeIfKnown();
        ls.spliterator()
          .hasCharacteristics(Spliterator.ORDERED);
        ls.spliterator()
          .tryAdvance(printConsumer);
        Stream<Integer> source = Stream.of(1, 2, 3, 4, 5, 6, 7, 8).parallel();
        ForkJoinPool fk = new ForkJoinPool();
        fk.getAsyncMode();
        fk.getParallelism();
        fk.execute(() -> {});
        fk.invoke(new ForkJoinTask<Object>() {
            @Override
            public Object getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(Object value) {

            }

            @Override
            protected boolean exec() {
                return false;
            }
        });
        fk.getPoolSize();
        fk.getActiveThreadCount();
        fk.submit(() -> {});
        fk.submit(() -> new Object());
        fk.getFactory();fk.shutdown();fk.getQueuedSubmissionCount();fk.isTerminated();fk.shutdownNow();fk.isQuiescent();fk.hasQueuedSubmissions();
        fk.getRunningThreadCount();fk.getQueuedTaskCount();
        Callable<Integer> c = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        };

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        String s1 = completableFuture.get();
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "Hello";
            }
        });

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        future1.get();
        CompletableFuture<Void> future2 = completableFuture
                .thenAccept(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println("Computation returned: " + s);
                    }
                });

        CompletableFuture<Void> future3 = completableFuture
                .thenAccept(s3 -> System.out.println("Computation returned: " + s));
        Executors.newFixedThreadPool(4).submit(() -> {
            sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        Executors.newSingleThreadExecutor().submit(() -> {
            sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        Executors.defaultThreadFactory().newThread(() -> {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            completableFuture.complete("Hello");
        });
        Executors.newWorkStealingPool().submit(() -> {
            sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        Executors.newWorkStealingPool(3).submit(() -> {
            sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        Executors.newScheduledThreadPool(3).submit(() -> {
            sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread();//
            }
        }).submit(() -> {
            sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newWorkStealingPool();

        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Future<Integer> sum = executor.submit(() -> {
            return ForkJoinTask.adapt(() -> {
                int result = 0;
                for (int num : numbers2) {
                    result += num;
                }
                return result;
            }).invoke();
        });//.join()

        executor.submit(()->{

        });
        System.out.println("Sum: " + sum);
        executor.shutdown();

        CompletableFuture<String> completableFuture4
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s4 -> CompletableFuture.supplyAsync(() -> s + " World"));
        CompletableFuture<String> completableFuture5
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(new Function<String, CompletionStage<String>>() {
                    @Override
                    public CompletionStage<String> apply(String s) {
                        return null;
                    }
                });
        Function<Integer,Integer> fn = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return null;
            }
        };
        Predicate<Integer> pr = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return false;
            }
        };
        Supplier<Integer> sp = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return null;
            }
        };
        Consumer<Integer> cn = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {

            }
        };
        BiConsumer<Integer,Integer> bsn = new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer integer, Integer integer2) {

            }
        };
        BiPredicate bpr = new BiPredicate() {
            @Override
            public boolean test(Object o, Object o2) {
                return false;
            }
        };
        BooleanSupplier bsp = new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return false;
            }
        };
        BiFunction<Integer,Integer,Integer> bfn = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return null;
            }
        };
        BinaryOperator<Integer> bin = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return null;
            }
        };

        BinaryOperator<Integer> add = (a, b5) -> a + b5;
        add.apply(5, 3);

        BinaryOperator<Integer> max = Math::max;
        max.apply(10, 15);
        List<Integer> numbers5 = Arrays.asList(1, 2, 3, 4, 5);
        numbers5.stream().reduce(0, add);

        BinaryOperator<String> concat = (s12, s2) -> s12 + " " + s2;
        concat.apply("Hello", "World");
        BinaryOperator<BlogPost> combineAges = (p1, p2) -> new BlogPost(p1.getAuthor() + " & " + p2.getAuthor(), p1.getTitle() + p2.getTitle()
        ,p2.getType(),p1.getLikes()+p2.getLikes());

        BiConsumer<String, Integer> printDetails = (name, age) ->
                System.out.println(name + " is " + age + " years old.");
        printDetails.accept("Alice", 25);

        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        BiConsumer<String, Integer> updateMap = (key, value) -> map.put(key, value);
        updateMap.accept("Charlie", 35);

        BiConsumer<String, Integer> printFormatted = System.out::printf;
        printFormatted.accept("%s is %d years old.%n" + "Alice", 25);//
        BlogPost blogPost = new BlogPost();

        BiConsumer<String, String> updatePerson = (author, title) -> {
            blogPost.author = author;
            blogPost.title = title;
        };
        updatePerson.accept("Bob", "tile");

        Map<String, Integer> map3 = new HashMap<>();
        map3.put("Alice", 25);
        map3.put("Bob", 30);
        map3.put("Charlie", 35);

        BiConsumer<String, Integer> printEntry = (key, value) ->
                System.out.println(key + " -> " + value);
        map3.forEach(printEntry);

        BiFunction<String, String, String> concat3 = (s14, s2) -> s14 + " " + s2;
        concat.apply("Hello", "World");

        BiFunction<Integer, Integer, Integer> add2 = (a12, b1) -> a12 + b1;
        add2.apply(3,4);

        BiFunction<Double, Double, Double> power = Math::pow;
        power.apply(2.0,3.0);

        BiFunction<BlogPost, BlogPost, BlogPost> combinePersons = (p1, p2) ->
                new BlogPost(p1.author + " & " + p2.author, p1.title + p2.title,BlogPostType.NEWS,2);

        List<Integer> numbers6 = Arrays.asList(1, 2, 3, 4, 5);
        BiFunction<Integer, Integer, Integer> add3 = (a3, b3) -> a3 + b3;
        int sum6 = numbers6.stream().reduce(0, add::apply);

        BiFunction<String, String, String> concatBiFunction = (s12, s2) -> s12 + " " + s2;
        Function<String, String> toUpper = String::toUpperCase;
        BiFunction<String, String, String> concatAndUpper = concat.andThen(toUpper);
        String result3 = concatAndUpper.apply("hello", "world");

        BooleanSupplier randomBoolean = () -> new Random().nextBoolean();
        BooleanSupplier isEven = () -> 10 % 2 == 0;
        BooleanSupplier isEmpty = numbers6::isEmpty;
        int number1 = 10;
        int number2 = 20;
        BooleanSupplier isEvenSupplier = () -> number1 % 2 == 0;
        BooleanSupplier isGreaterThan10 = () -> number2 > 10;
        if (isGreaterThan10.getAsBoolean()) {
            System.out.println("The number is greater than 10."); // Output: The number is greater than 10.
        }


        DoubleBinaryOperator addDouble = (a, b2) -> a + b2;
        double result = addDouble.applyAsDouble(5.5, 3.2);

        DoubleBinaryOperator multiply = (a, b2) -> a * b2;
        DoubleBinaryOperator divide = (a, b2) -> a / b2;
        multiply.applyAsDouble(5.5, 3.2);
        divide.applyAsDouble(10.0, 2.5);

        DoubleBinaryOperator maxDouble = Math::max;
        maxDouble.applyAsDouble(10.5, 15.2);

        DoubleStream numbersDouble = DoubleStream.of(1.5, 2.5, 3.5, 4.5);
        numbersDouble.reduce(addDouble).orElse(0.0);
        numbersDouble.reduce(0.0,addDouble);
        DoubleBinaryOperator powerDouble = (base, exponent) -> Math.pow(base, exponent);
        powerDouble.applyAsDouble(2.0, 3.0);

        DoubleConsumer printValue = (value) -> System.out.println("Value: " + value);
        printValue.accept(3.14);

        final double[] totalDouble = {0.0};
        DoubleConsumer addToTotalDouble = (value) -> totalDouble[0] += value;
        addToTotalDouble.accept(5.5);
        DoubleConsumer printValueDouble = System.out::println;
        DoubleConsumer printValueDouble2 = (value) -> System.out.println("Value: " + value);
        DoubleConsumer logValue = (value) -> System.out.println("Logged: " + value);
        DoubleConsumer printAndLog = printValueDouble.andThen(logValue);

        DoubleFunction<String> toString = (value) -> "Value: " + value;
        String result4 = toString.apply(3.14);

        DoublePredicate isPositive = (value) -> value > 0;
        System.out.println(isPositive.test(3.14));

        DoubleSupplier areaSupplier = () -> Math.PI * 8.0 * 8.0;
        System.out.println("Area of the circle: " + areaSupplier.getAsDouble());

        DoubleToIntFunction truncate = (value) -> (int) value;
        int result5 = truncate.applyAsInt(3.14);


        DoubleToLongFunction toLong = (value) -> (long) value;
        long result0 = toLong.applyAsLong(3.14);

        DoubleUnaryOperator square = (value) -> value * value;
        double v2 = square.applyAsDouble(4.0);

        IntBinaryOperator add11 = (a, b2) -> a + b2;
        int result11 = add11.applyAsInt(5, 3);

        IntConsumer printValue11 = (value) -> System.out.println("Value: " + value);

        IntFunction<String> toString11 = (value) -> "Number: " + value;
        String result111 = toString11.apply(42);

        IntPredicate isEven22 = (value) -> value % 2 == 0;
        System.out.println(isEven22.test(42));

        IntSupplier randomInt = () -> new Random().nextInt(100);
        System.out.println("Random integer: " + randomInt.getAsInt());

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.get();
        atomicInteger.compareAndSet(2,3);

        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s0 -> CompletableFuture.supplyAsync(() -> s0 + " World"));

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> " World"), (s0, s2) -> s0 + s2);

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                        (s0, s2) -> System.out.println(s0 + s2));

        CompletableFuture copy = new CompletableFuture().copy();

        CompletionStage completionStage = new CompletableFuture().minimalCompletionStage();

        CompletableFuture completableFuture2 = new CompletableFuture().orTimeout(1, TimeUnit.SECONDS);

        CompletableFuture completableFuture3 = new CompletableFuture().completeOnTimeout(3, 1, TimeUnit.SECONDS);


        CompletableFuture<String> stringCompletableFuture7 = stringCompletableFuture.completeAsync(new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        });

        Executor executor2 = CompletableFuture.delayedExecutor(7, TimeUnit.MICROSECONDS);
        CompletableFuture.delayedExecutor(7, TimeUnit.MICROSECONDS, Executors.newSingleThreadExecutor());

        CompletionStage<Integer> integerCompletionStage = CompletableFuture.completedStage(6);
        CompletionStage<Object> objectCompletionStage = CompletableFuture.failedStage(new RuntimeException(""));

        CompletableFuture<String> stringCompletableFuture8 = CompletableFuture.supplyAsync(() -> "World");
        CompletionStage<String> hello = CompletableFuture.completedStage("Hello");
        CompletionStage<Void> voidCompletionStage = hello.thenCombine(stringCompletableFuture8, (result1, result2) -> result1 + " " + result2)
                .thenAccept(System.out::println);

        Function<Integer, Function<Integer, Integer>> addFunction = a -> b0 -> a + b0;
        addFunction.apply(6).apply(78);

        MathOperation addMathOperation = (a, b0) -> a + b0;
        addMathOperation.operate(5, 3);

        Function<String, String> upperCaseFunction = Sample::toUpperCase;

        Predicate<String> spr = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };

        MathOperation2 operation2 = (a,b0) -> a+b0;




        int i = 16;

        List<String> words = Arrays.asList("hello", "world");
        List<String> characters = words.stream()
                                       .flatMap(word -> Stream.of(word.split("")))
                                       .collect(Collectors.toList());

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> 10 + 3);
        integerCompletableFuture.thenCompose(Sample::computeAnother);


        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> stringCompletableFuture3 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> stringCompletableFuture4 = CompletableFuture.supplyAsync(() -> "World");
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.allOf(stringCompletableFuture1, stringCompletableFuture2, stringCompletableFuture3);
        voidCompletableFuture1.get();

        String collect23 = Stream.of(stringCompletableFuture1, stringCompletableFuture2, stringCompletableFuture3)
                .map(CompletableFuture::join)
                .collect(joining(" "));

        String name = null;
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s0, t) -> s0 != null ? s0 : "Hello, Stranger!");

        CompletableFuture<Object> objectCompletableFuture = new CompletableFuture<>();
        objectCompletableFuture.completeExceptionally(new RuntimeException(""));
        objectCompletableFuture.get();

        List<List<String>> listStream = new ArrayList<>();
        List<String> collect24 = listStream.stream().flatMap(a -> a.stream()).collect(toUnmodifiableList());
        Stream<String> stringStream1 = listStream.stream().flatMap(a -> a.stream());

        CompletableFuture<String> stringCompletableFuture5 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> stringCompletableFuture6 = completableFuture.thenApplyAsync(s0 -> s0 + " World");
        String s3 = stringCompletableFuture6.get();

        Executor executor1 = new CompletableFuture().defaultExecutor();

        CompletableFuture<Integer> completableFuture1 = new CompletableFuture<Integer>().newIncompleteFuture();

        completableFuture1.thenApply(result1 -> "Transformed: " + result1);

        sampleList.stream().sorted(Comparator.reverseOrder());

        sampleList.stream().sorted(Collections.reverseOrder());

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> submit = executorService.submit(new FutureTask<>(() -> {
            return null;
        }));
        Future<?> submit1 = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        });
        Future<Integer> submit2 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        });
        submit2.isDone();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "Task's execution";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        executorService.execute(runnableTask);
        Future<String> submit3 = executorService.submit(callableTask);
        String s4 = executorService.invokeAny(callableTasks);
        List<Future<String>> futures = executorService.invokeAll(callableTasks);

        executorService.shutdown();
        ExecutorService executorService1 = Executors.newFixedThreadPool(3);
        List<Runnable> runnables = executorService1.shutdownNow();

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        BlockingQueue<Integer> integers = new LinkedBlockingQueue<>(10);
        Thread producerthread1 = new Thread(() -> {
            try {
                for (int i2 = 0; i2 < 15; i2++) {
                    System.out.println("Producing: " + i);
                    integers.put(i); // Block if the queue is full
                    sleep(100); // Simulate production time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumerthread1 = new Thread(() -> {
            try {
                for (int i2 = 0; i2 < 15; i2++) {
                    Integer item = integers.take(); // Block if the queue is empty
                    System.out.println("Consuming: " + item);
                    sleep(200); // Simulate consumption time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producerthread1.start();
        consumerthread1.start();


        BlockingQueue<String> objects = new LinkedBlockingQueue<>(2);
        Thread producer1 = new Thread(() -> {
            try {
                objects.put("Apple");
                System.out.println("Produced: Apple");
                objects.put("Banana");
                System.out.println("Produced: Banana");
                objects.put("Cherry"); // This will block until space is available
                System.out.println("Produced: Cherry");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer1 = new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate delay
                System.out.println("Consumed: " + objects.take());
                System.out.println("Consumed: " + objects.take());
                System.out.println("Consumed: " + objects.take()); // This will block until an element is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer1.start();
        consumer1.start();

        // Wait for threads to finish
        producer1.join();
        consumer1.join();

        BlockingQueue<Integer> integers1 = new ArrayBlockingQueue<Integer>(2);
        BlockingQueue<Integer> integers2 = new SynchronousQueue<>();
        BlockingQueue delayQueue = new DelayQueue(new ArrayList<Integer>());
        BlockingQueue<Integer> integers3 = new PriorityBlockingQueue<>(3);
        integers3.offer(3);
        integers3.poll();
        integers3.peek();
        integers3.remainingCapacity();
        integers3.take();

        boolean canceled = future.cancel(true);
        boolean isCancelled = future.isCancelled();
        String result00 = future.get(200, TimeUnit.MILLISECONDS);

        ScheduledExecutorService executorService2 = Executors.newScheduledThreadPool(1);

        executorService2.schedule(callableTask, 1, TimeUnit.SECONDS);
        executorService2.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);
        executorService2.scheduleWithFixedDelay(runnableTask, 100, 150, TimeUnit.MILLISECONDS);

        try {
            // Intentionally cause a NoClassDefFoundError
            Class.forName("NonExistentClass");
        } catch (NoClassDefFoundError e) {
            System.err.println("Custom handling for NoClassDefFoundError: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }


        try {
            throw new AssertionError("Input cannot be null!");
        } catch (AssertionError e) {
            System.err.println("Caught AssertionError: " + e.getMessage());
        }


        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            System.err.println("Uncaught error in thread " + thread.getName() + ": " + throwable.getMessage());
        });

        // Create and start a thread that throws an error
        Thread thread = new Thread(() -> {
            throw new OutOfMemoryError("Simulated OutOfMemoryError");
        });
        thread.start();

        Cipher.getInstance("ABC");

        Cipher.getInstance("AES/ABC");

        Cipher.getInstance("AES/CBC/ABC");

        Cipher.getInstance("AES/CBC/PKCS5Padding");

        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(Cipher.DECRYPT_MODE, new Key() {
            @Override
            public String getAlgorithm() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public byte[] getEncoded() {
                return new byte[0];
            }
        });

        instance.doFinal("".getBytes());

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new Key() {
            @Override
            public String getAlgorithm() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public byte[] getEncoded() {
                return new byte[0];
            }
        });
        byte[] cipherTextBytes = cipher.doFinal("".getBytes());

        AssertionError assertionError = new AssertionError();

        try (FileInputStream fis = new FileInputStream(new File("test.txt"))) {
            fis.read();
        } catch (IOException e) {
            e.printStackTrace();
        }



        BiConsumer2 biConsumer2 = new BiConsumer2() {
            @Override
            public void accept(Object o, Object o2) {

            }
        };

        BiConsumer2 biConsumer3 = (o,m)-> System.out.println();












        executorService.shutdown();



        Map<String, Integer> map0 = new HashMap<>();
        map0.put("apple", 1);
        map0.put("banana", 2);

        map0.compute("apple", (key, value) -> value == null ? 0 : value + 1);
        //JDBCStreamAPIRepository jdbcStreamAPIRepository = new JDBCStreamAPIRepository();


        List<String> collect12 = sampleList.stream()
                .sorted(Collections.reverseOrder())
                .collect(toUnmodifiableList());
        List<String> collect13 = sampleList.stream()
                .dropWhile(a -> a.length() > 5)
                .collect(toUnmodifiableList());
        sampleList.stream()
              .takeWhile(a->a.length()>5)
              .collect(toUnmodifiableList());
    sampleList.stream()
              .peek(a-> System.out.println(a))
              .collect(toUnmodifiableList());
    sampleList.stream()
              .filter(a->a.length()>5)
              .peek(a-> System.out.println(a))
              .collect(toUnmodifiableList());
    Arrays.copyOfRange(sampleList.toArray(), 0, 3);
     List<Integer> numbers = List.of(1, 2, 3, 4,99,5,0, 6, 7, 8, 9);
    numbers.stream()
           .takeWhile(n -> n < 5)
           .forEach(a-> System.out.println(a));
    numbers.stream()
           .filter(n -> n < 5)
           .forEach(a-> System.out.println(a));
    sampleList.stream()
              .collect(toSet());
    sampleList.stream()
              .collect(toUnmodifiableSet());
    sampleList.stream()
              .collect(toUnmodifiableList());
    sample.values();
    sample.entrySet();
    sample.containsKey("");
    sample.containsValue(3);
    sample.putIfAbsent("",2);
    sample.computeIfAbsent("",a->a.length());
    sample.putIfAbsent("",0);
    sample.computeIfPresent("",(s2, integer) -> integer);
    sample.put("",sample.getOrDefault("", 1));
    sample.putIfAbsent("",1);
    sampleList.stream()
              .filter(a->a.length()>0)
              .mapToInt(value -> 1)
              .average();
    sampleList.stream()
              .filter(a->a.length()>0)
              .mapToDouble(value -> 1.00)
              .average();
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> pq1 = new PriorityQueue<>((a,v)->a-v);
    PriorityQueue<Integer> pq2 = new PriorityQueue<>((a,v)->v-a);
    sampleList.stream().mapToDouble(a->1.00).average();
    sampleList.stream().mapToLong(a->1L).average();
    sampleList.stream().mapToInt(a->1).findAny();
    sampleList.stream().mapToInt(a->1).findFirst();
    pq.peek();
    pq.add(3);
    pq.poll();







    }

    static class Node{
        public Node prev;
        public Node next;
        public int key;
        public int value;

    }

    public static void swaptheelements(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    /*
     *only way is up
     * 1 3 3 2 add 2 to three and add two times to 2 and numer of times
     * added is three , this should be sent to the other output
     *a sequence ar[] is called a rising sequence if for each i> 0
     * a ri > a ri+1
     *
     *
     */





}
